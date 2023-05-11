package com.akshay.blog.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "comment_id")
        private Long commentId;

        @ManyToOne
        @JoinColumn(name = "post_id", nullable = false)
        private Post post;

        @Column(name = "author", nullable = false)
        private String author;

        @Column(name = "comment_text", nullable = false)
        private String commentText;

        @Column(name = "created_at", nullable = false, updatable = false)
        private LocalDateTime createdAt;

        public Comment() {
        }

        public Comment(Post post, String author, String commentText, LocalDateTime createdAt) {
                this.post = post;
                this.author = author;
                this.commentText = commentText;
                this.createdAt = createdAt;
        }

        public Long getCommentId() {
                return commentId;
        }

        public void setCommentId(Long commentId) {
                this.commentId = commentId;
        }

        public Post getPost() {
                return post;
        }

        public void setPost(Post post) {
                this.post = post;
        }

        public String getAuthor() {
                return author;
        }

        public void setAuthor(String author) {
                this.author = author;
        }

        public String getCommentText() {
                return commentText;
        }

        public void setCommentText(String commentText) {
                this.commentText = commentText;
        }

        public LocalDateTime getCreatedAt() {
                return createdAt;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
                this.createdAt = createdAt;
        }
}
