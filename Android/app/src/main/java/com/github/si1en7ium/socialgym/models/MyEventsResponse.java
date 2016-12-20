package com.github.si1en7ium.socialgym.models;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

@AutoValue
public abstract class MyEventsResponse {
    public static TypeAdapter<MyEventsResponse> typeAdapter(Gson gson) {
        return new AutoValue_MyEventsResponse.GsonTypeAdapter(gson);
    }

    public abstract List<Event> interested();

    public abstract List<Event> going();

    public abstract List<Event> myEvents();
}
