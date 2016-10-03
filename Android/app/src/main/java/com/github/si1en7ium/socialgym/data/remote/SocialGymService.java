package com.github.si1en7ium.socialgym.data.remote;


import com.github.si1en7ium.socialgym.data.SocialGymTypeAdapterFactory;
import com.github.si1en7ium.socialgym.models.Event;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

public interface SocialGymService {
    String ENDPOINT = "https://api.socialgym.io";

    @GET("events")
    Observable<List<Event>> getEvents();

    class Creator {
        public static SocialGymService newSocialGymService() {
            Gson gson = new GsonBuilder()
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
