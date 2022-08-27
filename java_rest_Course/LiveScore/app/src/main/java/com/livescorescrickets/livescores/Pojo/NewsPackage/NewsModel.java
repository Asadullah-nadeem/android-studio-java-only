package com.livescorescrickets.livescores.Pojo.NewsPackage;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class NewsModel {
    @SerializedName("articles")
    @Expose
    private List<Article> articles = null;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String status;
    @SerializedName("totalResults")
    @Expose
    private Integer totalResults;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public Integer getTotalResults() {
        return this.totalResults;
    }

    public void setTotalResults(Integer num) {
        this.totalResults = num;
    }

    public List<Article> getArticles() {
        return this.articles;
    }

    public void setArticles(List<Article> list) {
        this.articles = list;
    }
}
