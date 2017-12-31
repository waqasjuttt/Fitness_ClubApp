package com.example.waqasjutt.fitness_club;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Waqas Jutt on 10/3/2017.
 */

public class CustomAdapterLean extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] Fitness_Club_Description;

    public CustomAdapterLean(Activity context, String[] promo) {
        super(context, R.layout.body_item, promo);
        this.context = context;
        this.Fitness_Club_Description = promo;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View rowView = layoutInflater.inflate(R.layout.body_item, parent, false);

        TextView textView = rowView.findViewById(R.id.tv_lean_body_item);
        textView.setText(Fitness_Club_Description[position]);

        return rowView;
    }
}