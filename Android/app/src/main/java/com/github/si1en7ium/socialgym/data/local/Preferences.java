package com.github.si1en7ium.socialgym.data.local;


import android.content.Context;
import android.content.SharedPreferences;

import com.github.si1en7ium.socialgym.injection.ApplicationContext;

import javax.inject.Inject;

public final class Preferences {
    private static final String PREFERENCES_NAME = "SocialGymPreferences";
    private static final String USER_ID = "userId";
    private static final String USER_TOKEN = "userToken";
    private final Context context;
    private final SharedPreferences settings;
    private int userId;
    private String userToken;

    @Inject Preferences(@ApplicationContext Context context) {
        this.context = context;
        settings = context.getSharedPreferences(PREFERENCES_NAME, 0);
        userId = settings.getInt(USER_ID, 0);
        userToken = settings.getString(USER_TOKEN, "");
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
        settings.edit().putInt(USER_ID, userId).apply();
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
        settings.edit().putString(USER_TOKEN, userToken).apply();
    }
}
