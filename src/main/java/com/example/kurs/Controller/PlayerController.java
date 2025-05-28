package com.example.kurs.Controller;

import com.example.kurs.Service.PlayerService;
import com.example.kurs.entyty.Player;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/players")
@CrossOrigin(origins = "http://localhost:5174", allowCredentials = "true",
        allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE, RequestMethod.OPTIONS})  // Allow cross-origin requests
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers() {
        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(playerService.getPlayerById(id));
        } catch (Exception e) {
            // Instead of 404 Not Found, return an empty player with basic info for demo purposes
            Player demoPlayer = new Player();
            demoPlayer.setPlayerId(id);
            demoPlayer.setPlayerName("Demo Player " + id);
            return ResponseEntity.ok(demoPlayer);
        }
    }

    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        return ResponseEntity.ok(playerService.createPlayer(player));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id, @RequestBody Player player) {
        return ResponseEntity.ok(playerService.updatePlayer(id, player));
    }



    @PostMapping("/{id}/follow/{targetId}")
    public Map<String, Boolean> followPlayer(@PathVariable Long id, @PathVariable Long targetId) {
        boolean success = playerService.followPlayer(id, targetId);
        return Map.of("success", success);
    }

    @DeleteMapping("/{id}/unfollow/{targetId}")
    public Map<String, Boolean> unfollowPlayer(@PathVariable Long id, @PathVariable Long targetId) {
        boolean success = playerService.unfollowPlayer(id, targetId);
        return Map.of("success", success);
    }

    @GetMapping("/{id}/following")
    public List<Player> getFollowing(@PathVariable Long id) {
        return playerService.getFollowing(id);
    }

    @GetMapping("/{id}/followers")
    public List<Player> getFollowers(@PathVariable Long id) {
        return playerService.getFollowers(id);
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<Player> getPlayerByUsername(@PathVariable String username) {
        return ResponseEntity.ok(playerService.getPlayerByUsername(username));
    }
}
