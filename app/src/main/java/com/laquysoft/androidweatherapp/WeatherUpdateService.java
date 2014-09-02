package com.laquysoft.androidweatherapp;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

public class WeatherUpdateService extends IntentService {

    static final String TAG = WeatherUpdateService.class.getName();

    public static final String REQUEST_TYPE = "REQUEST_TYPE";

    public static final int STATUS_RUNNING = 0;
    public static final int STATUS_FINISHED = 1;

    public WeatherUpdateService() {
        super(WeatherUpdateService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.i(TAG, "onHandleIntent ");
        final ResultReceiver receiver = intent.getParcelableExtra("receiver");

        Bundle b = new Bundle();

        String msg = intent.getStringExtra(REQUEST_TYPE);

        if (msg.equals("refreshWeather")) {
            receiver.send(STATUS_RUNNING, Bundle.EMPTY);

            //Rest API
            //Fill Db
            receiver.send(STATUS_FINISHED, Bundle.EMPTY);

        }
    }
}
