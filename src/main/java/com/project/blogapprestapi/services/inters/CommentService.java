package com.project.blogapprestapi.services.inters;

import com.project.blogapprestapi.payloads.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Integer postId);
    void deleteComment(Integer commentId);
}