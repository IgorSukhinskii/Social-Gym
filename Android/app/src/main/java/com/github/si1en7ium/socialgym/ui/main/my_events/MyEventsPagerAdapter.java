package com.github.si1en7ium.socialgym.ui.main.my_events;


import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.github.si1en7ium.socialgym.R;
import com.github.si1en7ium.socialgym.data.local.Preferences;
import com.github.si1en7ium.socialgym.data.remote.SocialGymService;
import com.github.si1en7ium.socialgym.models.Event;
import com.github.si1en7ium.socialgym.ui.main.events.EventsAdapter;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

class MyEventsPagerAdapter extends PagerAdapter {
    private final EventsAdapter interestedAdapter;
    private final EventsAdapter attendedAdapter;
    private final EventsAdapter myEventsAdapter;
    private Activity activity;

    @Inject
    MyEventsPagerAdapter(SocialGymService api, Preferences prefs) {
        interestedAdapter = new EventsAdapter(api, prefs);
        attendedAdapter = new EventsAdapter(api, prefs);
        myEventsAdapter = new EventsAdapter(api, prefs);
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    /**
     * Return the number of pages to display
     */
    @Override
    public int getCount() {
        return 3;
    }

    /**
     * Return true if the value returned from is the same object as the View
     * added to the ViewPager.
     */
    @Override
    public boolean isViewFromObject(View view, Object o) {
        return o == view;
    }

    /**
     * Return the title of the item at position. This is important as what
     * this method returns is what is displayed in the SlidingTabLayout.
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return "Item " + (position + 1);
    }

    /**
     * Instantiate the View which should be displayed at position. Here we
     * inflate a layout from the apps resources and then change the text
     * view to signify the position.
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // Inflate a new layout from our resources
        View view = activity.getLayoutInflater().inflate(R.layout.pager_item,
                container, false);
        // Add the newly created View to the ViewPager
        container.addView(view);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.pager_list);

        Timber.i("Position in pager: %d", position);

        if (position == 0) {
            recyclerView.setAdapter(interestedAdapter);
        } else if (position == 1) {
            recyclerView.setAdapter(attendedAdapter);
        } else if (position == 2) {
            recyclerView.setAdapter(myEventsAdapter);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this.activity));

        // Return the View
        return view;
    }

    /**
     * Destroy the item from the ViewPager. In our case this is simply
     * removing the View.
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void updateInterested(List<Event> events) {
        interestedAdapter.setEvents(events);
        interestedAdapter.notifyDataSetChanged();
    }

    public void updateAttended(List<Event> events) {
        attendedAdapter.setEvents(events);
        attendedAdapter.notifyDataSetChanged();
    }

    public void updateMyEvents(List<Event> events) {
        myEventsAdapter.setEvents(events);
        myEventsAdapter.notifyDataSetChanged();
    }
}