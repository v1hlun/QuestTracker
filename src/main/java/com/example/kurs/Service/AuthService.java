package com.example.kurs.Service;

import com.example.kurs.Dao.PlayerRepository;
import com.example.kurs.Security.JwtTokenProvider;
import com.example.kurs.entyty.Player;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final PlayerRepository playerRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public Player register(String username, String password, String playerName){
        if(playerRepository.existsByUsername(username)){
            throw new RuntimeException("Username already exits");
        }

        Player player = new Player();
        player.setUsername(username);
        player.setPassword(passwordEncoder.encode(password));
        player.setPlayerName(playerName);

        return playerRepository.save(player);
    }

    public String login(String username, String password) {
        Player player = playerRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, player.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        return jwtTokenProvider.generateToken(username);
    }

}
