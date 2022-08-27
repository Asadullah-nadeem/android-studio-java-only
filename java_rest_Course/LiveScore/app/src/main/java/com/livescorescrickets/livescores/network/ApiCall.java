package com.livescorescrickets.livescores.network;

import com.livescorescrickets.livescores.Pojo.JsonDataFiles.MatchOddsModel;
import com.livescorescrickets.livescores.Pojo.JsonDataFiles.MatchResultModel;
import com.livescorescrickets.livescores.Pojo.JsonDataFiles.MatchStatsModel;
import com.livescorescrickets.livescores.Pojo.MultiMatch.GetAllPlayersPojo;
import com.livescorescrickets.livescores.Pojo.MultiMatch.GetLiveLinePojo;
import com.livescorescrickets.livescores.Pojo.MultiMatch.GetUpcomingMatchesPojo;
import com.livescorescrickets.livescores.Pojo.MultiMatch.MultimatchPojo;
import com.livescorescrickets.livescores.Pojo.MultiMatch.UserModel;
import java.util.ArrayList;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiCall {
    @Headers({"Content-Type:application/json"})
    @POST("Register")
    Call<UserModel> RegisterAPI(@Body Map<String, String> map);

    @GET("LiveLine")
    Call<ArrayList<MultimatchPojo>> getAllLiveMatchs();

    @POST("MatchResults")
    Call<MatchResultModel> getAllMatchesResult(@Body Map<String, String> map);

    @POST("GetAllPlayers")
    Call<GetAllPlayersPojo> getAllPlayers(@Body Map<String, String> map);

    @GET("LiveLine")
    Call<ArrayList<GetLiveLinePojo>> getLiveLine();

    @POST("LiveLine_Match")
    Call<ArrayList<GetLiveLinePojo>> getLiveLineDetail(@Body Map<String, String> map);

    @POST("MatchOdds")
    Call<MatchOddsModel> getMatchOdds(@Body Map<String, String> map);

    @POST("MatchStats")
    Call<MatchStatsModel> getMatchStats(@Body Map<String, String> map);

    @GET("news_aggregation")
    Call<GetUpcomingMatchesPojo> getNewapi();

    @GET("upcomingMatches")
    Call<GetUpcomingMatchesPojo> getUpcomingMatches();
}
