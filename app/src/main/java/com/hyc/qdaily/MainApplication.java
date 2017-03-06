package com.hyc.qdaily;

import android.app.Application;

/**
 * Created by hyc on 2017/3/3.
 */

public class MainApplication extends Application {
    private static MainApplication sApplication;


    @Override public void onCreate() {
        super.onCreate();
        sApplication=this;
    }

    public static MainApplication getInstance(){
        return sApplication;
    }
}
