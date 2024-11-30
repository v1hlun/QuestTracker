package com.example.kurs.Dao;

import com.example.kurs.entyty.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<Users, Long> {
    @Override
    Optional<Users> findById(Long aLong);
}
