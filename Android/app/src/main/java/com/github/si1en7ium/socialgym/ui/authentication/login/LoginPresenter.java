package com.github.si1en7ium.socialgym.ui.authentication.login;


import android.support.annotation.Nullable;

import com.github.si1en7ium.socialgym.data.remote.SocialGymService;
import com.github.si1en7ium.socialgym.models.LoginRequest;
import com.github.si1en7ium.socialgym.models.LoginResponse;
import com.github.si1en7ium.socialgym.ui.base.BasePresenter;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter<LoginMvpView> {
    private final SocialGymService api;
    private String email;
    private String password;

    @Inject
    public LoginPresenter(SocialGymService api) {
        this.api = api;
    }

    @Nullable
    public String validate() {
        if (email == null || email.equals("")) {
            return "Email must not be empty";
        }
        if (password == null || password.equals("")) {
            return "Password must not be empty";
        }
        return null;
    }

    public void login() {
        String error = validate();
        if (error != null) {
            getMvpView().showError(error);
            return;
        }
        LoginRequest request = LoginRequest.builder()
                .email(email)
                .password(password)
                .build();
        api.login(request)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<LoginResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().goToMainScreen(); // bypass login for now
                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        if (loginResponse.result().equals("success")) {
                            // save auth info
                            // go to main screen
                            getMvpView().goToMainScreen();
                        }
                    }
                });
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
