package com.ohoussein.showreader.test.common.injection.module;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ohoussein.showreader.data.DataManager;
import com.ohoussein.showreader.data.IShowRepository;
import com.ohoussein.showreader.helper.gson.DateTimeAdapter;

import org.joda.time.DateTime;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

/**
 * Provides application-level dependencies for an app running on a testing environment
 * This allows injecting mocks if necessary.
 */
@Module
public class DataTestModule {


    public DataTestModule() {
        super();
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(DateTime.class, new DateTimeAdapter())
                .create();
    }

    @Provides
    @Singleton
    public IShowRepository provideArticleRepository(DataManager dataManager) {
        return dataManager;
    }
    /************* MOCKS *************/

    @Provides
    @Singleton
    public DataManager provideDataManager() {
        return mock(DataManager.class);
    }
}
