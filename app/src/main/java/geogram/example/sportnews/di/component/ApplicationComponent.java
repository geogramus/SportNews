package geogram.example.sportnews.di.component;

import javax.inject.Singleton;

import dagger.Component;
import geogram.example.sportnews.mvp.presenters.MainFragmentPresenter;
import geogram.example.sportnews.ui.activityes.MainActivity;
import geogram.example.sportnews.ui.activityes.SplashActivity;
import geogram.example.sportnews.di.module.ApplicationModule;
import geogram.example.sportnews.di.module.RestModule;
import geogram.example.sportnews.ui.fragments.MainFragment;

/**
 * Created by geogr on 14.04.2018.
 */

@Singleton
@Component(modules = {ApplicationModule.class, RestModule.class})
public interface ApplicationComponent {
    void inject(SplashActivity activity);
    void inject(MainFragmentPresenter presenter);
}
