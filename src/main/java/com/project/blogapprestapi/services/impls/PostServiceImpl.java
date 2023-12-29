package com.project.blogapprestapi.services.impls;

import com.project.blogapprestapi.entities.Category;
import com.project.blogapprestapi.entities.Post;
import com.project.blogapprestapi.entities.User;
import com.project.blogapprestapi.exceptions.ResourceNotFoundException;
import com.project.blogapprestapi.payloads.PostDto;
import com.project.blogapprestapi.repositories.CategoryRepository;
import com.project.blogapprestapi.repositories.PostRepository;
import com.project.blogapprestapi.repositories.UserRepository;
import com.project.blogapprestapi.services.inters.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        Post post = modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post createdPost = postRepository.save(post);
        return modelMapper.map(createdPost, PostDto.class);
    }

    @Override
    public PostDto getSinglePost(Integer postId) {
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> listOfPost = postRepository.findAll();
        return listOfPost
                .stream()
                .map((post) -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllPostsByUser(Integer userId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        List<Post> listOfPost = postRepository.findByUser(user);
        return listOfPost
                .stream()
                .map((post) -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllPostsByCategory(Integer categoryId) {
        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        List<Post> listOfPost = postRepository.findByCategory(category);
        return listOfPost
                .stream()
                .map((post) -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost = postRepository.save(post);
        return modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        postRepository.delete(post);
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        return null;
    }
}