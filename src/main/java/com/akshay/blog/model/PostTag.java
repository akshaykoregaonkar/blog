package com.akshay.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@IdClass(PostTagId.class)
@Table(name = "post_tags")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostTag {
  @Id
  @Column(name = "post_id")
  private Long postId;

  @Id
  @Column(name = "tag_id")
  private Long tagId;

  public PostTag() {}

  public Long getPostId() {
    return postId;
  }

  public void setPostId(Long postId) {
    this.postId = postId;
  }

  public Long getTagId() {
    return tagId;
  }

  public void setTagId(Long tagId) {
    this.tagId = tagId;
  }

  @Override
  public String toString() {
    return "PostTag{" + "postId=" + postId + ", tagId=" + tagId + '}';
  }
}
