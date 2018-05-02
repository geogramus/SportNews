package geogram.example.sportnews.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import geogram.example.sportnews.rest.RestClient;
import geogram.example.sportnews.rest.api.News;
import geogram.example.sportnews.rest.api.ApiTest;

/**
 * Created by geogr on 14.04.2018.
 */

@Module
public class RestModule {
    private RestClient mRestClient;
    public RestModule(){
        mRestClient=new RestClient();
    }
    @Singleton
    @Provides
    public RestClient providesRestClient(){return mRestClient;}
    @Singleton
    @Provides
    public ApiTest getImages(){return mRestClient.createService(ApiTest.class);}

    @Singleton
    @Provides
    public News getNews(){return mRestClient.createService(News.class);}

}
