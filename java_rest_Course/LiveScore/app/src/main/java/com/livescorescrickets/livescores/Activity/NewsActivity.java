package com.livescorescrickets.livescores.Activity;
import com.livescorescrickets.livescores.R;
import static com.livescorescrickets.livescores.adsimp.FiveData;
import static com.livescorescrickets.livescores.adsimp.SixData;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.livescorescrickets.livescores.Adapter.MostRecentAdapter;
import com.livescorescrickets.livescores.Adapter.TopStoriesAdapter;
import com.livescorescrickets.livescores.Adapter.TopStoriesAdapter1;
import com.livescorescrickets.livescores.Adapter.TopStoriesAdapter2;
import com.livescorescrickets.livescores.Pojo.JsonDataFiles.NewsItems;
import com.livescorescrickets.livescores.Pojo.NewsPackage.NewsModel;

import com.livescorescrickets.livescores.R;
import com.livescorescrickets.livescores.utilities.ApiCallService;
import com.livescorescrickets.livescores.utilities.RequestHandler;
import com.livescorescrickets.livescores.utilities.RetrofitClientInstance;
import com.facebook.shimmer.ShimmerFrameLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {
    static final String url = FiveData;
    boolean isLoaded = false;
    JSONArray jsonArray;
    ArrayList<NewsItems> list;
    RelativeLayout mFound;
    private TopStoriesAdapter mNewsAdapter;
    public TopStoriesAdapter1 mNewsAdapter1;
    public TopStoriesAdapter2 mNewsAdapter2;
    private ListView mNewsListView;
    private MostRecentAdapter mostRecentAdapter;
    RecyclerView mostrecent;
    public List<NewsItems> newsItems = new ArrayList();
    public NewsModel newsModel = new NewsModel();
    public LottieAnimationView progressBar;
    private ScrollView scrollView;
    ShimmerFrameLayout shimmerLayout1;
    public RecyclerView topstoryRecycler1;
    public RecyclerView topstoryRecycler2;

    public void gotoNews(View view) {
    }
    String Datakey;

    RelativeLayout adContainerView;
    AdView adViewone;
    String TAG="bannerload";
    RelativeLayout adContainer;
    private void BannerLoad() {
        AdRequest adRequest = new AdRequest.Builder().build();
        AdSize adSize = BannerGetSize();
        adViewone.setAdSize(adSize);
        adViewone.loadAd(adRequest);
    }
    private AdSize BannerGetSize() {
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;
        int adWidth = (int) (widthPixels / density);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth);
    }
    public void BannerAds() {
        adContainerView = findViewById(R.id.adMobView);
        adViewone = new AdView(getApplicationContext());
        adViewone.setAdUnitId(getString(R.string.BannerAdsAdmob));
        adContainerView.addView(adViewone);
        BannerLoad();
    }
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_news);
        BannerAds();
        this.mostrecent = (RecyclerView) findViewById(R.id.mostrecentrv);
        this.mNewsListView = (ListView) findViewById(R.id.topstory);
        this.topstoryRecycler1 = (RecyclerView) findViewById(R.id.topstoryRecycler1);
        this.topstoryRecycler2 = (RecyclerView) findViewById(R.id.topstoryRecycler2);
        this.shimmerLayout1 = (ShimmerFrameLayout) findViewById(R.id.shimmerLayout1);
        this.progressBar = (LottieAnimationView) findViewById(R.id.loading_news);
        this.mNewsListView.setAdapter((ListAdapter) this.mNewsAdapter);
        this.mFound = (RelativeLayout) findViewById(R.id.news_not_found);
        this.mNewsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                String news_image = NewsActivity.this.newsItems.get(i).getNews_image();
                String news_title = NewsActivity.this.newsItems.get(i).getNews_title();
                String news_description = NewsActivity.this.newsItems.get(i).getNews_description();
                Intent intent = new Intent(NewsActivity.this, NewsDetailsActivity.class);
                intent.putExtra("image", news_image);
                intent.putExtra("title", news_title);
                intent.putExtra("description", news_description);
                NewsActivity.this.startActivity(intent);
            }
        });
        mFetchListFromAPI2();
        mFetchListFromAPI3();
    }

    public void mFetchListFromAPI2() {
        ((ApiCallService) RetrofitClientInstance.getRetrofitInstance(this).create(ApiCallService.class)).getEverything("cricket", "0e616f1090ae42c2a138fcc2b707e500").enqueue(new Callback<NewsModel>() {


            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                NewsActivity.this.newsModel = response.body();
                NewsActivity.this.progressBar.setVisibility(View.GONE);
                NewsActivity newsActivity = NewsActivity.this;
                NewsActivity newsActivity2 = NewsActivity.this;
                newsActivity.mNewsAdapter1 = new TopStoriesAdapter1(newsActivity2, newsActivity2.newsModel);
                NewsActivity.this.topstoryRecycler1.setAdapter(NewsActivity.this.mNewsAdapter1);
                NewsActivity.this.topstoryRecycler1.setLayoutManager(new LinearLayoutManager(NewsActivity.this));
                NewsActivity.this.shimmerLayout1.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable th) {
                NewsActivity.this.mFetchListFromAPI2();
            }
        });
    }

    public void mFetchListFromAPI3() {
        ((ApiCallService) RetrofitClientInstance.getRetrofitInstance(this).create(ApiCallService.class)).getHeadlines("in", "sports", "0e616f1090ae42c2a138fcc2b707e500").enqueue(new Callback<NewsModel>() {


            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                NewsActivity.this.newsModel = response.body();
                NewsActivity.this.progressBar.setVisibility(View.GONE);
                NewsActivity newsActivity = NewsActivity.this;
                newsActivity.mNewsAdapter2 = new TopStoriesAdapter2(newsActivity, newsActivity.newsModel);
                NewsActivity.this.topstoryRecycler2.setAdapter(NewsActivity.this.mNewsAdapter2);
                NewsActivity.this.topstoryRecycler2.setLayoutManager(new LinearLayoutManager(NewsActivity.this, 0, false));
                NewsActivity.this.shimmerLayout1.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable th) {
                NewsActivity.this.mFetchListFromAPI3();
            }
        });
    }

    private void mFetchMostRecentFromAPI() {
        RequestHandler.getInstance(this).addToRequestQueue(new StringRequest(0, url, new com.android.volley.Response.Listener<String>() {


            public void onResponse(String str) {
                NewsActivity.this.progressBar.setVisibility(View.GONE);
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    NewsActivity.this.jsonArray = jSONObject.getJSONArray("arts");
                    NewsActivity newsActivity = NewsActivity.this;
                    newsActivity.getMostData(newsActivity.jsonArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError volleyError) {
                NewsActivity.this.progressBar.setVisibility(View.GONE);
                NewsActivity.this.mFound.setVisibility(View.VISIBLE);
                NewsActivity.this.mFetchListFromAPI();
            }
        }) {


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("content-type", "application/json");
                hashMap.put("x-rapidapi-host", SixData);
                hashMap.put("x-rapidapi-key", Datakey);
                return hashMap;
            }
        });
    }

    public void getMostData(JSONArray jSONArray) {
        if (jSONArray.length() > 0) {
            this.mFound.setVisibility(View.GONE);
            this.progressBar.setVisibility(View.GONE);
            for (int i = 0; i < jSONArray.length(); i++) {
                NewsItems newsItems2 = new NewsItems();
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    newsItems2.setNews_title(jSONObject.getString("tit"));
                    newsItems2.setNews_description(jSONObject.getString("des"));
                    newsItems2.setNews_image(jSONObject.getString("img"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                this.newsItems.add(newsItems2);
            }
            this.mNewsAdapter.notifyDataSetChanged();
            return;
        }
        this.mFound.setVisibility(View.GONE);
        this.progressBar.setVisibility(View.GONE);
    }

    public void mFetchListFromAPI() {
        RequestHandler.getInstance(this).addToRequestQueue(new StringRequest(0, url, new com.android.volley.Response.Listener<String>() {


            public void onResponse(String str) {
                NewsActivity.this.progressBar.setVisibility(View.GONE);
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    NewsActivity.this.jsonArray = jSONObject.getJSONArray("arts");
                    NewsActivity newsActivity = NewsActivity.this;
                    newsActivity.getData(newsActivity.jsonArray);
                    NewsActivity.this.shimmerLayout1.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError volleyError) {
                NewsActivity.this.progressBar.setVisibility(View.GONE);
                NewsActivity.this.mFound.setVisibility(View.GONE);
                volleyError.printStackTrace();
                NewsActivity.this.mFetchListFromAPI();
            }
        }) {


            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("q", "cricket");
                hashMap.put("apiKey", "0e616f1090ae42c2a138fcc2b707e500");
                return hashMap;
            }
        });
    }

    public void getData(JSONArray jSONArray) {
        if (jSONArray.length() > 0) {
            this.mFound.setVisibility(View.GONE);
            this.progressBar.setVisibility(View.GONE);
            for (int i = 0; i < jSONArray.length(); i++) {
                NewsItems newsItems2 = new NewsItems();
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    newsItems2.setNews_title(jSONObject.getString("tit"));
                    newsItems2.setNews_description(jSONObject.getString("des"));
                    newsItems2.setNews_image(jSONObject.getString("img"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                this.newsItems.add(newsItems2);
            }
            this.mNewsAdapter.notifyDataSetChanged();
            return;
        }
        this.mFound.setVisibility(View.GONE);
        this.progressBar.setVisibility(View.GONE);
    }

    public void gotoSettings(View view) {
        startActivity(new Intent(this, SettingActivity.class));
    }

    public void gotoRanks(View view) {
        startActivity(new Intent(this, RankScreenActivity.class));
    }

    public void gotoMatches(View view) {
        startActivity(new Intent(this, MatchesActivity.class));
    }

    public void gotoHome(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
