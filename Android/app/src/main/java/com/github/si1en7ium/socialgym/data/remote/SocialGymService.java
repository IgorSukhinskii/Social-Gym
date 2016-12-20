package com.github.si1en7ium.socialgym.data.remote;


import com.github.si1en7ium.socialgym.data.SocialGymTypeAdapterFactory;
import com.github.si1en7ium.socialgym.models.Authenticated;
import com.github.si1en7ium.socialgym.models.Event;
import com.github.si1en7ium.socialgym.models.LoginRequest;
import com.github.si1en7ium.socialgym.models.LoginResponse;
import com.github.si1en7ium.socialgym.models.MyEventsResponse;
import com.github.si1en7ium.socialgym.models.RegistrationRequest;
import com.github.si1en7ium.socialgym.models.SimpleResponse;
import com.github.si1en7ium.socialgym.models.UserCredentials;
import com.github.si1en7ium.socialgym.util.datetime.ParcelableDateTime;
import com.github.si1en7ium.socialgym.util.datetime.ParcelableDuration;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.ReadableDateTime;
import org.joda.time.ReadableDuration;

import java.io.IOException;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface SocialGymService {
//    String ENDPOINT = "http://10.0.2.2:5000";
    String ENDPOINT = "http://188.166.73.100:8080/";

    @POST("events/get")
    Observable<List<Event>> getEvents(@Body Authenticated<String> query);

    @POST("events/post")
    Observable<SimpleResponse> postEvent(@Body Authenticated<Event> event);

    @POST("register")
    Observable<SimpleResponse> register(@Body RegistrationRequest registrationRequest);

    @POST("login")
    Observable<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("check")
    Observable<SimpleResponse> checkToken(@Body UserCredentials userCredentials);

    @POST("interested")
    Observable<SimpleResponse> interestedInEvent(@Body Authenticated<Integer> eventId);

    @POST("going")
    Observable<SimpleResponse> goingToEvent(@Body Authenticated<Integer> eventId);

    @POST("events/my")
    Observable<MyEventsResponse> getMyEvents(@Body Authenticated<String> param);

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
                    .registerTypeAdapter(ParcelableDateTime.class, new TypeAdapter<ParcelableDateTime>() {
                        @Override
                        public void write(JsonWriter out, ParcelableDateTime value) throws IOException {
                            if (value == null) {
                                out.nullValue();
                                return;
                            }
                            String s = value.val.toString();
                            out.value(s);
                        }

                        @Override
                        public ParcelableDateTime read(JsonReader in) throws IOException {
                            if (in.peek() == JsonToken.NULL) {
                                in.nextNull();
                                return null;
                            }
                            String s = in.nextString();
                            return new ParcelableDateTime(DateTime.parse(s));
                        }
                    })
                    .registerTypeAdapter(ParcelableDuration.class, new TypeAdapter<ParcelableDuration>() {
                        @Override
                        public void write(JsonWriter out, ParcelableDuration value) throws IOException {
                            if (value == null) {
                                out.nullValue();
                                return;
                            }
                            String s = value.val.toString();
                            out.value(s);
                        }

                        @Override
                        public ParcelableDuration read(JsonReader in) throws IOException {
                            if (in.peek() == JsonToken.NULL) {
                                in.nextNull();
                                return null;
                            }
                            String s = in.nextString();
                            return new ParcelableDuration(Duration.parse(s));
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
