package com.github.si1en7ium.socialgym.ui.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.si1en7ium.socialgym.R;
import com.github.si1en7ium.socialgym.ui.base.BaseActivity;
import com.github.si1en7ium.socialgym.ui.main.events.EventsFragment;
import com.github.si1en7ium.socialgym.ui.main.profile.ProfileFragment;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;
public class MainActivity extends BaseActivity implements MainMvpView {




    private static final String EXTRA_TRIGGER_SYNC_FLAG =
            "com.github.si1en7ium.socialgym.ui.main.MainActivity.EXTRA_TRIGGER_SYNC_FLAG";
    @Inject
    MainPresenter mainPresenter;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private List<PrimaryDrawerItem> drawerItems;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

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

        // set up drawer items
        PrimaryDrawerItem drawerProfile = new PrimaryDrawerItem()
                .withIdentifier(1)
                .withName(R.string.drawer_profile)

                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override

                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switchToFragment(ProfileFragment.newInstance());
                        fab.hide();
                        Timber.i("Clicked drawer item %1$d", position);
                        return false;
                    }
                });

        DividerDrawerItem dividerDrawerItem = new DividerDrawerItem();

        PrimaryDrawerItem drawerEventsList = new PrimaryDrawerItem()
                .withIdentifier(0)
                .withName(R.string.drawer_events_list)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switchToFragment(EventsFragment.newInstance());
                        fab.show();
                        Timber.i("Clicked drawer item %1$d", position);
                        return false;
                    }
                });

        PrimaryDrawerItem drawerMyEvents = new PrimaryDrawerItem()
                .withIdentifier(2)
                .withName(R.string.drawer_my_events)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override

                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switchToFragment(EventsFragment.newInstance());
                        fab.show();
                        Timber.i("Clicked drawer item %1$d", position);
                        return false;
                    }
                });

        // set up navigation drawer
        Drawer drawerResult = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(drawerEventsList)
                .addDrawerItems(dividerDrawerItem)
                .addDrawerItems(drawerMyEvents)
                .addDrawerItems(dividerDrawerItem)
                .addDrawerItems(drawerProfile)
                .withHeader(R.layout.drawer_header)
                .build();

       // switchToFragment(EventsFragment.newInstance());

        mainPresenter.attachView(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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


        //if (drawer.isDrawerOpen()) {
        //    drawer.closeDrawer();
        //} else {
        //    super.onBackPressed();
        // }

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

    private void selectItem(int position) {
        BaseMainFragment fragment = null;

        switch (position) {
            case 1:
                fragment = new ProfileFragment();
                break;
            case 2:
                fragment = new EventsFragment();
                break;
            default:
                break;
        }
        if (fragment != null) {
            switchToFragment(fragment);
        } else {
            Log.e("MainActivity", "Error");
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    //Обработчик кликов
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }


}

////////////