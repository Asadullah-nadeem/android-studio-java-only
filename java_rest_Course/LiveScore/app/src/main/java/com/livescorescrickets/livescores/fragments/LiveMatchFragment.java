package com.livescorescrickets.livescores.fragments;
import com.livescorescrickets.livescores.R;

import static com.livescorescrickets.livescores.adsimp.FourData;

import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.VideoOptions;
import com.livescorescrickets.livescores.Pojo.JsonDataFiles.Jsondata;
import com.livescorescrickets.livescores.Pojo.JsonDataFiles.Jsonruns;
import com.livescorescrickets.livescores.Pojo.JsonDataFiles.MainJsonData;
import com.livescorescrickets.livescores.Pojo.JsonDataFiles.MainJsonRuns;
import com.livescorescrickets.livescores.Pojo.JsonDataFiles.MatchResultModel;
import com.livescorescrickets.livescores.Pojo.MultiMatch.MultimatchPojo;

import com.livescorescrickets.livescores.utilities.RequestHandler;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import soup.neumorphism.NeumorphCardView;

import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;


public class LiveMatchFragment extends BaseFragment {
    static final String url = FourData;
    NeumorphCardView Speaker;
    ImageView Speakerimg;
    public int apiCalled = 0;
    public TextView ballUpdate;
    public TextView batsman;
    LinearLayout batsmandata;
    public TextView bowler_status;
    public MultimatchPojo data1;
    private MatchResultModel.AllMatch data2;
    Runnable f996r = new Runnable() {


        public void run() {
            LiveMatchFragment.this.getAllMatches();
            LiveMatchFragment.this.handler.postDelayed(LiveMatchFragment.this.f996r, 5000);
        }
    };
    public TextToSpeech f997t1;
    boolean flag = true;
    final Handler handler = new Handler();
    String imageUrl;
    public boolean isSpeak = true;
    ImageView ivTeamFlag2;
    ArrayList<MultimatchPojo> liveMatches;
    LottieAnimationView lottie_4;
    LottieAnimationView lottie_6;
    LottieAnimationView lottie_i;
    LottieAnimationView lottie_w;
    public int matchID = 0;
    public TextView nonstriker4s;
    public TextView nonstrikerSix;
    public TextView nonstrikerballs;
    public TextView nonstrikerruns;
    TextView over_1;
    NeumorphCardView over_1c;
    TextView over_2;
    NeumorphCardView over_2c;
    TextView over_3;
    NeumorphCardView over_3c;
    TextView over_4;
    NeumorphCardView over_4c;
    TextView over_5;
    NeumorphCardView over_5c;
    TextView over_6;
    NeumorphCardView over_6c;
    TextView overstv;
    CircularProgressIndicator progress;
    public TextView sbatsmen;
    public TextView srOther;
    public TextView srStriker;
    public TextView striker4s;
    public TextView strikerSix;
    public TextView strikerballs;
    public TextView strikerruns;
    ImageView teamFlag;
    int timerBall = 0;
    TextView tvMatchResult;
    TextView tvMatchTitle;
    TextView tvNeedsRuns;
    TextView tvOddsGreen;
    TextView tvOddsRed;
    TextView tvRR;
    TextView tvRunxBall1;
    TextView tvRunxBall2;
    TextView tvSession1;
    TextView tvSession2;
    TextView tvTeam1;
    TextView tvTeam1Name;
    TextView tvTeam1Over;
    TextView tvTeam1Score;
    TextView tvTeam1a;
    TextView tvTeam1b;
    TextView tvTeam2;
    TextView tvTeam2Name;
    TextView tvTeam2Over;
    TextView tvTeam2Score;
    TextView tvTeam2a;
    TextView tvTeam2b;
    TextView tvTeamDraw1;
    TextView tvTeamDraw2;
    String whatsAppurl;
    TextView overdata;

    public LiveMatchFragment() {
    }

    public LiveMatchFragment(MultimatchPojo multimatchPojo) {
        this.data1 = multimatchPojo;
        this.matchID = multimatchPojo.getMatchid();
    }

    public LiveMatchFragment(MatchResultModel.AllMatch allMatch) {
        this.data2 = allMatch;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_live_match, viewGroup, false);
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
        AdLoader.Builder builder = new AdLoader.Builder(getContext(), getString(R.string.Native));
        adsgone.setVisibility(View.GONE);
        builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
            @Override
            public void onNativeAdLoaded(NativeAd nativeAdone) {

                boolean isDestroyed = false;

                if (nativeAd != null) {
                    nativeAd.destroy();
                }
                nativeAd = nativeAdone;
                NativeAdView adView = (NativeAdView) getLayoutInflater().inflate(R.layout.admobnative, null);
                GoogleNAtiveAds(nativeAdone, adView);
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



    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initUI(view);
        getAllMatches();
        mCallApi();
        adsgone = view.findViewById(R.id.adsgone);
        GoogleNAtiveShow((FrameLayout) view.findViewById(R.id.native_ad_container));

        if (this.data1 == null) {
            setLiveData(this.data2);
        }
    }

    private void mCallApi() {
        this.handler.postDelayed(this.f996r, 100);
    }

