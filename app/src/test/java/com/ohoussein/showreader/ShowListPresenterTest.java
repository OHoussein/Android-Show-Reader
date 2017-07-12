package com.ohoussein.showreader;

import com.ohoussein.showreader.data.DataManager;
import com.ohoussein.showreader.entity.Show;
import com.ohoussein.showreader.test.common.TestDataFactory;
import com.ohoussein.showreader.ui.presenter.ShowListPresenter;
import com.ohoussein.showreader.ui.view.ShowListView;
import com.ohoussein.showreader.util.RxSchedulersOverrideRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import rx.Observable;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Houssein OUERGHEMMI on 12/07/17.
 * ouerghemmi.houssein@gmail.com
 */
@RunWith(MockitoJUnitRunner.class)
public class ShowListPresenterTest {

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();
    @Mock
    ShowListView mMockShowListView;
    @Mock
    DataManager mMockDataManager;
    private ShowListPresenter mShowListPresenter;

    @Before
    public void setUp() {
        mShowListPresenter = new ShowListPresenter(mMockDataManager);
        mShowListPresenter.attachView(mMockShowListView);
    }

    @After
    public void tearDown() {
        mShowListPresenter.detachView(true);
    }

    @Test
    public void loadCitiesReturnsCities() {
        List<Show> shows = TestDataFactory.makeCities(10);
        when(mMockDataManager.getShows())
                .thenReturn(Observable.just(shows));

        mShowListPresenter.load(true);
        verify(mMockShowListView).showLoading();
        verify(mMockShowListView).showShows(shows);
        verify(mMockShowListView, never()).showLoadingError();
    }
}
