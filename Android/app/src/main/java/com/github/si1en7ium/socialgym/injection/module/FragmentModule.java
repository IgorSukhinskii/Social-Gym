package com.github.si1en7ium.socialgym.injection.module;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.github.si1en7ium.socialgym.injection.FragmentContext;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {
    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    public Fragment provideFragment() {
        return fragment;
    }

    @Provides
    @FragmentContext
    public Context provideContext() {
        return fragment.getContext();
    }
}
