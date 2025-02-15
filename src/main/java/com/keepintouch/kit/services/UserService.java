package com.keepintouch.kit.services;

import com.keepintouch.kit.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> saveUser(User user);
    Optional<User> getById(String id);
    Optional<User> updateUser(User user);
    void deleteUser(String id);
    boolean isUserExists(String id);
    boolean isUserExistsWithEmail(String email);
    Optional<List<User>> getAllUsers();
}
