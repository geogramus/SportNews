package geogram.example.sportnews.rest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by geogr on 14.04.2018.
 */

public class TestResponseModel {
    @SerializedName("can_show")
    @Expose
    private Boolean canShow;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("banner_url")
    @Expose
    private String bannerUrl;

    public Boolean getCanShow() {
        return canShow;
    }

    public void setCanShow(Boolean canShow) {
        this.canShow = canShow;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

}
