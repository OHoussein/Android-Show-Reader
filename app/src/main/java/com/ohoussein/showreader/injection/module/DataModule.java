package com.ohoussein.showreader.injection.module;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ohoussein.showreader.data.DataManager;
import com.ohoussein.showreader.data.IShowRepository;
import com.ohoussein.showreader.data.RestService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Houssein OUERGHEMMI on 12/07/17.
 * <p>
 * ouerghemmi.houssein@gmail.com
 */
@Module
public class DataModule {

    @Provides
    @Singleton
    public Gson provideGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    @Provides
    @Singleton
    public DataManager provideDataManager(RestService restService) {
        return new DataManager(restService);
    }

    @Provides
    @Singleton
    public IShowRepository provideShowRepository(DataManager dataManager) {
        return dataManager;
    }

}
