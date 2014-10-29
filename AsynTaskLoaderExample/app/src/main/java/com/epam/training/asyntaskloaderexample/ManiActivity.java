package com.epam.training.asyntaskloaderexample;

import android.annotation.TargetApi;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ManiActivity extends ActionBarActivity implements LoaderManager.LoaderCallbacks<String> {

    static final int LOADER_EXAMPLE_ID = 10;

    static int Entry = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mani);


        EditText editText = (EditText) findViewById(R.id.text);
        Bundle bundle = new Bundle();
        bundle.putString("StringExample",editText.getText().toString());
        getLoaderManager().initLoader(LOADER_EXAMPLE_ID,bundle,this);

    }


    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        Loader<String> loader = null;
        if (id == LOADER_EXAMPLE_ID) {
            loader = new TestLoader(this, args);
        }
        Log.d("MyLog", "onCreateLoader" );
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String result) {
        TextView textView = (TextView) findViewById(R.id.example);
        textView.setText(result);
        Log.d("MyLog", "onLoadFinished" );
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        Log.d("MyLog", "onLoaderReset" );

    }

    public void onStartLoad(View view) {
        Loader<String> loader;

        if (Entry == 0){
            loader = getLoaderManager().getLoader(LOADER_EXAMPLE_ID);
            Entry = 1;
            loader.forceLoad();
        }
        else {
            EditText editText = (EditText) findViewById(R.id.text);
            Bundle bundle = new Bundle();
            bundle.putString("StringExample", editText.getText().toString());
            loader = getLoaderManager().restartLoader(LOADER_EXAMPLE_ID, bundle, this);
            loader.forceLoad();
        }

    }
}
