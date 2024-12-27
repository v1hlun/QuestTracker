package com.example.kurs.Controller;

import com.example.kurs.Service.QuestService;
import com.example.kurs.Service.RewardService;
import com.example.kurs.entyty.Quest;
import com.example.kurs.entyty.Reward;
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
    public List<Reward> getAllRewards() {
        return rewardService.getAllRewards();
    }

    @GetMapping("/{id}")
    public Reward getRewardById(@PathVariable Long id) {
        return rewardService.getRewardById(id);
    }

    @PostMapping
    public Reward createReward(@RequestBody Reward reward,@RequestParam Long questId) {
        Quest quest = questService.getQuestById(questId);
        reward.getQuests().add(quest);
        return rewardService.createReward(reward);
    }

    @PutMapping("/{id}")
    public Reward updateReward(@PathVariable Long id, @RequestBody Reward rewardDetails) {
        return rewardService.updateReward(id, rewardDetails);
    }

}

