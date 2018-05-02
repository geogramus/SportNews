package geogram.example.sportnews;

import android.app.Application;

import geogram.example.sportnews.di.component.ApplicationComponent;
import geogram.example.sportnews.di.component.DaggerApplicationComponent;
import geogram.example.sportnews.di.module.ApplicationModule;

/**
 * Created by geogr on 14.04.2018.
 */

public class MyApplication extends Application{
    private static ApplicationComponent sApplicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
    }
    private void initComponent(){
        sApplicationComponent= DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
    }
    public static ApplicationComponent getsApplicationComponent(){
        return sApplicationComponent;
    }
}
