package com.livescorescrickets.livescores.Activity;
import com.livescorescrickets.livescores.R;
import static com.livescorescrickets.livescores.adsimp.FourData;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.livescorescrickets.livescores.Adapter.MatchFilterAdapter;
import com.livescorescrickets.livescores.Pojo.MatchFilterData;

import com.livescorescrickets.livescores.fragments.DateWiseFragment;
import com.livescorescrickets.livescores.fragments.SeriesWiseFragment;
import com.livescorescrickets.livescores.fragments.TeamWiseFragment;
import com.livescorescrickets.livescores.utilities.RequestHandler;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.measurement.api.AppMeasurementSdk;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import soup.neumorphism.NeumorphButton;

public class MatchesActivity extends BaseActivity {
    static final String url = FourData;
    NeumorphButton btnDateWise;
    NeumorphButton btnSeriesWise;
    NeumorphButton btnTeamWise;
    public int darkColor;
    String imageUrl;
    ArrayList<MatchFilterData> invoiceFilterKeyData;
    private boolean isFiltered = false;
    private boolean isRefreshBroadcast = false;
    private String lastSelectedkeyword = "";
    public int lightColor;
    MatchFilterAdapter matchfilterAdapter;
    private RecyclerView recyclerView;
    ShimmerFrameLayout shimmerLayout1;
    ArrayList<MatchFilterData> userShopRequests;
    String whatsAppurl;

    public void gotoMatches(View view) {
    }

