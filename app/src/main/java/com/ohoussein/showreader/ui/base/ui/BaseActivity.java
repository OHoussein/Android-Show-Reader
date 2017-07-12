package com.ohoussein.showreader.ui.base.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ohoussein.showreader.App;
import com.ohoussein.showreader.injection.component.ActivityComponent;
import com.ohoussein.showreader.injection.component.ConfigPersistentComponent;
import com.ohoussein.showreader.injection.component.DaggerConfigPersistentComponent;
import com.ohoussein.showreader.injection.module.ActivityModule;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import icepick.Icepick;
import icepick.State;
import timber.log.Timber;

/**
 * Created by Houssein OUERGHEMMI on 21/09/16.
 *
 * ouerghemmi.houssein@gmail.com
 */

public class BaseActivity extends AppCompatActivity implements BaseView {

    private static final AtomicLong NEXT_ID = new AtomicLong(0);

    private static final HashMap<Long, ConfigPersistentComponent> sComponentsMap = new HashMap<>();
    @State
    long mActivityId;
    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        // Create the ActivityComponent and reuses cached ConfigPersistentComponent if this is
        // being called after a configuration change.
        if (savedInstanceState == null)
            mActivityId = NEXT_ID.getAndIncrement();
        ConfigPersistentComponent configPersistentComponent;
        if (!sComponentsMap.containsKey(mActivityId)) {
            Timber.i("Creating new ConfigPersistentComponent id=%d", mActivityId);
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .appComponent(App.get(this).getComponent())
                    .build();
            sComponentsMap.put(mActivityId, configPersistentComponent);
        } else {
            Timber.i("Reusing ConfigPersistentComponent id=%d", mActivityId);
            configPersistentComponent = sComponentsMap.get(mActivityId);
        }
        mActivityComponent = configPersistentComponent.activityComponent(new ActivityModule(this));

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    protected void onDestroy() {
        if (!isChangingConfigurations()) {
            Timber.i("Clearing ConfigPersistentComponent id=%d", mActivityId);
            sComponentsMap.remove(mActivityId);
        }
        super.onDestroy();
    }

    public ActivityComponent activityComponent() {
        return mActivityComponent;
    }
}
