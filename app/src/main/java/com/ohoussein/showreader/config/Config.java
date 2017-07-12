package com.ohoussein.showreader.config;

/**
 * Created by Houssein OUERGHEMMI on 20/09/16.
 *
 * ouerghemmi.houssein@gmail.com
 */

public class Config {

    /**
     * Release base url
     */
    public static final String API_BASE_URL = "http://api.tvmaze.com";

    public static final int HTTP_CONNECTION_TIMEOUT = 30; //in seconds

    public static final int HTTP_CACHE_FILE_SIZE = 20 * 1024 * 1024; // 20 MB
    public static final int HTTP_CACHE_MAX_SIZE = 60; // in seconds
    public static final int HTTP_CACHE_MAX_STALE = 60 * 60 * 24 * 7; // 7 days



}
