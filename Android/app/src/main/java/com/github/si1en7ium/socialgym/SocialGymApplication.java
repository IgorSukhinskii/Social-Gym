package com.github.si1en7ium.socialgym;

import android.app.Application;
import android.content.Context;

import com.github.si1en7ium.socialgym.injection.component.ApplicationComponent;
import com.github.si1en7ium.socialgym.injection.component.DaggerApplicationComponent;
import com.github.si1en7ium.socialgym.injection.module.ApplicationModule;

import net.danlew.android.joda.JodaTimeAndroid;

import timber.log.Timber;

public class SocialGymApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override public void onCreate() {
        super.onCreate();

        JodaTimeAndroid.init(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static SocialGymApplication get(Context context) {
        return (SocialGymApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (applicationComponent == null) {
            applicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return applicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }
}
