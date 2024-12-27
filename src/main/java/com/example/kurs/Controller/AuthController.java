package com.example.kurs.Controller;

import com.example.kurs.Service.AuthService;
import com.example.kurs.entyty.LoginRequest;
import com.example.kurs.entyty.Player;
import com.example.kurs.entyty.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody RegisterRequest request){
        Player  player = authService.register(request.getUsername(),request.getPassword(),request.getPlayerName());
        return ResponseEntity.ok(player);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        String token = authService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(Map.of("token", token));
    }

}
