package com.laquysoft.androidweatherapp.loader;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.AsyncTaskLoader;

/**
 * Created by joaobiriba on 29/08/14.
 */
public abstract class RetrofitLoader<D, R> extends AsyncTaskLoader<Response<D>>
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    private final R mService;

    private Response<D> mCachedResponse;

    private SharedPreferences prefs = null;


    public RetrofitLoader(Context context, R service) {

        super(context);

        mService = service;
    }

    @Override
    public Response<D> loadInBackground() {

        prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        prefs.registerOnSharedPreferenceChangeListener(this);
        try {

            final D data = call(mService, prefs);
            mCachedResponse = Response.ok(data);

        } catch (Exception ex) {

            mCachedResponse = Response.error(ex);
        }

        return mCachedResponse;
    }

    @Override
    protected void onStartLoading() {

        super.onStartLoading();

      //  if (mCachedResponse != null) {

      //      deliverResult(mCachedResponse);
      //  }

      //  if (takeContentChanged() || mCachedResponse == null) {

            forceLoad();
      //  }
    }

    @Override
    protected void onReset() {

        super.onReset();

        mCachedResponse = null;
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        onContentChanged();
    }
    public abstract D call(R service, SharedPreferences prefs);
}