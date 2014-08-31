package com.laquysoft.androidweatherapp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nhaarman.listviewanimations.ArrayAdapter;

import com.laquysoft.androidweatherapp.model.PlaceWeatherForecast;

import java.util.List;

/**
 * Created by joaobiriba on 31/08/14.
 */

public class CustomAdapter extends ArrayAdapter<PlaceWeatherForecast> {

    private final Context mContext;

    static final String  TAG = CustomAdapter.class.getName();

    public CustomAdapter(final Context context) {
        mContext = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.activity_googlecards_card, parent, false);



        TextView txtTitle = (TextView) view.findViewById(R.id.place);
        TextView weath1 = (TextView) view.findViewById(R.id.first);
        TextView weath2 = (TextView) view.findViewById(R.id.second);
        TextView weath3 = (TextView) view.findViewById(R.id.third);
        TextView weath4 = (TextView) view.findViewById(R.id.fourth);
        TextView weath5 = (TextView) view.findViewById(R.id.fifth);

        PlaceWeatherForecast row_pos = getItem(position);
        txtTitle.setText(row_pos.getData().getRequest().get(0).getQuery());
        weath1.setText(row_pos.getData().getWeather().get(0).getWeatherDesc().get(0).getValue());
        weath2.setText(row_pos.getData().getWeather().get(1).getWeatherDesc().get(0).getValue());
        weath3.setText(row_pos.getData().getWeather().get(2).getWeatherDesc().get(0).getValue());
        weath4.setText(row_pos.getData().getWeather().get(3).getWeatherDesc().get(0).getValue());
        weath5.setText(row_pos.getData().getWeather().get(4).getWeatherDesc().get(0).getValue());

        return view;

    }

}