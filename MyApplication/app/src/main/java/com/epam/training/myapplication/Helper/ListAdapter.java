package com.epam.training.myapplication.Helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.epam.training.myapplication.DataSource.Item;
import com.epam.training.myapplication.R;

import java.util.List;

/**
 * Created by NuclearOK on 17.10.2014.
 */
public class ListAdapter extends ArrayAdapter<Item> {
    private final Context context;
    private final List<Item> data;


    public ListAdapter(Context context, List<Item> data) {
        super(context, R.layout.activity_list_view_example, data);
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        return null;
    }
}