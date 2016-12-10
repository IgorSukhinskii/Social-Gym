package com.github.si1en7ium.socialgym.ui.main.profile;

import com.github.si1en7ium.socialgym.ui.base.BasePresenter;

import javax.inject.Inject;


class EditProfilePresenter extends BasePresenter<EditProfileMvpView> {
    int year;
    int month;
    int day;
    @Inject
    EditProfilePresenter() {
    }
    void setDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

}
