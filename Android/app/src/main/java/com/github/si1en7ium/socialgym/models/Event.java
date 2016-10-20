package com.github.si1en7ium.socialgym.models;

import android.os.Parcelable;

import com.github.si1en7ium.socialgym.util.datetime.ParcelableDateTime;
import com.github.si1en7ium.socialgym.util.datetime.ParcelableDuration;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import org.joda.time.ReadableDateTime;
import org.joda.time.ReadableDuration;

@AutoValue
public abstract class Event implements Parcelable {
    public abstract String title();
    public abstract String description();
    public abstract User creator();
    public abstract SportKind sportKind();
    @SerializedName("dateTime") protected abstract ParcelableDateTime _dateTime();
    @SerializedName("duration") protected abstract ParcelableDuration _duration();
    public abstract String location();
    public abstract String imageUrl();

    public ReadableDateTime dateTime() {
        return _dateTime().val;
    }
    public ReadableDuration duration() {
        return _duration().val;
    }

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
        protected abstract Builder _dateTime(ParcelableDateTime _dateTime);
        protected abstract Builder _duration(ParcelableDuration _duration);
        public abstract Builder location(String location);
        public abstract Builder imageUrl(String imageUrl);

        public Builder dateTime(ReadableDateTime dateTime) {
            return _dateTime(new ParcelableDateTime(dateTime));
        }

        public Builder duration(ReadableDuration duration) {
            return _duration(new ParcelableDuration(duration));
        }

        public abstract Event build();
    }
}
