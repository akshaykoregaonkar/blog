package com.akshay.blog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import java.util.Objects;

@Entity
@Table(name = "likes")
public class Like {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "like_id")
        private Long likeId;

        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        private User user;

        @ManyToOne
        @JoinColumn(name = "post_id")
        private Post post;

        @ManyToOne
        @JoinColumn(name = "comment_id")
        private Comment comment;

        public Long getLikeId() {
                return likeId;
        }

        public void setLikeId(Long likeId) {
                this.likeId = likeId;
        }

        public User getUser() {
                return user;
        }

        public void setUser(User user) {
                this.user = user;
        }

        public Post getPost() {
                return post;
        }

        public void setPost(Post post) {
                this.post = post;
        }

        public Comment getComment() {
                return comment;
        }

        public void setComment(Comment comment) {
                this.comment = comment;
        }

        @Override
        public String toString() {
                return "Like{" +
                        "likeId=" + likeId +
                        ", user=" + user +
                        ", post=" + post +
                        ", comment=" + comment +
                        '}';
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Like like = (Like) o;
                return Objects.equals(likeId, like.likeId) && Objects.equals(user, like.user) && Objects.equals(post, like.post) && Objects.equals(comment, like.comment);
        }

        @Override
        public int hashCode() {
                return Objects.hash(likeId, user, post, comment);
        }
}
