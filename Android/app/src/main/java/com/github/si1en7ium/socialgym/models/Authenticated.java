package com.github.si1en7ium.socialgym.models;

import com.github.si1en7ium.socialgym.data.local.Preferences;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

@AutoValue
public abstract class Authenticated<Model> {
    public static <Model> Builder<Model> builder() {
        return new AutoValue_Authenticated.Builder<Model>();
    }

    public static <Model> TypeAdapter<Authenticated<Model>> typeAdapter(Gson gson) {
        return new AutoValue_Authenticated.GsonTypeAdapter<>(gson, new TypeToken<Authenticated<Model>>() {
        });
    }

    public static <Model> Authenticated<Model> fromPreferences(Preferences prefs, Model model) {
        return Authenticated.<Model>builder()
                .auth(prefs.getCredentials())
                .model(model)
                .build();
    }

    public abstract UserCredentials auth();

    public abstract Model model();

    public abstract Builder<Model> toBuilder();

    @AutoValue.Builder
    public abstract static class Builder<Model> {
        public abstract Builder<Model> auth(UserCredentials auth);

        public abstract Builder<Model> model(Model model);

        public abstract Authenticated<Model> build();
    }
}
