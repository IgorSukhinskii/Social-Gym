package com.github.si1en7ium.socialgym.ui.main.view_event;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.si1en7ium.socialgym.R;
import com.github.si1en7ium.socialgym.models.Event;
import com.github.si1en7ium.socialgym.ui.main.BaseMainFragment;

import org.joda.time.MutableDateTime;
import org.joda.time.ReadableDateTime;
import org.joda.time.ReadableDuration;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ViewEventFragment extends BaseMainFragment {
    public static final String ARGUMENT_EVENT = "ARGUMENT_EVENT";

    private Event event;

    @BindView(R.id.text_title) TextView title;
    @BindView(R.id.text_event_type) TextView eventType;
    @BindView(R.id.text_time) TextView time;
    @BindView(R.id.text_date) TextView date;
    @BindView(R.id.text_location) TextView location;
    @BindView(R.id.text_description) TextView description;

    public ViewEventFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @return A new instance of fragment ViewEventFragment.
     */
    public static ViewEventFragment newInstance(Event event) {
        ViewEventFragment fragment = new ViewEventFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARGUMENT_EVENT, event);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.event = getArguments().getParcelable(ARGUMENT_EVENT);
        fragmentComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_view_event_fragment, container, false);

        ButterKnife.bind(this, view);

        title.setText(event.title());
        eventType.setText(event.sportKind().resourceId);
        MutableDateTime end = event.dateTime().toMutableDateTime();
        end.add(event.duration());
        String timeString = String.format("%1d:%2d - %3d:%4d",
                event.dateTime().getHourOfDay(),
                event.dateTime().getMinuteOfHour(),
                end.getHourOfDay(),
                end.getMinuteOfHour());
        time.setText(timeString);
        String dateString = String.format("%1d.%2d.%3d",
                event.dateTime().getDayOfMonth(),
                event.dateTime().getMonthOfYear(),
                event.dateTime().getYear());
        date.setText(dateString);
        location.setText(event.location());
        description.setText(event.description());

        return view;
    }

    @Override
    public boolean isFabShown() {
        return false;
    }
}
