package com.epam.training.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private static final int REQUEST_LOGIN = 0;

    public int attempts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tView= (TextView)findViewById(R.id.Attempts);
        tView.setText(new String().valueOf(attempts));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LOGIN && resultCode == RESULT_OK) {
            EditText Login = (EditText)findViewById(R.id.Login);
            EditText Password = (EditText)findViewById(R.id.Pass);
            Login.setText(data.getStringExtra("Login"));
            Password.setText(data.getStringExtra("Password"));
        }
    }

    public void onLoginClick(View view) {
        attempts++;
        TextView tView= (TextView)findViewById(R.id.Attempts);
        tView.setText(new String().valueOf(attempts));
    }

    public void onCreateClick(View view) {
        Intent intent = new Intent(this, CreateUser.class);
        startActivityForResult(intent, REQUEST_LOGIN);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("Attempts",attempts);
        super.onSaveInstanceState(savedInstanceState);

    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        attempts=savedInstanceState.getInt("Attempts");
        TextView tView= (TextView)findViewById(R.id.Attempts);
        tView.setText(new String().valueOf(attempts));
    }
}
