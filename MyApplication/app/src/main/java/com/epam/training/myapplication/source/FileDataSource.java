package com.epam.training.myapplication.source;

import android.content.Context;
import android.content.res.AssetManager;

import com.epam.training.myapplication.CoreApplication;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by NuclearOK on 23.10.2014.
 */
public class FileDataSource implements DataSource<InputStream, String> {

    public static final String KEY = "FileDataSource";

    AssetManager assetmanager;

    public static FileDataSource get(Context context) {
        return CoreApplication.get(context, KEY);
    }

    public FileDataSource(AssetManager assetManager){
        this.assetmanager = assetManager;
    }

    @Override
    public InputStream getResult(String p) throws Exception {
        //download data and return
        // Read all the text returned by the server
        return  assetmanager.open(p);
    }

    public static void close(Closeable in) {
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
