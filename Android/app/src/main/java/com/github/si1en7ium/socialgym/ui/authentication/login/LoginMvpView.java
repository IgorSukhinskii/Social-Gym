package com.github.si1en7ium.socialgym.ui.authentication.login;

import com.github.si1en7ium.socialgym.ui.base.MvpView;


public interface LoginMvpView extends MvpView {
    void showError(String error);

    void goToMainScreen();
}
