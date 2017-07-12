package com.ohoussein.showreader.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ohoussein.showreader.R;
import com.ohoussein.showreader.entity.Show;
import com.ohoussein.showreader.ui.base.ui.BaseActivity;
import com.ohoussein.showreader.ui.presenter.ShowDetailsPresenter;
import com.ohoussein.showreader.ui.util.ViewUtils;
import com.ohoussein.showreader.ui.view.ShowDetailsView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowDetailsActivity extends BaseActivity implements ShowDetailsView {

    private final static String KEY_SHOW_ID = "KEY_SHOW_ID";

    @Inject
    ShowDetailsPresenter presenter;

    @BindView(R.id.text_show_summary)
    TextView tvSummary;
    @BindView(R.id.image_show)
    ImageView imgShow;
    private long mShowId = -1;
    private String showUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        ButterKnife.bind(this);
        activityComponent().inject(this);
        mShowId = getIntent().getLongExtra(KEY_SHOW_ID, -1);
        presenter.attachView(this);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.loadShow(mShowId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView(true);
    }

    @Override
    public void showShow(Show show) {
        //Save the show object to
        showUrl = show.getUrl();
        setTitle(show.getName());
        tvSummary.setText(ViewUtils.fromHtml(show.getSummary())); // from html compat
        Glide.with(this).load(show.getImage().getImage())
                .crossFade()
                .into(imgShow);
    }

    @Override
    public void showShowNotFound() {
        Toast.makeText(this, R.string.error_show_not_found, Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_show_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_share:
                if (showUrl == null)
                    return false;
                Intent intent2 = new Intent();
                intent2.setAction(Intent.ACTION_SEND);
                intent2.setType("text/plain");
                intent2.putExtra(Intent.EXTRA_TEXT, showUrl);
                startActivity(Intent.createChooser(intent2, getString(R.string.share_via)));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static Intent getOpenIntent(Context context, long showId) {
        Intent intent = new Intent(context, ShowDetailsActivity.class);
        intent.putExtra(KEY_SHOW_ID, showId);
        return intent;
    }
}
