package com.ohoussein.showreader.test.common.injection.component;

import com.ohoussein.showreader.injection.component.AppComponent;
import com.ohoussein.showreader.injection.module.AppExtensionModule;
import com.ohoussein.showreader.injection.module.AppModule;
import com.ohoussein.showreader.injection.module.NetModule;
import com.ohoussein.showreader.test.common.injection.module.DataTestModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        NetModule.class,
        DataTestModule.class,
        AppExtensionModule.class})
public interface TestComponent extends AppComponent {

}
