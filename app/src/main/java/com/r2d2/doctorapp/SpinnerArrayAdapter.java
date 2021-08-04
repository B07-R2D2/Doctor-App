package com.r2d2.doctorapp;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class SpinnerArrayAdapter extends ArrayAdapter {
    public SpinnerArrayAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public SpinnerArrayAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public SpinnerArrayAdapter(@NonNull Context context, int resource, @NonNull Object[] objects) {
        super(context, resource, objects);
    }

    public SpinnerArrayAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull Object[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public SpinnerArrayAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    public SpinnerArrayAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List objects) {
        super(context, resource, textViewResourceId, objects);
    }

    // Disables the first option from being selected again
    @Override
    public boolean isEnabled(int position){
        return !(position == 0);
    }

    // Greys out the first option in the dropdown menu
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent){
        View view = super.getDropDownView(position, convertView, parent);
        TextView v = (TextView) view;
        if (position == 0)
            v.setTextColor(Color.GRAY);
        else
            v.setTextColor(Color.BLACK);
        return view;
    }
}
