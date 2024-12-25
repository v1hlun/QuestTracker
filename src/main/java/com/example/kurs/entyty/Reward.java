package com.example.kurs.entyty;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reward")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rewardId;

    private String rewardName;

    private String rewardDescription;

    @OneToMany(mappedBy = "reward")
    private List<Quest> quests = new ArrayList<>();
}
