package com.github.si1en7ium.socialgym.injection.component;

import com.github.si1en7ium.socialgym.injection.PerFragment;
import com.github.si1en7ium.socialgym.injection.module.FragmentModule;
import com.github.si1en7ium.socialgym.ui.authentication.login.LoginFragment;
import com.github.si1en7ium.socialgym.ui.authentication.register.RegisterFragment;
import com.github.si1en7ium.socialgym.ui.main.add_event.AddEventFragment;
import com.github.si1en7ium.socialgym.ui.main.events.EventsFragment;
import com.github.si1en7ium.socialgym.ui.main.my_events.MyEventsFragment;
import com.github.si1en7ium.socialgym.ui.main.profile.EditProfileFragment;
import com.github.si1en7ium.socialgym.ui.main.profile.ProfileFragment;
import com.github.si1en7ium.socialgym.ui.main.settings.SettingsFragment;
import com.github.si1en7ium.socialgym.ui.main.view_event.ViewEventFragment;

import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(EventsFragment fragment);

    void inject(AddEventFragment fragment);

    void inject(ViewEventFragment fragment);

    void inject(ProfileFragment fragment);

    void inject(RegisterFragment fragment);

    void inject(LoginFragment fragment);

    void inject(EditProfileFragment fragment);

    void inject(SettingsFragment fragment);

    void inject(MyEventsFragment fragment);
}
