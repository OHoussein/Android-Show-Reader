package com.ohoussein.showreader.ui.presenter;

import com.ohoussein.showreader.data.IShowRepository;
import com.ohoussein.showreader.ui.base.presenter.BasePresenter;
import com.ohoussein.showreader.ui.view.ShowDetailsView;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Houssein OUERGHEMMI on 12/07/17.
 * ouerghemmi.houssein@gmail.com
 */

public class ShowDetailsPresenter extends BasePresenter<ShowDetailsView> {

    private final IShowRepository mShowRepository;

    @Inject
    public ShowDetailsPresenter(IShowRepository mShowRepository) {
        this.mShowRepository = mShowRepository;
    }

    public void loadShow(long showId) {
        mShowRepository.getShow(showId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(show -> getMvpView().showShow(show),
                        throwable -> getMvpView().showShowNotFound())
        ;
    }
}
