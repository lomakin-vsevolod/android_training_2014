package com.epam.training.myapplication.source;

import android.graphics.Bitmap;

/**
 * Created by NuclearOK on 17.10.2014.
 */
public class Item {

    private String Name;
    private Bitmap Avatar;

    public void setName(String name) {
        Name = name;
    }
    public String getName() {
        return Name;
    }

    public Bitmap getAvatar() {
        return Avatar;
    }
    public void setAvatar(Bitmap avatar) {
        Avatar = avatar;
    }
}
