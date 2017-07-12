package com.ohoussein.showreader.injection.module;

import android.app.Application;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.ohoussein.showreader.BuildConfig;
import com.ohoussein.showreader.config.Config;
import com.ohoussein.showreader.data.RestService;
import com.ohoussein.showreader.helper.OkHttpUtils;
import com.ohoussein.showreader.injection.qualifier.OkHttpInterceptors;
import com.ohoussein.showreader.injection.qualifier.OkHttpNetworkInterceptors;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Houssein OUERGHEMMI on 21/09/16.
 * <p>
 * ouerghemmi.houssein@gmail.com
 */
@Module
public class NetModule {

    /**
     * Provide  okhttpclient
     * interceptors can be used for extension(add user token on the http header for example)
     *
     * @param app                 the Application context
     * @param interceptors        interceptors that implements {@link Interceptor}
     * @param networkInterceptors
     * @return the okhttp client
     */
    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Application app,
                                            @OkHttpInterceptors
                                            @NonNull List<Interceptor> interceptors,
                                            @OkHttpNetworkInterceptors
                                            @NonNull List<Interceptor> networkInterceptors) {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder()
                .connectTimeout(Config.HTTP_CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Config.HTTP_CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        for (Interceptor interceptor : interceptors) {
            okHttpBuilder.addInterceptor(interceptor);
        }

        for (Interceptor networkInterceptor : networkInterceptors) {
            okHttpBuilder.addNetworkInterceptor(networkInterceptor);
        }
        //Offline mode
        File httpCacheDirectory = new File(app.getCacheDir(), "network_cache");
        okHttpBuilder
                .cache(new Cache(httpCacheDirectory, Config.HTTP_CACHE_FILE_SIZE))
                .addInterceptor(OkHttpUtils.OFFLINE_INTERCEPTOR)
                .addNetworkInterceptor(OkHttpUtils.REWRITE_RESPONSE_INTERCEPTOR);
        return okHttpBuilder.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Config.API_BASE_URL)
                // Fail early: check Retrofit configuration at creation time in Debug build.
                .validateEagerly(BuildConfig.DEBUG)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public RestService provideRestService(Retrofit retrofit) {
        return retrofit.create(RestService.class);
    }
}
