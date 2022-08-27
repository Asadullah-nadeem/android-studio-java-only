package com.livescorescrickets.livescores.utilities;

import com.livescorescrickets.livescores.Pojo.NewsPackage.NewsModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCallService {
    @GET("everything")
    Call<NewsModel> getEverything(@Query("q") String str, @Query("apiKey") String str2);

    @GET("top-headlines")
    Call<NewsModel> getHeadlines(@Query("country") String str, @Query("category") String str2, @Query("apiKey") String str3);
}
