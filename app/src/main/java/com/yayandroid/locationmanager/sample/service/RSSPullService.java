package com.yayandroid.locationmanager.sample.service;

import android.app.IntentService;
import android.content.Intent;


public class RSSPullService extends IntentService {
    public RSSPullService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {
        // Gets data from the incoming Intent
        String dataString = workIntent.getDataString();

        // Do work here, based on the contents of dataString

    }
}

