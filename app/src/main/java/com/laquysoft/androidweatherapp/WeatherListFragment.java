package com.laquysoft.androidweatherapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.laquysoft.androidweatherapp.loader.Callback;
import com.laquysoft.androidweatherapp.loader.RetrofitLoader;
import com.laquysoft.androidweatherapp.loader.RetrofitLoaderManager;
import com.laquysoft.androidweatherapp.model.PlaceWeatherForecast;
import com.laquysoft.androidweatherapp.net.WorldWeatherOnline;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by joaobiriba on 29/08/14.
 */
public class WeatherListFragment extends ListFragment implements
        Callback<PlaceWeatherForecast> {

    final static String TAG = WeatherListFragment.class.getName();

    @Inject
    WorldWeatherOnline worldWeatherOnlineService;

    PlaceWeatherForecastLoader loader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather_app_main, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((App) (getActivity().getApplication())).inject(this);

        loader = new PlaceWeatherForecastLoader(getActivity(), worldWeatherOnlineService);

        RetrofitLoaderManager.init(getLoaderManager(), 0, loader, this);

        setHasOptionsMenu(true);
        ActionBar ab = ((ActionBarActivity) getActivity())
                .getSupportActionBar();
        
        
        
    }

    public void refresh() {
        Log.i(TAG, "Refreshin...");
        RetrofitLoaderManager.init(getLoaderManager(), 0, loader, this);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        showDetailedWeatherForecast(position);

    }

    private void showDetailedWeatherForecast(int position) {
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.weather_list_menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.add_place:

			    //Add place to list
                break;

            case R.id.refresh:

                //onRefresh();

                break;
        }

        return false;

    }


    @Override
    public void onFailure(Exception ex) {
        Toast.makeText(getActivity(), "Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();

    }

    @Override
    public void onSuccess(PlaceWeatherForecast result) {
        Log.d("PlaceWeatherForecastLoader", "onSuccess");

        displayResults(result);
    }


    public void displayResults(PlaceWeatherForecast placeWeatherForecasts) {



        Log.d(TAG, placeWeatherForecasts.getData().getRequest().get(0).getQuery());
        List<String> strings = toStringList(placeWeatherForecasts.getData().getWeather());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, strings);

        ListView viewById = (ListView) getActivity().findViewById(android.R.id.list);
        viewById.setAdapter(adapter);
    }


    private static List<String> toStringList(List<PlaceWeatherForecast.Weather> placeWeatherForecasts) {

        ArrayList<String> strings = new ArrayList<String>(placeWeatherForecasts.size());

        for (PlaceWeatherForecast.Weather weather : placeWeatherForecasts) {

            strings.add(weather.getDate() + " " + weather.getWeatherDesc().get(0).getValue());
        }

        return strings;
    }



    static class PlaceWeatherForecastLoader extends RetrofitLoader<PlaceWeatherForecast, WorldWeatherOnline> {

        public PlaceWeatherForecastLoader(Context context, WorldWeatherOnline service) {

            super(context, service);
        }

        @Override
        public PlaceWeatherForecast call(WorldWeatherOnline service) {

            Log.d("PlaceWeatherForecastLoader", "call");

            return service.listPlaceWeatherForecast("London", "json", 5,"a8d9da468063a74e52b5d697329e730bbe04f438");
        }
    }


}
