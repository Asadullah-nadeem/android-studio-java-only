package com.livescorescrickets.livescores.Activity;

import com.livescorescrickets.livescores.R;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.livescorescrickets.livescores.Adapter.FinishedMatchesHomeAdapter;
import com.livescorescrickets.livescores.Adapter.RecentMatchesHomeAdapter;
import com.livescorescrickets.livescores.Adapter.TopSlideAdapter;
import com.livescorescrickets.livescores.Adapter.UpcomingMatchesHomeAdapter;
import com.livescorescrickets.livescores.BottomSheet.FinishedMatchBottomSheet;
import com.livescorescrickets.livescores.BottomSheet.LiveMatchBottomSheet;
import com.livescorescrickets.livescores.BottomSheet.UpcomingMatchBottomSheet;
import com.livescorescrickets.livescores.OnItemClickListener;
import com.livescorescrickets.livescores.Pojo.JsonDataFiles.Jsondata;
import com.livescorescrickets.livescores.Pojo.JsonDataFiles.MainJsonData;
import com.livescorescrickets.livescores.Pojo.JsonDataFiles.MatchResultModel;
import com.livescorescrickets.livescores.Pojo.MultiMatch.GetUpcomingMatchesPojo;
import com.livescorescrickets.livescores.Pojo.MultiMatch.MultimatchPojo;

import com.livescorescrickets.livescores.utilities.MyContextWrapper;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphImageButton;

import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;


public class MainActivity extends BaseActivity implements OnItemClickListener, View.OnClickListener {
    private static final int ID_ACCOUNT = 5;
    private static final int ID_EXPLORE = 2;
    private static final int ID_HOME = 1;
    private static final int ID_MESSAGE = 3;
    private static final int ID_NOTIFICATION = 4;
    private static final int RC_APP_UPDATE = 11;
    boolean doubleBackToExitPressedOnce = false;
    ArrayList<MultimatchPojo> allMatches;
    int apiCalled = 0;
    public AppUpdateManager appUpdateManager;
    NeumorphButton btnALL;
    NeumorphButton btnFinished;
    NeumorphButton btnLive;
    NeumorphButton btnUpcoming;
    private int darkColor;
    public ImageView[] dots;
    public int dotscount;
    Runnable f954r = new Runnable() {

        public void run() {
            MainActivity.this.getAllMatches();
            MainActivity.this.handler.postDelayed(MainActivity.this.f954r, 5000);
        }
    };
    ArrayList<MatchResultModel.AllMatch> finishedMatches;
    final Handler handler = new Handler();
    ImageView ivLogo;
    NeumorphImageButton ivTheme;
    private int lightColor;
    LinearLayout liveLayout;
    ArrayList<MultimatchPojo> liveMatches;
    public SharedPreferences mPrefrences;
    private ScheduledFuture<?> mScheduledFuture;
    private ScheduledExecutorService mScheduler;
    OnItemClickListener onItemClickListener;
    LottieAnimationView progress;
    RecentMatchesHomeAdapter recentMatchesHomeAdapter;
    RecyclerView rvAllMatchList;
    ShimmerFrameLayout shimmerLayout1;
    ShimmerFrameLayout shimmerLayout2;
    LinearLayout sliderDotspanel;
    TopSlideAdapter top_ada;
    ViewPager2 topside_pager;
    ArrayList<GetUpcomingMatchesPojo.AllMatch> upcomingMatches;
    UpcomingMatchesHomeAdapter upcomingMatchesHomeAdapter;

    public void gotoHome(View view) {
    }

