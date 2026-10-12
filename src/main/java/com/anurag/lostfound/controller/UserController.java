
package com.anurag.lostfound.controller;

import com.anurag.lostfound.entity.User;
import com.anurag.lostfound.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = {
    "http://localhost:5173",
    "http://localhost:5174",
    "https://lost-found-frontend-udmv.onrender.com"
})
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User result = service.registerUser(
                user.getEmail(),
                user.getPassword()
            );

            return ResponseEntity.ok(
                Map.of(
                    "email", result.getEmail(),
                    "role", result.getRole().toString()
                )
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/user-login")
    public ResponseEntity<?> userLogin(@RequestBody User user) {
        try {
            User result = service.userLogin(
                user.getEmail(),
                user.getPassword()
            );

            return ResponseEntity.ok(
                Map.of(
                    "email", result.getEmail(),
                    "role", result.getRole().toString()
                )
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/admin-login")
    public ResponseEntity<?> adminLogin(@RequestBody User user) {
        try {
            User result = service.adminLogin(
                user.getEmail(),
                user.getPassword()
            );

            return ResponseEntity.ok(
                Map.of(
                    "email", result.getEmail(),
                    "role", result.getRole().toString()
                )
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

