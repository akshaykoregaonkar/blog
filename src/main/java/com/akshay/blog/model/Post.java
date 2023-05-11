package com.akshay.blog.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "posts")
@JsonInclude()
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "post_id")
  private Long postId;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "content", nullable = false)
  private String content;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", columnDefinition = "bigint default 1")
  @JsonBackReference
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", columnDefinition = "bigint default 1")
  @JsonBackReference
  private Category category;

  @JsonIgnore
  @OneToMany(mappedBy = "postId", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<PostTag> postTags;

  public Long getPostId() {
    return postId;
  }

  public void setPostId(Long postId) {
    this.postId = postId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public List<PostTag> getPostTags() {
    return postTags;
  }

  public void setPostTags(List<PostTag> postTags) {
    this.postTags = postTags;
  }

  @Override
  public String toString() {
    return "Post{"
        + "postId="
        + postId
        + ", title='"
        + title
        + '\''
        + ", content='"
        + content
        + '\''
        + ", createdAt="
        + createdAt
        + ", updatedAt="
        + updatedAt
        + ", user="
        + user
        + ", category="
        + category
        + ", postTags="
        + postTags
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Post post = (Post) o;
    return Objects.equals(postId, post.postId) && Objects.equals(title, post.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(postId, title);
  }
}
