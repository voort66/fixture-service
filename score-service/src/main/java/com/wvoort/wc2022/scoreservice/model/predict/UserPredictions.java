package com.wvoort.wc2022.scoreservice.model.predict;

import com.wvoort.wc2022.scoreservice.model.ScoreCalculation;
import lombok.Data;

@Data
public class UserPredictions implements Comparable<UserPredictions>{

    private ScoreCalculation scoreCalculation;

    private String user;

    private transient Predictions predictions;

    private Integer calculatedScore;

    private Integer rank;


    UserPredictions(String user) {
        this.user = user;
        predictions = new Predictions();
    }

    void addPrediction(Prediction prediction) {
        predictions.add(prediction);
    }


    public Integer getCalculatedScore(ScoreCalculation scoreCalculation) {
        if(calculatedScore == null) {
            calculatedScore = scoreCalculation.calculateScore(predictions);
        }
        return calculatedScore;

    }

    @Override
    public int compareTo(UserPredictions userPredictions) {
        return userPredictions.getCalculatedScore().compareTo(getCalculatedScore());
    }
}
