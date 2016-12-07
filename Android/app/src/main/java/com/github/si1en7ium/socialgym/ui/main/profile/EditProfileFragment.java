package com.github.si1en7ium.socialgym.ui.main.profile;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.github.si1en7ium.socialgym.R;
import com.github.si1en7ium.socialgym.ui.main.BaseMainFragment;
import android.widget.ListView;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.github.si1en7ium.socialgym.R.layout.main_edit_profile_fragment;


public class EditProfileFragment extends BaseMainFragment {
    @Inject
    ProfilePresenter profilePresenter;
    @BindView(R.id.typeOfSportsSelector) Spinner typeOfSportsSelector;

    public EditProfileFragment() {

        // Required empty public constructor
    }


    public static EditProfileFragment newInstance() {
        return new EditProfileFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(main_edit_profile_fragment, container, false);

        ButterKnife.bind(this, view);
       return view;
    }
    //Попыточка намутить спиннер
    Spinner spinner = (Spinner) findViewById(R.id.typeOfSportsSelector);
    // Create an ArrayAdapter using the string array and a default spinner layout
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
            R.array.genders, android.R.layout.simple_spinner_item);
    // Specify the layout to use when the list of choices appears
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    // Apply the adapter to the spinner
    spinner.setAdapter(adapter);

}





