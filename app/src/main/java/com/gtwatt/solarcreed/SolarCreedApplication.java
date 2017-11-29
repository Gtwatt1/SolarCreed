package com.gtwatt.solarcreed;

import android.app.Application;

import com.orm.SugarContext;

/**
 * Created by Gtwatt on 11/21/17.
 */

public class SolarCreedApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
