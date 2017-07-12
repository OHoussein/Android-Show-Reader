package com.ohoussein.showreader.injection.component;

import com.ohoussein.showreader.app.AppInitializer;
import com.ohoussein.showreader.data.DataManager;
import com.ohoussein.showreader.data.IShowRepository;
import com.ohoussein.showreader.injection.module.AppExtensionModule;
import com.ohoussein.showreader.injection.module.AppModule;
import com.ohoussein.showreader.injection.module.DataModule;
import com.ohoussein.showreader.injection.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;


/**
 * Created by Houssein OUERGHEMMI on 20/09/16.
 * <p>
 * ouerghemmi.houssein@gmail.com
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class, DataModule.class, AppExtensionModule.class})
public interface AppComponent {

    AppInitializer appInitializer();

    DataManager dataManager();

    IShowRepository showRepository();
}
