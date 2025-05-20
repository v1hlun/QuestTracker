package com.example.kurs.dto;

import lombok.Data;

/**
 * DTO класс для передачи данных квеста без циклических ссылок
 */
@Data
public class QuestDTO {
    private Long questId;
    private String questName;
    private String description;
    private Long rewardId;
    private String rewardName;

    // Конструктор по умолчанию
    public QuestDTO() {
    }

    // Конструктор с полями
    public QuestDTO(Long questId, String questName, String description, Long rewardId, String rewardName) {
        this.questId = questId;
        this.questName = questName;
        this.description = description;
        this.rewardId = rewardId;
        this.rewardName = rewardName;
    }
}