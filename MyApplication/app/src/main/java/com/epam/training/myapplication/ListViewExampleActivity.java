package com.epam.training.myapplication;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.epam.training.myapplication.bo.Note;
import com.epam.training.myapplication.bo.NoteGsonModel;
import com.epam.training.myapplication.callbacks.SimpleCallback;
import com.epam.training.myapplication.helper.DataManager;
import com.epam.training.myapplication.processing.NoteArrayProcessor;
import com.epam.training.myapplication.processing.RedirectProcessor;
import com.epam.training.myapplication.processing.StringProcessor;
import com.epam.training.myapplication.source.ArrayStringDataSource;
import com.epam.training.myapplication.source.FileDataSource;
import com.epam.training.myapplication.source.HttpDataSource;

import java.util.ArrayList;
import java.util.List;


public class ListViewExampleActivity extends ActionBarActivity implements DataManager.Callback<List<Note>> {

    public static final String URL = "https://dl.dropboxusercontent.com/u/16403954/test.json";
    private ArrayAdapter mAdapter;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        setContentView(R.layout.activity_list_view_example);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        final HttpDataSource dataSource = HttpDataSource.get(ListViewExampleActivity.this);
        final NoteArrayProcessor processor = new NoteArrayProcessor();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                DataManager.loadData(ListViewExampleActivity.this,
                        URL,
                        dataSource,
                        processor);
            }
        });
        DataManager.loadData(ListViewExampleActivity.this,
                URL,
                dataSource,
                processor);
      /* DataManager.loadData(ListViewExampleActivity.this,
         /      "test.txt",
                filedataSource,
                fileprocessor);*/

    }

    @Override
    public void onDataLoadStart() {
        if (!mSwipeRefreshLayout.isRefreshing()) {
            findViewById(android.R.id.progress).setVisibility(View.VISIBLE);
        }
        findViewById(android.R.id.empty).setVisibility(View.GONE);
    }

    private List<Note> mData;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onDone(List<Note> data) {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        findViewById(android.R.id.progress).setVisibility(View.GONE);
        if (data == null || data.isEmpty()) {
            findViewById(android.R.id.empty).setVisibility(View.VISIBLE);
        }
        AdapterView listView = (AbsListView) findViewById(android.R.id.list);
        if (mAdapter == null) {
            mData = data;
            mAdapter = new ArrayAdapter<Note>(this, R.layout.adapter_item, android.R.id.text1, data) {

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    if (convertView == null) {
                        convertView = View.inflate(ListViewExampleActivity.this, R.layout.adapter_item, null);
                    }
                    Note item = getItem(position);
                    TextView textView1 = (TextView) convertView.findViewById(android.R.id.text1);
                    textView1.setText(item.getTitle());
                    TextView textView2 = (TextView) convertView.findViewById(android.R.id.text2);
                    textView2.setText(item.getContent());
                    convertView.setTag(item.getId());
                    return convertView;
                }

            };
            listView.setAdapter(mAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(ListViewExampleActivity.this, DetailsActivity.class);
                    Note item = (Note) mAdapter.getItem(position);
                    NoteGsonModel note = new NoteGsonModel(item.getId(), item.getTitle(), item.getContent());
                    intent.putExtra("item", note);
                    startActivity(intent);
                }
            });
        } else {
            mData.clear();
            mData.addAll(data);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onError(Exception e) {
        e.printStackTrace();
        findViewById(android.R.id.progress).setVisibility(View.GONE);
        findViewById(android.R.id.empty).setVisibility(View.GONE);
        TextView errorView = (TextView) findViewById(R.id.error);
        errorView.setVisibility(View.VISIBLE);
        errorView.setText(errorView.getText() + "\n" + e.getMessage());
    }

}