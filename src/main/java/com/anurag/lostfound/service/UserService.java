package com.anurag.lostfound.service;

import com.anurag.lostfound.entity.User;
import com.anurag.lostfound.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    // USER REGISTER
    public User registerUser(String email, String password) {

        if (email == null || email.trim().isEmpty()) {
            throw new RuntimeException("Email is required");
        }

        if (password == null || password.trim().isEmpty()) {
            throw new RuntimeException("Password is required");
        }

        String registerEmail = email.trim();

        if (repository.findByEmail(registerEmail).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setEmail(registerEmail);
        user.setPassword(password);
        user.setRole("USER");

        return repository.save(user);
    }

    // USER LOGIN
    public User userLogin(String email, String password) {

        if (email == null || email.trim().isEmpty()) {
            throw new RuntimeException("Email is required");
        }

        if (password == null || password.trim().isEmpty()) {
            throw new RuntimeException("Password is required");
        }

        String loginEmail = email.trim();

        User user = repository.findByEmail(loginEmail)
                .orElseThrow(() ->
                    new RuntimeException(
                        "User not registered. Please register first."
                    )
                );

        if (user.getPassword() == null
                || !user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid email or password");
        }

        if (!"USER".equals(user.getRole())) {
            throw new RuntimeException("This is not a user account");
        }

        return user;
    }

    // ADMIN LOGIN
    public User adminLogin(String email, String password) {

        if (email == null || password == null) {
            throw new RuntimeException("Invalid admin login");
        }

        if (email.trim().equals("admin@gmail.com")
                && password.equals("admin123")) {

            User admin = repository.findByEmail("admin@gmail.com")
                    .orElseGet(User::new);

            admin.setEmail("admin@gmail.com");
            admin.setPassword("admin123");
            admin.setRole("ADMIN");

            return repository.save(admin);
        }

        throw new RuntimeException("Invalid admin email or password");
    }
}

