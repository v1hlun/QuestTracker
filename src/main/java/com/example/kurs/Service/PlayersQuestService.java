package com.example.kurs.Service;

import com.example.kurs.Dao.PlayersQuestRepository;
import com.example.kurs.entyty.PlayersQuest;
import com.example.kurs.entyty.QuestStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

@Service
public class PlayersQuestService {

    private final PlayersQuestRepository playersQuestRepository;

    public PlayersQuestService(PlayersQuestRepository playersQuestRepository) {
        this.playersQuestRepository = playersQuestRepository;
    }

    public List<PlayersQuest> getAllPlayersQuests() {
        return playersQuestRepository.findAll();
    }

    public PlayersQuest getPlayersQuestById(Long id) {
        return playersQuestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PlayersQuest not found with id: " + id));
    }

    public List<PlayersQuest> findByPlayerId(Long playerId) {
        return playersQuestRepository.findByPlayerId(playerId);
    }

    public List<PlayersQuest> findByQuestId(Long questId) {
        return playersQuestRepository.findByQuestId(questId);
    }

    public List<PlayersQuest> findByPlayerIdAndStatus(Long playerId, String status) {
        return playersQuestRepository.findByPlayerIdAndStatus(playerId, status);
    }

    public List<PlayersQuest> findActiveQuestsByPlayerId(Long playerId) {
        return playersQuestRepository.findByPlayerIdAndStatus(playerId, "IN_PROGRESS");
    }

    public List<PlayersQuest> findCompletedQuestsByPlayerId(Long playerId) {
        return playersQuestRepository.findByPlayerIdAndStatus(playerId, "COMPLETED");
    }

    public List<PlayersQuest> findFailedQuestsByPlayerId(Long playerId) {
        return playersQuestRepository.findByPlayerIdAndStatus(playerId, "FAILED");
    }

    public List<PlayersQuest> findNotStartedQuestsByPlayerId(Long playerId) {
        return playersQuestRepository.findByPlayerIdAndStatus(playerId, "NOT_STARTED");
    }

    public Map<String, List<PlayersQuest>> findAllQuestsByPlayerIdGroupedByStatus(Long playerId) {
        List<PlayersQuest> allQuests = findByPlayerId(playerId);

        Map<String, List<PlayersQuest>> groupedQuests = new HashMap<>();
        groupedQuests.put("IN_PROGRESS", new ArrayList<>());
        groupedQuests.put("COMPLETED", new ArrayList<>());
        groupedQuests.put("FAILED", new ArrayList<>());
        groupedQuests.put("NOT_STARTED", new ArrayList<>());

        for (PlayersQuest quest : allQuests) {
            String status = String.valueOf(quest.getStatus());
            groupedQuests.computeIfAbsent(status, k -> new ArrayList<>()).add(quest);
        }

        return groupedQuests;
    }

    public PlayersQuest createPlayersQuest(PlayersQuest playersQuest) {
        // Set initial status if not provided
        if (playersQuest.getStatus() == null) {
            playersQuest.setStatus(QuestStatus.valueOf("NOT_STARTED"));
        }
        return playersQuestRepository.save(playersQuest);
    }

    public PlayersQuest updatePlayersQuest(Long id, PlayersQuest updatedPlayersQuest) {
        PlayersQuest existingPlayersQuest = getPlayersQuestById(id);
        existingPlayersQuest.setPlayer(updatedPlayersQuest.getPlayer());
        existingPlayersQuest.setQuest(updatedPlayersQuest.getQuest());
        existingPlayersQuest.setStatus(updatedPlayersQuest.getStatus());
        return playersQuestRepository.save(existingPlayersQuest);
    }

    public PlayersQuest updateQuestStatus(Long id, String newStatus) {
        PlayersQuest quest = getPlayersQuestById(id);
        quest.setStatus(QuestStatus.valueOf(newStatus));
        return playersQuestRepository.save(quest);
    }

    public void deletePlayersQuest(Long id) {
        playersQuestRepository.deleteById(id);
    }
}

