package com.github.si1en7ium.socialgym.models;


import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class RegistrationRequest {
    public static Builder builder() {
        return new AutoValue_RegistrationRequest.Builder();
    }

    public static TypeAdapter<RegistrationRequest> typeAdapter(Gson gson) {
        return new AutoValue_RegistrationRequest.GsonTypeAdapter(gson);
    }

    public abstract String name();

    public abstract String email();

    public abstract String password();

    public abstract Builder toBuilder();

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder name(String name);

        public abstract Builder email(String email);

        public abstract Builder password(String password);

        public abstract RegistrationRequest build();
    }
}
