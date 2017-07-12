package com.ohoussein.showreader;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ohoussein.showreader.entity.Show;
import com.ohoussein.showreader.test.common.TestComponentRule;
import com.ohoussein.showreader.test.common.TestDataFactory;
import com.ohoussein.showreader.ui.activity.ShowListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import java.util.List;

import rx.Observable;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.when;

/**
 * Created by Houssein OUERGHEMMI on 12/07/17.
 * ouerghemmi.houssein@gmail.com
 */
@RunWith(AndroidJUnit4.class)
public class ShowListActivityTest {


    private final TestComponentRule mComponent =
            new TestComponentRule(InstrumentationRegistry.getTargetContext());
    private final ActivityTestRule<ShowListActivity> mCityListTestRule =
            new ActivityTestRule<>(ShowListActivity.class, false, false);

    // TestComponentRule needs to go first to make sure the Dagger ApplicationTestComponent is set
    // in the Application before any Activity is launched.
    @Rule
    public final TestRule chain = RuleChain.outerRule(mComponent).around(mCityListTestRule);


    @Test
    public void listOfCitiesShows() {
        List<Show> testDataShows = TestDataFactory.makeCities(20);
        when(mComponent.getMockDataManager().getShows())
                .thenReturn(Observable.just(testDataShows));

        mCityListTestRule.launchActivity(null);

        int position = 0;
        for (Show show : testDataShows) {
            onView(withId(R.id.recycler_view))
                    .perform(RecyclerViewActions.scrollToPosition(position));
            onView(withText(show.getName()))
                    .check(matches(isDisplayed()));
            position++;
        }
    }


}
