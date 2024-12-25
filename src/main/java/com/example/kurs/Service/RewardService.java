package com.example.kurs.Service;

import com.example.kurs.Dao.RewardRepository;
import com.example.kurs.entyty.Reward;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardService {

    private final RewardRepository rewardRepository;

    public RewardService(RewardRepository rewardRepository) {
        this.rewardRepository = rewardRepository;
    }

    public List<Reward> getAllRewards() {
        return rewardRepository.findAll();
    }

    public Reward getRewardById(Long id) {
        return rewardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reward not found with id: " + id));
    }

    public Reward createReward(Reward reward) {
        return rewardRepository.save(reward);
    }

    public Reward updateReward(Long id, Reward updatedReward) {
        Reward reward = getRewardById(id);
        reward.setRewardId(updatedReward.getRewardId());
        reward.setRewardName(updatedReward.getRewardName());
        reward.setRewardDescription((updatedReward.getRewardDescription()));
        reward.setQuests(updatedReward.getQuests());
        return rewardRepository.save(reward);
    }

}

