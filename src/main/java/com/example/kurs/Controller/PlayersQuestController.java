package com.example.kurs.Controller;

import com.example.kurs.Service.PlayersQuestService;
import com.example.kurs.entyty.PlayersQuest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/playersQuests")
@CrossOrigin(origins = "http://localhost:5174", allowCredentials = "true",
        allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE, RequestMethod.OPTIONS})
public class PlayersQuestController {

    private final PlayersQuestService playersQuestService;

    public PlayersQuestController(PlayersQuestService playersQuestService) {
        this.playersQuestService = playersQuestService;
    }

    @GetMapping
    public ResponseEntity<List<PlayersQuest>> getAllPlayersQuests() {
        return ResponseEntity.ok(playersQuestService.getAllPlayersQuests());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayersQuest> getPlayersQuestById(@PathVariable Long id) {
        return ResponseEntity.ok(playersQuestService.getPlayersQuestById(id));
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<List<PlayersQuest>> getQuestsByPlayerId(@PathVariable Long playerId) {
        return ResponseEntity.ok(playersQuestService.findByPlayerId(playerId));
    }

    @GetMapping("/player/{playerId}/grouped")
    public ResponseEntity<Map<String, List<PlayersQuest>>> getQuestsByPlayerIdGrouped(@PathVariable Long playerId) {
        return ResponseEntity.ok(playersQuestService.findAllQuestsByPlayerIdGroupedByStatus(playerId));
    }

    @GetMapping("/player/{playerId}/status/{status}")
    public ResponseEntity<List<PlayersQuest>> getQuestsByPlayerIdAndStatus(
            @PathVariable Long playerId,
            @PathVariable String status) {
        return ResponseEntity.ok(playersQuestService.findByPlayerIdAndStatus(playerId, status));
    }

    @GetMapping("/player/{playerId}/active")
    public ResponseEntity<List<PlayersQuest>> getActiveQuestsByPlayerId(@PathVariable Long playerId) {
        return ResponseEntity.ok(playersQuestService.findActiveQuestsByPlayerId(playerId));
    }

    @GetMapping("/player/{playerId}/completed")
    public ResponseEntity<List<PlayersQuest>> getCompletedQuestsByPlayerId(@PathVariable Long playerId) {
        return ResponseEntity.ok(playersQuestService.findCompletedQuestsByPlayerId(playerId));
    }

    @GetMapping("/player/{playerId}/failed")
    public ResponseEntity<List<PlayersQuest>> getFailedQuestsByPlayerId(@PathVariable Long playerId) {
        return ResponseEntity.ok(playersQuestService.findFailedQuestsByPlayerId(playerId));
    }

    @GetMapping("/player/{playerId}/not-started")
    public ResponseEntity<List<PlayersQuest>> getNotStartedQuestsByPlayerId(@PathVariable Long playerId) {
        return ResponseEntity.ok(playersQuestService.findNotStartedQuestsByPlayerId(playerId));
    }

    @GetMapping("/quest/{questId}")
    public ResponseEntity<List<PlayersQuest>> getPlayersByQuestId(@PathVariable Long questId) {
        return ResponseEntity.ok(playersQuestService.findByQuestId(questId));
    }

    @PostMapping
    public ResponseEntity<PlayersQuest> createPlayersQuest(@RequestBody PlayersQuest playersQuest) {
        return ResponseEntity.ok(playersQuestService.createPlayersQuest(playersQuest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayersQuest> updatePlayersQuest(
            @PathVariable Long id,
            @RequestBody PlayersQuest updatedPlayersQuest) {
        return ResponseEntity.ok(playersQuestService.updatePlayersQuest(id, updatedPlayersQuest));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PlayersQuest> updateQuestStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> statusUpdate) {
        String newStatus = statusUpdate.get("status");
        return ResponseEntity.ok(playersQuestService.updateQuestStatus(id, newStatus));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayersQuest(@PathVariable Long id) {
        playersQuestService.deletePlayersQuest(id);
        return ResponseEntity.ok().build();
    }
}
