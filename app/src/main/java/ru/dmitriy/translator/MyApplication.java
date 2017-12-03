package ru.dmitriy.translator;

import android.app.Application;
import android.support.annotation.NonNull;

import ru.dmitriy.translator.dagger.application.AppComponent;
import ru.dmitriy.translator.dagger.application.DaggerAppComponent;

/**
 * Created by Dmitriy on 26.11.2017.
 */

public class MyApplication extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.create();
    }

    @NonNull
    public static AppComponent getAppComponent() {
        return appComponent;
    }

}
