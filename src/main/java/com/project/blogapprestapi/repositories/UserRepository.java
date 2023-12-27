package com.project.blogapprestapi.repositories;

import com.project.blogapprestapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}