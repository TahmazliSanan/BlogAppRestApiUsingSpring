package com.project.blogapprestapi.controllers;

import com.project.blogapprestapi.configs.AppConstants;
import com.project.blogapprestapi.payloads.ApiResponse;
import com.project.blogapprestapi.payloads.PostDto;
import com.project.blogapprestapi.payloads.PostResponse;
import com.project.blogapprestapi.services.inters.FileService;
import com.project.blogapprestapi.services.inters.PostService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

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
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {
        return new ResponseEntity<>(postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir),
                HttpStatus.OK);
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

    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords) {
        return new ResponseEntity<>(postService.searchPosts(keywords), HttpStatus.OK);
    }

    @PostMapping("/posts/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(
            @RequestParam("image") MultipartFile image,
            @PathVariable("postId") Integer postId)
            throws IOException {
        PostDto postDto = postService.getSinglePost(postId);
        String fileName = fileService.uploadImage(path, image);
        postDto.setImageName(fileName);
        return new ResponseEntity<>(postService.updatePost(postDto, postId), HttpStatus.OK);
    }

    @GetMapping(value = "/posts/image/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    public void downloadImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response)
            throws IOException {
        InputStream resource = fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }
}