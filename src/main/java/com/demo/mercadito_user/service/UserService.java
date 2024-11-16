package com.demo.mercadito_user.service;

import com.demo.mercadito_user.model.User;
import com.demo.mercadito_user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        Optional<User> existingUser = Optional.ofNullable(userRepository.findByEmail(user.getEmail()));
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        user.encryptPassword();
        return userRepository.save(user);
    }

    public boolean login(String email, String password) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        if (user.isEmpty()) {
            return false;
        }
        return new BCryptPasswordEncoder().matches(password, user.get().getPassword());
    }
}
