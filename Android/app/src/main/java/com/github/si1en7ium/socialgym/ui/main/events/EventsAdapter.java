package com.github.si1en7ium.socialgym.ui.main.events;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.si1en7ium.socialgym.R;
import com.github.si1en7ium.socialgym.data.local.Preferences;
import com.github.si1en7ium.socialgym.data.remote.SocialGymService;
import com.github.si1en7ium.socialgym.models.Authenticated;
import com.github.si1en7ium.socialgym.models.Event;
import com.github.si1en7ium.socialgym.models.SimpleResponse;
import com.github.si1en7ium.socialgym.ui.main.MainActivity;
import com.github.si1en7ium.socialgym.ui.main.view_event.ViewEventFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventViewHolder> {
    private List<Event> events;
    private Context context;
    private SocialGymService api;
    private Preferences prefs;

    @Inject
    public EventsAdapter(SocialGymService api, Preferences prefs) {
        this.api = api;
        this.prefs = prefs;
        events = new ArrayList<>();
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.main_events_list_item, parent, false);
        return new EventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final EventViewHolder holder, int position) {
        final Event event = events.get(position);
        Glide.with(context).load(event.imageUrl()).centerCrop().into(holder.eventImage);
        holder.eventTitle.setText(event.title());
        holder.eventDescription.setText(event.description());
        holder.eventImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ((MainActivity) context).switchToFragment(ViewEventFragment.newInstance(event));
            }
        });
        holder.buttonInterested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // send interested request
                Timber.i("Event id: %d", event.id());
                holder.buttonInterested.setEnabled(false);
                api.interestedInEvent(Authenticated.fromPreferences(prefs, event.id()))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Subscriber<SimpleResponse>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(SimpleResponse simpleResponse) {

                            }
                        });
            }
        });
        holder.buttonGoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.buttonGoing.setEnabled(false);
                api.goingToEvent(Authenticated.fromPreferences(prefs, event.id()))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Subscriber<SimpleResponse>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(SimpleResponse simpleResponse) {

                            }
                        });
            }
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class EventViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_event) ImageView eventImage;
        @BindView(R.id.text_event_title) TextView eventTitle;
        @BindView(R.id.text_event_description) TextView eventDescription;
        @BindView(R.id.button_interested) Button buttonInterested;
        @BindView(R.id.button_going) Button buttonGoing;

        EventViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
