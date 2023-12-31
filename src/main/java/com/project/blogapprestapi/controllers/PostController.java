package com.project.blogapprestapi.controllers;

import com.project.blogapprestapi.payloads.ApiResponse;
import com.project.blogapprestapi.payloads.PostDto;
import com.project.blogapprestapi.services.inters.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/users/{userId}/categories/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(
            @Valid @RequestBody PostDto postDto,
            @PathVariable("userId") Integer userId,
            @PathVariable("categoryId") Integer categoryId) {
        return new ResponseEntity<>(postService.createPost(postDto, userId, categoryId), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getSinglePost(@PathVariable("postId") Integer postId) {
        return new ResponseEntity<>(postService.getSinglePost(postId), HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize) {
        return new ResponseEntity<>(postService.getAllPosts(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/posts")
    public ResponseEntity<List<PostDto>> getAllPostsByUser(@PathVariable("userId") Integer userId) {
        return new ResponseEntity<>(postService.getAllPostsByUser(userId), HttpStatus.OK);
    }

    @GetMapping("/categories/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getAllPostsByCategory(@PathVariable("categoryId") Integer categoryId) {
        return new ResponseEntity<>(postService.getAllPostsByCategory(categoryId), HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(
            @Valid @RequestBody PostDto postDto,
            @PathVariable("postId") Integer postId) {
        return new ResponseEntity<>(postService.updatePost(postDto, postId), HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") Integer postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>(
                new ApiResponse("Post is deleted successfully!", true),
                HttpStatus.OK);
    }
}