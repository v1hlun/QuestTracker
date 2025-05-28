package com.example.kurs.Dao;

import com.example.kurs.entyty.PlayersQuest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PlayersQuestRepository extends JpaRepository<PlayersQuest, Long> {
    // Find all quests for a specific player using JPQL query
    @Query("SELECT pq FROM PlayersQuest pq WHERE pq.player.playerId = :playerId")
    List<PlayersQuest> findByPlayerId(@Param("playerId") Long playerId);

    // Find all players for a specific quest using JPQL
    @Query("SELECT pq FROM PlayersQuest pq WHERE pq.quest.questId = :questId")
    List<PlayersQuest> findByQuestId(@Param("questId") Long questId);

    // Find quests by player ID and status
    @Query("SELECT pq FROM PlayersQuest pq WHERE pq.player.playerId = :playerId AND pq.status = :status")
    List<PlayersQuest> findByPlayerIdAndStatus(@Param("playerId") Long playerId, @Param("status") String status);
}