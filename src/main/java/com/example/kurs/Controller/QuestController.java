package com.example.kurs.Controller;

import com.example.kurs.Service.QuestService;
import com.example.kurs.entyty.Quest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/quests")
public class QuestController {

    private final QuestService questService;
    private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);

    public QuestController(QuestService questService) {
        this.questService = questService;
    }

    @GetMapping
    public List<Quest> getAllQuests() {
        logger.info("Получен запрос на получение всех квестов");
        List<Quest> quests = questService.getAllQuests();
        logger.info("Найдено {} квестов", quests.size());
        return quests;
    }

    @GetMapping("/{id}")
    public Quest getQuestById(@PathVariable Long id) {
        logger.info("Получен запрос на получение квеста с ID: {}", id);
        Quest quest = questService.getQuestById(id);
        if (quest != null) {
            logger.info("Квест с ID {} найден: {}", id, quest.getQuestName());
        } else {
            logger.warn("Квест с ID {} не найден", id);
        }
        return quest;
    }

    @PostMapping
    public Quest createQuest(@RequestBody Quest quest) {
        logger.info("Получен запрос на создание нового квеста: {}", quest.getQuestName());
        Quest createdQuest = questService.createQuest(quest);
        logger.info("Квест создан с ID: {}", createdQuest.getQuestId());
        return createdQuest;
    }

    @PutMapping("/{id}")
    public Quest updateQuest(@PathVariable Long id, @RequestBody Quest updatedQuest) {
        return questService.updateQuest(id, updatedQuest);
    }

}

