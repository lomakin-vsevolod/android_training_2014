package com.epam.training.myapplication.DataSource;

import android.content.Context;
import android.text.format.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NuclearOK on 17.10.2014.
 */
public class DataSource {

    private static final List<Item> DATA;

    static {
        DATA = new ArrayList<Item>();
        for (int i = 0; i < 21; i++) {
            Item item = new Item();
            item.setName(i+". Test");

            DATA.add(item);
        }
    }
    public static List<Item> getData() throws Exception {
        Thread.currentThread().sleep(DateUtils.SECOND_IN_MILLIS * 1);
        return DATA;
    }

    public static void addItem(Item item)  {
        DATA.add(item);
    }
}