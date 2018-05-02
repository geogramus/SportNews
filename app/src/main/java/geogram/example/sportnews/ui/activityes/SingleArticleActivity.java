package geogram.example.sportnews.ui.activityes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import geogram.example.sportnews.R;
import geogram.example.sportnews.rest.models.FootballRespose.NewsItem;

public class SingleArticleActivity extends AppCompatActivity {
    private NewsItem item;
    @BindView(R.id.singleArticleImage)
    ImageView imageView;
    @BindView(R.id.titleArticle)
    TextView title;
    @BindView(R.id.textArticle)
    TextView textView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_article);
        ButterKnife.bind(this);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        String image= intent.getStringExtra("image");
        String titleText= intent.getStringExtra("title");
        String text= intent.getStringExtra("text");
        setTitle(titleText);
        Picasso.with(this)
                .load(image)
                .into(imageView);
        title.setText(titleText);
        textView.setText(text);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
