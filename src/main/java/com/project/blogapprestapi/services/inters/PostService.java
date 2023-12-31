package com.project.blogapprestapi.services.inters;

import com.project.blogapprestapi.payloads.PostDto;
import com.project.blogapprestapi.payloads.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
    PostDto getSinglePost(Integer postId);
    PostResponse getAllPosts(Integer pageNumber, Integer pageSize);
    List<PostDto> getAllPostsByUser(Integer userId);
    List<PostDto> getAllPostsByCategory(Integer categoryId);
    PostDto updatePost(PostDto postDto, Integer postId);
    void deletePost(Integer postId);
    List<PostDto> searchPosts(String keyword);
}