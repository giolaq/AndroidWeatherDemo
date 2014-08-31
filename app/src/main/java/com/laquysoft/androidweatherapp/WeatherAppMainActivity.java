package com.laquysoft.androidweatherapp;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Toast;

import com.laquysoft.androidweatherapp.loader.Callback;
import com.laquysoft.androidweatherapp.loader.RetrofitLoader;
import com.laquysoft.androidweatherapp.loader.RetrofitLoaderManager;
import com.laquysoft.androidweatherapp.model.PlaceWeatherForecast;
import com.laquysoft.androidweatherapp.net.WorldWeatherOnline;

import java.util.List;

import javax.inject.Inject;


public class WeatherAppMainActivity extends ActionBarActivity implements
        Callback<PlaceWeatherForecast> {


    final static String TAG = WeatherAppMainActivity.class.getName();

    WeatherListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_app_main);

         listFragment = new WeatherListFragment();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, listFragment)
                    .commit();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.weather_app_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.refresh) {
            Log.i(TAG, "Refreshin...");
            listFragment.refresh();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onFailure(Exception ex) {
        Log.d("PlaceWeatherForecastLoader", "onFailure");

        Toast.makeText(this, "Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();

    }

    @Override
    public void onSuccess(PlaceWeatherForecast result) {
        Log.d("PlaceWeatherForecastLoader", "onSuccess");

        listFragment.displayResults(result);
    }

}
