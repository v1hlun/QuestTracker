package com.example.kurs.Controller;

import com.example.kurs.Service.QuestService;
import com.example.kurs.Service.RewardService;
import com.example.kurs.dto.RewardDTO;
import com.example.kurs.entyty.Quest;
import com.example.kurs.entyty.Reward;
import com.example.kurs.util.DTOConverter;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/rewards")
public class RewardController {

    private final RewardService rewardService;
    private final QuestService questService;


    @GetMapping
    public List<RewardDTO> getAllRewards() {
        List<Reward> rewards = rewardService.getAllRewards();
        return DTOConverter.convertToRewardDTOList(rewards);
    }

    @GetMapping("/{id}")
    public RewardDTO getRewardById(@PathVariable Long id) {
        Reward reward = rewardService.getRewardById(id);
        return DTOConverter.convertToRewardDTO(reward);
    }

    @PostMapping
    public RewardDTO createReward(@RequestBody Reward reward, @RequestParam Long questId) {
        Quest quest = questService.getQuestById(questId);
        reward.getQuests().add(quest);
        Reward createdReward = rewardService.createReward(reward);
        return DTOConverter.convertToRewardDTO(createdReward);
    }

    @PutMapping("/{id}")
    public RewardDTO updateReward(@PathVariable Long id, @RequestBody Reward rewardDetails) {
        Reward updatedReward = rewardService.updateReward(id, rewardDetails);
        return DTOConverter.convertToRewardDTO(updatedReward);
    }
}

