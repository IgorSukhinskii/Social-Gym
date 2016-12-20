package com.github.si1en7ium.socialgym.ui.main.my_events;

import com.github.si1en7ium.socialgym.data.local.Preferences;
import com.github.si1en7ium.socialgym.data.remote.SocialGymService;
import com.github.si1en7ium.socialgym.models.Authenticated;
import com.github.si1en7ium.socialgym.models.MyEventsResponse;
import com.github.si1en7ium.socialgym.ui.base.BasePresenter;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


class MyEventsPresenter extends BasePresenter<MyEventsMvpView> {
    private SocialGymService api;
    private Preferences preferences;

    @Inject
    MyEventsPresenter(SocialGymService api, Preferences preferences) {
        this.api = api;
        this.preferences = preferences;
    }

    void loadEvents() {
        api.getMyEvents(Authenticated.fromPreferences(preferences, ""))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<MyEventsResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MyEventsResponse myEventsResponse) {
                        getMvpView().showEvents(myEventsResponse);
                    }
                });
    }
}
