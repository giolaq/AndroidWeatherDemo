package com.laquysoft.androidweatherapp.loader;

/**
 * Created by joaobiriba on 29/08/14.
 */
public interface Callback<D> {


        public abstract void onFailure(Exception ex);

        public abstract void onSuccess(D result);

}
