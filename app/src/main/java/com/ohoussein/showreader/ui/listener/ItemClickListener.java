package com.ohoussein.showreader.ui.listener;

import android.view.View;

/**
 * Created by Houssein OUERGHEMMI on 12/07/17.
 * ouerghemmi.houssein@gmail.com
 */

public interface ItemClickListener<T> {

    void onClick(View view, T item);
}
