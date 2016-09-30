package com.github.si1en7ium.socialgym.ui.main;

import com.github.si1en7ium.socialgym.models.EventModel;
import com.github.si1en7ium.socialgym.ui.base.MvpView;

import java.util.List;


public interface MainMvpView extends MvpView {
    void showEvents(List<EventModel> events);

    void showError();
}
