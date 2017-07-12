package com.ohoussein.showreader.tool;

import android.app.Application;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.ohoussein.showreader.app.AppInitializer;

import rx.plugins.RxJavaHooks;
import timber.log.Timber;

/**
 * Created by houssein.elouerghemm on 17/01/2017.
 */

public class DebuggableAppInitializer implements AppInitializer {

    @Override
    public void init(Application application) {
        //Enable debug Tools
        Timber.plant(new Timber.DebugTree());
        RxJavaHooks.setOnError(e -> {
            if (e != null)
                Log.e("RxjavaError", e.getMessage(), e);
        });
        Stetho.initializeWithDefaults(application);
        //Fabric

    }
}
