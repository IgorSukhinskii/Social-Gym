package com.github.si1en7ium.socialgym.ui.authentication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.si1en7ium.socialgym.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Сергей on 07.12.2016.
 */

public class EnterActivity extends Activity {
    @BindView(R.id.button_create_account) Button btnSignUp;
    @BindView(R.id.button_enter) Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_enter_screen);
        ButterKnife.bind(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentSignUP = new Intent(getApplicationContext(),
                        AuthenticationActivity.class);
                startActivity(intentSignUP);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentSignIN = new Intent(getApplicationContext(),
                        LoginActivity.class);
                startActivity(intentSignIN);
            }
        });
    }
}