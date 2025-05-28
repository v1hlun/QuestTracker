package com.example.kurs.Controller;

import com.example.kurs.Service.AuthService;
import com.example.kurs.entyty.LoginRequest;
import com.example.kurs.entyty.Player;
import com.example.kurs.entyty.RegisterRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:5174")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            log.info("Registration attempt for user: {}", request.getUsername());
            Player player = authService.register(request.getUsername(), request.getPassword(), request.getPlayerName());

            // Generate a token for immediate login after registration
            String token = authService.login(request.getUsername(), request.getPassword());

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("player", player);

            log.info("Registration successful for user: {}", request.getUsername());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Registration failed for user: {}", request.getUsername(), e);
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            log.info("Login attempt for user: {}", request.getUsername());
            String token = authService.login(request.getUsername(), request.getPassword());

            log.info("Login successful for user: {}", request.getUsername());
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Login failed for user: {}", request.getUsername(), e);
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", e.getMessage()));
        }
    }
}
