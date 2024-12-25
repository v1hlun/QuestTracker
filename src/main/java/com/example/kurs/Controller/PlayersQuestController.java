package com.example.kurs.Controller;

import com.example.kurs.Service.PlayersQuestService;
import com.example.kurs.entyty.PlayersQuest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/playersQuests")
public class PlayersQuestController {

    private final PlayersQuestService playersQuestService;

    public PlayersQuestController(PlayersQuestService playersQuestService) {
        this.playersQuestService = playersQuestService;
    }

    @GetMapping
    public List<PlayersQuest> getAllPlayersQuests() {
        return playersQuestService.getAllPlayersQuests();
    }

    @GetMapping("/{id}")
    public PlayersQuest getPlayersQuestById(@PathVariable Long id) {
        return playersQuestService.getPlayersQuestById(id);
    }

    @PostMapping
    public PlayersQuest createPlayersQuest(@RequestBody PlayersQuest playersQuest) {
        return playersQuestService.createPlayersQuest(playersQuest);
    }

    @PutMapping("/{id}")
    public PlayersQuest updatePlayersQuest(@PathVariable Long id, @RequestBody PlayersQuest updatedPlayersQuest) {
        return playersQuestService.updatePlayersQuest(id, updatedPlayersQuest);
    }

    @DeleteMapping("/{id}")
    public void deletePlayersQuest(@PathVariable Long id) {
        playersQuestService.deletePlayersQuest(id);
    }
}
