package com.github.si1en7ium.socialgym.ui.authentication;


import android.content.Intent;
import android.os.Bundle;

import com.github.si1en7ium.socialgym.R;
import com.github.si1en7ium.socialgym.ui.authentication.entry.EntryFragment;
import com.github.si1en7ium.socialgym.ui.base.BaseActivity;
import com.github.si1en7ium.socialgym.ui.base.BaseFragment;
import com.github.si1en7ium.socialgym.ui.main.MainActivity;

import javax.inject.Inject;

public class AuthenticationActivity extends BaseActivity implements AuthenticationMvpView {
    @Inject AuthenticationPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        presenter.attachView(this);
        presenter.checkUserToken();
        setContentView(R.layout.authentication_activity);
    }

    public void switchToFragment(BaseFragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_placeholder, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToMainScreen() {
        // switch activity to main
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void goToAuth() {
        switchToFragment(EntryFragment.newInstance());
    }
}
