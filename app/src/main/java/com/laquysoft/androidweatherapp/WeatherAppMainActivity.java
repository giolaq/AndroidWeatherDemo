package com.laquysoft.androidweatherapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.EditText;
import android.widget.Toast;

import com.laquysoft.androidweatherapp.loader.Callback;
import com.laquysoft.androidweatherapp.loader.RetrofitLoader;
import com.laquysoft.androidweatherapp.loader.RetrofitLoaderManager;
import com.laquysoft.androidweatherapp.model.PlaceWeatherForecast;
import com.laquysoft.androidweatherapp.net.WorldWeatherOnline;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;


public class WeatherAppMainActivity extends ActionBarActivity implements
        Callback<List<PlaceWeatherForecast>> {


    final static String TAG = WeatherAppMainActivity.class.getName();

    WeatherListFragment listFragment;

    SharedPreferences prefs;

    private static final String KEY = "prefs";
    private static final String default_cities = "Dublin, London, New York, Barcelona";
    private LinkedList<String> city_list;

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
                SharedPreferences.Editor editor = prefs.edit();
                if ( ! city_list.contains(place)) {
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

    public void addCity(View v) {
        inputPlace();
    }
}
