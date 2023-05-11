package com.akshay.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "categories")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "category_id", nullable = false)
  private Long categoryId;

  @Column(name = "category_name", nullable = false)
  private String categoryName;

  @Column(
      name = "created_at",
      nullable = false,
      updatable = false,
      columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private LocalDateTime createdAt;

  @Column(
      name = "updated_at",
      nullable = false,
      columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
  private LocalDateTime updatedAt;

  @OneToMany(mappedBy = "postId", cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<Post> posts = new ArrayList<>();

  public Category() {}

  public Category(String categoryName) {
    this.categoryName = categoryName;
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
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

  public List<Post> getPosts() {
    return posts;
  }

  public void setPosts(List<Post> posts) {
    this.posts = posts;
  }

  @Override
  public String toString() {
    return "Category{"
        + "categoryId="
        + categoryId
        + ", categoryName='"
        + categoryName
        + '\''
        + ", createdAt="
        + createdAt
        + ", updatedAt="
        + updatedAt
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Category category = (Category) o;
    return Objects.equals(categoryId, category.categoryId)
        && Objects.equals(categoryName, category.categoryName)
        && Objects.equals(createdAt, category.createdAt)
        && Objects.equals(updatedAt, category.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(categoryId, categoryName, createdAt, updatedAt);
  }
}
