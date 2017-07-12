package com.ohoussein.showreader.data;

import com.ohoussein.showreader.entity.Show;

import java.util.List;
import java.util.NoSuchElementException;

import rx.Observable;

/**
 * Created by Houssein OUERGHEMMI on 12/07/17.
 * <p>
 * ouerghemmi.houssein@gmail.com
 */

public class DataManager implements IShowRepository {

    private RestService mService;

    public DataManager(RestService restService) {
        mService = restService;
    }

    private List<Show> localShowsList = null;

    @Override
    public Observable<List<Show>> getShows() {
        return mService.getShows()
                //cache list in localShowsList
                .doOnNext(shows -> localShowsList = shows);
    }

    @Override
    public Observable<Show> getShow(final long id) {
        if (localShowsList == null)
            return Observable.error(new NoSuchElementException());
        return Observable.from(localShowsList)
                .first(show -> show.getId() == id);
    }
}
