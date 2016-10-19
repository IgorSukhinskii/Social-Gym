package com.github.si1en7ium.socialgym.ui.main.add_event;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.DialogFragment;
import android.widget.Button;


import com.github.si1en7ium.socialgym.R;
import com.github.si1en7ium.socialgym.ui.main.BaseMainFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.github.si1en7ium.socialgym.R.id.dateButton;
import static com.github.si1en7ium.socialgym.R.layout.main_add_event_fragment;

/**
 * A {@link BaseMainFragment} subclass that is responsible for adding new events.
 * Use the {@link AddEventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddEventFragment extends BaseMainFragment {

    @BindView(R.id.dateButton) Button dateButton;
    @BindView(R.id.timeButton) Button timeButton;

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

        timeButton = (Button)view.findViewById(R.id.timeButton);
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getActivity().getSupportFragmentManager(), "timePicker") ;
            }
        });

        dateButton = (Button)view.findViewById(R.id.dateButton);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getActivity().getSupportFragmentManager(), "datePicker") ;
            }
        });


        return view;
    }

    @Override
    public boolean isFabShown() {
        return false;
    }


    public void onClickSetTeam (View view) {}
    public void onClickSetPhoto (View view) {}

}
