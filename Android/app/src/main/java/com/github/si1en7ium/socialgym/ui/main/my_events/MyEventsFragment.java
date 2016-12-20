package com.github.si1en7ium.socialgym.ui.main.my_events;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.si1en7ium.socialgym.R;
import com.github.si1en7ium.socialgym.models.Event;
import com.github.si1en7ium.socialgym.ui.main.BaseMainFragment;
import com.github.si1en7ium.socialgym.ui.main.MainActivity;
import com.github.si1en7ium.socialgym.view.SlidingTabLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyEventsFragment extends BaseMainFragment implements MyEventsMvpView {


    @Inject MyEventsPresenter presenter;
    @BindView(R.id.sliding_tabs) SlidingTabLayout slidingTabLayout;
    @BindView(R.id.viewpager) ViewPager viewPager;
    private MyEventsPagerAdapter adapter;
    public MyEventsFragment() {
    }

    public static MyEventsFragment newInstance() {
        return new MyEventsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.my_events, container, false);

        ButterKnife.bind(this, rootView);

        adapter = new MyEventsPagerAdapter(getActivity());

        fragmentComponent().inject(this);
        presenter.attachView(this);

        ((MainActivity)getActivity()).setToolbarTitle("Events List");

        return rootView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        viewPager.setAdapter(adapter);

        // Give the SlidingTabLayout the ViewPager, this must be
        // done AFTER the ViewPager has had it's PagerAdapter set.
        slidingTabLayout.setViewPager(viewPager);

        presenter.loadEvents();
    }

    @Override
    public void showEvents(List<Event> events) {
        adapter.updateAttended(events);
        adapter.notifyDataSetChanged();
    }
}
