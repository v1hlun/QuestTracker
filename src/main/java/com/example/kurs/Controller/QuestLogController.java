package com.example.kurs.Controller;

import com.example.kurs.Service.QuestLogService;
import com.example.kurs.entyty.QuestLog;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/questLogs")
public class QuestLogController {

    private final QuestLogService questLogService;

    public QuestLogController(QuestLogService questLogService) {
        this.questLogService = questLogService;
    }

    @GetMapping
    public List<QuestLog> getAllQuestLogs() {
        return questLogService.getAllQuestLogs();
    }

    @GetMapping("/{id}")
    public QuestLog getQuestLogById(@PathVariable Long id) {
        return questLogService.getQuestLogById(id);
    }

    @PostMapping
    public QuestLog createQuestLog(@RequestBody QuestLog questLog) {
        return questLogService.createQuestLog(questLog);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestLog(@PathVariable Long id) {
        questLogService.deleteQuestLog(id);
    }
}

