package com.github.si1en7ium.socialgym.ui.main.profile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.si1en7ium.socialgym.ui.base.BaseFragment;
import com.github.si1en7ium.socialgym.ui.main.BaseMainFragment;
import com.github.si1en7ium.socialgym.ui.main.MainActivity;


import javax.inject.Inject;

import butterknife.ButterKnife;

import static com.github.si1en7ium.socialgym.R.layout.main_profile_fragment;



public class ProfileFragment extends BaseMainFragment {
    @Inject
    ProfilePresenter profilePresenter;


    public ProfileFragment() {

        // Required empty public constructor
    }


    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(main_profile_fragment, container, false);

        ButterKnife.bind(this, view);
       return view;
    }

}





