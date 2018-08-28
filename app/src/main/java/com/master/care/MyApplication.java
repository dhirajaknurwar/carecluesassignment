package com.master.care;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.master.care.network.NetworkModule;


import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;


public class MyApplication extends DaggerApplication {

    public static final String TAG = MyApplication.class.getSimpleName();

    private static MyApplication _instance;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        _instance = this;
        Fresco.initialize(this);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder()
                .application(this)
                .networkModule(new NetworkModule(BuildConfig.SERVER_URL))
                .build();
        appComponent.inject(this);
        return appComponent;
    }

    public static MyApplication getInstance() {
        return _instance;
    }
}
