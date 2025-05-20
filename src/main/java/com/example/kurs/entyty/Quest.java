package com.example.kurs.entyty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "quest")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Quest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questId;

    private String questName;

    private String description;

    @ManyToOne
    @JoinColumn(name = "rewardId")
    @JsonBackReference
    private Reward reward;
}
