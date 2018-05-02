package geogram.example.sportnews.rest.api;

import geogram.example.sportnews.rest.models.FootballRespose.NewsArray;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by geogr on 14.04.2018.
 */

public interface News {

    @GET("news")
    Call<NewsArray> getNews();

    @GET("news/football")
    Call<NewsArray> getFootball();

    @GET("news/basketball")
    Call<NewsArray> getBasketball();

    @GET("news/volleyball")
    Call<NewsArray> getVolleyball();

    @GET("news/hockey")
    Call<NewsArray> getHockey();
}
