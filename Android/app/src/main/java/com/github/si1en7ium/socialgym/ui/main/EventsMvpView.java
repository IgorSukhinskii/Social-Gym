package com.github.si1en7ium.socialgym.ui.main;


import com.github.si1en7ium.socialgym.models.Event;
import com.github.si1en7ium.socialgym.ui.base.MvpView;

import java.util.List;

public interface EventsMvpView extends MvpView {
    void showEvents(List<Event> events);

    void showError();
}
