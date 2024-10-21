package com.nominapp.user.service.abstraction;

import com.nominapp.user.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();
    User getUSer(String id);
}
