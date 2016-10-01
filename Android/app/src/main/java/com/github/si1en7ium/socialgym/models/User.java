package com.github.si1en7ium.socialgym.models;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class User {
    public abstract String name();
    public abstract String avatarUrl();

    public abstract Builder toBuilder();

    public static Builder builder() {
        return new AutoValue_User.Builder();
    }

    public static TypeAdapter<User> typeAdapter(Gson gson) {
        return new AutoValue_User.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    abstract static class Builder {
        public abstract Builder name(String name);
        public abstract Builder avatarUrl(String avatarUrl);

        public abstract User build();
    }
}
