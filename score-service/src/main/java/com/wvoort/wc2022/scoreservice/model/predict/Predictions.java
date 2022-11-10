package com.wvoort.wc2022.scoreservice.model.predict;

import com.google.gson.GsonBuilder;
import com.wvoort.wc2022.scoreservice.model.InstantDeserializer;
import lombok.Data;
import org.apache.catalina.User;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class Predictions {


    private List<Prediction> predictions = new ArrayList<>();


    public List<UserPredictions> initUserPredictions() {
        final Map<String, UserPredictions> userPredictionsMap = new HashMap<>();
        predictions.stream().forEach(prediction -> {
            UserPredictions userPredictions;
            if(userPredictionsMap.containsKey(prediction.getUserName())) {
                userPredictions = userPredictionsMap.get(prediction.getUserName());
            } else {
                userPredictions = new UserPredictions(prediction.getUserName());
                userPredictionsMap.put(prediction.getUserName(), userPredictions);
            }
            userPredictions.addPrediction(prediction);
        });

        return userPredictionsMap.values().stream().collect(Collectors.toList());
    }

    void add(Prediction prediction) {
        predictions.add(prediction);
    }


    public static Predictions fromJson(String jsonString) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Instant.class, new InstantDeserializer());
        Predictions predictions = gsonBuilder.create().fromJson(jsonString, Predictions.class);


        return predictions;
    }

    public Prediction getPredictionByMatchId(Long matchId) {
        return predictions.stream().filter(p -> p.getMatchId().equals(matchId)).findFirst().orElse(null);
    }


}
