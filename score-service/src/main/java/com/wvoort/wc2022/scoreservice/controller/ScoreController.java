package com.wvoort.wc2022.scoreservice.controller;

import com.wvoort.wc2022.scoreservice.model.predict.UserPredictions;
import com.wvoort.wc2022.scoreservice.model.predict.UserScore;
import com.wvoort.wc2022.scoreservice.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScoreController {

    @Autowired
    private ScoreService scoreService;


    @GetMapping("/score")
    public UserScore getScore(Authentication authentication) {
        return scoreService.getUserScore(authentication);

    }

    @GetMapping("/score/top5")
    public List<UserScore> getTop5() {
        return scoreService.getTop5();
    }





}
