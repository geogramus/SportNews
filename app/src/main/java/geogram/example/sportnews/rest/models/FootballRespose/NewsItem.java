package geogram.example.sportnews.rest.models.FootballRespose;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by geogr on 14.04.2018.
 */

public class NewsItem implements Parcelable {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("short_text")
    @Expose
    private String shortText;
    @SerializedName("full_text")
    @Expose
    private String fullText;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("coefficient")
    @Expose
    private String coefficient;
    @SerializedName("schedule")
    @Expose
    private String schedule;
    @SerializedName("publicated_at")
    @Expose
    private String publicatedAt;

    protected NewsItem(Parcel in) {
        title = in.readString();
        shortText = in.readString();
        fullText = in.readString();
        picture = in.readString();
        coefficient = in.readString();
        schedule = in.readString();
        publicatedAt = in.readString();
    }

    public static final Creator<NewsItem> CREATOR = new Creator<NewsItem>() {
        @Override
        public NewsItem createFromParcel(Parcel in) {
            return new NewsItem(in);
        }

        @Override
        public NewsItem[] newArray(int size) {
            return new NewsItem[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(String coefficient) {
        this.coefficient = coefficient;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getPublicatedAt() {
        return publicatedAt;
    }

    public void setPublicatedAt(String publicatedAt) {
        this.publicatedAt = publicatedAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(shortText);
        parcel.writeString(fullText);
        parcel.writeString(picture);
        parcel.writeString(coefficient);
        parcel.writeString(schedule);
        parcel.writeString(publicatedAt);
    }
}
