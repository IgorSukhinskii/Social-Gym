package com.github.si1en7ium.socialgym.ui.main.my_events;

import com.github.si1en7ium.socialgym.data.remote.SocialGymService;
import com.github.si1en7ium.socialgym.models.Event;
import com.github.si1en7ium.socialgym.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;


class MyEventsPresenter extends BasePresenter<MyEventsMvpView> {
    private SocialGymService api;

    @Inject
    MyEventsPresenter(SocialGymService api) {
        this.api = api;
    }

    void loadEvents() {
        api.getEvents()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Event>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error loading events");
                    }

                    @Override
                    public void onNext(List<Event> events) {
                        getMvpView().showEvents(events);
                    }
                });
    }
}
