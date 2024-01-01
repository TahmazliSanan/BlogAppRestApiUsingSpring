package com.project.blogapprestapi.services.impls;

import com.project.blogapprestapi.entities.Comment;
import com.project.blogapprestapi.entities.Post;
import com.project.blogapprestapi.exceptions.ResourceNotFoundException;
import com.project.blogapprestapi.payloads.CommentDto;
import com.project.blogapprestapi.repositories.CommentRepository;
import com.project.blogapprestapi.repositories.PostRepository;
import com.project.blogapprestapi.services.inters.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment createdComment = commentRepository.save(comment);
        return modelMapper.map(createdComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        commentRepository.delete(comment);
    }
}