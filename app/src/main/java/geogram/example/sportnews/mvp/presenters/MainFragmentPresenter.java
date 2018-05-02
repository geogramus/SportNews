package geogram.example.sportnews.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import geogram.example.sportnews.MyApplication;
import geogram.example.sportnews.mvp.views.MainFragmentView;
import geogram.example.sportnews.rest.api.News;
import geogram.example.sportnews.rest.models.FootballRespose.NewsArray;
import geogram.example.sportnews.rest.models.FootballRespose.NewsItem;
import geogram.example.sportnews.ui.fragments.MainFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by geogr on 15.04.2018.
 */
@InjectViewState
public class MainFragmentPresenter extends MvpPresenter<MainFragmentView> {
    @Inject
    News news;

    public MainFragmentPresenter() {
        MyApplication.getsApplicationComponent().inject(this);
    }

    public void getItems(String newsType) {

            switch (newsType) {

                case MainFragment.news:
                    getNews();
                    break;
                case MainFragment.football:
                    getFootball();
                    break;
                case MainFragment.basketBall:
                    getBasketball();
                    break;
                case MainFragment.volleyBall:
                    getVolleyball();
                    break;
                case MainFragment.hockey:
                    getHockey();
                    break;
            }



    }

    private void getNews() {
        news.getNews().enqueue(new Callback<NewsArray>() {
            @Override
            public void onResponse(Call<NewsArray> call, Response<NewsArray> response) {
                if (response == null) {
                    getViewState().errorLoading("Load Error, check internet connection  and refresh.");
                } else {
                    List<NewsItem> list = new ArrayList<>();
                    NewsArray array = response.body();
                    list = array.getData();
                    getViewState().addItems(list);
                }
            }

            @Override
            public void onFailure(Call<NewsArray> call, Throwable t) {

            }
        });

    }

    private void getFootball() {
        news.getFootball().enqueue(new Callback<NewsArray>() {
            @Override
            public void onResponse(Call<NewsArray> call, Response<NewsArray> response) {
                if (response == null) {
                    getViewState().errorLoading("Load Error, check internet connection  and refresh.");
                } else {
                    List<NewsItem> list = new ArrayList<>();
                    NewsArray array = response.body();
                    list = array.getData();
                    getViewState().addItems(list);
                }
            }

            @Override
            public void onFailure(Call<NewsArray> call, Throwable t) {
            }
        });

    }

    private void getBasketball() {
        news.getBasketball().enqueue(new Callback<NewsArray>() {
            @Override
            public void onResponse(Call<NewsArray> call, Response<NewsArray> response) {
                if (response == null) {
                    getViewState().errorLoading("Load Error, check internet connection  and refresh.");
                } else {
                    List<NewsItem> list = new ArrayList<>();
                    NewsArray array = response.body();
                    list = array.getData();
                    getViewState().addItems(list);
                }
            }

            @Override
            public void onFailure(Call<NewsArray> call, Throwable t) {

            }
        });

    }

    private void getVolleyball() {
        news.getVolleyball().enqueue(new Callback<NewsArray>() {
            @Override
            public void onResponse(Call<NewsArray> call, Response<NewsArray> response) {
                if (response == null) {
                    getViewState().errorLoading("Load Error, check internet connection  and refresh.");
                } else {
                    List<NewsItem> list = new ArrayList<>();
                    NewsArray array = response.body();
                    list = array.getData();
                    getViewState().addItems(list);
                }
            }

            @Override
            public void onFailure(Call<NewsArray> call, Throwable t) {

            }
        });

    }

    private void getHockey() {
        news.getHockey().enqueue(new Callback<NewsArray>() {
            @Override
            public void onResponse(Call<NewsArray> call, Response<NewsArray> response) {
                if (response == null) {
                    getViewState().errorLoading("Load Error, check internet connection  and refresh.");
                } else {
                    List<NewsItem> list = new ArrayList<>();
                    NewsArray array = response.body();
                    list = array.getData();
                    getViewState().addItems(list);
                }
            }

            @Override
            public void onFailure(Call<NewsArray> call, Throwable t) {

            }
        });

    }
}
