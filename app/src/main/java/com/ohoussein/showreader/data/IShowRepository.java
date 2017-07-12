package com.ohoussein.showreader.data;

import com.ohoussein.showreader.entity.Show;

import java.util.List;

import rx.Observable;

/**
 * Created by Houssein OUERGHEMMI on 1  2/07/17.
 * ouerghemmi.houssein@gmail.com
 */

public interface IShowRepository {

    Observable<List<Show>> getShows();

    Observable<Show> getShow(long id);

}
