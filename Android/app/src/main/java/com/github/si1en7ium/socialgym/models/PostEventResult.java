package com.github.si1en7ium.socialgym.models;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class PostEventResult {
    public abstract String result();

    public static Builder builder() {
        return new AutoValue_PostEventResult.Builder();
    }

    public static TypeAdapter<PostEventResult> typeAdapter(Gson gson) {
        return new AutoValue_PostEventResult.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder result(String result);

        public abstract PostEventResult build();
    }
}
