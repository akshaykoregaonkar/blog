package com.akshay.blog.repository;

import com.akshay.blog.model.Post;
import com.akshay.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser(User user);
}
