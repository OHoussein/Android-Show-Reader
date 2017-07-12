package com.ohoussein.showreader.ui.view;

import com.ohoussein.showreader.entity.Show;
import com.ohoussein.showreader.ui.base.ui.BaseView;

import java.util.List;

/**
 * Created by Houssein OUERGHEMMI on 12/07/17.
 * ouerghemmi.houssein@gmail.com
 */

public interface ShowListView extends BaseView {

    void showShows(List<Show> shows);

    void showLoading();

    void showLoadingError();
}
