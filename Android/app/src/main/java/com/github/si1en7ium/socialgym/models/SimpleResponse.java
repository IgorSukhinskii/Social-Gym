package com.github.si1en7ium.socialgym.models;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class SimpleResponse {
    public static Builder builder() {
        return new AutoValue_SimpleResponse.Builder();
    }

    public static TypeAdapter<SimpleResponse> typeAdapter(Gson gson) {
        return new AutoValue_SimpleResponse.GsonTypeAdapter(gson);
    }

    public abstract String result();

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder result(String result);

        public abstract SimpleResponse build();
    }
}
