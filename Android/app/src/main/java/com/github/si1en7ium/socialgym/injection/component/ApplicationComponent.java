package com.github.si1en7ium.socialgym.injection.component;

import android.app.Application;
import android.content.Context;

import com.github.si1en7ium.socialgym.injection.ApplicationContext;
import com.github.si1en7ium.socialgym.injection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    @ApplicationContext
    Context context();

    Application application();
}
