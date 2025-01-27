package com.example.notemanager.users.service;

import com.example.notemanager.users.exception.ExceptionMessages;
import com.example.notemanager.users.exception.UserServiceException;
import com.example.notemanager.users.model.User;
import com.example.notemanager.users.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String createUser(String username, String email, String password) {
        if (userRepository.existsByUserName(username)) {
            return "User already exists";
        }

        User user = User.builder()
                .userName(username)
                .email(email)
                .password(passwordEncoder.encode(password))
                .role("ROLE_USER")
                .build();
        userRepository.save(user);
        return "User created";
    }

    public User getAuthenticatedUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UserServiceException(ExceptionMessages.USER_NOT_FOUND.getMessage()));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserServiceException(ExceptionMessages.USER_NOT_FOUND.getMessage()));
    }
}
