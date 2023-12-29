package com.project.blogapprestapi.services.inters;

import com.project.blogapprestapi.payloads.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
    PostDto getSinglePost(Integer postId);
    List<PostDto> getAllPosts();
    List<PostDto> getAllPostsByUser(Integer userId);
    List<PostDto> getAllPostsByCategory(Integer categoryId);
    PostDto updatePost(PostDto postDto, Integer postId);
    void deletePost(Integer postId);
    List<PostDto> searchPosts(String keyword);
}