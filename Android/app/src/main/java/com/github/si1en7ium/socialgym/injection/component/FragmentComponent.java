package com.github.si1en7ium.socialgym.injection.component;

import com.github.si1en7ium.socialgym.injection.PerFragment;
import com.github.si1en7ium.socialgym.injection.module.FragmentModule;
import com.github.si1en7ium.socialgym.ui.main.add_event.AddEventFragment;
import com.github.si1en7ium.socialgym.ui.main.events.EventsFragment;

import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(EventsFragment fragment);

    void inject(AddEventFragment fragment);
}
