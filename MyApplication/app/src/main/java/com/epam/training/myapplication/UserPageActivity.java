package com.epam.training.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class UserPageActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        TextView tView= (TextView)findViewById(R.id.Message);
        tView.setText("Hello " +getIntent().getExtras().getString("Login")+"!");
    }


    public void onClick(View view) {
        startActivity(new Intent(this,SampleActivity.class));
    }
}
