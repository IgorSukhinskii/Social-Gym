package com.github.si1en7ium.socialgym.ui.authentication.register;


import com.github.si1en7ium.socialgym.ui.base.MvpView;

public interface RegisterMvpView extends MvpView {
    void showError(String errorMessage);

    void proceedToLogin();
}
