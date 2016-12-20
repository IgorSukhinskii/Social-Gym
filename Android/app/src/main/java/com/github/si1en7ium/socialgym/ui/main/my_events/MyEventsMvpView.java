package com.github.si1en7ium.socialgym.ui.main.my_events;


import com.github.si1en7ium.socialgym.models.MyEventsResponse;
import com.github.si1en7ium.socialgym.ui.base.MvpView;

public interface MyEventsMvpView extends MvpView {
    void showEvents(MyEventsResponse events);
}
