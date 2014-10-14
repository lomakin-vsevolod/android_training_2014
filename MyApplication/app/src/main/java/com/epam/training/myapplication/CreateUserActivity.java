package com.epam.training.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class CreateUserActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
    }

    public void onOkClick(View view) {
        Intent UserInformation = new Intent();
        EditText eLogin = (EditText)findViewById(R.id.Login);
        EditText ePassword = (EditText)findViewById(R.id.Pass);
        UserInformation.putExtra("Login", eLogin.getText().toString());
        UserInformation.putExtra("Password", ePassword.getText().toString());
        setResult(RESULT_OK, UserInformation);
        finish();
    }

    public void onCancelClick(View view) {
        finish();
    }
}
