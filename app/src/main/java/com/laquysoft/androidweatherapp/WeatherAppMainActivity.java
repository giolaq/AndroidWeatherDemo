package com.laquysoft.androidweatherapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.laquysoft.androidweatherapp.loader.Callback;
import com.laquysoft.androidweatherapp.model.PlaceWeatherForecast;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import io.snapback.sdk.gesture.sequence.adapter.AmbientLightSensorAdapter;
import io.snapback.sdk.gesture.sequence.adapter.ProximitySensorAdapter;
import io.snapback.sdk.gesture.wave.WaveGestureEvent;
import io.snapback.sdk.gesture.wave.WaveGestureListener;
import io.snapback.sdk.gesture.wave.WaveGestureHandler;


public class WeatherAppMainActivity extends ActionBarActivity implements
        Callback<List<PlaceWeatherForecast>>, WaveGestureListener {


    final static String TAG = WeatherAppMainActivity.class.getName();

    WeatherListFragment listFragment;

    SharedPreferences prefs;

    private static final String default_cities = "Dublin,London,New York,Barcelona";
    private LinkedList<String> city_list;

    private WaveGestureHandler waveGestureHandler;
    private ProximitySensorAdapter proximitySensorAdapter;
    private AmbientLightSensorAdapter ambientLightSensorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_app_main);

        listFragment = new WeatherListFragment();

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String serialized = prefs.getString("city_list", null);
        if (serialized == null) {
            Log.i(TAG, " No shared preferences ");
            city_list = new LinkedList<String>(Arrays.asList(TextUtils.split(default_cities, ",")));
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("city_list", TextUtils.join(",", city_list));
            editor.commit();
        } else {

            city_list = new LinkedList<String>(Arrays.asList(TextUtils.split(serialized, ",")));
            Log.i(TAG, " Loading shared preferences " + city_list.toString());

        }


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, listFragment)
                    .commit();
        }

        waveGestureHandler = new WaveGestureHandler();
        proximitySensorAdapter = new ProximitySensorAdapter(waveGestureHandler, this);
        ambientLightSensorAdapter = new AmbientLightSensorAdapter(waveGestureHandler, this);
        waveGestureHandler.setSensorAdapters(proximitySensorAdapter, ambientLightSensorAdapter);

        waveGestureHandler.register(this);
        waveGestureHandler.start();


    }


    @Override
    public void onFailure(Exception ex) {
        Log.d("PlaceWeatherForecastLoader", "onFailure");

        Toast.makeText(this, "Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();

    }

    @Override
    public void onSuccess(List<PlaceWeatherForecast> result) {
        Log.d("PlaceWeatherForecastLoader", "onSuccess");

        listFragment.displayResults(result);
    }

    public void inputPlace() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Write the City Name to get its Weather Forecasts");
        final Context ctx = this;
        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String place = input.getText().toString().trim();

                String serialized = prefs.getString("city_list", null);
                if (serialized != null) {
                    city_list = new LinkedList<String>(Arrays.asList(TextUtils.split(serialized, ",")));
                    Log.i(TAG, " Reloading shared preferences " + city_list.toString());
                }

                SharedPreferences.Editor editor = prefs.edit();
                if (!city_list.contains(place)) {
                    city_list.add(place);
                } else {
                    Toast.makeText(ctx, place + " already in list", Toast.LENGTH_LONG).show();
                }
                editor.putString("city_list", TextUtils.join(",", city_list)); // Add Array list elements to shared preferences
                editor.apply();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }


    @Override
    public void onEvent(WaveGestureEvent waveGestureEvent) {
        Log.d(TAG, "Wave!");

        switch (waveGestureEvent.getType()) {
            case WaveGestureEvent.SINGLE_WAVE_EVENT_TYPE:
                inputPlace();
            default:
                Log.d(TAG, "Event " + waveGestureEvent.getType());
        }
    }
}
