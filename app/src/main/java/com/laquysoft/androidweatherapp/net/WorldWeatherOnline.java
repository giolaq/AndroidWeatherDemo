package com.laquysoft.androidweatherapp.net;

import com.laquysoft.androidweatherapp.model.PlaceWeatherForecast;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by joaobiriba on 29/08/14.
 */
public interface WorldWeatherOnline {
    @GET("/weather.ashx")
    PlaceWeatherForecast listPlaceWeatherForecast(@Query("q") String city,
                                                        @Query("format") String format,
                                                        @Query("num_of_days") Integer num_of_days,
                                                        @Query("key") String apikey
                                                       );
}
