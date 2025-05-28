package com.example.kurs.Service;

import com.example.kurs.Dao.PlayerRepository;
import com.example.kurs.Dao.FollowRepository;
import com.example.kurs.entyty.Player;
import com.example.kurs.entyty.Follow;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final FollowRepository followRepository;

    public PlayerService(PlayerRepository playerRepository, FollowRepository followRepository) {
        this.playerRepository = playerRepository;
        this.followRepository = followRepository;
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player getPlayerById(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found with id: " + id));
    }

    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }

    public Player updatePlayer(Long id, Player updatedPlayer) {
        Player player = getPlayerById(id);
        player.setPlayerName(updatedPlayer.getPlayerName());
        return playerRepository.save(player);
    }

    /**
     * Подписаться на игрока
     * @param followerId ID пользователя, который подписывается
     * @param targetId ID пользователя, на которого подписываются
     * @return true, если операция успешна
     */
    @Transactional
    public boolean followPlayer(Long followerId, Long targetId) {
        // Проверяем, существуют ли оба игрока
        Player follower = getPlayerById(followerId);
        Player target = getPlayerById(targetId);

        // Проверяем, что пользователь не пытается подписаться на самого себя
        if (followerId.equals(targetId)) {
            return false;
        }

        // Проверяем, существует ли уже подписка
        if (followRepository.existsByFollowerIdAndTargetId(followerId, targetId)) {
            return false; // Подписка уже существует
        }

        // Создаем новую подписку
        Follow follow = new Follow();
        follow.setFollowerId(followerId);
        follow.setTargetId(targetId);

        followRepository.save(follow);
        return true;
    }

    /**
     * Отписаться от игрока
     * @param followerId ID пользователя, который отписывается
     * @param targetId ID пользователя, от которого отписываются
     * @return true, если операция успешна
     */
    @Transactional
    public boolean unfollowPlayer(Long followerId, Long targetId) {
        // Находим и удаляем подписку, если она существует
        Follow follow = followRepository.findByFollowerIdAndTargetId(followerId, targetId);
        if (follow != null) {
            followRepository.delete(follow);
            return true;
        }
        return false; // Подписка не найдена
    }

    /**
     * Получить список игроков, на которых подписан пользователь
     * @param playerId ID пользователя
     * @return Список игроков
     */
    public List<Player> getFollowing(Long playerId) {
        List<Long> followingIds = followRepository.findByFollowerId(playerId)
                .stream()
                .map(Follow::getTargetId)
                .collect(Collectors.toList());

        return playerRepository.findAllById(followingIds);
    }

    /**
     * Получить список игроков, которые подписаны на пользователя
     * @param playerId ID пользователя
     * @return Список игроков
     */
    public List<Player> getFollowers(Long playerId) {
        List<Long> followerIds = followRepository.findByTargetId(playerId)
                .stream()
                .map(Follow::getFollowerId)
                .collect(Collectors.toList());

        return playerRepository.findAllById(followerIds);
    }
}
