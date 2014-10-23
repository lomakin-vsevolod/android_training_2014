package com.epam.training.myapplication.callbacks;

import android.util.Log;

import com.epam.training.myapplication.helper.DataManager;

/**
 * Created by NuclearOK on 23.10.2014.
 */
public abstract class SimpleCallback<Result> implements DataManager.Callback {

    @Override
    public void onDataLoadStart() {
        Log.d("SimpleCallback", "onDataLoadStart");
    }

    @Override
    public void onError(Exception e) {
        Log.e("SimpleCallback", "onError", e);
    }

}

