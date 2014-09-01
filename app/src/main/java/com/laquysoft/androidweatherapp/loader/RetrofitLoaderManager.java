package com.laquysoft.androidweatherapp.loader;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

public class RetrofitLoaderManager {


    public static <D, R> void init(final LoaderManager manager, final int loaderId,
                                   final RetrofitLoader<D, R> loader, final Callback<D> callback) {

        manager.initLoader(loaderId, Bundle.EMPTY, new LoaderManager.LoaderCallbacks<Response<D>>() {

            @Override
            public Loader<Response<D>> onCreateLoader(int id, Bundle args) {

                return loader;
            }

            @Override
            public void onLoadFinished(Loader<Response<D>> loader, Response<D> data) {

                if (data.hasError()) {

                    callback.onFailure(data.getException());

                } else {

                    callback.onSuccess(data.getResult());
                }
            }

            @Override
            public void onLoaderReset(Loader<Response<D>> loader) {

                //Nothing to do here
            }
        });
    }


}