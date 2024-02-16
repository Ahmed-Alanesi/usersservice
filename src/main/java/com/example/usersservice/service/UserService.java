package com.example.usersservice.service;


import com.example.usersservice.model.User;
import com.example.usersservice.model.UserResource;

import java.util.List;

public interface UserService {

    UserResource registerNewUser(User user);

    Object findById(Long id);

    List<UserResource> getAll();

    UserResource updateUser(User user, Long id);
}
