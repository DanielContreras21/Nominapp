package com.nominapp.user.service;

import com.nominapp.user.entity.User;
import com.nominapp.user.exception.NotFoundException;
import com.nominapp.user.repository.UserRepository;
import com.nominapp.user.service.abstraction.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository repository;
    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setId(randomUserId);
        return repository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User getUSer(String id) {
        return repository.findById(id).orElseThrow( () -> new NotFoundException("User with id: " + id + " was not found"));
    }
}
