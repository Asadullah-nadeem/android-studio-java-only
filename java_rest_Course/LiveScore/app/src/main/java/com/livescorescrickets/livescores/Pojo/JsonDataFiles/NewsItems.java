package com.livescorescrickets.livescores.Pojo.JsonDataFiles;

public class NewsItems {
    String news_description;
    String news_image;
    String news_title;

    public NewsItems() {
    }

    public NewsItems(String str, String str2, String str3) {
        this.news_title = str;
        this.news_description = str2;
        this.news_image = str3;
    }

    public String getNews_title() {
        return this.news_title;
    }

    public void setNews_title(String str) {
        this.news_title = str;
    }

    public String getNews_description() {
        return this.news_description;
    }

    public void setNews_description(String str) {
        this.news_description = str;
    }

    public String getNews_image() {
        return this.news_image;
    }

    public void setNews_image(String str) {
        this.news_image = str;
    }
}
