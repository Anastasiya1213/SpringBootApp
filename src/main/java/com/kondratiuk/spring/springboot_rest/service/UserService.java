package com.kondratiuk.spring.springboot_rest.service;

import com.kondratiuk.spring.springboot_rest.ForErrors.UserAlreadyExistsException;
import com.kondratiuk.spring.springboot_rest.dto.UserDTO;
import com.kondratiuk.spring.springboot_rest.entity.Users;
import com.kondratiuk.spring.springboot_rest.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Users register(UserDTO userDTO) {
        logger.info("Attempting to register user with email: {}", userDTO.getEmail());
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            logger.error("Registration failed: User with email {} already exists", userDTO.getEmail());
            throw new UserAlreadyExistsException("User with email " + userDTO.getEmail() + " already exists");
        }
        Users user = new Users();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPasswordHash(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(Users.Role.valueOf(userDTO.getRole().toUpperCase()));
        userRepository.save(user);
        logger.info("User with email {} registered successfully", userDTO.getEmail());
        return user;
    }

    public boolean authenticate(String email, String password) {
        Users user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPasswordHash())) {
            logger.info("User with email {} authenticated successfully", email);
            return true; // The user is authenticated
        } else {
            logger.info("Authentication failed for user with email {}", email);
            return false; // Authentication failed
        }
    }

}



