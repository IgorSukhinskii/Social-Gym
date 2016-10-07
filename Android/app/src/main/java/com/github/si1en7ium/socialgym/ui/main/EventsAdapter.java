package com.github.si1en7ium.socialgym.ui.main;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.si1en7ium.socialgym.R;
import com.github.si1en7ium.socialgym.models.Event;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventViewHolder> {
    private List<Event> events;
    private Context context;

    @Inject
    public EventsAdapter() {
        events = new ArrayList<Event>();
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
    public void onBindViewHolder(EventViewHolder holder, int position) {
        Event event = events.get(position);
        Glide.with(context).load(event.imageUrl()).centerCrop().into(holder.eventImage);
        holder.eventTitle.setText(event.title());
        holder.eventDescription.setText(event.description());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class EventViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.eventImage) ImageView eventImage;
        @BindView(R.id.eventTitle) TextView eventTitle;
        @BindView(R.id.eventDescription) TextView eventDescription;

        public EventViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}