package com.github.si1en7ium.socialgym.ui.authentication;


import com.github.si1en7ium.socialgym.data.local.Preferences;
import com.github.si1en7ium.socialgym.data.remote.SocialGymService;
import com.github.si1en7ium.socialgym.models.CheckTokenRequest;
import com.github.si1en7ium.socialgym.models.SimpleResponse;
import com.github.si1en7ium.socialgym.ui.base.BasePresenter;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class AuthenticationPresenter extends BasePresenter<AuthenticationMvpView> {
    private final SocialGymService api;
    private final Preferences prefs;

    public @Inject AuthenticationPresenter(SocialGymService api, Preferences prefs) {
        this.api = api;
        this.prefs = prefs;
    }

    public void checkUserToken() {
        CheckTokenRequest request = CheckTokenRequest.builder()
                .id(prefs.getUserId())
                .token(prefs.getUserToken())
                .build();
        Timber.i("sending check token request: %s", request.toString());
        api.checkToken(request)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<SimpleResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e("error checking existing token: %s", e.getMessage());
                        getMvpView().goToAuth();
                    }

                    @Override
                    public void onNext(SimpleResponse simpleResponse) {
                        if (simpleResponse.result().equals("success")) {
                            getMvpView().goToMainScreen();
                        } else {
                            getMvpView().goToAuth();
                        }
                    }
                });
    }
}
