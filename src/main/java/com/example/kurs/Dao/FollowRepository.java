package com.example.kurs.Dao;

import com.example.kurs.entyty.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    
    /**
     * Проверяет существование подписки между пользователями
     * @param followerId ID подписчика
     * @param targetId ID цели подписки
     * @return true, если подписка существует
     */
    boolean existsByFollowerIdAndTargetId(Long followerId, Long targetId);
    
    /**
     * Находит подписку между пользователями
     * @param followerId ID подписчика
     * @param targetId ID цели подписки
     * @return объект подписки или null
     */
    Follow findByFollowerIdAndTargetId(Long followerId, Long targetId);
    
    /**
     * Находит все подписки пользователя
     * @param followerId ID подписчика
     * @return список подписок
     */
    List<Follow> findByFollowerId(Long followerId);
    
    /**
     * Находит всех подписчиков пользователя
     * @param targetId ID пользователя
     * @return список подписок
     */
    List<Follow> findByTargetId(Long targetId);
} 