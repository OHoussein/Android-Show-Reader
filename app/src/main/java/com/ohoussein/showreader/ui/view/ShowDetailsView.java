package com.ohoussein.showreader.ui.view;

import com.ohoussein.showreader.entity.Show;
import com.ohoussein.showreader.ui.base.ui.BaseView;

/**
 * Created by Houssein OUERGHEMMI on 12/07/17.
 * ouerghemmi.houssein@gmail.com
 */

public interface ShowDetailsView extends BaseView {

    void showShow(Show show);

    void showShowNotFound();
}
