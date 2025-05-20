package com.example.kurs.dto;

import lombok.Data;
import java.util.List;

/**
 * DTO класс для передачи данных награды без циклических ссылок
 */
@Data
public class RewardDTO {
    private Long rewardId;
    private String rewardName;
    private String rewardDescription;
    private List<Long> questIds; // Только ID связанных квестов
    
    // Конструктор по умолчанию
    public RewardDTO() {
    }
    
    // Конструктор с полями
    public RewardDTO(Long rewardId, String rewardName, String rewardDescription, List<Long> questIds) {
        this.rewardId = rewardId;
        this.rewardName = rewardName;
        this.rewardDescription = rewardDescription;
        this.questIds = questIds;
    }
} 