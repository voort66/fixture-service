package com.wvoort.wc2022.wc2022app.controller;


import com.wvoort.wc2022.wc2022app.model.predict.Predictions;
import com.wvoort.wc2022.wc2022app.services.MatchService;
import com.wvoort.wc2022.wc2022app.services.PredictionService;
import com.wvoort.wc2022.wc2022app.services.StandingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Wc2022Controller {

    @Autowired
    private MatchService matchService;

    @Autowired
    private StandingsService standingsService;

    @Autowired
    private PredictionService predictionService;

    @GetMapping("/")
    public String getApp(Model model) {
        model.addAttribute("module", "home");
        return "layout";
    }

    @GetMapping("/fixtures")
    public String getFixtures(Model model) {
        model.addAttribute("module", "fixtures");
        model.addAttribute("allFixtures", matchService.getMatches());
       return "fixtures";
    }

    @GetMapping("/fixtures/live")
    public String getLiveFixtures(Model model) {
        model.addAttribute("module", "fixtures-live");
        model.addAttribute("allFixtures", matchService.getLiveMatches());
        return "fixtures_live";
    }

    @GetMapping("/fixtures/today")
    public String getTodaysFixtures(Model model) {
        model.addAttribute("module", "fixtures-today");
        model.addAttribute("allFixtures", matchService.getTodaysMatches());
        return "fixtures_today";
    }

    @GetMapping("/standings")
    public String getStandings(Model model) {
        model.addAttribute("module", "standings");
        model.addAttribute("standings", standingsService.getStandings());
        return "standings";
    }

    @GetMapping("/predictions/editable")
    public String getEditablePredictions(Authentication authentication, Model model) {
        model.addAttribute("user", authentication.getName());
        model.addAttribute("module", "predictions-edit");
        model.addAttribute("predictionDto", predictionService.getEditablePredictions(authentication));
        return "predictions_create";
    }

    @GetMapping("/predictions/all")
    public String getAllPredictions(Authentication authentication, Model model) {
        model.addAttribute("user", authentication.getName());
        model.addAttribute("module", "predictions-all");
        model.addAttribute("predictionDto", predictionService.getAllPredictions(authentication));
        return "predictions_view";
    }

    @PostMapping("/predictions/save")
    public String doCreatePredictions(Authentication authentication, @ModelAttribute Predictions predictionDto,
                                      BindingResult bindingResult,
                                      Model model) {
        Predictions savedPredictions = predictionService.savePredictions(authentication, predictionDto);
        model.addAttribute("user", authentication.getName());
        model.addAttribute("module", "predictions-all");
        model.addAttribute("predictionDto", predictionService.getAllPredictions(authentication));
        return "predictions_view";
    }
}
