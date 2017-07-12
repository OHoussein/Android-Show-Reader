package com.ohoussein.showreader.helper;

import com.blankj.utilcode.util.NetworkUtils;
import com.ohoussein.showreader.config.Config;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Houssein OUERGHEMMI on 12/07/17.
 * ouerghemmi.houssein@gmail.com
 */

public class OkHttpUtils {

    public static final Interceptor REWRITE_RESPONSE_INTERCEPTOR = chain -> {
        Response originalResponse = chain.proceed(chain.request());
        String cacheControl = originalResponse.header("Cache-Control");

        if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache") ||
                cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")) {
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, max-age=" + Config.HTTP_CACHE_MAX_SIZE)
                    .build();
        } else {
            return originalResponse;
        }
    };

    public static final Interceptor OFFLINE_INTERCEPTOR = chain -> {
        Request request = chain.request();

        if (!NetworkUtils.isConnected()) {

            request = request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + Config.HTTP_CACHE_MAX_STALE)
                    .build();
        }

        return chain.proceed(request);
    };

}
