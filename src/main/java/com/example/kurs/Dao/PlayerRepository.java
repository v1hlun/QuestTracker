package com.example.kurs.Dao;

import com.example.kurs.entyty.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    boolean existsByUsername(String username);

    Optional<Player> findByUsername(String username);
}
