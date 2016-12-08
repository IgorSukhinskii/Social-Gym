package com.github.si1en7ium.socialgym.ui.authentication.register;


import android.support.annotation.Nullable;

import com.github.si1en7ium.socialgym.data.remote.SocialGymService;
import com.github.si1en7ium.socialgym.models.RegistrationRequest;
import com.github.si1en7ium.socialgym.models.SimpleResponse;
import com.github.si1en7ium.socialgym.ui.base.BasePresenter;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RegisterPresenter extends BasePresenter<RegisterMvpView> {
    private final SocialGymService api;
    private String name;
    private String email;
    private String password;
    private String confirmation;

    @Inject
    RegisterPresenter(SocialGymService api) {
        this.api = api;
    }

    @Nullable
    public String validate() {
        if (name == null || name.equals("")) {
            return "Name should not be empty";
        }
        if (email == null || email.equals("")) {
            return "Email should not be null";
        }
        if (password == null || password.equals("")) {
            return "Password should not be null";
        }
        if (!password.equals(confirmation)) {
            return "Passwords should match";
        }
        return null;
    }

    public void register() {
        String error = validate();
        if (error != null) {
            getMvpView().showError(error);
            return;
        }
        RegistrationRequest request = RegistrationRequest.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
        api.register(request)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<SimpleResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().showError(e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(SimpleResponse simpleResponse) {
                        if (simpleResponse.result().equals("success")) {
                            getMvpView().proceedToLogin();
                        } else {
                            getMvpView().showError("Registration error");
                        }
                    }
                });
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }
}
