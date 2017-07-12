package com.ohoussein.showreader.injection.module;

import android.support.annotation.NonNull;

import com.ohoussein.showreader.ReleaseAppInitializer;
import com.ohoussein.showreader.app.AppInitializer;
import com.ohoussein.showreader.injection.qualifier.OkHttpInterceptors;
import com.ohoussein.showreader.injection.qualifier.OkHttpNetworkInterceptors;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;

import static java.util.Collections.emptyList;

/**
 * Created by Houssein OUERGHEMMI on 21/09/16.
 *
 * ouerghemmi.houssein@gmail.com
 */
@Module
public class AppExtensionModule {


    @Provides
    @OkHttpInterceptors
    @Singleton
    @NonNull
    public List<Interceptor> provideOkHttpInterceptors() {
        return emptyList();
    }
    @Provides
    @OkHttpNetworkInterceptors
    @Singleton
    @NonNull
    public List<Interceptor> provideOkHttpNetworkInterceptors() {
        return emptyList();
    }

    @Provides
    @Singleton
    public AppInitializer provideAppInitializer() {
        return new ReleaseAppInitializer();
    }
}
