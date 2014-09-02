package com.laquysoft.androidweatherapp;

import android.app.Application;

import dagger.ObjectGraph;


public class App extends Application {

    private ObjectGraph graph;

    @Override
    public void onCreate() {
        super.onCreate();

        graph = ObjectGraph.create(new WeatherAppMainModule());
    }

    public void inject(Object object) {
        graph.inject(object);
    }
}

