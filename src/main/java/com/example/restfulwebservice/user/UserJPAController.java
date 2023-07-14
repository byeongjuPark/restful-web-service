package com.example.restfulwebservice.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;;

@RestController
@RequestMapping("/jpa")
public class UserJPAController {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id){
        // 사용자 검색
        Optional<User> user = userRepository.findById(id); // return Optional

        if(!user.isPresent()){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        // hateoas
        EntityModel<User> userModel = EntityModel.of(user.get());
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        userModel.add(linkTo.withRel("all-users"));
        // return userModel;

        return user.get();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        System.out.println("delete mapping");
        userRepository.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = userRepository.save(user);
        
        URI location = 
        ServletUriComponentsBuilder
        .fromCurrentRequest() 
        .path("/{id}")
        .buildAndExpand(savedUser.getId())
        .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> retrieveAllPostsByUser(@PathVariable int id){
        // 사용자 검색
        Optional<User> user = userRepository.findById(id); // return Optional

        if(!user.isPresent()){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        return user.get().getPosts();
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<User> createPost(@PathVariable int id, @RequestBody Post post){
        Optional<User> user = userRepository.findById(id); // return Optional

        if(!user.isPresent()){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        post.setUser(user.get()); // 사용자(객체) 값을 지정.
        Post savedPost = postRepository.save(post);
        
        URI location = 
        ServletUriComponentsBuilder
        .fromCurrentRequest() 
        .path("/{id}")
        .buildAndExpand(savedPost.getId())
        .toUri();

        return ResponseEntity.created(location).build();
    }
}
