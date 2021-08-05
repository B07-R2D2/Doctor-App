package com.r2d2.doctorapp;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

public class SpinnerArrayAdapter extends ArrayAdapter {

    public SpinnerArrayAdapter(Context context, int resource,Object[] objects) {
        super(context, resource, objects);
    }

    public SpinnerArrayAdapter(Context context, int resource,List objects) {
        super(context, resource, objects);
    }

    // Disables the first option from being selected
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
