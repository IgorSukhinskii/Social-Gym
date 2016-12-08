package com.github.si1en7ium.socialgym.ui.authentication.entry;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.si1en7ium.socialgym.R;
import com.github.si1en7ium.socialgym.ui.authentication.AuthenticationActivity;
import com.github.si1en7ium.socialgym.ui.authentication.login.LoginFragment;
import com.github.si1en7ium.socialgym.ui.authentication.register.RegisterFragment;
import com.github.si1en7ium.socialgym.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EntryFragment extends BaseFragment {
    @BindView(R.id.button_register) Button registerButton;
    @BindView(R.id.button_login) Button loginButton;

    public EntryFragment() {
    }

    public static EntryFragment newInstance() {
        return new EntryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_enter_screen, container, false);
        ButterKnife.bind(this, view);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AuthenticationActivity) getActivity()).switchToFragment(RegisterFragment.newInstance());
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AuthenticationActivity) getActivity()).switchToFragment(LoginFragment.newInstance());
            }
        });

        return view;
    }
}
