package com.example.kurs.Service;

import com.example.kurs.Dao.PlayersQuestRepository;
import com.example.kurs.entyty.PlayersQuest;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public PlayersQuest createPlayersQuest(PlayersQuest playersQuest) {
        return playersQuestRepository.save(playersQuest);
    }

    public PlayersQuest updatePlayersQuest(Long id, PlayersQuest updatedPlayersQuest) {
        PlayersQuest existingPlayersQuest = getPlayersQuestById(id);
        existingPlayersQuest.setPlayer(updatedPlayersQuest.getPlayer());
        existingPlayersQuest.setQuest(updatedPlayersQuest.getQuest());
        existingPlayersQuest.setStatus(updatedPlayersQuest.getStatus());
        return playersQuestRepository.save(existingPlayersQuest);
    }

    public void deletePlayersQuest(Long id) {
        playersQuestRepository.deleteById(id);
    }
}

