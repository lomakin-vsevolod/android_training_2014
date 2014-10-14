package com.epam.training.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private static final int REQUEST_LOGIN = 0;

    public int attempts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tView= (TextView)findViewById(R.id.Attempts);
        tView.setText(String.valueOf(attempts));
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
        EditText Login = (EditText)findViewById(R.id.Login);
        if (Login.getText().toString().equals("Seva")) {
            Intent intent = new Intent(this, UserPageActivity.class);
            intent.putExtra("Login", Login.getText().toString());
            startActivity(intent);
        } else{
            Toast toast = Toast.makeText(getApplicationContext(),"Wrong!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onCreateClick(View view) {
        Intent intent = new Intent(this, CreateUserActivity.class);
        startActivityForResult(intent, REQUEST_LOGIN);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("Attempts", attempts);
        super.onSaveInstanceState(savedInstanceState);

    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        attempts=savedInstanceState.getInt("Attempts");
        TextView tView= (TextView)findViewById(R.id.Attempts);
        tView.setText(String.valueOf(attempts));
    }
}
