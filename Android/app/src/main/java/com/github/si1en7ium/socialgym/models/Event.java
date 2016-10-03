package com.github.si1en7ium.socialgym.models;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.ReadableDateTime;
import org.joda.time.ReadableDuration;

@AutoValue
public abstract class Event {
    public abstract String title();
    public abstract String description();
    public abstract User creator();
    public abstract SportKind sportKind();
    public abstract ReadableDateTime dateTime();
    public abstract ReadableDuration duration();

    public abstract Builder toBuilder();

    public static Builder builder() {
        return new AutoValue_Event.Builder()
                .sportKind(SportKind.OTHER); // By default sport kind is always OTHER
    }

    public static TypeAdapter<Event> typeAdapter(Gson gson) {
        return new AutoValue_Event.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder title(String title);
        public abstract Builder description(String description);
        public abstract Builder creator(User creator);
        public abstract Builder sportKind(SportKind sportKind);
        public abstract Builder dateTime(ReadableDateTime dateTime);
        public abstract Builder duration(ReadableDuration duration);

        public abstract Event build();
    }
}
