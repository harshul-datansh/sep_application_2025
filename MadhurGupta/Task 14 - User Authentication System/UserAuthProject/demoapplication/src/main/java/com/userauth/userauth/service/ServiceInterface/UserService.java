package com.userauth.userauth.service.ServiceInterface;

import com.userauth.userauth.dto.AuthRequest;
import com.userauth.userauth.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    User createUser(User user);

    User updateUser(Long id, User user);

    void deleteUser(Long id);

    // New method to save user during signup from AuthRequest
    User saveUser(AuthRequest request);

    // New method to check if a user exists by username
    boolean userExists(String username);
}
