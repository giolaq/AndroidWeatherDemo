package com.laquysoft.androidweatherapp;

import android.app.Application;

import dagger.ObjectGraph;

/**
 * Created by joaobiriba on 29/08/14.
 */
public class App extends Application {

    private ObjectGraph graph;

    @Override public void onCreate() {
        super.onCreate();

        graph = ObjectGraph.create(new WeatherAppMainModule());
    }

    public void inject(Object object) {
        graph.inject(object);
    }
}

