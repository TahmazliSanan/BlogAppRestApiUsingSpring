package com.project.blogapprestapi.controllers;

import com.project.blogapprestapi.payloads.ApiResponse;
import com.project.blogapprestapi.payloads.CommentDto;
import com.project.blogapprestapi.services.inters.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(
            @RequestBody CommentDto commentDto,
            @PathVariable("postId") Integer postId) {
        return new ResponseEntity<>(commentService.createComment(commentDto, postId), HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable("commentId") Integer commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(
                new ApiResponse("Comment is deleted successfully!", true),
                HttpStatus.OK);
    }
}