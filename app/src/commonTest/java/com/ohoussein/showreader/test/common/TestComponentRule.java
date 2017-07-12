package com.ohoussein.showreader.test.common;

import android.content.Context;

import com.ohoussein.showreader.App;
import com.ohoussein.showreader.data.DataManager;
import com.ohoussein.showreader.injection.module.AppExtensionModule;
import com.ohoussein.showreader.injection.module.AppModule;
import com.ohoussein.showreader.injection.module.NetModule;
import com.ohoussein.showreader.test.common.injection.component.DaggerTestComponent;
import com.ohoussein.showreader.test.common.injection.component.TestComponent;
import com.ohoussein.showreader.test.common.injection.module.DataTestModule;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;


/**
 * Test rule that creates and sets a Dagger TestComponent into the application overriding the
 * existing application component.
 * Use this rule in your test case in order for the app to use mock dependencies.
 * It also exposes some of the dependencies so they can be easily accessed from the tests, e.g. to
 * stub mocks etc.
 */
public class TestComponentRule implements TestRule {

    private final TestComponent mTestComponent;
    private final Context mContext;

    public TestComponentRule(Context context) {
        mContext = context;
        App application = App.get(context);
        mTestComponent = DaggerTestComponent.builder()
                .appModule(new AppModule(application))
                .netModule(new NetModule())
                .appExtensionModule(new AppExtensionModule())
                .dataTestModule(new DataTestModule())
                .build();
    }

    public Context getContext() {
        return mContext;
    }

    public DataManager getMockDataManager() {
        return mTestComponent.dataManager();
    }

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                App application = App.get(mContext);
                application.setComponent(mTestComponent);
                base.evaluate();
                application.setComponent(null);
            }
        };
    }
}
