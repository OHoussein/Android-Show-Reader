package com.ohoussein.showreader.injection.module;

import android.support.annotation.NonNull;

import com.ohoussein.showreader.app.AppInitializer;
import com.ohoussein.showreader.injection.qualifier.OkHttpInterceptors;
import com.ohoussein.showreader.injection.qualifier.OkHttpNetworkInterceptors;
import com.ohoussein.showreader.tool.DebuggableAppInitializer;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;

import static java.util.Collections.singletonList;

/**
 * Created by Houssein OUERGHEMMI on 21/09/16.
 *
 * ouerghemmi.houssein@gmail.com
 */
@Module
public class AppExtensionModule {


    @Provides
    @Singleton
    @NonNull
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }
    @Provides
    @OkHttpInterceptors
    @Singleton
    @NonNull
    public List<Interceptor> provideOkHttpInterceptors(@NonNull HttpLoggingInterceptor intercept) {
        return singletonList(intercept);
    }
    @Provides
    @OkHttpNetworkInterceptors
    @Singleton
    @NonNull
    public List<Interceptor> provideOkHttpNetworkInterceptors() {
        return singletonList(new com.facebook.stetho.okhttp3.StethoInterceptor());
    }

    @Provides
    @Singleton
    public AppInitializer provideAppInitializer() {
        return new DebuggableAppInitializer();
    }
}
