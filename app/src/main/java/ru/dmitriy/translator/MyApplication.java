package ru.dmitriy.translator;

import android.app.Application;
import android.support.annotation.NonNull;

import ru.dmitriy.translator.dagger.application.AppComponent;
import ru.dmitriy.translator.dagger.application.AppModule;
import ru.dmitriy.translator.dagger.application.DaggerAppComponent;

/**
 * Created by Dmitriy on 26.11.2017.
 */

public class MyApplication extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = prepareAppComponent().build();
    }

    @NonNull
    private DaggerAppComponent.Builder prepareAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this));
    }

    @NonNull
    public static AppComponent getAppComponent() {
        return appComponent;
    }

}
