package com.github.si1en7ium.socialgym.ui.main;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.github.si1en7ium.socialgym.R;
import com.github.si1en7ium.socialgym.ui.base.BaseActivity;
import com.github.si1en7ium.socialgym.ui.main.events.EventsFragment;


public class TopFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {return inflater.inflate(R.layout.fragment_top, container, false);
} }
