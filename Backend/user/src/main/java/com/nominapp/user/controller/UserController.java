package com.nominapp.user.controller;

import com.nominapp.user.entity.User;
import com.nominapp.user.service.abstraction.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.ok(service.saveUser(user));
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable String id){
        return ResponseEntity.ok(service.getUSer(id));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(service.getAllUsers());
    }
}
