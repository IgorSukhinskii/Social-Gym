package com.github.si1en7ium.socialgym.ui.main.add_event;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;


import com.github.si1en7ium.socialgym.R;
import com.github.si1en7ium.socialgym.ui.main.BaseMainFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.github.si1en7ium.socialgym.R.layout.main_add_event_fragment;

/**
 * A {@link BaseMainFragment} subclass that is responsible for adding new events.
 * Use the {@link AddEventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddEventFragment extends BaseMainFragment implements
        TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener {

    @Inject AddEventPresenter presenter;

    @BindView(R.id.dateButton) Button dateButton;
    @BindView(R.id.timeButton) Button timeButton;
    @BindView(R.id.doneButton) Button doneButton;
    @BindView(R.id.typeOfSportsSelector) Spinner typeOfSportsSelector;
    @BindView(R.id.placeEdit) EditText locationEdit;
    @BindView(R.id.commentsEdit) EditText descriptionEdit;

    public AddEventFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @return A new instance of fragment AddEventFragment.
     */
    public static AddEventFragment newInstance() {
        return new AddEventFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(main_add_event_fragment, container, false);

        ButterKnife.bind(this, view);

        final SportKindEnumAdaper spinnerAdapter = new SportKindEnumAdaper(getContext());
        typeOfSportsSelector.setAdapter(spinnerAdapter);

        timeButton = (Button) view.findViewById(R.id.timeButton);
        final DialogFragment timePickerFragment = new TimePickerFragment(this);
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timePickerFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
            }
        });

        dateButton = (Button) view.findViewById(R.id.dateButton);
        final DialogFragment datePickerFragment = new DatePickerFragment(this);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                datePickerFragment.show(getActivity().getSupportFragmentManager(), "datePicker");

            }
        });




        doneButton = (Button) view.findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.setLocation(locationEdit.getText().toString());
                presenter.setDescription(descriptionEdit.getText().toString());
                presenter.setSportKind(spinnerAdapter.getItem(typeOfSportsSelector.getSelectedItemPosition()));
                presenter.postEvent();
            }
        });

        return view;
    }

    @Override
    public boolean isFabShown() {
        return false;
    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        presenter.setStartTime(hourOfDay, minute);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        presenter.setDate(year, month, dayOfMonth);
    }
}
