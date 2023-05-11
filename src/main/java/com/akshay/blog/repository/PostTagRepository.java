package com.akshay.blog.repository;

import com.akshay.blog.model.Post;
import com.akshay.blog.model.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostTagRepository extends JpaRepository<PostTag, Long> {
    List<PostTag> findByPostId(Long postId);
}
