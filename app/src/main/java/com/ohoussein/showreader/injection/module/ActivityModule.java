package com.ohoussein.showreader.injection.module;

import android.app.Activity;
import android.content.Context;

import com.ohoussein.showreader.injection.qualifier.ActivityContext;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    public Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    public Context providesContext() {
        return mActivity;
    }
}
