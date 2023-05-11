package com.akshay.blog.controller;

import com.akshay.blog.model.Post;
import com.akshay.blog.model.User;
import com.akshay.blog.model.form.UserForm;
import com.akshay.blog.repository.PostRepository;
import com.akshay.blog.repository.UserRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired private UserRepository userRepository;

  @Autowired private PostRepository postRepository;

  @GetMapping
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @GetMapping("/{userId}")
  public ResponseEntity<User> getUserById(@PathVariable Long userId) {
    Optional<User> user = userRepository.findById(userId);
    return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/{userId}/posts")
  public ResponseEntity<List<Post>> getPostsByUser(@PathVariable Long userId){
    Optional<User> user = userRepository.findById(userId);
    if(user.isPresent()){
      List<Post> posts = postRepository.findByUser(user.get());
      return ResponseEntity.ok(posts);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public String createUser(@ModelAttribute("userForm") UserForm userForm) {
    User user = new User();
    user.setUsername(userForm.getUsername());
    user.setPassword(userForm.getPassword());
    user.setEmail(userForm.getEmail());
    userRepository.save(user);
    return "redirect:/users";
  }

  @PutMapping("/{userId}")
  public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
    User existingUser = userRepository.findById(userId).orElse(null);
    if (existingUser != null) {
      User newUser =
          new User(
              existingUser.getUserId(),
              updatedUser.getUsername(),
              updatedUser.getPassword(),
              updatedUser.getEmail());
      userRepository.save(newUser);
      return new ResponseEntity<>(newUser, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long userId) {
    User user = userRepository.findById(userId).orElse(null);
    if (user != null) {
      userRepository.delete(user);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
