package com.laquysoft.androidweatherapp.loader;


public interface Callback<D> {


    public abstract void onFailure(Exception ex);

    public abstract void onSuccess(D result);

}
