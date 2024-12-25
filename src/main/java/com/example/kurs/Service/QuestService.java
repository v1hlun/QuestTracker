package com.example.kurs.Service;

import com.example.kurs.Dao.QuestRepository;
import com.example.kurs.entyty.Quest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestService {

    private final QuestRepository questRepository;

    public QuestService(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }

    public List<Quest> getAllQuests() {
        return questRepository.findAll();
    }

    public Quest getQuestById(Long id) {
        return questRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quest not found with id: " + id));
    }

    public Quest createQuest(Quest quest) {
        return questRepository.save(quest);
    }

    public Quest updateQuest(Long id, Quest updatedQuest) {
        Quest quest = getQuestById(id);
        quest.setQuestName(updatedQuest.getQuestName());
        quest.setDescription(updatedQuest.getDescription());
        quest.setReward(updatedQuest.getReward());
        return questRepository.save(quest);
    }

}

