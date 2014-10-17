package com.epam.training.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.epam.training.myapplication.DataSource.DataSource;
import com.epam.training.myapplication.DataSource.Item;
import com.epam.training.myapplication.Helper.DataManager;

import java.util.List;


public class ListViewExampleActivity extends ActionBarActivity implements DataManager.Callback {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_example);
        DataManager.loadData(this);
    }
    @Override
    public void onDataLoadStart() {
        findViewById(android.R.id.progress).setVisibility(View.VISIBLE);
        findViewById(android.R.id.empty).setVisibility(View.GONE);
    }
    @Override
    public void onDone(List<Item> data) {
        findViewById(android.R.id.progress).setVisibility(View.GONE);
        if (data == null || data.isEmpty()) {
            findViewById(android.R.id.empty).setVisibility(View.VISIBLE);
        }
        AdapterView listView = (AbsListView) findViewById(android.R.id.list);
        ArrayAdapter<Item> adapter = new ArrayAdapter<Item>(this, R.layout.adapter_item, android.R.id.text1, data) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = View.inflate(ListViewExampleActivity.this, R.layout.adapter_item, null);
                }

                Item item = getItem(position);
                TextView textView1 = (TextView) convertView.findViewById(android.R.id.text1);
                textView1.setText(item.getName());
                ImageView imageView1 = (ImageView) convertView.findViewById(android.R.id.background);
                imageView1.setImageResource(R.drawable.avatar);
                return convertView;
            }
        };
        listView.setAdapter(adapter);
    }
    @Override
    public void onError(Exception e) {
        findViewById(android.R.id.progress).setVisibility(View.GONE);
        findViewById(android.R.id.empty).setVisibility(View.GONE);
        TextView errorView = (TextView) findViewById(R.id.error);
        errorView.setVisibility(View.VISIBLE);
        errorView.setText(errorView.getText() + "\n" + e.getMessage());
    }

    public void onClick(View view) {
        Item item = new Item();
        item.setName("Addtest");
        DataSource.addItem(item);

    }
}