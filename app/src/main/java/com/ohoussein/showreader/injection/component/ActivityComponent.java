package com.ohoussein.showreader.injection.component;

import com.ohoussein.showreader.injection.module.ActivityModule;
import com.ohoussein.showreader.injection.scope.PerActivity;
import com.ohoussein.showreader.ui.activity.ShowDetailsActivity;
import com.ohoussein.showreader.ui.activity.ShowListActivity;

import dagger.Subcomponent;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(ShowListActivity activity);

    void inject(ShowDetailsActivity activity);
}
