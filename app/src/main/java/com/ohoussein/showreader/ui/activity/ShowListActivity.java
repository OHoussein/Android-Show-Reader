package com.ohoussein.showreader.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.kennyc.view.MultiStateView;
import com.ohoussein.showreader.R;
import com.ohoussein.showreader.entity.Show;
import com.ohoussein.showreader.ui.adapter.ShowAdapter;
import com.ohoussein.showreader.ui.base.ui.BaseActivity;
import com.ohoussein.showreader.ui.presenter.ShowListPresenter;
import com.ohoussein.showreader.ui.view.ShowListView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowListActivity extends BaseActivity implements ShowListView {

    @Inject
    ShowListPresenter mPresenter;
    @Inject
    ShowAdapter adapter;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.multi_state_view)
    MultiStateView multiStateView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);
        ButterKnife.bind(this);
        activityComponent().inject(this);


        mRecyclerView.setAdapter(adapter);
        int showListCountSpan = getResources().getInteger(R.integer.show_list_count_span);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(showListCountSpan, OrientationHelper.VERTICAL));
        mPresenter.attachView(this);
        mPresenter.load(savedInstanceState == null);

        adapter.setItemClickListener((view, item) ->
                startActivity(ShowDetailsActivity.getOpenIntent(this, item.getId())));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //d'ont cancel Tasks when Changing Configurations (keep loading)
        mPresenter.detachView(!isChangingConfigurations());
    }

    @Override
    public void showShows(List<Show> shows) {
        multiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
        adapter.setNewData(shows);
    }

    @Override
    public void showLoading() {
        multiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
    }

    @Override
    public void showLoadingError() {
        multiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
        multiStateView.findViewById(R.id.btn_error_retry)
                .setOnClickListener(view -> mPresenter.retry());
    }
}
