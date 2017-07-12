package com.ohoussein.showreader.injection.module;

import android.app.Application;
import android.content.Context;

import com.ohoussein.showreader.injection.scope.ApplicationContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Houssein OUERGHEMMI on 20/09/16.
 *
 * ouerghemmi.houssein@gmail.com
 */
@Module
public class AppModule {
    protected final Application mApplication;

    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }
    @Provides
    public Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }
}
