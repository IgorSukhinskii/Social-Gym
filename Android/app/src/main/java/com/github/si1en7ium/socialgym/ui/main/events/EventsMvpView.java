package com.github.si1en7ium.socialgym.ui.main.events;


import com.github.si1en7ium.socialgym.models.Event;
import com.github.si1en7ium.socialgym.ui.base.MvpView;

import java.util.List;

interface EventsMvpView extends MvpView {
    void showEvents(List<Event> events);

    void showError();
}
