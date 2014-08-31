package com.laquysoft.androidweatherapp;

import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.laquysoft.androidweatherapp.net.WorldWeatherOnline;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by joaobiriba on 29/08/14.
 */

@Module(
        library = true,
        injects = {WeatherListFragment.class})
public class WeatherAppMainModule {

    final static String TAG = WeatherAppMainModule.class.getName();

    @Provides
    @Singleton
    WorldWeatherOnline buildWorldWeatherOnlineRestClient() {

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        RestAdapter adapter =
                new RestAdapter.Builder().setEndpoint("https://api.worldweatheronline.com/free/v1").
                        setLogLevel(RestAdapter.LogLevel.FULL).
                        setLog(new RestAdapter.Log() {
                            @Override
                            public void log(String msg) {
                                Log.i(TAG, msg);
                            }
                        }).
                        build();

        return adapter.create(WorldWeatherOnline.class);
    }
}

