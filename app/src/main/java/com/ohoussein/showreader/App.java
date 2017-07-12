package com.ohoussein.showreader;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.Utils;
import com.ohoussein.showreader.injection.component.AppComponent;
import com.ohoussein.showreader.injection.component.DaggerAppComponent;
import com.ohoussein.showreader.injection.module.AppExtensionModule;
import com.ohoussein.showreader.injection.module.AppModule;
import com.ohoussein.showreader.injection.module.DataModule;
import com.ohoussein.showreader.injection.module.NetModule;

/**
 * Created by Houssein OUERGHEMMI on 20/09/16.
 *
 * ouerghemmi.houssein@gmail.com
 */

public class App extends Application {

    AppComponent mAppComponent;

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        getComponent().appInitializer().init(this);
    }

    public AppComponent getComponent() {
        if (mAppComponent == null) {
            mAppComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .netModule(new NetModule())
                    .appExtensionModule(new AppExtensionModule())
                    .dataModule(new DataModule())
                    .build();
        }
        return mAppComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(AppComponent applicationComponent) {
        mAppComponent = applicationComponent;
    }
    
}
