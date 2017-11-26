package ru.dmitriy.translator;

import android.app.Application;
import android.content.Context;

/**
 * Created by Dmitriy on 26.11.2017.
 */

public class MyApplication extends Application {

    private Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

}
