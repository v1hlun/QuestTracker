package com.example.kurs.Dao;

import com.example.kurs.entyty.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