    public void getAllMatches() {
        try {
            if (isNetworkAvailable()) {
                mGetRetroObject(baseURL()).getAllLiveMatchs().enqueue(new Callback<ArrayList<MultimatchPojo>>() {


                    @Override
                    public void onResponse(Call<ArrayList<MultimatchPojo>> call, Response<ArrayList<MultimatchPojo>> response) {
                        MainJsonData mainJsonData;
                        try {
                            if (response.code() == 200) {
                                Log.d("APIresponse", "onResponse: " + response);
                                for (int i = 0; i < response.body().size(); i++) {
                                    MultimatchPojo multimatchPojo = response.body().get(i);
                                    if (multimatchPojo.getJsondata().length() > 0 && (mainJsonData = (MainJsonData) new Gson().fromJson(multimatchPojo.getJsondata(), MainJsonData.class)) != null) {
                                        Jsondata jsondata = mainJsonData.getJsondata();
                                        new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                                        if (multimatchPojo.getResult().trim().length() <= 0) {
                                            if (multimatchPojo.getJsondata().length() > 0) {
                                                mainJsonData = (MainJsonData) new Gson().fromJson(multimatchPojo.getJsondata(), MainJsonData.class);
                                            }
                                            if (mainJsonData != null && !jsondata.getBowler().equalsIgnoreCase("0") && multimatchPojo.getMatchid() == LiveMatchFragment.this.matchID) {
                                                LiveMatchFragment.this.data1 = multimatchPojo;
                                                Log.e("onFailure ", " asdfasdf " + mainJsonData);
                                            }
                                        }
                                    }
                                    LiveMatchFragment.this.apiCalled++;
                                    LiveMatchFragment liveMatchFragment = LiveMatchFragment.this;
                                    liveMatchFragment.setLiveData(liveMatchFragment.data1);
                                }
                            }
                        } catch (Exception e) {
                            Log.e("onFailure ", "asdfasdf" + e.getMessage());
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<MultimatchPojo>> call, Throwable th) {
                        Log.e("onFailure ", "asdfasdf" + th.getMessage());
                        th.printStackTrace();
                    }
                });
            }
        } catch (Exception unused) {
        }
    }

    private void initUI(View view) {
        this.overstv = (TextView) view.findViewById(R.id.overstv);
        this.Speakerimg = (ImageView) view.findViewById(R.id.Speakerimg);
        this.ballUpdate = (TextView) view.findViewById(R.id.ballUpdate);
        this.batsmandata = (LinearLayout) view.findViewById(R.id.batsmandata);
        this.tvNeedsRuns = (TextView) view.findViewById(R.id.tvNeedsRuns);
        tvNeedsRuns.setSelected(true);
        this.tvMatchResult = (TextView) view.findViewById(R.id.tvMatchResult);
        this.striker4s = (TextView) view.findViewById(R.id.striker4s);
        this.nonstriker4s = (TextView) view.findViewById(R.id.nonstriker4s);
        this.strikerSix = (TextView) view.findViewById(R.id.strikerSix);
        this.nonstrikerSix = (TextView) view.findViewById(R.id.nonstrikerSix);
        this.batsman = (TextView) view.findViewById(R.id.batsman);
        this.strikerruns = (TextView) view.findViewById(R.id.strikerruns);
        this.strikerballs = (TextView) view.findViewById(R.id.strikerballs);
        this.nonstrikerruns = (TextView) view.findViewById(R.id.nonstrikerruns);
        this.sbatsmen = (TextView) view.findViewById(R.id.strikerbatsman);
        this.nonstrikerballs = (TextView) view.findViewById(R.id.nonstrikerballs);
        this.srStriker = (TextView) view.findViewById(R.id.srStriker);
        this.bowler_status = (TextView) view.findViewById(R.id.bowlerstatus);
        this.srOther = (TextView) view.findViewById(R.id.srOther);
        this.progress = (CircularProgressIndicator) view.findViewById(R.id.progress);
        this.teamFlag = (ImageView) view.findViewById(R.id.teamFlag);
        this.tvTeam1Score = (TextView) view.findViewById(R.id.tvTeam1Score);
        this.tvTeam2Score = (TextView) view.findViewById(R.id.tvTeam2Score);
        this.tvMatchTitle = (TextView) view.findViewById(R.id.tvMatchTitle);
        this.tvTeam1Over = (TextView) view.findViewById(R.id.tvTeam1Over);
        this.tvTeam2Over = (TextView) view.findViewById(R.id.tvTeam2Over);
        this.ivTeamFlag2 = (ImageView) view.findViewById(R.id.ivTeamFlag2);
        this.tvTeam1Name = (TextView) view.findViewById(R.id.tvTeam1Name);
        this.tvTeam2Name = (TextView) view.findViewById(R.id.tvTeam2Name);
        this.tvOddsGreen = (TextView) view.findViewById(R.id.tvOddsGreen);
        this.tvOddsRed = (TextView) view.findViewById(R.id.tvOddsRed);
        this.overdata = (TextView) view.findViewById(R.id.overdata);
        this.tvSession1 = (TextView) view.findViewById(R.id.tvSession1);
        this.tvSession2 = (TextView) view.findViewById(R.id.tvSession2);
        this.tvRunxBall1 = (TextView) view.findViewById(R.id.tvRunxBall1);
        this.tvRunxBall2 = (TextView) view.findViewById(R.id.tvRunxBall2);
        this.tvRunxBall2 = (TextView) view.findViewById(R.id.tvRunxBall2);
        this.tvTeam1a = (TextView) view.findViewById(R.id.tvTeam1a);
        this.tvTeam2a = (TextView) view.findViewById(R.id.tvTeam2a);
        this.tvTeamDraw1 = (TextView) view.findViewById(R.id.tvTeamDraw1);
        this.tvTeamDraw2 = (TextView) view.findViewById(R.id.tvTeamDraw2);
        this.tvTeam1b = (TextView) view.findViewById(R.id.tvTeam1b);
        this.tvTeam2b = (TextView) view.findViewById(R.id.tvTeam2b);
        this.tvTeam1 = (TextView) view.findViewById(R.id.tvTeam1);
        this.tvTeam2 = (TextView) view.findViewById(R.id.tvTeam2);
        this.over_1 = (TextView) view.findViewById(R.id.over_1);
        this.over_2 = (TextView) view.findViewById(R.id.over_2);
        this.over_3 = (TextView) view.findViewById(R.id.over_3);
        this.over_4 = (TextView) view.findViewById(R.id.over_4);
        this.over_5 = (TextView) view.findViewById(R.id.over_5);
        this.over_6 = (TextView) view.findViewById(R.id.over_6);
        this.over_1c = (NeumorphCardView) view.findViewById(R.id.over_1c);
        this.over_2c = (NeumorphCardView) view.findViewById(R.id.over_2c);
        this.over_3c = (NeumorphCardView) view.findViewById(R.id.over_3c);
        this.over_4c = (NeumorphCardView) view.findViewById(R.id.over_4c);
        this.over_5c = (NeumorphCardView) view.findViewById(R.id.over_5c);
        this.over_6c = (NeumorphCardView) view.findViewById(R.id.over_6c);
        this.Speaker = (NeumorphCardView) view.findViewById(R.id.Speaker);
        this.tvRR = (TextView) view.findViewById(R.id.tvRR);
        this.lottie_4 = (LottieAnimationView) view.findViewById(R.id.lottie_4);
        this.lottie_6 = (LottieAnimationView) view.findViewById(R.id.lottie_6);
        this.lottie_w = (LottieAnimationView) view.findViewById(R.id.lottie_w);
        this.lottie_i = (LottieAnimationView) view.findViewById(R.id.lottie_i);
//        mFetchListFromAPI();
    }

    private void setLiveData(MultimatchPojo multimatchPojo) {
        MainJsonData mainJsonData;
        char c;
        char c2;
        char c3;
        char c4;
        char c5;
        TextView textView = this.tvMatchTitle;
        textView.setText(multimatchPojo.getTitle() + " --- " + this.apiCalled);
        this.tvMatchTitle.setText(multimatchPojo.getTitle());
        this.tvTeam1.setText(multimatchPojo.getTeama());
        this.tvTeam1Name.setText(multimatchPojo.getTeama());
        this.tvTeam2Name.setText(multimatchPojo.getTeamb());
        this.tvTeam2.setText(multimatchPojo.getTeamb());
        if (multimatchPojo.getJsondata().length() > 0 && (mainJsonData = (MainJsonData) new Gson().fromJson(multimatchPojo.getJsondata(), MainJsonData.class)) != null) {
            final Jsondata jsondata = mainJsonData.getJsondata();
            Jsonruns jsonruns = ((MainJsonRuns) new Gson().fromJson(multimatchPojo.getJsonruns(), MainJsonRuns.class)).getJsonruns();
            this.tvTeam1Name.setText(jsondata.getTeamA());
            this.tvTeam2Name.setText(jsondata.getTeamB());
            this.tvTeam1Over.setText(jsondata.getOversA());
            this.tvTeam2Over.setText(jsondata.getOversB());
            this.tvTeam1Score.setText(jsondata.getWicketA());
            this.tvTeam2Score.setText(jsondata.getWicketB());
            this.overstv.setText(jsondata.getScore());
            this.tvRR.setText(jsondata.getScore());
            this.striker4s.setText(jsondata.getS4());
            this.nonstriker4s.setText(jsondata.getNs4());
            this.strikerSix.setText(jsondata.getS6());
            this.nonstrikerSix.setText(jsondata.getNs6());
            this.tvOddsGreen.setText(jsonruns.getRateA());
            this.tvOddsRed.setText(jsonruns.getRateB());
            this.tvSession1.setText(jsonruns.getSessionA());
            this.overdata.setText(jsondata.getSessionOver());
            Log.d("overdata", "setLiveData: " + jsondata.getOversA());
            this.tvSession2.setText(jsonruns.getSessionB());
            this.tvRunxBall1.setText(jsonruns.getRunxa());
            this.tvRunxBall2.setText(jsonruns.getRunxb());
            this.tvTeam1a.setText(jsondata.getTestTeamARate1());
            this.tvTeam2a.setText(jsondata.getTestTeamARate2());
            this.tvTeam1b.setText(jsondata.getTestTeamBRate1());
            this.tvTeam2b.setText(jsondata.getTestTeamBRate2());
            this.tvTeamDraw1.setText(jsondata.getTestdrawRate1());
            this.tvTeamDraw2.setText(jsondata.getTestdrawRate2());
            TextView textView2 = this.tvRR;
            textView2.setText("Partnership : " + jsondata.getPartnership());
            Log.d("datageterror", "setLiveData: " + jsondata.getPartnership());

            this.tvNeedsRuns.setText(jsondata.getTitle().split("Toss -")[1].split("\n")[0]);
            TextView textView3 = this.tvMatchResult;
            textView3.setText("Last wkt: " + jsondata.getLastwicket());
            if (jsondata.getWicketA().equals("0/0")) {
                this.tvTeam1Name.setText(jsondata.getTeamA());
                this.tvTeam2Name.setText(jsondata.getTeamB());
            } else {
                this.tvTeam1Name.setText(jsondata.getTeamA());
                this.tvTeam2Name.setText(jsondata.getTeamB());
            }
            int i = this.apiCalled;
            if (i > this.timerBall + 20) {
                this.timerBall = i;
                this.lottie_4.setVisibility(View.GONE);
                this.lottie_6.setVisibility(View.GONE);
                this.lottie_w.setVisibility(View.GONE);
                this.ballUpdate.setVisibility(View.GONE);
                this.lottie_i.setVisibility(View.VISIBLE);
            }

            RequestManager with = Glide.with(getActivity());
            ((RequestBuilder) with.load(teamURL() + jsondata.getTeamABanner()).circleCrop()).into(this.teamFlag);
            RequestManager with2 = Glide.with(getActivity());
            ((RequestBuilder) with2.load(teamURL() + jsondata.getTeamBBanner()).circleCrop()).into(this.ivTeamFlag2);
// used           if (jsondata.getLast6Balls() != null || !TextUtils.isEmpty(jsondata.getLast6Balls()))
            if (jsondata.getLast6Balls() != null) {
                Log.d("overoneball", "setLiveData: " + jsondata.getLast6Balls());
                String[] split = jsondata.getLast6Balls().split("-");
                if (split.length > 0) {
                    this.ballUpdate.setText(split[0].toLowerCase());

                    this.ballUpdate.setVisibility(View.VISIBLE);
                    this.over_1.setText(split[0].toLowerCase());

                    this.lottie_4.setVisibility(View.GONE);
                    this.lottie_6.setVisibility(View.GONE);
                    this.lottie_w.setVisibility(View.GONE);
                    this.lottie_i.setVisibility(View.GONE);
                    String lowerCase = split[0].toLowerCase();
                    lowerCase.hashCode();
                    int hashCode = lowerCase.hashCode();
                    char c6 = 65535;
                    if (hashCode != 52) {
                        if (hashCode != 54) {
                            if (hashCode != 119) {
                                if (hashCode != 3446) {
                                    if (hashCode != 3789) {
                                        switch (hashCode) {
                                            case 48:
                                                if (lowerCase.equals("0")) {
                                                    c = 0;
                                                    break;
                                                }
                                                break;
                                            case 49:
                                                if (lowerCase.equals("1")) {
                                                    c = 1;
                                                    break;
                                                }
                                                break;
                                            case 50:
                                                if (lowerCase.equals("2")) {
                                                    c = 2;
                                                    break;
                                                }
                                                break;
                                        }
                                        switch (hashCode) {
                                            case 0:
                                                this.over_1c.setBackgroundColor(getContext().getResources().getColor(R.color.color_grey));
                                                break;
                                            case 1:
                                                this.over_1c.setBackgroundColor(getContext().getResources().getColor(R.color.color_grey));
                                                break;
                                            case 2:
                                                this.over_1c.setBackgroundColor(getContext().getResources().getColor(R.color.color_grey));
                                                break;
                                            case 3:
                                                this.over_1c.setBackgroundColor(getContext().getResources().getColor(R.color.color_green));
                                                this.lottie_4.setVisibility(View.VISIBLE);
                                                this.lottie_6.setVisibility(View.GONE);
                                                this.lottie_i.setVisibility(View.GONE);
                                                this.ballUpdate.setVisibility(View.GONE);
                                                this.lottie_w.setVisibility(View.GONE);
                                                break;
                                            case 4:
                                                this.over_1c.setBackgroundColor(getContext().getResources().getColor(R.color.color_dark_green));
                                                this.lottie_4.setVisibility(View.GONE);
                                                this.ballUpdate.setVisibility(View.GONE);
                                                this.lottie_i.setVisibility(View.GONE);
                                                this.lottie_6.setVisibility(View.GONE);
                                                this.lottie_w.setVisibility(View.VISIBLE);
                                                break;
                                            case 5:
                                                this.over_1c.setBackgroundColor(getContext().getResources().getColor(R.color.color_red));
                                                this.lottie_4.setVisibility(View.GONE);
                                                this.lottie_6.setVisibility(View.GONE);
                                                this.ballUpdate.setVisibility(View.GONE);
                                                this.lottie_i.setVisibility(View.GONE);
                                                this.lottie_w.setVisibility(View.VISIBLE);
                                                break;
                                            case 6:
                                                this.over_1c.setBackgroundColor(getContext().getResources().getColor(R.color.color_text_orange));
                                                break;
                                            case 7:
                                                this.over_1c.setBackgroundColor(getContext().getResources().getColor(R.color.color_blue));
                                                break;
                                            default:
                                                this.over_1c.setBackgroundColor(getContext().getResources().getColor(R.color.color_dark_red));
                                                break;
                                        }
                                        if (split.length > 1) {
                                            this.ballUpdate.setText(split[1].toLowerCase());
                                            this.ballUpdate.setVisibility(View.VISIBLE);
                                            this.over_2.setText(split[1].toLowerCase());
                                            this.lottie_4.setVisibility(View.GONE);
                                            this.lottie_i.setVisibility(View.GONE);
                                            this.lottie_6.setVisibility(View.GONE);
                                            this.lottie_w.setVisibility(View.GONE);
                                            String lowerCase2 = split[1].toLowerCase();
                                            lowerCase2.hashCode();
                                            int hashCode2 = lowerCase2.hashCode();
                                            if (hashCode2 != 52) {
                                                if (hashCode2 != 54) {
                                                    if (hashCode2 != 119) {
                                                        if (hashCode2 != 3446) {
                                                            if (hashCode2 != 3789) {
                                                                switch (hashCode2) {
                                                                    case 48:
                                                                        if (lowerCase2.equals("0")) {
                                                                            c5 = 0;
                                                                            break;
                                                                        }
                                                                        break;
                                                                    case 49:
                                                                        if (lowerCase2.equals("1")) {
                                                                            c5 = 1;
                                                                            break;
                                                                        }
                                                                        break;
                                                                    case 50:
                                                                        if (lowerCase2.equals("2")) {
                                                                            c5 = 2;
                                                                            break;
                                                                        }
                                                                        break;
                                                                }
                                                                switch (hashCode2) {
                                                                    case 0:
                                                                        this.over_2c.setBackgroundColor(getContext().getResources().getColor(R.color.color_grey));
                                                                        break;
                                                                    case 1:
                                                                        this.over_2c.setBackgroundColor(getContext().getResources().getColor(R.color.color_grey));
                                                                        break;
                                                                    case 2:
                                                                        this.over_2c.setBackgroundColor(getContext().getResources().getColor(R.color.color_grey));
                                                                        break;
                                                                    case 3:
                                                                        this.over_2c.setBackgroundColor(getContext().getResources().getColor(R.color.color_green));
                                                                        this.lottie_4.setVisibility(View.VISIBLE);
                                                                        this.lottie_i.setVisibility(View.GONE);
                                                                        this.lottie_6.setVisibility(View.GONE);
                                                                        this.ballUpdate.setVisibility(View.GONE);
                                                                        this.lottie_w.setVisibility(View.GONE);
                                                                        break;
                                                                    case 4:
                                                                        this.over_2c.setBackgroundColor(getContext().getResources().getColor(R.color.color_dark_green));
                                                                        this.lottie_4.setVisibility(View.GONE);
                                                                        this.lottie_6.setVisibility(View.VISIBLE);
                                                                        this.lottie_i.setVisibility(View.GONE);
                                                                        this.ballUpdate.setVisibility(View.GONE);
                                                                        this.lottie_w.setVisibility(View.GONE);
                                                                        break;
                                                                    case 5:
                                                                        this.over_2c.setBackgroundColor(getContext().getResources().getColor(R.color.color_red));
                                                                        this.lottie_4.setVisibility(View.GONE);
                                                                        this.lottie_6.setVisibility(View.GONE);
                                                                        this.lottie_i.setVisibility(View.GONE);
                                                                        this.ballUpdate.setVisibility(View.GONE);
                                                                        this.lottie_w.setVisibility(View.VISIBLE);
                                                                        break;
                                                                    case 6:
                                                                        this.over_2c.setBackgroundColor(getContext().getResources().getColor(R.color.color_text_orange));
                                                                        break;
                                                                    case 7:
                                                                        this.over_2c.setBackgroundColor(getContext().getResources().getColor(R.color.color_blue));
                                                                        break;
                                                                    default:
                                                                        this.over_2c.setBackgroundColor(getContext().getResources().getColor(R.color.color_dark_red));
                                                                        break;
                                                                }
                                                            } else if (lowerCase2.equals("wd")) {
                                                                c5 = 7;
                                                                switch (c5) {
                                                                }
                                                            }
                                                        } else if (lowerCase2.equals("lb")) {
                                                            c5 = 6;
                                                            switch (c5) {
                                                            }
                                                        }
                                                    } else if (lowerCase2.equals("w")) {
                                                        c5 = 5;
                                                        switch (c5) {
                                                        }
                                                    }
                                                } else if (lowerCase2.equals("6")) {
                                                    c5 = 4;
                                                    switch (c5) {
                                                    }
                                                }
                                            } else if (lowerCase2.equals("4")) {
                                                c5 = 3;
                                                switch (c5) {
                                                }
                                            }
                                            c5 = 65535;
                                            switch (c5) {
                                            }
                                        }
                                        if (split.length > 2) {
                                            this.ballUpdate.setText(split[2].toLowerCase());
                                            this.ballUpdate.setVisibility(View.VISIBLE);
                                            this.over_3.setText(split[2].toLowerCase());
                                            this.lottie_4.setVisibility(View.GONE);
                                            this.lottie_i.setVisibility(View.GONE);
                                            this.lottie_6.setVisibility(View.GONE);
                                            this.lottie_w.setVisibility(View.GONE);
                                            String lowerCase3 = split[2].toLowerCase();
                                            lowerCase3.hashCode();
                                            int hashCode3 = lowerCase3.hashCode();
                                            if (hashCode3 != 52) {
                                                if (hashCode3 != 54) {
                                                    if (hashCode3 != 119) {
                                                        if (hashCode3 != 3446) {
                                                            if (hashCode3 != 3789) {
                                                                switch (hashCode3) {
                                                                    case 48:
                                                                        if (lowerCase3.equals("0")) {
                                                                            c4 = 0;
                                                                            break;
                                                                        }
                                                                        break;
                                                                    case 49:
                                                                        if (lowerCase3.equals("1")) {
                                                                            c4 = 1;
                                                                            break;
                                                                        }
                                                                        break;
                                                                    case 50:
                                                                        if (lowerCase3.equals("2")) {
                                                                            c4 = 2;
                                                                            break;
                                                                        }
                                                                        break;
                                                                }
                                                                switch (hashCode3) {
                                                                    case 0:
                                                                        this.over_3c.setBackgroundColor(getContext().getResources().getColor(R.color.color_grey));
                                                                        break;
                                                                    case 1:
                                                                        this.over_3c.setBackgroundColor(getContext().getResources().getColor(R.color.color_grey));
                                                                        break;
                                                                    case 2:
                                                                        this.over_3c.setBackgroundColor(getContext().getResources().getColor(R.color.color_grey));
                                                                        break;
                                                                    case 3:
                                                                        this.over_3c.setBackgroundColor(getContext().getResources().getColor(R.color.color_green));
                                                                        this.lottie_4.setVisibility(View.VISIBLE);
                                                                        this.lottie_i.setVisibility(View.GONE);
                                                                        this.lottie_6.setVisibility(View.GONE);
                                                                        this.ballUpdate.setVisibility(View.GONE);
                                                                        this.lottie_w.setVisibility(View.GONE);
                                                                        break;
                                                                    case 4:
                                                                        this.over_3c.setBackgroundColor(getContext().getResources().getColor(R.color.color_dark_green));
                                                                        this.lottie_4.setVisibility(View.GONE);
                                                                        this.lottie_i.setVisibility(View.GONE);
                                                                        this.ballUpdate.setVisibility(View.GONE);
                                                                        this.lottie_6.setVisibility(View.VISIBLE);
                                                                        this.lottie_w.setVisibility(View.GONE);
                                                                        break;
                                                                    case 5:
                                                                        this.over_3c.setBackgroundColor(getContext().getResources().getColor(R.color.color_red));
                                                                        this.lottie_4.setVisibility(View.GONE);
                                                                        this.lottie_i.setVisibility(View.GONE);
                                                                        this.ballUpdate.setVisibility(View.GONE);
                                                                        this.lottie_6.setVisibility(View.GONE);
                                                                        this.lottie_w.setVisibility(View.VISIBLE);
                                                                        break;
                                                                    case 6:
                                                                        this.over_3c.setBackgroundColor(getContext().getResources().getColor(R.color.color_text_orange));
                                                                        break;
                                                                    case 7:
                                                                        this.over_3c.setBackgroundColor(getContext().getResources().getColor(R.color.color_blue));
                                                                        break;
                                                                    default:
                                                                        this.over_3c.setBackgroundColor(getContext().getResources().getColor(R.color.color_dark_red));
                                                                        break;
                                                                }
                                                            } else if (lowerCase3.equals("wd")) {
                                                                c4 = 7;
                                                                switch (c4) {
                                                                }
                                                            }
                                                        } else if (lowerCase3.equals("lb")) {
                                                            c4 = 6;
                                                            switch (c4) {
                                                            }
                                                        }
                                                    } else if (lowerCase3.equals("w")) {
                                                        c4 = 5;
                                                        switch (c4) {
                                                        }
                                                    }
                                                } else if (lowerCase3.equals("6")) {
                                                    c4 = 4;
                                                    switch (c4) {
                                                    }
                                                }
                                            } else if (lowerCase3.equals("4")) {
                                                c4 = 3;
                                                switch (c4) {
                                                }
                                            }
                                            c4 = 65535;
                                            switch (c4) {
                                            }
                                        }
                                        if (split.length > 3) {
                                            this.ballUpdate.setText(split[3].toLowerCase());
                                            this.ballUpdate.setVisibility(View.VISIBLE);
                                            this.over_4.setText(split[3].toLowerCase());
                                            this.lottie_4.setVisibility(View.GONE);
                                            this.lottie_i.setVisibility(View.GONE);
                                            this.lottie_6.setVisibility(View.GONE);
                                            this.lottie_w.setVisibility(View.GONE);
                                            String lowerCase4 = split[3].toLowerCase();
                                            lowerCase4.hashCode();
                                            int hashCode4 = lowerCase4.hashCode();
                                            if (hashCode4 != 52) {
                                                if (hashCode4 != 54) {
                                                    if (hashCode4 != 119) {
                                                        if (hashCode4 != 3446) {
                                                            if (hashCode4 != 3789) {
                                                                switch (hashCode4) {
                                                                    case 48:
                                                                        if (lowerCase4.equals("0")) {
                                                                            c3 = 0;
                                                                            break;
                                                                        }
                                                                        break;
                                                                    case 49:
                                                                        if (lowerCase4.equals("1")) {
                                                                            c3 = 1;
                                                                            break;
                                                                        }
                                                                        break;
                                                                    case 50:
                                                                        if (lowerCase4.equals("2")) {
                                                                            c3 = 2;
                                                                            break;
                                                                        }
                                                                        break;
                                                                }
                                                                switch (hashCode4) {
                                                                    case 0:
                                                                        this.over_4c.setBackgroundColor(getContext().getResources().getColor(R.color.color_grey));
                                                                        break;
                                                                    case 1:
                                                                        this.over_4c.setBackgroundColor(getContext().getResources().getColor(R.color.color_grey));
                                                                        break;
                                                                    case 2:
                                                                        this.over_4c.setBackgroundColor(getContext().getResources().getColor(R.color.color_grey));
                                                                        break;
                                                                    case 3:
                                                                        this.over_4c.setBackgroundColor(getContext().getResources().getColor(R.color.color_green));
                                                                        this.lottie_i.setVisibility(View.GONE);
                                                                        this.lottie_4.setVisibility(View.VISIBLE);
                                                                        this.lottie_6.setVisibility(View.GONE);
                                                                        this.ballUpdate.setVisibility(View.GONE);
                                                                        this.lottie_w.setVisibility(View.GONE);
                                                                        break;
                                                                    case 4:
                                                                        this.over_4c.setBackgroundColor(getContext().getResources().getColor(R.color.color_dark_green));
                                                                        this.lottie_4.setVisibility(View.GONE);
                                                                        this.lottie_6.setVisibility(View.VISIBLE);
                                                                        this.lottie_i.setVisibility(View.GONE);
                                                                        this.ballUpdate.setVisibility(View.GONE);
                                                                        this.lottie_w.setVisibility(View.GONE);
                                                                        break;
                                                                    case 5:
                                                                        this.over_4c.setBackgroundColor(getContext().getResources().getColor(R.color.color_red));
                                                                        this.lottie_4.setVisibility(View.GONE);
                                                                        this.lottie_i.setVisibility(View.GONE);
                                                                        this.lottie_6.setVisibility(View.GONE);
                                                                        this.ballUpdate.setVisibility(View.GONE);
                                                                        this.lottie_w.setVisibility(View.VISIBLE);
                                                                        break;
                                                                    case 6:
                                                                        this.over_4c.setBackgroundColor(getContext().getResources().getColor(R.color.color_text_orange));
                                                                        break;
                                                                    case 7:
                                                                        this.over_4c.setBackgroundColor(getContext().getResources().getColor(R.color.color_blue));
                                                                        break;
                                                                    default:
                                                                        this.over_4c.setBackgroundColor(getContext().getResources().getColor(R.color.color_dark_red));
                                                                        break;
                                                                }
                                                            } else if (lowerCase4.equals("wd")) {
                                                                c3 = 7;
                                                                switch (c3) {
                                                                }
                                                            }
                                                        } else if (lowerCase4.equals("lb")) {
                                                            c3 = 6;
                                                            switch (c3) {
                                                            }
                                                        }
                                                    } else if (lowerCase4.equals("w")) {
                                                        c3 = 5;
                                                        switch (c3) {
                                                        }
                                                    }
                                                } else if (lowerCase4.equals("6")) {
                                                    c3 = 4;
                                                    switch (c3) {
                                                    }
                                                }
                                            } else if (lowerCase4.equals("4")) {
                                                c3 = 3;
                                                switch (c3) {
                                                }
                                            }
                                            c3 = 65535;
                                            switch (c3) {
                                            }
                                        }
                                        if (split.length > 4) {
                                            this.ballUpdate.setText(split[4].toLowerCase());
                                            this.ballUpdate.setVisibility(View.VISIBLE);
                                            this.over_5.setText(split[4].toLowerCase());
                                            this.lottie_4.setVisibility(View.GONE);
                                            this.lottie_i.setVisibility(View.GONE);
                                            this.lottie_6.setVisibility(View.GONE);
                                            this.lottie_w.setVisibility(View.GONE);
                                            String lowerCase5 = split[4].toLowerCase();
                                            lowerCase5.hashCode();
                                            int hashCode5 = lowerCase5.hashCode();
                                            if (hashCode5 != 52) {
                                                if (hashCode5 != 54) {
                                                    if (hashCode5 != 119) {
                                                        if (hashCode5 != 3446) {
                                                            if (hashCode5 != 3789) {
                                                                switch (hashCode5) {
                                                                    case 48:
                                                                        if (lowerCase5.equals("0")) {
                                                                            c2 = 0;
                                                                            break;
                                                                        }
                                                                        break;
                                                                    case 49:
                                                                        if (lowerCase5.equals("1")) {
                                                                            c2 = 1;
                                                                            break;
                                                                        }
                                                                        break;
                                                                    case 50:
                                                                        if (lowerCase5.equals("2")) {
                                                                            c2 = 2;
                                                                            break;
                                                                        }
                                                                        break;
                                                                }
                                                                switch (hashCode5) {
                                                                    case 0:
                                                                        this.over_5c.setBackgroundColor(getContext().getResources().getColor(R.color.color_grey));
                                                                        break;
                                                                    case 1:
                                                                        this.over_5c.setBackgroundColor(getContext().getResources().getColor(R.color.color_grey));
                                                                        break;
                                                                    case 2:
                                                                        this.over_5c.setBackgroundColor(getContext().getResources().getColor(R.color.color_grey));
                                                                        break;
                                                                    case 3:
                                                                        this.over_5c.setBackgroundColor(getContext().getResources().getColor(R.color.color_green));
                                                                        this.lottie_i.setVisibility(View.GONE);
                                                                        this.lottie_4.setVisibility(View.VISIBLE);
                                                                        this.lottie_6.setVisibility(View.GONE);
                                                                        this.ballUpdate.setVisibility(View.GONE);
                                                                        this.lottie_w.setVisibility(View.GONE);
                                                                        break;
                                                                    case 4:
                                                                        this.over_5c.setBackgroundColor(getContext().getResources().getColor(R.color.color_dark_green));
                                                                        this.lottie_4.setVisibility(View.GONE);
                                                                        this.lottie_i.setVisibility(View.GONE);
                                                                        this.ballUpdate.setVisibility(View.GONE);
                                                                        this.lottie_6.setVisibility(View.VISIBLE);
                                                                        this.lottie_w.setVisibility(View.GONE);
                                                                        break;
                                                                    case 5:
                                                                        this.over_5c.setBackgroundColor(getContext().getResources().getColor(R.color.color_red));
                                                                        this.lottie_4.setVisibility(View.GONE);
                                                                        this.lottie_i.setVisibility(View.GONE);
                                                                        this.lottie_6.setVisibility(View.GONE);
                                                                        this.ballUpdate.setVisibility(View.GONE);
                                                                        this.lottie_w.setVisibility(View.VISIBLE);
                                                                        break;
                                                                    case 6:
                                                                        this.over_5c.setBackgroundColor(getContext().getResources().getColor(R.color.color_text_orange));
                                                                        break;
                                                                    case 7:
                                                                        this.over_5c.setBackgroundColor(getContext().getResources().getColor(R.color.color_blue));
                                                                        break;
                                                                    default:
                                                                        this.over_5c.setBackgroundColor(getContext().getResources().getColor(R.color.color_dark_red));
                                                                        break;
                                                                }
                                                            } else if (lowerCase5.equals("wd")) {
                                                                c2 = 7;
                                                                switch (c2) {
                                                                }
                                                            }
                                                        } else if (lowerCase5.equals("lb")) {
                                                            c2 = 6;
                                                            switch (c2) {
                                                            }
                                                        }
                                                    } else if (lowerCase5.equals("w")) {
                                                        c2 = 5;
                                                        switch (c2) {
                                                        }
                                                    }
                                                } else if (lowerCase5.equals("6")) {
                                                    c2 = 4;
                                                    switch (c2) {
                                                    }
                                                }
                                            } else if (lowerCase5.equals("4")) {
                                                c2 = 3;
                                                switch (c2) {
                                                }
                                            }
                                            c2 = 65535;
                                            switch (c2) {
                                            }
                                        }
                                        if (split.length > 5) {
                                            this.ballUpdate.setText(split[5].toLowerCase());
                                            this.ballUpdate.setVisibility(View.VISIBLE);
                                            this.over_6.setText(split[5].toLowerCase());
                                            this.lottie_4.setVisibility(View.GONE);
                                            this.lottie_i.setVisibility(View.GONE);
                                            this.lottie_6.setVisibility(View.GONE);
                                            this.lottie_w.setVisibility(View.GONE);
                                            String lowerCase6 = split[5].toLowerCase();
                                            lowerCase6.hashCode();
                                            int hashCode6 = lowerCase6.hashCode();
                                            if (hashCode6 != 52) {
                                                if (hashCode6 != 54) {
                                                    if (hashCode6 != 119) {
                                                        if (hashCode6 != 3446) {
                                                            if (hashCode6 != 3789) {
                                                                switch (hashCode6) {
                                                                    case 48:
                                                                        if (lowerCase6.equals("0")) {
                                                                            c6 = 0;
                                                                            break;
                                                                        }
                                                                        break;
                                                                    case 49:
                                                                        if (lowerCase6.equals("1")) {
                                                                            c6 = 1;
                                                                            break;
                                                                        }
                                                                        break;
                                                                    case 50:
                                                                        if (lowerCase6.equals("2")) {
                                                                            c6 = 2;
                                                                            break;
                                                                        }
                                                                        break;
                                                                }
                                                            } else if (lowerCase6.equals("wd")) {
                                                                c6 = 7;
                                                            }
                                                        } else if (lowerCase6.equals("lb")) {
                                                            c6 = 6;
                                                        }
                                                    } else if (lowerCase6.equals("w")) {
                                                        c6 = 5;
                                                    }
                                                } else if (lowerCase6.equals("6")) {
                                                    c6 = 4;
                                                }
                                            } else if (lowerCase6.equals("4")) {
                                                c6 = 3;
                                            }
                                            switch (c6) {
                                                case 0:
                                                    this.over_6c.setBackgroundColor(getContext().getResources().getColor(R.color.color_grey));
                                                    break;
                                                case 1:
                                                    this.over_6c.setBackgroundColor(getContext().getResources().getColor(R.color.color_grey));
                                                    break;
                                                case 2:
                                                    this.over_6c.setBackgroundColor(getContext().getResources().getColor(R.color.color_grey));
                                                    break;
                                                case 3:
                                                    this.over_6c.setBackgroundColor(getContext().getResources().getColor(R.color.color_green));
                                                    this.lottie_4.setVisibility(View.VISIBLE);
                                                    this.lottie_i.setVisibility(View.GONE);
                                                    this.ballUpdate.setVisibility(View.GONE);
                                                    this.lottie_6.setVisibility(View.GONE);
                                                    this.lottie_w.setVisibility(View.GONE);
                                                    break;
                                                case 4:
                                                    this.over_6c.setBackgroundColor(getContext().getResources().getColor(R.color.color_dark_green));
                                                    this.lottie_i.setVisibility(View.GONE);
                                                    this.lottie_4.setVisibility(View.GONE);
                                                    this.ballUpdate.setVisibility(View.GONE);
                                                    this.lottie_6.setVisibility(View.VISIBLE);
                                                    this.lottie_w.setVisibility(View.GONE);
                                                    break;
                                                case 5:
                                                    this.over_6c.setBackgroundColor(getContext().getResources().getColor(R.color.color_red));
                                                    this.lottie_4.setVisibility(View.GONE);
                                                    this.ballUpdate.setVisibility(View.GONE);
                                                    this.lottie_i.setVisibility(View.GONE);
                                                    this.lottie_6.setVisibility(View.GONE);
                                                    this.lottie_w.setVisibility(View.VISIBLE);
                                                    break;
                                                case 6:
                                                    this.over_6c.setBackgroundColor(getContext().getResources().getColor(R.color.color_text_orange));
                                                    break;
                                                case 7:
                                                    this.over_6c.setBackgroundColor(getContext().getResources().getColor(R.color.color_blue));
                                                    break;
                                                default:
                                                    this.over_6c.setBackgroundColor(getContext().getResources().getColor(R.color.color_dark_red));
                                                    break;
                                            }
                                        }
                                    } else if (lowerCase.equals("wd")) {
                                        c = 7;
                                        switch (c) {
                                        }
                                        if (split.length > 1) {
                                        }
                                        if (split.length > 2) {
                                        }
                                        if (split.length > 3) {
                                        }
                                        if (split.length > 4) {
                                        }
                                        if (split.length > 5) {
                                        }
                                    }
                                } else if (lowerCase.equals("lb")) {
                                    c = 6;
                                    switch (c) {
                                    }
                                    if (split.length > 1) {
                                    }
                                    if (split.length > 2) {
                                    }
                                    if (split.length > 3) {
                                    }
                                    if (split.length > 4) {
                                    }
                                    if (split.length > 5) {
                                    }
                                }
                            } else if (lowerCase.equals("w")) {
                                c = 5;
                                switch (c) {
                                }
                                if (split.length > 1) {
                                }
                                if (split.length > 2) {
                                }
                                if (split.length > 3) {
                                }
                                if (split.length > 4) {
                                }
                                if (split.length > 5) {
                                }
                            }
                        } else if (lowerCase.equals("6")) {
                            c = 4;
                            switch (c) {
                            }
                            if (split.length > 1) {
                            }
                            if (split.length > 2) {
                            }
                            if (split.length > 3) {
                            }
                            if (split.length > 4) {
                            }
                            if (split.length > 5) {
                            }
                        }
                    } else if (lowerCase.equals("4")) {
                        c = 3;
                        switch (c) {
                        }
                        if (split.length > 1) {
                        }
                        if (split.length > 2) {
                        }
                        if (split.length > 3) {
                        }
                        if (split.length > 4) {
                        }
                        if (split.length > 5) {
                        }
                    }
                    c = 65535;
                    switch (c) {
                    }
                    if (split.length > 1) {
                    }
                    if (split.length > 2) {
                    }
                    if (split.length > 3) {
                    }
                    if (split.length > 4) {
                    }
                    if (split.length > 5) {
                    }
                }
            }
            this.sbatsmen.setText(jsondata.getBatsman().substring(0, jsondata.getBatsman().indexOf("|")));
            this.batsman.setText(jsondata.getBatsman().substring(jsondata.getBatsman().indexOf("|") + 1));
            String substring = jsondata.getOversB().substring(0, jsondata.getOversB().indexOf("|"));
            if (substring != null && substring.length() > 0) {
                String[] split2 = substring.split(",");
                if (split2.length > 0) {
                    if (split2[0] != null) {
                        this.nonstrikerruns.setText(split2[0]);
                    }
                    if (split2[1] != null) {
                        this.strikerruns.setText(split2[1]);
                    }
                    this.batsmandata.setVisibility(View.VISIBLE);
                } else {
                    this.batsmandata.setVisibility(View.GONE);
                }
            }
            String substring2 = jsondata.getOversB().substring(jsondata.getOversB().indexOf("|") + 1);
            if (substring2 != null && substring2.length() > 0) {
                String[] split3 = substring2.split(",");
                if (split3.length > 0) {
                    if (split3[0] != null) {
                        this.nonstrikerballs.setText(substring2.split(",")[0]);
                    }
                    if (split3[1] != null) {
                        this.strikerballs.setText(substring2.split(",")[1]);
                    }
                }
            }
            try {
                if (!TextUtils.isEmpty(this.strikerruns.getText()) && !TextUtils.isEmpty(this.strikerballs.getText())) {
                    double parseInt = (double) ((int) ((((double) Integer.parseInt(this.strikerruns.getText().toString().trim())) / ((double) Integer.parseInt(this.strikerballs.getText().toString().trim()))) * 100.0d));
                    Log.e(" Srike rate ", "" + parseInt);
                    TextView textView4 = this.srStriker;
                    textView4.setText(parseInt + "");
                }
                if (!TextUtils.isEmpty(this.nonstrikerruns.getText()) && !TextUtils.isEmpty(this.nonstrikerballs.getText())) {
                    double parseInt2 = (double) Integer.parseInt(this.nonstrikerruns.getText().toString().trim());
                    double parseInt3 = (double) Integer.parseInt(this.nonstrikerballs.getText().toString().trim());
                    TextView textView5 = this.srOther;
                    textView5.setText(((double) ((int) ((parseInt2 / parseInt3) * 100.0d))) + "");
                }
            } catch (Exception unused) {
                this.srOther.setText("0.0");
                this.srStriker.setText("0.0");
            }
            if (jsondata.getBowler() != null && !jsondata.getBowler().equals("0")) {
                this.bowler_status.setText(jsondata.getBowler());
            }
            this.Speaker.setOnClickListener(new View.OnClickListener() {


                public void onClick(View view) {
                    LiveMatchFragment.this.textToSpeech(jsondata.getScore());
                    if (LiveMatchFragment.this.flag) {
                        LiveMatchFragment.this.Speakerimg.setImageResource(R.drawable.ic_baseline_volume_up_24);
                    } else {
                        LiveMatchFragment.this.Speakerimg.setImageResource(R.drawable.ic_baseline_volume_off_24);
                    }
                    LiveMatchFragment liveMatchFragment = LiveMatchFragment.this;
                    liveMatchFragment.flag = !liveMatchFragment.flag;
                }
            });
        }
    }

    private void setLiveData(MatchResultModel.AllMatch allMatch) {
        RequestManager with = Glide.with(getActivity());
        ((RequestBuilder) with.load(teamURL() + allMatch.getTeamAImage()).circleCrop()).into(this.teamFlag);
        RequestManager with2 = Glide.with(getActivity());
        ((RequestBuilder) with2.load(teamURL() + allMatch.getTeamBImage()).circleCrop()).into(this.ivTeamFlag2);
        this.tvMatchTitle.setText(allMatch.getTitle());
        this.tvTeam1Name.setText(allMatch.getTeamA());
        this.tvTeam1.setText(allMatch.getTeamA());
        this.tvTeam2Name.setText(allMatch.getTeamB());
        this.tvTeam2.setText(allMatch.getTeamB());
        this.tvMatchTitle.setText(allMatch.getTitle());
    }

    public void textToSpeech(String str) {
        String str2;
        try {
            if (this.isSpeak) {
                if (str.equalsIgnoreCase("4-4-4")) {
                    str2 = "4 run";
                } else {
                    str2 = str.equalsIgnoreCase("6-6-6") ? "6 run" : str;
                }
                TextToSpeech textToSpeech = this.f997t1;
                if (!str.equalsIgnoreCase("0") && !str.equalsIgnoreCase("1") && !str.equalsIgnoreCase("2") && !str.equalsIgnoreCase("3") && !str.equalsIgnoreCase("4") && !str.equalsIgnoreCase("5") && !str.equalsIgnoreCase("6")) {
                    textToSpeech.speak(str2 + "", 0, null);
                }
                textToSpeech.speak(str2 + " run", 0, null);
            }
        } catch (Exception unused) {
            Log.d("", "");
        }
    }

    private void mFetchListFromAPI() {
        RequestHandler.getInstance(getActivity()).addToRequestQueue(new StringRequest(0, url, new com.android.volley.Response.Listener<String>() {


            public void onResponse(String str) {
                Log.d("dataApi", str);
                try {
                    JSONArray jSONArray = new JSONObject(str).getJSONArray("data");
                    Log.d("dataApi", jSONArray.toString());
                    LiveMatchFragment.this.getData(jSONArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("MainError", e.getMessage());
                    Toast.makeText(LiveMatchFragment.this.getActivity(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {


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
                    if (jSONObject.getString(AppMeasurementSdk.ConditionalUserProperty.NAME).equals("Liveline11")) {
                        Log.d("dataApi", jSONObject.toString());
                        this.imageUrl = jSONObject.getString("bigsize_img");
                        this.whatsAppurl = jSONObject.getString(ImagesContract.URL);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
