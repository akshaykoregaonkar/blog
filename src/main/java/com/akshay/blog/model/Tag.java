package com.akshay.blog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
@Table(name = "tags")
public class Tag {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "tag_id")
        private Long tagId;

        @Column(name = "name", nullable = false)
        private String name;

        @Column(name = "slug", nullable = false)
        private String slug;

        @OneToMany(mappedBy = "tagId")
        private List<PostTag> postTags;

        public Tag() {}

        public Tag(String name, String slug) {
                this.name = name;
                this.slug = slug;
        }

        public Long getTagId() {
                return tagId;
        }

        public void setTagId(Long tagId) {
                this.tagId = tagId;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getSlug() {
                return slug;
        }

        public void setSlug(String slug) {
                this.slug = slug;
        }

        public List<PostTag> getPostTags() {
                return postTags;
        }

        public void setPostTags(List<PostTag> postTags) {
                this.postTags = postTags;
        }

        @Override
        public String toString() {
                return "Tag{" +
                        "tagId=" + tagId +
                        ", name='" + name + '\'' +
                        ", slug='" + slug + '\'' +
                        '}';
        }
}
