package com.github.si1en7ium.socialgym.ui.main.add_event;


import com.github.si1en7ium.socialgym.data.remote.SocialGymService;
import com.github.si1en7ium.socialgym.models.Event;
import com.github.si1en7ium.socialgym.models.SimpleResponse;
import com.github.si1en7ium.socialgym.models.SportKind;
import com.github.si1en7ium.socialgym.models.User;
import com.github.si1en7ium.socialgym.ui.base.BasePresenter;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

class AddEventPresenter extends BasePresenter<AddEventMvpView> {
    private final SocialGymService api;
    private int year;
    private int month;
    private int day;
    private int startHour;
    private int startMinute;
    private String title = "Event";
    private String description;
    private String location;
    private SportKind sportKind;

    @Inject
    public AddEventPresenter(SocialGymService api) {
        this.api = api;
    }

    void setStartTime(int hour, int minute) {
        startHour = hour;
        startMinute = minute;
    }

    void setDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    void setTitle(String title) {
        this.title = title;
    }

    void setDescription(String description) {
        this.description = description;
    }

    void setSportKind(SportKind sportKind) {
        this.sportKind = sportKind;
    }

    void setLocation(String location) {
        this.location = location;
    }

    void postEvent() {
        Event event = Event.builder()
                .creator(User.builder().name("asdf").avatarUrl("").build())
                .description(this.description)
                .imageUrl("")
                .location(this.location)
                .sportKind(this.sportKind)
                .title(this.title)
                .dateTime(new DateTime(year, month+1, day, startHour, startMinute))
                .duration(Duration.standardHours(1))
                .build();

        api.postEvent(event)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<SimpleResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SimpleResponse simpleResponse) {

                    }
                });
    }
}
