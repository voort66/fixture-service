package com.wvoort.wc2022.fixtureservice.controller;

import com.wvoort.wc2022.fixtureservice.service.FixtureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class FixutreWebController {

    @Autowired
    private FixtureService fixtureService;

    @GetMapping(value = "/fixtures/view")
    public String displayGames(final Model model) {
        log.info("Getting all games from repository");
        model.addAttribute("allFixtures", fixtureService.getFixtures() );

        return "fixtures";

    }

    @GetMapping(value = "/fixtures/view/today")
    public String displayGamesToday(final Model model) {
        log.info("Getting todays games from repository");
        model.addAttribute("allFixtures", fixtureService.getFixtures() );

        return "fixtures_today";

    }

    @GetMapping(value = "/fixtures/view/live")
    public String displayLiveGames(final Model model) {
        log.info("Getting live games from repository");
        model.addAttribute("allFixtures", fixtureService.getLiveFixtures() );

        return "fixtures_live";

    }





}
