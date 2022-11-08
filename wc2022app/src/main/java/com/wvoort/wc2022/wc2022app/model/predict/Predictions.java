package com.wvoort.wc2022.wc2022app.model.predict;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wvoort.wc2022.wc2022app.model.InstantDeserializer;
import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
public class Predictions {


    private List<Prediction> predictions = new ArrayList<>();


    public static Predictions fromJson(String jsonString) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Instant.class, new InstantDeserializer());
        return gsonBuilder.create().fromJson(jsonString, Predictions.class);
    }

}
