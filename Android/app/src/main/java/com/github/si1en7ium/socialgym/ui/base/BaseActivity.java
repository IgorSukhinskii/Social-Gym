package com.github.si1en7ium.socialgym.ui.base;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.si1en7ium.socialgym.SocialGymApplication;
import com.github.si1en7ium.socialgym.injection.component.ActivityComponent;
import com.github.si1en7ium.socialgym.injection.component.ConfigPersistentActivityComponent;
import com.github.si1en7ium.socialgym.injection.component.DaggerConfigPersistentActivityComponent;
import com.github.si1en7ium.socialgym.injection.module.ActivityModule;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import timber.log.Timber;

/**
 * Abstract activity that every other Activity in this application must implement. It handles
 * creation of Dagger components and makes sure that instances of ConfigPersistentActivityComponent survive
 * across configuration changes.
 */
public class BaseActivity extends AppCompatActivity {

    private static final String KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID";
    private static final AtomicLong NEXT_ID = new AtomicLong(0);
    private static final Map<Long, ConfigPersistentActivityComponent> componentsMap = new HashMap<>();

    private ActivityComponent activityComponent;
    private long activityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create the ActivityComponent and reuses cached ConfigPersistentActivityComponent if this is
        // being called after a configuration change.
        activityId = savedInstanceState != null ?
                savedInstanceState.getLong(KEY_ACTIVITY_ID) :
                NEXT_ID.getAndIncrement();
        ConfigPersistentActivityComponent configPersistentActivityComponent;
        if (!componentsMap.containsKey(activityId)) {
            Timber.i("Creating new ConfigPersistentActivityComponent id=%d", activityId);
            configPersistentActivityComponent = DaggerConfigPersistentActivityComponent.builder()
                    .applicationComponent(SocialGymApplication.get(this).getComponent())
                    .build();
            componentsMap.put(activityId, configPersistentActivityComponent);
        } else {
            Timber.i("Reusing ConfigPersistentActivityComponent id=%d", activityId);
            configPersistentActivityComponent = componentsMap.get(activityId);
        }
        activityComponent = configPersistentActivityComponent.activityComponent(new ActivityModule(this));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_ACTIVITY_ID, activityId);
    }

    @Override
    protected void onDestroy() {
        if (!isChangingConfigurations()) {
            Timber.i("Clearing ConfigPersistentActivityComponent id=%d", activityId);
            componentsMap.remove(activityId);
        }
        super.onDestroy();
    }

    public ActivityComponent activityComponent() {
        return activityComponent;
    }

}
