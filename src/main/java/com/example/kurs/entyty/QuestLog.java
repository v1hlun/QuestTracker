package com.example.kurs.entyty;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "quest_log")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuestLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    @ManyToOne
    @JoinColumn(name = "playerId")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "questId")
    private Quest quest;

    @Enumerated(EnumType.STRING)
    private QuestStatus questStatus;


    private LocalDateTime timestamp; // Метка времени события

    @PrePersist
    protected void onCreate() {
        this.timestamp = LocalDateTime.now();
    }
}
