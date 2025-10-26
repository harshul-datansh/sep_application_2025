package com.example.crudrest.service;

import com.example.crudrest.model.User;
import com.example.crudrest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create(User user) {
        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User update(Long id, User newUser) {
        User existing = userRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setName(newUser.getName());
            existing.setEmail(newUser.getEmail());
            return userRepository.save(existing);
        }
        return null;
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
