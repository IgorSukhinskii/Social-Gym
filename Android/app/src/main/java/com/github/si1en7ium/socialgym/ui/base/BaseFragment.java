package com.github.si1en7ium.socialgym.ui.base;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.github.si1en7ium.socialgym.SocialGymApplication;
import com.github.si1en7ium.socialgym.injection.component.ConfigPersistentFragmentComponent;
import com.github.si1en7ium.socialgym.injection.component.DaggerConfigPersistentFragmentComponent;
import com.github.si1en7ium.socialgym.injection.component.FragmentComponent;
import com.github.si1en7ium.socialgym.injection.module.FragmentModule;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import timber.log.Timber;

public class BaseFragment extends Fragment {
    private static final String KEY_FRAGMENT_ID = "KEY_FRAGMENT_ID";
    private static final AtomicLong NEXT_ID = new AtomicLong(0);
    private static final Map<Long, ConfigPersistentFragmentComponent> componentsMap = new HashMap<>();

    private FragmentComponent fragmentComponent;
    private long fragmentId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentId = savedInstanceState != null ?
                savedInstanceState.getLong(KEY_FRAGMENT_ID) :
                NEXT_ID.getAndIncrement();

        ConfigPersistentFragmentComponent configPersistentFragmentComponent;
        if (!componentsMap.containsKey(fragmentId)) {
            Timber.i("Creating new ConfigPersistentFragmentComponent id=%d", fragmentId);
            configPersistentFragmentComponent = DaggerConfigPersistentFragmentComponent.builder()
                    .applicationComponent(SocialGymApplication.get(this.getContext()).getComponent())
                    .build();
            componentsMap.put(fragmentId, configPersistentFragmentComponent);
        } else {
            Timber.i("Reusing ConfigPersistentFragmentComponent id=%d", fragmentId);
            configPersistentFragmentComponent = componentsMap.get(fragmentId);
        }

        fragmentComponent = configPersistentFragmentComponent.fragmentComponent(new FragmentModule(this));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_FRAGMENT_ID, fragmentId);
    }

    @Override
    public void onDestroy() {
        if (getActivity().isChangingConfigurations()) {
            Timber.i("Clearing ConfigPersistentFragmentComponent id=%d", fragmentId);
            componentsMap.remove(fragmentId);
        }
        super.onDestroy();
    }

    public FragmentComponent fragmentComponent() {
        return fragmentComponent;
    }
}
