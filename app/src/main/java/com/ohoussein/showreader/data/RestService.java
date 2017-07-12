package com.ohoussein.showreader.data;

import com.ohoussein.showreader.entity.Show;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Houssein OUERGHEMMI on 12/07/17.
 * <p>
 * ouerghemmi.houssein@gmail.com
 */

public interface RestService {

    @GET("shows")
    Observable<List<Show>> getShows();
}
