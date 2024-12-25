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
    @JoinColumn(name = "playerId")
    private Player playerId;

    @ManyToOne
    @JoinColumn(name = "questId")
    private Quest questId;

    @Enumerated(EnumType.STRING)
    private QuestStatus status;
}
