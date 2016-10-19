package com.github.si1en7ium.socialgym.ui.main.add_event;


import android.app.AlertDialog;
import android.support.v4.app.DialogFragment;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.NumberPicker;


public class NumberPickerFragment extends DialogFragment  {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Create a new instance of NumberPickerDialog and return it
        return new AlertDialog.Builder(getActivity())
                .create();
    }

}
