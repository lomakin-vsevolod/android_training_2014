package com.epam.training.myapplication.processing;

import com.epam.training.myapplication.bo.User;

import org.json.JSONObject;

/**
 * Created by IstiN on 21.10.2014.
 */
public class UserArrayProcessor extends WrapperArrayProcessor<User> {

    @Override
    protected User createObject(JSONObject jsonObject) {
        return new User(jsonObject);
    }

}