    RelativeLayout adContainerView;
    AdView adViewone;
    String TAG = "bannerload";
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
        setContentView(R.layout.activity_matches);
        BannerAds();
        this.btnDateWise = (NeumorphButton) findViewById(R.id.btnDateWise);
        this.btnSeriesWise = (NeumorphButton) findViewById(R.id.btnSeriesWise);
        this.btnTeamWise = (NeumorphButton) findViewById(R.id.btnTeamWise);
        this.shimmerLayout1 = (ShimmerFrameLayout) findViewById(R.id.shimmerLayout1);
        changeMode();
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        initDomeBusiness();
//        mFetchListFromAPI();
        ArrayList<MatchFilterData> arrayList = new ArrayList<>();
        this.invoiceFilterKeyData = arrayList;
        arrayList.add(new MatchFilterData());
        this.invoiceFilterKeyData.add(new MatchFilterData());
        this.invoiceFilterKeyData.add(new MatchFilterData());
        this.invoiceFilterKeyData.add(new MatchFilterData());
        this.matchfilterAdapter = new MatchFilterAdapter(this, this.invoiceFilterKeyData);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.recyclerView.setAdapter(this.matchfilterAdapter);
        this.btnSeriesWise.setShadowColorLight(this.lightColor);
        this.btnSeriesWise.setShadowColorDark(this.darkColor);
        this.btnTeamWise.setShadowColorLight(this.lightColor);
        this.btnTeamWise.setShadowColorDark(this.darkColor);
        this.btnDateWise.setShadowColorLight(getResources().getColor(R.color.color_dark_red));
        this.btnDateWise.setShadowColorDark(getResources().getColor(R.color.color_dark_red));
        loadFragment(new DateWiseFragment(this.shimmerLayout1));
        this.btnDateWise.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                MatchesActivity.this.changeMode();
                MatchesActivity.this.btnSeriesWise.setShadowColorLight(MatchesActivity.this.lightColor);
                MatchesActivity.this.btnSeriesWise.setShadowColorDark(MatchesActivity.this.darkColor);
                MatchesActivity.this.btnTeamWise.setShadowColorLight(MatchesActivity.this.lightColor);
                MatchesActivity.this.btnTeamWise.setShadowColorDark(MatchesActivity.this.darkColor);
                MatchesActivity.this.btnDateWise.setShadowColorLight(MatchesActivity.this.getResources().getColor(R.color.color_dark_red));
                MatchesActivity.this.btnDateWise.setShadowColorDark(MatchesActivity.this.getResources().getColor(R.color.color_dark_red));
                MatchesActivity matchesActivity = MatchesActivity.this;
                matchesActivity.loadFragment(new DateWiseFragment(matchesActivity.shimmerLayout1));
            }
        });
        this.btnSeriesWise.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                MatchesActivity.this.changeMode();
                MatchesActivity.this.btnTeamWise.setShadowColorLight(MatchesActivity.this.lightColor);
                MatchesActivity.this.btnTeamWise.setShadowColorDark(MatchesActivity.this.darkColor);
                MatchesActivity.this.btnDateWise.setShadowColorLight(MatchesActivity.this.lightColor);
                MatchesActivity.this.btnDateWise.setShadowColorDark(MatchesActivity.this.darkColor);
                MatchesActivity.this.btnSeriesWise.setShadowColorLight(MatchesActivity.this.getResources().getColor(R.color.color_dark_red));
                MatchesActivity.this.btnSeriesWise.setShadowColorDark(MatchesActivity.this.getResources().getColor(R.color.color_dark_red));
                MatchesActivity matchesActivity = MatchesActivity.this;
                matchesActivity.loadFragment(new SeriesWiseFragment(matchesActivity.shimmerLayout1));
            }
        });
        this.btnTeamWise.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                MatchesActivity.this.changeMode();
                MatchesActivity.this.btnSeriesWise.setShadowColorLight(MatchesActivity.this.lightColor);
                MatchesActivity.this.btnSeriesWise.setShadowColorDark(MatchesActivity.this.darkColor);
                MatchesActivity.this.btnDateWise.setShadowColorLight(MatchesActivity.this.lightColor);
                MatchesActivity.this.btnDateWise.setShadowColorDark(MatchesActivity.this.darkColor);
                MatchesActivity.this.btnTeamWise.setShadowColorLight(MatchesActivity.this.getResources().getColor(R.color.color_dark_red));
                MatchesActivity.this.btnTeamWise.setShadowColorDark(MatchesActivity.this.getResources().getColor(R.color.color_dark_red));
                MatchesActivity matchesActivity = MatchesActivity.this;
                matchesActivity.loadFragment(new TeamWiseFragment(matchesActivity.shimmerLayout1));
            }
        });
    }

    public void changeMode() {
        this.shimmerLayout1.setVisibility(View.VISIBLE);
        int i = getResources().getConfiguration().uiMode & 48;
        if (i == 16) {
            this.darkColor = getResources().getColor(R.color.color_white_dark_shadow);
            this.lightColor = getResources().getColor(R.color.color_white_light_shadow);
        } else if (i == 32) {
            this.darkColor = getResources().getColor(R.color.color_dark_shadow);
            this.lightColor = getResources().getColor(R.color.color_light_shadow);
        }
    }

    public void initDomeBusiness() {
        this.userShopRequests = new ArrayList<>();
        MatchFilterData matchFilterData = new MatchFilterData();
        matchFilterData.setTeam1Name("All");
        this.userShopRequests.add(matchFilterData);
        MatchFilterData matchFilterData2 = new MatchFilterData();
        matchFilterData2.setTeam1Name("International");
        this.userShopRequests.add(matchFilterData2);
        MatchFilterData matchFilterData3 = new MatchFilterData();
        matchFilterData3.setTeam1Name("ODI");
        this.userShopRequests.add(matchFilterData3);
        MatchFilterData matchFilterData4 = new MatchFilterData();
        matchFilterData4.setTeam1Name("T20");
        this.userShopRequests.add(matchFilterData4);
        MatchFilterData matchFilterData5 = new MatchFilterData();
        matchFilterData5.setTeam1Name("TEST");
        this.userShopRequests.add(matchFilterData5);
        MatchFilterData matchFilterData6 = new MatchFilterData();
        matchFilterData6.setTeam1Name("League");
        this.userShopRequests.add(matchFilterData6);
    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.frameLayout, fragment);
        beginTransaction.addToBackStack(null);
        beginTransaction.commit();
    }

    public void gotoSettings(View view) {
        startActivity(new Intent(this, SettingActivity.class));
    }

    public void gotoRanks(View view) {
        startActivity(new Intent(this, RankScreenActivity.class));
    }

    public void gotoNews(View view) {
        startActivity(new Intent(this, NewsActivity.class));
    }

    public void gotoHome(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    private void mFetchListFromAPI() {
        RequestHandler.getInstance(this).addToRequestQueue(new StringRequest(0, url, new Response.Listener<String>() {


            public void onResponse(String str) {
                Log.d("dataApi", str);
                try {
                    JSONArray jSONArray = new JSONObject(str).getJSONArray("data");
                    Log.d("dataApi", jSONArray.toString());
                    MatchesActivity.this.getData(jSONArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("MainError", e.getMessage());
                    Toast.makeText(MatchesActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("dataApi", volleyError.getMessage());
            }
        }));
    }

    public void getData(JSONArray jSONArray) {
        if (jSONArray.length() > 0) {
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    if (jSONObject.getString(AppMeasurementSdk.ConditionalUserProperty.NAME).equals("Guru Exchange")) {
                        Log.d("dataApi", jSONObject.toString());
                        this.imageUrl = jSONObject.getString("banner");
                        this.whatsAppurl = jSONObject.getString(ImagesContract.URL);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
