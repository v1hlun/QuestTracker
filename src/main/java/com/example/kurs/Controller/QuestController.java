package com.example.kurs.Controller;

import com.example.kurs.Service.QuestService;
import com.example.kurs.dto.QuestDTO;
import com.example.kurs.entyty.Quest;
import com.example.kurs.util.DTOConverter;
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
    public List<QuestDTO> getAllQuests() {
        logger.info("Получен запрос на получение всех квестов");
        List<Quest> quests = questService.getAllQuests();
        logger.info("Найдено {} квестов", quests.size());
        return DTOConverter.convertToQuestDTOList(quests);
    }

    @GetMapping("/{id}")
    public QuestDTO getQuestById(@PathVariable Long id) {
        logger.info("Получен запрос на получение квеста с ID: {}", id);
        Quest quest = questService.getQuestById(id);
        if (quest != null) {
            logger.info("Квест с ID {} найден: {}", id, quest.getQuestName());
            return DTOConverter.convertToQuestDTO(quest);
        } else {
            logger.warn("Квест с ID {} не найден", id);
            return null;
        }
    }

    @PostMapping
    public QuestDTO createQuest(@RequestBody Quest quest) {
        logger.info("Получен запрос на создание нового квеста: {}", quest.getQuestName());
        Quest createdQuest = questService.createQuest(quest);
        logger.info("Квест создан с ID: {}", createdQuest.getQuestId());
        return DTOConverter.convertToQuestDTO(createdQuest);
    }

    @PutMapping("/{id}")
    public QuestDTO updateQuest(@PathVariable Long id, @RequestBody Quest updatedQuest) {
        Quest result = questService.updateQuest(id, updatedQuest);
        return DTOConverter.convertToQuestDTO(result);
    }
}

