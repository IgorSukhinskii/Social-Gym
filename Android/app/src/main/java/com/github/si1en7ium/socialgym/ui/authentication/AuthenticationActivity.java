package com.github.si1en7ium.socialgym.ui.authentication;


import android.content.Intent;
import android.os.Bundle;

import com.github.si1en7ium.socialgym.R;
import com.github.si1en7ium.socialgym.ui.authentication.entry.EntryFragment;
import com.github.si1en7ium.socialgym.ui.base.BaseActivity;
import com.github.si1en7ium.socialgym.ui.base.BaseFragment;
import com.github.si1en7ium.socialgym.ui.main.MainActivity;

public class AuthenticationActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authentication_activity);

        switchToFragment(EntryFragment.newInstance());
    }

    public void switchToFragment(BaseFragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_placeholder, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void goToMainScreen() {
        // switch activity to main
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
