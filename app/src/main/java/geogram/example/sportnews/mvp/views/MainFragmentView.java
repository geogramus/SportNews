package geogram.example.sportnews.mvp.views;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import geogram.example.sportnews.rest.models.FootballRespose.NewsItem;

/**
 * Created by geogr on 15.04.2018.
 */

public interface MainFragmentView extends MvpView{

    void addItems(List<NewsItem> list);
    void errorLoading(String error);

}
