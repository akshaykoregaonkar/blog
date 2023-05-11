package com.akshay.blog.controller;

import com.akshay.blog.model.Post;
import com.akshay.blog.model.PostTag;
import com.akshay.blog.repository.PostRepository;
import com.akshay.blog.repository.PostTagRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

  private static final Logger logger = LoggerFactory.getLogger(PostController.class);
  @Autowired private PostRepository postRepository;
  @Autowired private PostTagRepository postTagRepository;

  @GetMapping
  public ResponseEntity<List<Post>> getAllPosts() {
    try {
      List<Post> posts = postRepository.findAll();
      return ResponseEntity.ok(posts);
    } catch (Exception e) {
      // Log the error
      logger.error("Error occurred while retrieving posts: {}", e.getMessage(), e);
      // Return an error response
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping("/{postId}")
  public ResponseEntity<Post> getPostById(@PathVariable Long postId) {
    Optional<Post> post = postRepository.findById(postId);
    return post.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/{postId}/postTags")
  public ResponseEntity<List<PostTag>> getPostTagsByPost(@PathVariable Long postId) {
    List<PostTag> postTags = postTagRepository.findByPostId(postId);
    return !postTags.isEmpty() ? ResponseEntity.ok(postTags) : ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<?> createPost(@RequestBody Post post) {
    try {
      post.setCreatedAt(LocalDateTime.now());
      post.setUpdatedAt(LocalDateTime.now());
      Post createdPost = postRepository.save(post);
      return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    } catch (Exception e) {
      logger.error("Error creating post", e);
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Post> updatePost(@PathVariable Long postId, @RequestBody Post updatedPost) {
    Optional<Post> existingPost = postRepository.findById(postId);
    if (!existingPost.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    Post post = existingPost.get();
    post.setTitle(updatedPost.getTitle());
    post.setContent(updatedPost.getContent());
    post.setCategory(updatedPost.getCategory());
    post.setPostTags(updatedPost.getPostTags());

    Post updatedPostEntity = postRepository.save(post);
    return ResponseEntity.ok(updatedPostEntity);
  }

  @DeleteMapping("/{postId}")
  public ResponseEntity<HttpStatus> deletePost(@PathVariable Long postId) {
    Post post = postRepository.findById(postId).orElse(null);
    if (post != null) {
      postRepository.delete(post);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
