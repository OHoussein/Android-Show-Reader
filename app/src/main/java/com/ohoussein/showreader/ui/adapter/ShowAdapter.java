package com.ohoussein.showreader.ui.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ohoussein.showreader.R;
import com.ohoussein.showreader.entity.Show;
import com.ohoussein.showreader.ui.listener.ItemClickListener;

import javax.inject.Inject;

/**
 * Created by Houssein OUERGHEMMI on 12/07/17.
 * ouerghemmi.houssein@gmail.com
 */

public class ShowAdapter extends BaseQuickAdapter<Show> {

    private ItemClickListener<Show> itemClickListener;

    @Inject
    public ShowAdapter() {
        super(R.layout.item_show, null);
    }

    public void setItemClickListener(ItemClickListener<Show> itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, Show show) {
        viewHolder.setText(R.id.text_show_title, show.getName());
        Glide.with(mContext).load(show.getImage().getImage())
                .crossFade()
                .into((ImageView) viewHolder.getView(R.id.image_show));

        viewHolder.getView(R.id.card_show_item).setOnClickListener(view -> {
            if (itemClickListener != null)
                itemClickListener.onClick(view, show);
        });
    }
}
