package com.epam.training.myapplication;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;

import com.epam.training.myapplication.source.FileDataSource;
import com.epam.training.myapplication.source.HttpDataSource;

/**
 * Created by NuclearOK on 23.10.2014.
 */
public class CoreApplication extends Application {

    private HttpDataSource mHttpDataSource;
    private FileDataSource mFileDataSource;
    AssetManager assetmanager;


    @Override
    public void onCreate() {
        super.onCreate();
        assetmanager = getApplicationContext().getAssets();
        mHttpDataSource = new HttpDataSource();
        mFileDataSource = new FileDataSource(assetmanager);

    }

    @Override
    public Object getSystemService(String name) {
        if (HttpDataSource.KEY.equals(name)) {
            //for android kitkat +
            if (mHttpDataSource == null) {
                mHttpDataSource = new HttpDataSource();
            }
            return mHttpDataSource;
        }

        if (FileDataSource.KEY.equals(name)) {
            //for android kitkat +
            if (mFileDataSource == null) {
                mFileDataSource = new FileDataSource(assetmanager);
            }
            return mFileDataSource;
        }
        return super.getSystemService(name);
    }

    public static <T> T get(Context context, String key) {
        if (context == null || key == null){
            throw new IllegalArgumentException("Context and key must not be null");
        }
        T systemService = (T) context.getSystemService(key);
        if (systemService == null) {
            context = context.getApplicationContext();
            systemService = (T) context.getSystemService(key);
        }
        if (systemService == null) {
            throw new IllegalStateException(key + " not available");
        }
        return systemService;
    }
}