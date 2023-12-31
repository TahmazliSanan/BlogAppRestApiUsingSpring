package com.project.blogapprestapi.repositories;

import com.project.blogapprestapi.entities.Category;
import com.project.blogapprestapi.entities.Post;
import com.project.blogapprestapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    List<Post> findByTitleContaining(String title);
}