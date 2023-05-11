package com.akshay.blog.repository;

import com.akshay.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/*
* By extending this interface, you get access to many helpful
* methods for performing CRUD (Create, Read, Update, Delete) operations
* */
public interface UserRepository extends JpaRepository<User, Long> {
}
