package com.github.si1en7ium.socialgym.data.remote;


import com.github.si1en7ium.socialgym.data.SocialGymTypeAdapterFactory;
import com.github.si1en7ium.socialgym.models.Event;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.ReadableDateTime;
import org.joda.time.ReadableDuration;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

public interface SocialGymService {
    String ENDPOINT = "http://10.0.2.2:5000";

    @GET("events")
    Observable<List<Event>> getEvents();

    class Creator {
        public static SocialGymService newSocialGymService() {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(ReadableDateTime.class, new TypeAdapter<ReadableDateTime>() {
                        @Override
                        public void write(JsonWriter out, ReadableDateTime value) throws IOException {
                            if (value == null) {
                                out.nullValue();
                                return;
                            }
                            String s = value.toString();
                            out.value(s);
                        }

                        @Override
                        public ReadableDateTime read(JsonReader in) throws IOException {
                            if (in.peek() == JsonToken.NULL) {
                                in.nextNull();
                                return null;
                            }
                            String s = in.nextString();
                            return DateTime.parse(s);
                        }
                    })
                    .registerTypeAdapter(ReadableDuration.class, new TypeAdapter<ReadableDuration>() {
                        @Override
                        public void write(JsonWriter out, ReadableDuration value) throws IOException {
                            if (value == null) {
                                out.nullValue();
                                return;
                            }
                            String s = value.toString();
                            out.value(s);
                        }

                        @Override
                        public ReadableDuration read(JsonReader in) throws IOException {
                            if (in.peek() == JsonToken.NULL) {
                                in.nextNull();
                                return null;
                            }
                            String s = in.nextString();
                            return Duration.parse(s);
                        }
                    })
                    .registerTypeAdapterFactory(SocialGymTypeAdapterFactory.create())
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(SocialGymService.class);
        }
    }
}
