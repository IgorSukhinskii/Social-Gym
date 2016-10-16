package com.github.si1en7ium.socialgym.injection.component;


import com.github.si1en7ium.socialgym.injection.ConfigPersistent;
import com.github.si1en7ium.socialgym.injection.module.FragmentModule;

import dagger.Component;

/**
 * The same as {@link ConfigPersistentActivityComponent}, but for Fragment instead of Activity
 */
@ConfigPersistent
@Component(dependencies = ApplicationComponent.class)
public interface ConfigPersistentFragmentComponent {

    FragmentComponent fragmentComponent(FragmentModule fragmentModule);

}
