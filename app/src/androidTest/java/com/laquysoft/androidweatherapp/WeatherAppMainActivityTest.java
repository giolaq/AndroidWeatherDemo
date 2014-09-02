package com.laquysoft.androidweatherapp;


import android.content.Intent;
import android.widget.FrameLayout;

public class WeatherAppMainActivityTest extends android.test.ActivityUnitTestCase<WeatherAppMainActivity> {

    private WeatherAppMainActivity activity;

    private FrameLayout container;

    public WeatherAppMainActivityTest() {
        super(WeatherAppMainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(getInstrumentation().getTargetContext(),
                WeatherAppMainActivity.class);
        startActivity(intent, null, null);
        activity = getActivity();
    }

    public void testLayout() {

        assertNotNull(activity.findViewById(R.id.container));
    }

}