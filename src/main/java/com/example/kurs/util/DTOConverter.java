package com.example.kurs.util;

import com.example.kurs.dto.QuestDTO;
import com.example.kurs.dto.RewardDTO;
import com.example.kurs.entyty.Quest;
import com.example.kurs.entyty.Reward;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Утилитный класс для конвертации между сущностями и DTO объектами
 */
public class DTOConverter {

    /**
     * Конвертирует сущность Quest в DTO
     */
    public static QuestDTO convertToQuestDTO(Quest quest) {
        if (quest == null) return null;
        
        QuestDTO dto = new QuestDTO();
        dto.setQuestId(quest.getQuestId());
        dto.setQuestName(quest.getQuestName());
        dto.setDescription(quest.getDescription());
        
        // Добавляем информацию о награде, если она присутствует
        if (quest.getReward() != null) {
            dto.setRewardId(quest.getReward().getRewardId());
            dto.setRewardName(quest.getReward().getRewardName());
        }
        
        return dto;
    }
    
    /**
     * Конвертирует список сущностей Quest в список DTO
     */
    public static List<QuestDTO> convertToQuestDTOList(List<Quest> quests) {
        return quests.stream()
                .map(DTOConverter::convertToQuestDTO)
                .collect(Collectors.toList());
    }

    /**
     * Конвертирует сущность Reward в DTO
     */
    public static RewardDTO convertToRewardDTO(Reward reward) {
        if (reward == null) return null;
        
        RewardDTO dto = new RewardDTO();
        dto.setRewardId(reward.getRewardId());
        dto.setRewardName(reward.getRewardName());
        dto.setRewardDescription(reward.getRewardDescription());
        
        // Извлекаем только ID квестов, чтобы избежать циклических ссылок
        if (reward.getQuests() != null) {
            List<Long> questIds = reward.getQuests().stream()
                    .map(Quest::getQuestId)
                    .collect(Collectors.toList());
            dto.setQuestIds(questIds);
        }
        
        return dto;
    }
    
    /**
     * Конвертирует список сущностей Reward в список DTO
     */
    public static List<RewardDTO> convertToRewardDTOList(List<Reward> rewards) {
        return rewards.stream()
                .map(DTOConverter::convertToRewardDTO)
                .collect(Collectors.toList());
    }
} 