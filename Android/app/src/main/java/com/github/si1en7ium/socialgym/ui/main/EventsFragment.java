package com.github.si1en7ium.socialgym.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.si1en7ium.socialgym.R;
import com.github.si1en7ium.socialgym.models.Event;
import com.github.si1en7ium.socialgym.ui.base.BaseFragment;
import com.github.si1en7ium.socialgym.util.DialogFactory;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * A simple {@link BaseFragment} subclass.
 * Use the {@link EventsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventsFragment extends BaseFragment implements EventsMvpView {

    @Inject EventsPresenter eventsPresenter;
    @Inject EventsAdapter eventsAdapter;

    @BindView(R.id.eventsList) RecyclerView eventsListView;

    public EventsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment EventsFragment.
     */
    public static EventsFragment newInstance() {
        return new EventsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.main_events_list, container, false);

        ButterKnife.bind(this, view);

        eventsListView.setAdapter(eventsAdapter);
        eventsListView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        eventsPresenter.attachView(this);
        eventsPresenter.loadEvents();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showEvents(List<Event> events) {
        Timber.i("Showing the list of events");
        eventsAdapter.setEvents(events);
        eventsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this.getContext(), R.string.error_loading_events)
                .show();
    }
}
