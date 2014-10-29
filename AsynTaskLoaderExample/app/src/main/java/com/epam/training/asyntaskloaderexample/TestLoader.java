package com.epam.training.asyntaskloaderexample;

import android.annotation.TargetApi;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.TimeUnit;

/**
 * Created by NuclearOK on 29.10.2014.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class TestLoader extends AsyncTaskLoader<String> {

    final int PAUSE = 3;
    Bundle bundle;

    public TestLoader(Context context, Bundle args) {
        super(context);
        bundle = args;
        Log.d("MyLog", "TestLoaderConstructor");
    }

    @Override
    public String loadInBackground() {

        String text = "Is empty!!!";
        try {
            text = "LoadFinished Result:  " + bundle.getString("StringExample");
            TimeUnit.SECONDS.sleep(PAUSE);
            Log.d("MyLog", "loadInBackground" );
            return text;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return text;
   }


}
