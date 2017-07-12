package com.ohoussein.showreader.ui.presenter;

import com.ohoussein.showreader.data.IShowRepository;
import com.ohoussein.showreader.entity.Show;
import com.ohoussein.showreader.helper.RxUtil;
import com.ohoussein.showreader.injection.scope.ConfigPersistent;
import com.ohoussein.showreader.ui.DefaultViewState;
import com.ohoussein.showreader.ui.base.presenter.BasePresenter;
import com.ohoussein.showreader.ui.view.ShowListView;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Houssein OUERGHEMMI on 12/07/17.
 * ouerghemmi.houssein@gmail.com
 */

@ConfigPersistent
public class ShowListPresenter extends BasePresenter<ShowListView> {

    private final IShowRepository mShowRepository;
    private Subscription mSubscription;
    private DefaultViewState<List<Show>> mViewState = new DefaultViewState<>();

    @Inject
    public ShowListPresenter(IShowRepository mShowRepository) {
        this.mShowRepository = mShowRepository;
    }

    @Override
    public void detachView(boolean cancelAllTasks) {
        super.detachView(cancelAllTasks);
        if (cancelAllTasks && mSubscription != null) mSubscription.unsubscribe();
    }

    public void load(boolean forceRefresh) {
        checkViewAttached();
        if (!forceRefresh && mSubscription != null && !mSubscription.isUnsubscribed()) {
            applyViewState();
        } else {
            RxUtil.unsubscribe(mSubscription);
            mSubscription = mShowRepository.getShows()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(() -> {
                        mViewState.setState(DefaultViewState.STATE_LOADING);
                        applyViewState();
                    })
                    .subscribe(
                            shows -> {
                                Timber.d("Shows loaded");
                                mViewState.setState(DefaultViewState.STATE_CONTENT);
                                mViewState.setContent(shows);
                                applyViewState();
                            }, throwable -> {
                                Timber.e("setState error: %s", throwable.getMessage());
                                mViewState.setState(DefaultViewState.STATE_ERROR);
                                mViewState.setError(throwable);
                                applyViewState();
                            });
        }
    }

    public void retry() {
        load(false);
    }

    private void applyViewState() {
        if (!isViewAttached()) {
            Timber.e("Apply view state for unattached view !");
            return;
        }
        switch (mViewState.getState()) {
            case DefaultViewState.STATE_CONTENT:
                getMvpView().showShows(mViewState.getContent());
                break;
            case DefaultViewState.STATE_LOADING:
                getMvpView().showLoading();
                break;
            case DefaultViewState.STATE_ERROR:
                getMvpView().showLoadingError();
                break;
            default:
                Timber.e("Unknow view state %d", mViewState.getState());
                break;
        }
    }
}
