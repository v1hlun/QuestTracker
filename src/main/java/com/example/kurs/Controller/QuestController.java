package com.example.kurs.Controller;

import com.example.kurs.Service.QuestService;
import com.example.kurs.entyty.Quest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quests")
public class QuestController {

    private final QuestService questService;

    public QuestController(QuestService questService) {
        this.questService = questService;
    }

    @GetMapping
    public List<Quest> getAllQuests() {
        return questService.getAllQuests();
    }

    @GetMapping("/{id}")
    public Quest getQuestById(@PathVariable Long id) {
        return questService.getQuestById(id);
    }

    @PostMapping
    public Quest createQuest(@RequestBody Quest quest) {
        return questService.createQuest(quest);
    }

    @PutMapping("/{id}")
    public Quest updateQuest(@PathVariable Long id, @RequestBody Quest updatedQuest) {
        return questService.updateQuest(id, updatedQuest);
    }

}

