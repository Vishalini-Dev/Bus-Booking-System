package com.example.CapstoneProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CapstoneProject.model.User;
import com.example.CapstoneProject.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

	@Autowired private UserRepository userRepository;

    public User register(User user) {
        return userRepository.save(user);
    }

    public Optional<User> login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.filter(u -> u.getPassword().equals(password));
    }

    public User getProfile(Long id) {
    	 User user = userRepository.findById(id)
    	        .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    	 return user;

    }
    public void changePassword(String name, String oldPassword, String newPassword) {
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!oldPassword.equals(user.getPassword())) {
            throw new RuntimeException("Old password is incorrect");
        }

        user.setPassword(newPassword);
        userRepository.save(user);
    }
}
