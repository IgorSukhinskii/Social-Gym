package com.github.si1en7ium.socialgym.ui.main;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.si1en7ium.socialgym.R;

import butterknife.ButterKnife;

/**
 * A {@link BaseMainFragment} subclass that is responsible for adding new events.
 * Use the {@link AddEventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddEventFragment extends BaseMainFragment {

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
        View view = inflater.inflate(R.layout.main_add_event, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public boolean isFabShown() {
        return false;
    }
}
