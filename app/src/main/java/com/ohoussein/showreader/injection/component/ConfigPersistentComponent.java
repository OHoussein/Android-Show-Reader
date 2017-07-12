package com.ohoussein.showreader.injection.component;

import com.ohoussein.showreader.injection.module.ActivityModule;
import com.ohoussein.showreader.injection.scope.ConfigPersistent;
import com.ohoussein.showreader.ui.base.ui.BaseActivity;

import dagger.Component;

/**
 * A dagger component that will live during the lifecycle of an Activity but it won't
 * be destroy during configuration changes. Check {@link BaseActivity} to see how this components
 * survives configuration changes.
 * Use the {@link ConfigPersistent} scope to annotate dependencies that need to survive
 * configuration changes (for example Presenters).
 */
@ConfigPersistent
@Component(dependencies = AppComponent.class)
public interface ConfigPersistentComponent {

    ActivityComponent activityComponent(ActivityModule activityModule);

}