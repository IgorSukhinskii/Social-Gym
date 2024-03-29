package com.github.si1en7ium.socialgym.injection.component;

import com.github.si1en7ium.socialgym.injection.ConfigPersistent;
import com.github.si1en7ium.socialgym.injection.module.ActivityModule;
import com.github.si1en7ium.socialgym.ui.base.BaseActivity;

import dagger.Component;

/**
 * A dagger component that will live during the lifecycle of an Activity but it won't
 * be destroy during configuration changes. Check {@link BaseActivity} to see how this components
 * survives configuration changes.
 * Use the {@link ConfigPersistent} scope to annotate dependencies that need to survive
 * configuration changes (for example Presenters).
 */
@ConfigPersistent
@Component(dependencies = ApplicationComponent.class)
public interface ConfigPersistentActivityComponent {

    ActivityComponent activityComponent(ActivityModule activityModule);

}
