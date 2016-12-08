package com.github.si1en7ium.socialgym.models;


import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class LoginResponse {
    public static Builder builder() {
        return new AutoValue_LoginResponse.Builder();
    }

    public static TypeAdapter<LoginResponse> typeAdapter(Gson gson) {
        return new AutoValue_LoginResponse.GsonTypeAdapter(gson);
    }

    public abstract String result();

    public abstract String token();

    public abstract int id();

    public abstract Builder toBuilder();

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder result(String result);

        public abstract Builder token(String token);

        public abstract Builder id(int id);

        public abstract LoginResponse build();
    }
}
