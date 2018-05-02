package geogram.example.sportnews.rest.models.FootballRespose;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by geogr on 14.04.2018.
 */

public class NewsArray {
    @SerializedName("data")
    @Expose
    private List<NewsItem> data = null;

    public List<NewsItem> getData() {
        return data;
    }

    public void setData(List<NewsItem> data) {
        this.data = data;
    }
}
