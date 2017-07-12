package com.ohoussein.showreader.ui;

/**
 * Created by Houssein OUERGHEMMI on 23/04/17.
 * ouerghemmi.houssein@gmail.com
 */

public class DefaultViewState<D> {

    public static final int STATE_CONTENT = 1;
    public static final int STATE_LOADING = 2;
    public static final int STATE_ERROR = 3;

    private D mContent;
    private int mState = -1;
    private Throwable mError;

    public DefaultViewState() {
    }

    public D getContent() {
        return mContent;
    }

    public DefaultViewState<D> setContent(D content) {
        this.mContent = content;
        return this;
    }

    public int getState() {
        return mState;
    }

    public DefaultViewState<D> setState(int state) {
        this.mState = state;
        return this;
    }

    public Throwable getError() {
        return mError;
    }

    public DefaultViewState<D> setError(Throwable error) {
        this.mError = error;
        return this;
    }

}
