package com.ohoussein.showreader.ui.base.presenter;

import com.ohoussein.showreader.ui.base.ui.BaseView;

/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MvpView type that wants to be attached with.
 */
public interface Presenter<V extends BaseView> {

    void attachView(V mvpView);

    void detachView(boolean cancelAllTasks);
}
