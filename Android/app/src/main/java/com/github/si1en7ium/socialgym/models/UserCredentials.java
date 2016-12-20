package com.github.si1en7ium.socialgym.models;


import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class UserCredentials {
    public static Builder builder() {
        return new AutoValue_UserCredentials.Builder();
    }

    public static TypeAdapter<UserCredentials> typeAdapter(Gson gson) {
        return new AutoValue_UserCredentials.GsonTypeAdapter(gson);
    }

    public abstract int id();

    public abstract String token();

    public abstract Builder toBuilder();

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(int id);

        public abstract Builder token(String token);

        public abstract UserCredentials build();
    }
}
