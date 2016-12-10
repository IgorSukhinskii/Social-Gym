package com.github.si1en7ium.socialgym.ui.main.settings;

        import android.os.Bundle;
        import android.support.v4.view.PagerAdapter;
        import android.support.v4.view.ViewPager;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import com.github.si1en7ium.socialgym.R;
        import com.github.si1en7ium.socialgym.ui.main.BaseMainFragment;
        import com.github.si1en7ium.socialgym.ui.main.MainActivity;
        import com.github.si1en7ium.socialgym.ui.main.settings.SettingsPresenter;
        import com.github.si1en7ium.socialgym.view.SlidingTabLayout;

        import butterknife.ButterKnife;
        import javax.inject.Inject;


public class SettingsFragment extends BaseMainFragment {

    @Inject
    SettingsPresenter settingsPresenter;

    public SettingsFragment() {
    }

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.settings, container, false);

        ButterKnife.bind(this, view);
        ((MainActivity)getActivity()).setToolbarTitle("Settings");


        return view;
    }
}