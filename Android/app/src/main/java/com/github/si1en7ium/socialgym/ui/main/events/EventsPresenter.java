package com.github.si1en7ium.socialgym.ui.main.events;


import com.github.si1en7ium.socialgym.data.local.Preferences;
import com.github.si1en7ium.socialgym.data.remote.SocialGymService;
import com.github.si1en7ium.socialgym.models.Authenticated;
import com.github.si1en7ium.socialgym.models.Event;
import com.github.si1en7ium.socialgym.ui.base.BasePresenter;
import com.github.si1en7ium.socialgym.util.RxUtil;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class EventsPresenter extends BasePresenter<EventsMvpView> {
    private final SocialGymService api;
    private final Preferences preferences;
    private Subscription eventsSubscription;

    @Inject
    public EventsPresenter(SocialGymService api, Preferences preferences) {
        this.api = api;
        this.preferences = preferences;
    }

    void loadEvents() {
        RxUtil.unsubscribe(eventsSubscription);
        eventsSubscription = api.getEvents(Authenticated.fromPreferences(preferences, ""))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Event>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error loading events");
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(List<Event> events) {
                        getMvpView().showEvents(events);
                    }
                });
    }
}
