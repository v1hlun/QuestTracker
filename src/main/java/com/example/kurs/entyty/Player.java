package com.example.kurs.entyty;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "player")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerId;

    private String playerName;

    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    // URL к изображению аватара
    private String avatarUrl;

    // Класс персонажа (можно использовать Enum)
    private String characterClass;

    // Уровень персонажа
    private Integer level = 1;

    // Опыт персонажа
    private Integer experience = 0;

    // Дополнительная информация о персонаже (может быть JSON)
    @Column(columnDefinition = "TEXT")
    private String characterInfo;
}