    @Override
    public void attachBaseContext(Context context) {
        super.attachBaseContext(MyContextWrapper.wrap(context, "hi"));
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

    private NativeAd nativeAd;

    private LinearLayout adView;
    TextView adsgone;

    private void GoogleNAtiveAds(NativeAd nativeAd, NativeAdView adView) {
        MediaView mediaView = adView.findViewById(R.id.ad_media);
        adView.setMediaView(mediaView);
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));

        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }
        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }
        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }
        adView.setNativeAd(nativeAd);

    }

    public void GoogleNAtiveShow(final FrameLayout frameLayout) {
        AdLoader.Builder builder = new AdLoader.Builder(getApplication(), getString(R.string.Native));
        adsgone.setVisibility(View.GONE);
        builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
            @Override
            public void onNativeAdLoaded(NativeAd nativeAd) {

                boolean isDestroyed = false;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    isDestroyed = isDestroyed();
                }
                if (isDestroyed || isFinishing() || isChangingConfigurations()) {
                    nativeAd.destroy();
                    return;
                }
                if (MainActivity.this.nativeAd != null) {
                    MainActivity.this.nativeAd.destroy();
                }
                MainActivity.this.nativeAd = nativeAd;
                NativeAdView adView = (NativeAdView) getLayoutInflater().inflate(R.layout.admobnative, null);
                GoogleNAtiveAds(nativeAd, adView);
                frameLayout.removeAllViews();
                frameLayout.addView(adView);
            }
        });
        VideoOptions videoOptions = new VideoOptions.Builder().build();
        com.google.android.gms.ads.nativead.NativeAdOptions adOptions = new com.google.android.gms.ads.nativead.NativeAdOptions.Builder().setVideoOptions(videoOptions).build();
        builder.withNativeAdOptions(adOptions);
        AdLoader adLoader = builder.withAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
            }
        }).build();
        adLoader.loadAd(new AdRequest.Builder().build());


    }

    public void NativeADmob() {
        GoogleNAtiveShow((FrameLayout) findViewById(R.id.native_ad_container));
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        BannerAds();
        adsgone = findViewById(R.id.adsgone);
        NativeADmob();
        this.rvAllMatchList = (RecyclerView) findViewById(R.id.rvAllMatchList);
        this.topside_pager = (ViewPager2) findViewById(R.id.topside_pager);
        this.sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);
        this.liveLayout = (LinearLayout) findViewById(R.id.liveLayout);
        this.progress = (LottieAnimationView) findViewById(R.id.progress);
        this.ivTheme = (NeumorphImageButton) findViewById(R.id.ivTheme);
        this.shimmerLayout1 = (ShimmerFrameLayout) findViewById(R.id.shimmerLayout1);
        this.shimmerLayout2 = (ShimmerFrameLayout) findViewById(R.id.shimmerLayout2);
        this.ivLogo = (ImageView) findViewById(R.id.ivLogo);
        this.appUpdateManager = AppUpdateManagerFactory.create(this);
        checkUpdate();
        changeMode();
        MyContextWrapper.wrap(this, "hi");
        this.onItemClickListener = this;
        init();
    }

    private void checkUpdate() {
        this.appUpdateManager.getAppUpdateInfo().addOnSuccessListener(new OnSuccessListener() {


            @Override
            public final void onSuccess(Object obj) {
                MainActivity.this.lambda$checkUpdate$0$MainActivity((AppUpdateInfo) obj);
            }
        });
    }

    public void lambda$checkUpdate$0$MainActivity(AppUpdateInfo appUpdateInfo) {
        if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
            startUpdateFlow(appUpdateInfo);
        } else if (appUpdateInfo.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
            startUpdateFlow(appUpdateInfo);
        }
    }

    private void startUpdateFlow(AppUpdateInfo appUpdateInfo) {
        try {
            this.appUpdateManager.startUpdateFlowForResult(appUpdateInfo, AppUpdateType.IMMEDIATE, this, 11);
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.appUpdateManager.getAppUpdateInfo().addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {


            public void onSuccess(AppUpdateInfo appUpdateInfo) {
                if (appUpdateInfo.updateAvailability() == ID_MESSAGE && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                    try {
                        MainActivity.this.appUpdateManager.startUpdateFlowForResult(appUpdateInfo, AppUpdateType.IMMEDIATE, MainActivity.this, 11);
                    } catch (IntentSender.SendIntentException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void changeMode() {
        int i = getResources().getConfiguration().uiMode & 48;
        if (i == 16) {
            this.darkColor = getResources().getColor(R.color.color_white_dark_shadow);
            this.lightColor = getResources().getColor(R.color.color_white_light_shadow);
        } else if (i == 32) {
            this.darkColor = getResources().getColor(R.color.color_dark_shadow);
            this.lightColor = getResources().getColor(R.color.color_light_shadow);
        }
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    public void init() {
        this.btnALL = (NeumorphButton) findViewById(R.id.btnALL);
        this.btnLive = (NeumorphButton) findViewById(R.id.btnLive);
        this.btnFinished = (NeumorphButton) findViewById(R.id.btnFinished);
        this.btnUpcoming = (NeumorphButton) findViewById(R.id.btnUpcoming);
        this.btnALL.setOnClickListener(this);
        this.btnLive.setOnClickListener(this);
        this.btnFinished.setOnClickListener(this);
        this.btnUpcoming.setOnClickListener(this);
        this.allMatches = new ArrayList<>();
        this.liveMatches = new ArrayList<>();
        this.upcomingMatches = new ArrayList<>();
        this.finishedMatches = new ArrayList<>();
        mCallApi();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        this.btnALL.setShadowColorDark(getResources().getColor(R.color.color_dark_red));
        this.btnALL.setShadowColorLight(getResources().getColor(R.color.color_dark_red));
        this.btnLive.setShadowColorDark(this.darkColor);
        this.btnLive.setShadowColorLight(this.lightColor);
        this.btnFinished.setShadowColorDark(this.darkColor);
        this.btnFinished.setShadowColorLight(this.lightColor);
        this.btnUpcoming.setShadowColorDark(this.darkColor);
        this.btnUpcoming.setShadowColorLight(this.lightColor);
        this.rvAllMatchList.setLayoutManager(linearLayoutManager);
        this.rvAllMatchList.setLayoutManager(linearLayoutManager);
        this.rvAllMatchList.setHasFixedSize(true);
        RecentMatchesHomeAdapter recentMatchesHomeAdapter2 = new RecentMatchesHomeAdapter(this.allMatches, this, this.onItemClickListener);
        this.recentMatchesHomeAdapter = recentMatchesHomeAdapter2;
        this.rvAllMatchList.setAdapter(recentMatchesHomeAdapter2);
        TopSlideAdapter topSlideAdapter = new TopSlideAdapter(this.liveMatches, this, this.onItemClickListener);
        this.top_ada = topSlideAdapter;
        this.topside_pager.setAdapter(topSlideAdapter);
        runOnUiThread(new Runnable() {


            public void run() {
                MainActivity.this.top_ada.notifyDataSetChanged();
                MainActivity mainActivity = MainActivity.this;
                mainActivity.dots = new ImageView[mainActivity.dotscount];
                MainActivity.this.sliderDotspanel.removeAllViews();
                for (int i = 0; i < MainActivity.this.dotscount; i++) {
                    MainActivity.this.dots[i] = new ImageView(MainActivity.this);
                    MainActivity.this.dots[i].setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.non_active_dot));
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.setMargins(10, 0, 10, 0);
                    MainActivity.this.sliderDotspanel.addView(MainActivity.this.dots[i], layoutParams);
                }
            }
        });
    }

    private void showLiveBottomSheet(MultimatchPojo multimatchPojo) {
        new LiveMatchBottomSheet(multimatchPojo).show(getSupportFragmentManager(), "LiveBottomSheet");
    }

    private void showUpcomingMatchBottomSheet(MultimatchPojo multimatchPojo) {
        new UpcomingMatchBottomSheet(multimatchPojo).show(getSupportFragmentManager(), "UpcomingBottomSheet");
    }

    private void showUpcomingMatchBottomSheet(GetUpcomingMatchesPojo.AllMatch allMatch) {
        new UpcomingMatchBottomSheet(allMatch).show(getSupportFragmentManager(), "UpcomingBottomSheet");
    }

    private void showFinishedMatchBottomSheet(MultimatchPojo multimatchPojo) {
        new FinishedMatchBottomSheet(multimatchPojo).show(getSupportFragmentManager(), "FinishedBottomSheet");
    }

    private void showFinishedMatchBottomSheet(MatchResultModel.AllMatch allMatch) {
        new FinishedMatchBottomSheet(allMatch).show(getSupportFragmentManager(), "FinishedBottomSheet");
    }

    @Override
    public void setOnItemClickListener(int i, MultimatchPojo multimatchPojo) {
        new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        MainJsonData mainJsonData = multimatchPojo.getJsondata().length() > 0 ? (MainJsonData) new Gson().fromJson(multimatchPojo.getJsondata(), MainJsonData.class) : null;
        String str = multimatchPojo.getResult().trim().length() > 0 ? "3" : (mainJsonData == null || mainJsonData.getJsondata().getBowler().equalsIgnoreCase("0")) ? "2" : "1";
        Log.d("987", "setOnItemClickListener: 987 " + multimatchPojo.getIsfinished());
        char c = 65535;
        switch (str.hashCode()) {
            case 49:
                if (str.equals("1")) {
                    c = 0;
                    break;
                }
                break;
            case 50:
                if (str.equals("2")) {
                    c = 1;
                    break;
                }
                break;
            case 51:
                if (str.equals("3")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                showLiveBottomSheet(multimatchPojo);
                return;
            case 1:
                showUpcomingMatchBottomSheet(multimatchPojo);
                return;
            case 2:
                showFinishedMatchBottomSheet(multimatchPojo);
                return;
            default:
                return;
        }
    }

    @Override
    public void setOnItemClickListener(int i, GetUpcomingMatchesPojo.AllMatch allMatch) {
        showUpcomingMatchBottomSheet(allMatch);
    }

    @Override
    public void setOnItemClickListener(int i, MatchResultModel.AllMatch allMatch) {
        showFinishedMatchBottomSheet(allMatch);
    }

    public void onClick(View view) {
        changeMode();
        switch (view.getId()) {
            case R.id.btnALL:
                this.shimmerLayout2.setVisibility(View.VISIBLE);
                this.btnALL.setShadowColorDark(getResources().getColor(R.color.color_dark_red));
                this.btnALL.setShadowColorLight(getResources().getColor(R.color.color_dark_red));
                changeMode();
                this.btnLive.setShadowColorDark(this.darkColor);
                this.btnLive.setShadowColorLight(this.lightColor);
                this.btnFinished.setShadowColorDark(this.darkColor);
                this.btnFinished.setShadowColorLight(this.lightColor);
                this.btnUpcoming.setShadowColorDark(this.darkColor);
                this.btnUpcoming.setShadowColorLight(this.lightColor);
                RecentMatchesHomeAdapter recentMatchesHomeAdapter2 = new RecentMatchesHomeAdapter(this.allMatches, this, this.onItemClickListener);
                this.recentMatchesHomeAdapter = recentMatchesHomeAdapter2;
                recentMatchesHomeAdapter2.notifyDataSetChanged();
                this.rvAllMatchList.setAdapter(this.recentMatchesHomeAdapter);
                return;
            case R.id.btnFinished:
                this.shimmerLayout2.setVisibility(View.VISIBLE);
                this.btnFinished.setShadowColorDark(getResources().getColor(R.color.color_dark_red));
                this.btnFinished.setShadowColorLight(getResources().getColor(R.color.color_dark_red));
                this.btnLive.setShadowColorDark(this.darkColor);
                this.btnLive.setShadowColorLight(this.lightColor);
                this.btnALL.setShadowColorDark(this.darkColor);
                this.btnALL.setShadowColorLight(this.lightColor);
                this.btnUpcoming.setShadowColorDark(this.darkColor);
                this.btnUpcoming.setShadowColorLight(this.lightColor);
                this.progress.setVisibility(View.VISIBLE);
                getResultMatches(0, 20);
                return;
            case R.id.btnLive:
                this.shimmerLayout2.setVisibility(View.VISIBLE);
                this.btnLive.setShadowColorDark(getResources().getColor(R.color.color_dark_red));
                this.btnLive.setShadowColorLight(getResources().getColor(R.color.color_dark_red));
                this.btnALL.setShadowColorDark(this.darkColor);
                this.btnALL.setShadowColorLight(this.lightColor);
                this.btnFinished.setShadowColorDark(this.darkColor);
                this.btnFinished.setShadowColorLight(this.lightColor);
                this.btnUpcoming.setShadowColorDark(this.darkColor);
                this.btnUpcoming.setShadowColorLight(this.lightColor);
                RecentMatchesHomeAdapter recentMatchesHomeAdapter3 = new RecentMatchesHomeAdapter(this.liveMatches, this, this.onItemClickListener);
                this.recentMatchesHomeAdapter = recentMatchesHomeAdapter3;
                recentMatchesHomeAdapter3.notifyDataSetChanged();
                this.rvAllMatchList.setAdapter(this.recentMatchesHomeAdapter);
                return;
            case R.id.btnUpcoming:
                this.shimmerLayout2.setVisibility(View.VISIBLE);
                this.btnUpcoming.setShadowColorDark(getResources().getColor(R.color.color_dark_red));
                this.btnUpcoming.setShadowColorLight(getResources().getColor(R.color.color_dark_red));
                this.btnLive.setShadowColorDark(this.darkColor);
                this.btnLive.setShadowColorLight(this.lightColor);
                this.btnFinished.setShadowColorDark(this.darkColor);
                this.btnFinished.setShadowColorLight(this.lightColor);
                this.btnALL.setShadowColorDark(this.darkColor);
                this.btnALL.setShadowColorLight(this.lightColor);
                this.progress.setVisibility(View.VISIBLE);
                getUpcomingMatchesRetro();
                return;
            default:
                return;
        }
    }

    private void mCallApi() {
        this.handler.postDelayed(this.f954r, 100);
    }

    public void getAllMatches() {
        try {
            if (isNetworkAvailable()) {
                mGetRetroObject(baseURL()).getAllLiveMatchs().enqueue(new Callback<ArrayList<MultimatchPojo>>() {


                    @Override
                    public void onFailure(Call<ArrayList<MultimatchPojo>> call, Throwable th) {
                    }

                    @Override
                    public void onResponse(Call<ArrayList<MultimatchPojo>> call, Response<ArrayList<MultimatchPojo>> response) {
                        MainJsonData mainJsonData;
                        try {
                            if (response.code() == 200) {
                                MainActivity.this.allMatches.clear();
                                MainActivity.this.liveMatches.clear();
                                MainActivity.this.finishedMatches.clear();
                                MainActivity.this.upcomingMatches.clear();
                                for (int i = 0; i < response.body().size(); i++) {
                                    MultimatchPojo multimatchPojo = response.body().get(i);
                                    if (multimatchPojo.getJsondata().length() > 0 && (mainJsonData = (MainJsonData) new Gson().fromJson(multimatchPojo.getJsondata(), MainJsonData.class)) != null) {
                                        Jsondata jsondata = mainJsonData.getJsondata();
                                        new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                                        if (multimatchPojo.getResult().trim().length() <= 0) {
                                            if (multimatchPojo.getJsondata().length() > 0) {
                                                mainJsonData = (MainJsonData) new Gson().fromJson(multimatchPojo.getJsondata(), MainJsonData.class);
                                            }
                                            if (mainJsonData != null && !jsondata.getBowler().equalsIgnoreCase("0")) {
                                                MainActivity.this.liveMatches.add(multimatchPojo);
                                                Log.e("onFailure ", "" + mainJsonData);
                                            }
                                        }
                                    }
                                    if (MainActivity.this.liveMatches.size() <= 0) {
                                        MainActivity.this.btnLive.setVisibility(View.GONE);
                                        MainActivity.this.liveLayout.setVisibility(View.GONE);
                                    } else {
                                        MainActivity.this.btnLive.setVisibility(View.VISIBLE);
                                        MainActivity.this.liveLayout.setVisibility(View.VISIBLE);
                                    }
                                    MainActivity.this.allMatches.add(multimatchPojo);
                                    MainActivity.this.apiCalled++;
                                    MainActivity.this.progress.setVisibility(View.GONE);
                                }
                                MainActivity.this.recentMatchesHomeAdapter.notifyDataSetChanged();
                                MainActivity.this.top_ada.notifyDataSetChanged();
                                MainActivity.this.shimmerLayout1.setVisibility(View.GONE);
                                MainActivity.this.shimmerLayout2.setVisibility(View.GONE);
                            }
                        } catch (Exception e) {
                            Log.e("onFailure ", "" + e.getMessage());
                        }
                    }
                });
            }
        } catch (Exception unused) {
        }
    }

    public void getUpcomingMatchesRetro() {
        try {
            if (isNetworkAvailable()) {
                mGetRetroObject(baseURL()).getUpcomingMatches().enqueue(new Callback<GetUpcomingMatchesPojo>() {

                    @Override
                    public void onFailure(Call<GetUpcomingMatchesPojo> call, Throwable th) {
                    }

                    @Override
                    public void onResponse(Call<GetUpcomingMatchesPojo> call, Response<GetUpcomingMatchesPojo> response) {
                        try {
                            if (response.body().getSuccess().booleanValue()) {
                                MainActivity.this.upcomingMatches = new ArrayList<>();
                                int size = response.body().getAllMatch().size();
                                if (size > 0) {
                                    for (int i = 0; i < size; i++) {
                                        GetUpcomingMatchesPojo.AllMatch allMatch = new GetUpcomingMatchesPojo.AllMatch();
                                        allMatch.setTitle(response.body().getAllMatch().get(i).getTitle());
                                        allMatch.setMatchtime(response.body().getAllMatch().get(i).getMatchtime());
                                        allMatch.setVenue(response.body().getAllMatch().get(i).getVenue());
                                        allMatch.setResult(response.body().getAllMatch().get(i).getResult());
                                        allMatch.setTeamA(response.body().getAllMatch().get(i).getTeamA());
                                        allMatch.setTeamB(response.body().getAllMatch().get(i).getTeamB());
                                        allMatch.setTeamAImage(response.body().getAllMatch().get(i).getTeamAImage());
                                        allMatch.setTeamBImage(response.body().getAllMatch().get(i).getTeamBImage());
                                        allMatch.setImageUrl(response.body().getAllMatch().get(i).getImageUrl());
                                        MainActivity.this.upcomingMatches.add(allMatch);
                                    }
                                    MainActivity mainActivity = MainActivity.this;
                                    ArrayList<GetUpcomingMatchesPojo.AllMatch> arrayList = mainActivity.upcomingMatches;
                                    MainActivity mainActivity2 = MainActivity.this;
                                    mainActivity.upcomingMatchesHomeAdapter = new UpcomingMatchesHomeAdapter(arrayList, mainActivity2, mainActivity2.onItemClickListener);
                                    MainActivity.this.rvAllMatchList.setAdapter(MainActivity.this.upcomingMatchesHomeAdapter);
                                    MainActivity.this.upcomingMatchesHomeAdapter.notifyDataSetChanged();
                                    MainActivity.this.progress.setVisibility(View.GONE);
                                    MainActivity.this.shimmerLayout1.setVisibility(View.GONE);
                                    MainActivity.this.shimmerLayout2.setVisibility(View.GONE);
                                }
                            }
                        } catch (Exception unused) {
                        }
                    }
                });
            } else {
                showToast("No internet! Please try again");
            }
        } catch (Exception unused) {
        }
    }

    public void getResultMatches(int i, int i2) {
        try {
            if (isNetworkAvailable()) {
                HashMap hashMap = new HashMap();
                hashMap.put("start", i + "");
                hashMap.put("end", i2 + "");
                mGetRetroObject(baseURL()).getAllMatchesResult(hashMap).enqueue(new Callback<MatchResultModel>() {


                    @Override
                    public void onResponse(Call<MatchResultModel> call, Response<MatchResultModel> response) {
                        try {
                            if (response.code() == 200 && response.body().getAllMatch().size() > 0) {
                                for (int i = 0; i < response.body().getAllMatch().size(); i++) {
                                    MainActivity.this.finishedMatches.add(response.body().getAllMatch().get(i));
                                }
                                ArrayList<MatchResultModel.AllMatch> arrayList = MainActivity.this.finishedMatches;
                                MainActivity mainActivity = MainActivity.this;
                                FinishedMatchesHomeAdapter finishedMatchesHomeAdapter = new FinishedMatchesHomeAdapter(arrayList, mainActivity, mainActivity.onItemClickListener);
                                MainActivity.this.rvAllMatchList.setAdapter(finishedMatchesHomeAdapter);
                                finishedMatchesHomeAdapter.notifyDataSetChanged();
                                MainActivity.this.progress.setVisibility(View.GONE);
                                MainActivity.this.shimmerLayout1.setVisibility(View.GONE);
                                MainActivity.this.shimmerLayout2.setVisibility(View.GONE);
                            }
                        } catch (Exception unused) {
                            Log.d("", "");
                        }
                    }

                    @Override
                    public void onFailure(Call<MatchResultModel> call, Throwable th) {
                        Log.e("onFailure ", "" + th.getMessage());
                    }
                });
                return;
            }
            showToast("No internet! Please try again");
        } catch (Exception unused) {
            Log.d("", "");
        }
    }

    public void mSetSharedData(String str, String str2) {
        SharedPreferences sharedPreferences = this.mPrefrences;
        if (sharedPreferences != null) {
            if (TextUtils.isEmpty(sharedPreferences.getString(str, ""))) {
                SharedPreferences.Editor edit = this.mPrefrences.edit();
                edit.putString(str, str2);
                edit.apply();
            } else if (!this.mPrefrences.getString(str, "").equals(str2)) {
                SharedPreferences.Editor edit2 = this.mPrefrences.edit();
                edit2.putString(str, str2);
                edit2.apply();
            }
        }
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

    public void gotoNews(View view) {
        startActivity(new Intent(this, NewsActivity.class));
    }

    public void changeTheme(View view) {
        if (AppCompatDelegate.getDefaultNightMode() != AppCompatDelegate.MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "double tap to exit!", Toast.LENGTH_SHORT).show();
        new Handler(Looper.getMainLooper()).postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }
}
