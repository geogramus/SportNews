package geogram.example.sportnews.ui.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import geogram.example.sportnews.R;
import geogram.example.sportnews.adapters.NewsAdapter;
import geogram.example.sportnews.mvp.presenters.MainFragmentPresenter;
import geogram.example.sportnews.mvp.views.MainFragmentView;
import geogram.example.sportnews.rest.models.FootballRespose.NewsItem;
import geogram.example.sportnews.ui.activityes.MainActivity;
import geogram.example.sportnews.ui.activityes.SingleArticleActivity;

/**
 * Created by geogr on 15.04.2018.
 */

public class MainFragment extends MvpAppCompatFragment implements MainFragmentView, MainActivity.OnMenuItemClick, NewsAdapter.onListClickedRowListner {
    private List<NewsItem> items;
    public final static String news = "news";
    public final static String football = "footBall";
    public final static String basketBall = "basketBall";
    public final static String volleyBall = "volleyBall";
    public final static String hockey = "hockey";
    private ProgressDialog mProgressDialog;
    @BindView(R.id.newsList)
    RecyclerView recyclerView;
    NewsAdapter adapter;
    @InjectPresenter
    MainFragmentPresenter presenter;
    private String newsType;
    ArrayList<NewsItem> list;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putParcelableArrayList("ll",list);
        outState.putString("newsType", newsType);
        if (mProgressDialog.isShowing()){
            outState.putBoolean("show", true);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        adapter = new NewsAdapter(getContext(), this);
        recyclerView.setAdapter(adapter);
        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setCancelable(false);
        if (savedInstanceState == null) {
            newsType = news;
            if (getInternetConnection()) {
                presenter.getItems(newsType);
                showProgressBar();
            } else {
                errorLoading("Load Error, check internet connection  and refresh.");
            }
        } else {
            if(savedInstanceState.getBoolean("show")){

            }
            newsType = savedInstanceState.getString("newsType");
            adapter.clear();
            adapter.addAll(list);
        }
        recyclerView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });

    }

    @Override
    public void addItems(List<NewsItem> list) {
        this.list=new ArrayList<>();
        this.list.addAll(list);
        adapter.addAll(list);
        if (mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

    @Override
    public void errorLoading(String error) {

        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
        if (mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

    @Override
    public void ItemCLick(String type) {
        newsType = type;
        list.clear();
        adapter.clear();
        if (getInternetConnection()) {
            presenter.getItems(type);
            showProgressBar();
        } else {
            errorLoading("Load Error, check internet connection  and refresh.");
        }
    }

    @Override
    public void refresh() {
        list.clear();
        adapter.clear();
        if (getInternetConnection()) {
            presenter.getItems(newsType);
            showProgressBar();
        } else {
            errorLoading("Load Error, check internet connection  and refresh.");
        }
    }

    final GestureDetector gestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {
        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {

            NewsItem item = list.get(recyclerView.getChildLayoutPosition(recyclerView.findChildViewUnder(e.getX(), e.getY())));
            startArticle(item);
            return super.onSingleTapConfirmed(e);
        }
    });

    @Override
    public void onListSelected(NewsItem item) {
        startArticle(item);
    }

    public ArrayList<NewsItem> getList() {
        return list;
    }

    public void setData(ArrayList<NewsItem> list) {
        this.list = list;
        adapter.clear();
        adapter.addAll(list);
        adapter.notifyDataSetChanged();
    }

    private void startArticle(NewsItem item) {
        Intent intent = new Intent(getContext(), SingleArticleActivity.class);
        intent.putExtra("image", item.getPicture());
        intent.putExtra("title", item.getTitle());
        intent.putExtra("text", item.getFullText());
        startActivity(intent);

    }

    public boolean getInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        return false;
    }
    public void showProgressBar() {
        mProgressDialog.setMessage(getString(R.string.loading_data));
        mProgressDialog.show();
    }
}
