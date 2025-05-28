package com.example.kurs.entyty;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "players_quest")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlayersQuest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerQuestId;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "quest_id")
    private Quest quest;

    @Enumerated(EnumType.STRING)
    private QuestStatus status;
}
