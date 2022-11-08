package com.wvoort.wc2022.wc2022app.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.Instant;

public class InstantSerializer implements JsonSerializer<Instant> {

    @Override
    public JsonElement serialize(Instant instant, Type srcType, JsonSerializationContext context) {
        return new JsonPrimitive(instant.toString());
    }
}
