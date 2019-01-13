package com.example.avinashravilla.sharkfeed.activities;

import android.app.Application;
import android.content.Context;

public class App extends Application {

    public static Context mContext;

    public App() {
        mContext = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    /**
     *
     * @return
     * application level context
     */
    public static Context getContext() {
        return mContext;
    }
}
