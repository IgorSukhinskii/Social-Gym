package com.github.si1en7ium.socialgym.ui.main;

import com.github.si1en7ium.socialgym.injection.ConfigPersistent;
import com.github.si1en7ium.socialgym.ui.base.BasePresenter;

import javax.inject.Inject;


@ConfigPersistent
public class MainPresenter extends BasePresenter<MainMvpView> {
    @Inject
    MainPresenter() {
    }

    void showAddEventScreen() {
        getMvpView().switchToFragment(AddEventFragment.newInstance());
    }
}
