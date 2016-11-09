package com.github.si1en7ium.socialgym.ui.main.add_event;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

import com.github.si1en7ium.socialgym.models.SportKind;

class SportKindEnumAdaper extends ArrayAdapter<SportKind> {
    SportKindEnumAdaper(Context context){
        super(context, 0, SportKind.values());
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        CheckedTextView text= (CheckedTextView) convertView;

        if (text== null) {
            text = (CheckedTextView) LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_dropdown_item,  parent, false);
        }

        text.setText(getItem(position).resourceId);

        return text;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        CheckedTextView text = (CheckedTextView) convertView;

        if (text == null) {
            text = (CheckedTextView) LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_dropdown_item,  parent, false);
        }

        text.setText(getItem(position).resourceId);

        return text;
    }
}
