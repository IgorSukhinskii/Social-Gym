package com.github.si1en7ium.socialgym.ui.main.profile;

import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.github.si1en7ium.socialgym.R;
import com.github.si1en7ium.socialgym.ui.main.BaseMainFragment;
import com.github.si1en7ium.socialgym.ui.main.MainActivity;

import android.widget.TextView;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.github.si1en7ium.socialgym.R.layout.main_edit_profile_fragment;


public class EditProfileFragment extends BaseMainFragment implements DatePickerDialog.OnDateSetListener, EditProfileMvpView {

    @Inject EditProfilePresenter editProfilePresenter;
  //  @BindView(R.id.typeOfSportsSelector) Spinner typeOfSportsSelector;
  @BindView(R.id.editTextDateOfBirth) TextView editTextDateOfBirth;

    public EditProfileFragment() {

        // Required empty public constructor
    }


    public static EditProfileFragment newInstance() {
        return new EditProfileFragment();
    }


 //       String date=String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(dayOfMonth);
  //     dateText.setText(date);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentComponent().inject(this);
        editProfilePresenter.attachView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(main_edit_profile_fragment, container, false);


        ButterKnife.bind(this, view);
        ((MainActivity)getActivity()).setToolbarTitle("Edit Event");

        editTextDateOfBirth = (TextView) view.findViewById(R.id.editTextDateOfBirth);

        final DialogFragment datePickerFragment = new DatePickerFragment(this);

        editTextDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
            }
        });
        return view;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        editProfilePresenter.setDate(year, month, dayOfMonth);
        String date=String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(dayOfMonth);
        editTextDateOfBirth.setText(date);}

}





