package geogram.example.sportnews.ui.activityes;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import geogram.example.sportnews.R;
import geogram.example.sportnews.rest.api.News;
import geogram.example.sportnews.rest.models.FootballRespose.NewsItem;
import geogram.example.sportnews.ui.fragments.MainFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Inject
    News footballApi;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;
    MainFragment mainFragment;

    private OnMenuItemClick onMenuItemClick;
    ArrayList<NewsItem> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        FragmentManager fragmentManager = getSupportFragmentManager();
        mainFragment = (MainFragment) fragmentManager.findFragmentById(R.id.list_container);

        if (mainFragment == null) {
            mainFragment = new MainFragment();
            if (mainFragment instanceof OnMenuItemClick) {
                onMenuItemClick = (OnMenuItemClick) mainFragment;
            } else {
                throw new RuntimeException(mainFragment.toString() + " must implement OnMenuItemClick");
            }
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.list_container, mainFragment)
                    .commit();

        }else {
            onMenuItemClick = (OnMenuItemClick) mainFragment;
            list=mainFragment.getList();
        }


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            onMenuItemClick.refresh();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_news:
                onMenuItemClick.ItemCLick(MainFragment.news);
                break;
            case R.id.nav_football:
                onMenuItemClick.ItemCLick(MainFragment.football);
                break;
            case R.id.nav_basketball:
                onMenuItemClick.ItemCLick(MainFragment.basketBall);
                break;
            case R.id.nav_volleyball:
                onMenuItemClick.ItemCLick(MainFragment.volleyBall);
                break;
            case R.id.nav_hockey:
                onMenuItemClick.ItemCLick(MainFragment.hockey);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public interface OnMenuItemClick {
        public void ItemCLick(String type);

        public void refresh();
    }

    @Override
    protected void onPause() {
//         mainFragment.setData(list);
        super.onPause();
    }

}
