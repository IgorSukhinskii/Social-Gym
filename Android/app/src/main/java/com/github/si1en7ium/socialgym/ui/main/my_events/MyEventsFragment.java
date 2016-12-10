package com.github.si1en7ium.socialgym.ui.main.my_events;

import com.github.si1en7ium.socialgym.R;
import com.github.si1en7ium.socialgym.ui.main.BaseMainFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.si1en7ium.socialgym.ui.main.MainActivity;
import com.github.si1en7ium.socialgym.view.SlidingTabLayout;

import butterknife.ButterKnife;


public class MyEventsFragment extends BaseMainFragment {


    public static MyEventsFragment newInstance() {
        return new MyEventsFragment();
    }
    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;

    public MyEventsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.my_events, container, false);

        ButterKnife.bind(this, rootView);
        ((MainActivity)getActivity()).setToolbarTitle("Events List");


        return rootView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(new SamplePagerAdapter());

        // Give the SlidingTabLayout the ViewPager, this must be
        // done AFTER the ViewPager has had it's PagerAdapter set.
        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setViewPager(mViewPager);
    }

    // Adapter
    class SamplePagerAdapter extends PagerAdapter {

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
            View view = getActivity().getLayoutInflater().inflate(R.layout.pager_item,
                    container, false);
            // Add the newly created View to the ViewPager
            container.addView(view);

            // Retrieve a TextView from the inflated View, and update it's text
            TextView title = (TextView) view.findViewById(R.id.item_title);
            title.setText(String.valueOf(position + 1));

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
    }
}