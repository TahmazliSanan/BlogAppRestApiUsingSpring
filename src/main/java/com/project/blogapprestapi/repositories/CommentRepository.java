package com.project.blogapprestapi.repositories;

import com.project.blogapprestapi.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}