package com.laquysoft.androidweatherapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nhaarman.listviewanimations.ArrayAdapter;

import com.laquysoft.androidweatherapp.model.PlaceWeatherForecast;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by joaobiriba on 31/08/14.
 */

public class CustomAdapter extends ArrayAdapter<PlaceWeatherForecast> {

    private final Context mContext;

    static final String TAG = CustomAdapter.class.getName();

    public CustomAdapter(final Context context) {
        mContext = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.activity_googlecards_card, parent, false);


        ImageView weatherIcon = (ImageView) view.findViewById(R.id.currentDayIcon);
        ImageView weatherIconFirst = (ImageView) view.findViewById(R.id.firstDayIcon);
        ImageView weatherIconSecond = (ImageView) view.findViewById(R.id.secondDayIcon);
        ImageView weatherIconThird = (ImageView) view.findViewById(R.id.thidDayIcon);
        ImageView weatherIconFourth = (ImageView) view.findViewById(R.id.fourthDayIcon);
        ImageView weatherIconFifth = (ImageView) view.findViewById(R.id.fifthDayIcon);

        if (weatherIcon == null) {
            weatherIcon = new ImageView(mContext);
        }

        String url = getItem(position).getData().getCurrentCondition().get(0).getWeatherIconUrl().get(0).getValue();
        Picasso.with(mContext).load(url).into(weatherIcon);



        Picasso.with(mContext).load(
                getItem(position).getData().getWeather().get(0).getWeatherIconUrl().get(0).getValue()
        ).into(weatherIconFirst);

        Picasso.with(mContext).load(
                getItem(position).getData().getWeather().get(1).getWeatherIconUrl().get(0).getValue()
        ).into(weatherIconSecond);

        Picasso.with(mContext).load(
                getItem(position).getData().getWeather().get(2).getWeatherIconUrl().get(0).getValue()
        ).into(weatherIconThird);

        Picasso.with(mContext).load(
                getItem(position).getData().getWeather().get(3).getWeatherIconUrl().get(0).getValue()
        ).into(weatherIconFourth);

        Picasso.with(mContext).load(
                getItem(position).getData().getWeather().get(4).getWeatherIconUrl().get(0).getValue()
        ).into(weatherIconFifth);


        TextView txtTitle = (TextView) view.findViewById(R.id.place);
        TextView currentCondition = (TextView) view.findViewById(R.id.currentCondition);

        TextView weath1 = (TextView) view.findViewById(R.id.firstWeatherCondition);
        TextView weath2 = (TextView) view.findViewById(R.id.secondWeatherCondition);
        TextView weath3 = (TextView) view.findViewById(R.id.thirdWeatherCondition);
        TextView weath4 = (TextView) view.findViewById(R.id.fourthWeatherCondition);
        TextView weath5 = (TextView) view.findViewById(R.id.fifthWeatherCondition);

        TextView date1 = (TextView) view.findViewById(R.id.firstDay);
        TextView date2 = (TextView) view.findViewById(R.id.secondDay);
        TextView date3 = (TextView) view.findViewById(R.id.thirdDay);
        TextView date4 = (TextView) view.findViewById(R.id.fourthDay);
        TextView date5 = (TextView) view.findViewById(R.id.fifthDay);

        PlaceWeatherForecast row_pos = getItem(position);

        currentCondition.setText(row_pos.getData().getCurrentCondition().get(0).getWeatherDesc().get(0).getValue());
        txtTitle.setText(TextUtils.split(row_pos.getData().getRequest().get(0).getQuery(),",")[0]);
        weath1.setText(row_pos.getData().getWeather().get(0).getWeatherDesc().get(0).getValue());
        weath2.setText(row_pos.getData().getWeather().get(1).getWeatherDesc().get(0).getValue());
        weath3.setText(row_pos.getData().getWeather().get(2).getWeatherDesc().get(0).getValue());
        weath4.setText(row_pos.getData().getWeather().get(3).getWeatherDesc().get(0).getValue());
        weath5.setText(row_pos.getData().getWeather().get(4).getWeatherDesc().get(0).getValue());

        date1.setText(row_pos.getData().getWeather().get(0).getDate().substring(5));
        date2.setText(row_pos.getData().getWeather().get(1).getDate().substring(5));
        date3.setText(row_pos.getData().getWeather().get(2).getDate().substring(5));
        date4.setText(row_pos.getData().getWeather().get(3).getDate().substring(5));
        date5.setText(row_pos.getData().getWeather().get(4).getDate().substring(5));

        return view;

    }

}