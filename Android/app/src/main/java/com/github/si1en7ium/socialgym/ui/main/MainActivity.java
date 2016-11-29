package com.github.si1en7ium.socialgym.ui.main;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.github.si1en7ium.socialgym.R;
import com.github.si1en7ium.socialgym.ui.base.BaseActivity;

import com.github.si1en7ium.socialgym.ui.main.add_event.AddEventFragment;
import com.github.si1en7ium.socialgym.ui.main.events.EventsFragment;
import com.github.si1en7ium.socialgym.ui.main.profile.ProfileFragment;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.fragment;

public class MainActivity extends BaseActivity implements MainMvpView {

    private static final String EXTRA_TRIGGER_SYNC_FLAG =
            "com.github.si1en7ium.socialgym.ui.main.MainActivity.EXTRA_TRIGGER_SYNC_FLAG";

    private  String[] titles;
    private ListView drawerList;


    @Inject MainPresenter mainPresenter;

    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.toolbar) Toolbar toolbar;

    /**
     * Return an Intent to start this Activity.
     * triggerDataSyncOnCreate allows disabling the background sync service onCreate. Should
     * only be set to false during testing.
     */
    public static Intent getStartIntent(Context context, boolean triggerDataSyncOnCreate) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(EXTRA_TRIGGER_SYNC_FLAG, triggerDataSyncOnCreate);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.showAddEventScreen();
            }
        });

        switchToFragment(EventsFragment.newInstance());

        mainPresenter.attachView(this);

        titles = getResources().getStringArray(R.array.titles);
        drawerList = (ListView)findViewById(R.id.drawer);
        drawerList.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_activated_1, titles));
        drawerList.setOnItemClickListener( new DrawerItemClickListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void switchToFragment(BaseMainFragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_placeholder, fragment)
                .addToBackStack(null)
                .commit();
       setFabVisibility(fragment.isFabShown());
    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        BaseMainFragment currentFragment = (BaseMainFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_placeholder);
        setFabVisibility(currentFragment.isFabShown());
    }

    private void setFabVisibility(boolean isFabShown) {
        if (isFabShown && !fab.isShown()) {
            fab.show();
        } else if (!isFabShown && fab.isShown()) {
            fab.hide();
        }
    }


    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }


    //Обработчик кликов
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private  void selectItem (int position) {
        Fragment fragment = null;

        switch (position) {
            case 1: fragment = new ProfileFragment();
                break;
            case 2: fragment = new EventsFragment();
                break;
            default: break;
        }

        if (fragment !=null) {
            android.app.FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();}

        else {Log.e("MainActivity", "Error");}}


}

////////////