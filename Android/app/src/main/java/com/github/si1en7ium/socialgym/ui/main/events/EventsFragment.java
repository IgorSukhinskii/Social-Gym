package com.github.si1en7ium.socialgym.ui.main.events;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.si1en7ium.socialgym.R;
import com.github.si1en7ium.socialgym.models.Event;
import com.github.si1en7ium.socialgym.ui.main.BaseMainFragment;
import com.github.si1en7ium.socialgym.util.DialogFactory;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * A {@link BaseMainFragment} subclass that is responsible for showing nearby events in a list.
 * Use the {@link EventsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventsFragment extends BaseMainFragment implements EventsMvpView, SwipeRefreshLayout.OnRefreshListener {

    @Inject EventsPresenter eventsPresenter;
    @Inject EventsAdapter eventsAdapter;

    @BindView(R.id.list_events) RecyclerView eventsListView;
    @BindView(R.id.refresh) SwipeRefreshLayout refreshLayout;

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
        View view = inflater.inflate(R.layout.main_events_list_fragment, container, false);

        ButterKnife.bind(this, view);

        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent);

        eventsListView.setAdapter(eventsAdapter);
        eventsListView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        eventsPresenter.attachView(this);
        eventsPresenter.loadEvents();
        refreshLayout.setRefreshing(true);

        return view;
    }

    @Override
    public void showEvents(List<Event> events) {
        Timber.i("Showing the list of events");
        eventsAdapter.setEvents(events);
        eventsAdapter.notifyDataSetChanged();
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this.getContext(), R.string.error_loading_events)
                .show();
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        eventsPresenter.loadEvents();
        refreshLayout.setRefreshing(true);
    }
}
