package com.livescorescrickets.livescores.fragments;
import com.livescorescrickets.livescores.R;

import static com.livescorescrickets.livescores.adsimp.FourData;

import android.content.Context;
import android.os.Bundle;
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

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.livescorescrickets.livescores.Activity.HomeActivity;
import com.livescorescrickets.livescores.Adapter.PlayerRankAdapter;
import com.livescorescrickets.livescores.Adapter.RVPlayerAdapter;
import com.livescorescrickets.livescores.Pojo.JsonDataFiles.MatchResultModel;
import com.livescorescrickets.livescores.Pojo.MultiMatch.GetAllPlayersPojo;
import com.livescorescrickets.livescores.Pojo.MultiMatch.MultimatchPojo;
import com.livescorescrickets.livescores.Pojo.PlayerDetails;

import com.livescorescrickets.livescores.utilities.RequestHandler;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoMatchFragment extends BaseFragment {
    static final String url = FourData;
    private MultimatchPojo data;
    private MatchResultModel.AllMatch data2;
    String imageUrl;
    ImageView ivFlagTeam1;
    ImageView ivFlagTeam1Squads;
    ImageView ivFlagTeam2;
    ImageView ivFlagTeam2Squads;
    ArrayList<GetAllPlayersPojo.Playerslist> p1details = new ArrayList<>();
    ArrayList<GetAllPlayersPojo.Playerslist> p2details = new ArrayList<>();
    ArrayList<PlayerDetails> playerDetails1;
    ArrayList<PlayerDetails> playerDetails2;
    CircularProgressIndicator progress;
    RecyclerView rvOrangeCap;
    RecyclerView rvPlayer1;
    RecyclerView rvPlayer2;
    RecyclerView rvPurpleCap;
    TextView tvDateTime;
    TextView tvMatch;
    TextView tvSeries;
    TextView tvSquadsTeam1Name;
    TextView tvSquadsTeam2Name;
    TextView tvTeam1Name;
    TextView tvTeam2Name;
    TextView tvVenue;
    View view;
    String whatsAppurl;

    public InfoMatchFragment(Context context, MultimatchPojo multimatchPojo) {
        this.data = multimatchPojo;
    }

    public InfoMatchFragment(HomeActivity homeActivity, MatchResultModel.AllMatch allMatch) {
        this.data2 = allMatch;
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
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.view = layoutInflater.inflate(R.layout.fragment_match_info, viewGroup, false);
        initUI();
        methods();
        adsgone = view.findViewById(R.id.adsgone);
        GoogleNAtiveShow((FrameLayout) view.findViewById(R.id.native_ad_container));

        return this.view;
    }

    private void initUI() {
        this.progress = (CircularProgressIndicator) this.view.findViewById(R.id.progress);
        this.tvTeam1Name = (TextView) this.view.findViewById(R.id.tvTeam1Name);
        this.tvTeam2Name = (TextView) this.view.findViewById(R.id.tvTeam2Name);
        this.tvSeries = (TextView) this.view.findViewById(R.id.tvSeries);
        this.tvMatch = (TextView) this.view.findViewById(R.id.tvMatch);
        this.tvDateTime = (TextView) this.view.findViewById(R.id.tvDateTime);
        this.tvVenue = (TextView) this.view.findViewById(R.id.tvVenue);
        this.tvSquadsTeam1Name = (TextView) this.view.findViewById(R.id.tvSquadsTeam1Name);
        this.tvSquadsTeam2Name = (TextView) this.view.findViewById(R.id.tvSquadsTeam2Name);
        this.ivFlagTeam1 = (ImageView) this.view.findViewById(R.id.ivFlagTeam1);
        this.ivFlagTeam2 = (ImageView) this.view.findViewById(R.id.ivFlagTeam2);
        this.ivFlagTeam1Squads = (ImageView) this.view.findViewById(R.id.ivFlagTeam1Squads);
        this.ivFlagTeam2Squads = (ImageView) this.view.findViewById(R.id.ivFlagTeam2Squads);
    }

    private void methods() {
        if (getActivity() != null) {
            if (this.data != null) {
                getAllPlayers(this.data.getMatchid() + "");
            } else {
                getAllPlayers(this.data2.getMatchId() + "");
                this.progress.setVisibility(View.GONE);
            }
            setData();
        }
    }

    private void setData() {
        MultimatchPojo multimatchPojo = this.data;
        if (multimatchPojo != null) {
            this.tvTeam1Name.setText(multimatchPojo.getTeama());
            this.tvTeam2Name.setText(this.data.getTeamb());
            this.tvSquadsTeam1Name.setText(this.data.getTeama());
            this.tvSquadsTeam2Name.setText(this.data.getTeamb());
            this.tvSeries.setText(this.data.getTitle());
            this.tvSeries.setText(this.data.getMatchtype());
            this.tvDateTime.setText(this.data.getMatchdate());
            this.tvVenue.setText(this.data.getVenue());
            RequestManager with = Glide.with(getActivity());
            ((RequestBuilder) with.load(teamURL() + this.data.getTeamaimage()).circleCrop()).into(this.ivFlagTeam1Squads);
            RequestManager with2 = Glide.with(getActivity());
            ((RequestBuilder) with2.load(teamURL() + this.data.getTeambimage()).circleCrop()).into(this.ivFlagTeam2Squads);
            RequestManager with3 = Glide.with(getActivity());
            ((RequestBuilder) with3.load(teamURL() + this.data.getTeamaimage()).circleCrop()).into(this.ivFlagTeam1);
            RequestManager with4 = Glide.with(getActivity());
            ((RequestBuilder) with4.load(teamURL() + this.data.getTeambimage()).circleCrop()).into(this.ivFlagTeam2);
            return;
        }
        this.tvTeam1Name.setText(this.data2.getTeamA());
        this.tvTeam2Name.setText(this.data2.getTeamB());
        this.tvSquadsTeam1Name.setText(this.data2.getTeamA());
        this.tvSquadsTeam2Name.setText(this.data2.getTeamB());
        this.tvSeries.setText(this.data2.getTitle());
        this.tvSeries.setText(this.data2.getMatchtype());
        this.tvDateTime.setText(this.data2.getMatchtime());
        this.tvVenue.setText(this.data2.getVenue());
        RequestManager with5 = Glide.with(getActivity());
        ((RequestBuilder) with5.load(teamURL() + this.data2.getTeamAImage()).circleCrop()).into(this.ivFlagTeam1Squads);
        RequestManager with6 = Glide.with(getActivity());
        ((RequestBuilder) with6.load(teamURL() + this.data2.getTeamBImage()).circleCrop()).into(this.ivFlagTeam2Squads);
        RequestManager with7 = Glide.with(getActivity());
        ((RequestBuilder) with7.load(teamURL() + this.data2.getTeamAImage()).circleCrop()).into(this.ivFlagTeam1);
        RequestManager with8 = Glide.with(getActivity());
        ((RequestBuilder) with8.load(teamURL() + this.data2.getTeamBImage()).circleCrop()).into(this.ivFlagTeam2);
    }

    public void init(ArrayList<GetAllPlayersPojo.Playerslist> arrayList, ArrayList<GetAllPlayersPojo.Playerslist> arrayList2) {
        this.progress.setVisibility(View.GONE);
        this.rvPlayer1 = (RecyclerView) this.view.findViewById(R.id.rvPlayer1);
        this.rvPlayer2 = (RecyclerView) this.view.findViewById(R.id.rvPlayer2);
        this.rvOrangeCap = (RecyclerView) this.view.findViewById(R.id.rvOrangeCap);
        this.rvPurpleCap = (RecyclerView) this.view.findViewById(R.id.rvPurpleCap);
        this.playerDetails1 = new ArrayList<>();
        this.playerDetails2 = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            GetAllPlayersPojo.Playerslist playerslist = arrayList.get(i);
            this.playerDetails2.add(new PlayerDetails(playerslist.getSeqno() + "", playerslist.getPlayerName(), playerslist.getPlayerImage(), playerslist.getTeamName(), playerslist.getRuns() + "", playerslist.getOutby()));
        }
        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
            GetAllPlayersPojo.Playerslist playerslist2 = arrayList2.get(i2);
            this.playerDetails1.add(new PlayerDetails(playerslist2.getSeqno() + "", playerslist2.getPlayerName(), playerslist2.getPlayerImage(), playerslist2.getTeamName(), playerslist2.getRuns() + "", playerslist2.getOutby()));
        }
        this.rvPlayer1.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        this.rvPlayer1.setHasFixedSize(true);
        this.rvPlayer1.setAdapter(new RVPlayerAdapter(this.playerDetails1, getActivity()));
        this.rvPlayer2.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        this.rvPlayer2.setHasFixedSize(true);
        this.rvPlayer2.setAdapter(new RVPlayerAdapter(this.playerDetails2, getActivity()));
        this.rvOrangeCap.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.rvOrangeCap.setHasFixedSize(true);
        this.rvOrangeCap.setAdapter(new PlayerRankAdapter(this.playerDetails1, getActivity()));
        this.rvPurpleCap.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.rvPurpleCap.setHasFixedSize(true);
        this.rvPurpleCap.setAdapter(new PlayerRankAdapter(this.playerDetails2, getActivity()));
    }

    public void getAllPlayers(String str) {
        try {
            if (getActivity() != null) {
                if (isNetworkAvailable()) {
                    HashMap hashMap = new HashMap();
                    if (this.data != null) {
                        hashMap.put("MatchId", "" + this.data.getMatchid());
                    } else {
                        hashMap.put("MatchId", "" + this.data2.getMatchId());
                    }
                    mGetRetroObject(baseURL()).getAllPlayers(hashMap).enqueue(new Callback<GetAllPlayersPojo>() {


                        @Override
                        public void onResponse(Call<GetAllPlayersPojo> call, Response<GetAllPlayersPojo> response) {
                            try {
                                if (response.body().getSuccess().booleanValue()) {
                                    int size = response.body().getPlayerslist().size();
                                    if (size > 0) {
                                        InfoMatchFragment.this.p1details.clear();
                                        InfoMatchFragment.this.p2details.clear();
                                        for (int i = 0; i < size; i++) {
                                            if (response.body().getPlayerslist().get(i).getTeamSide().equalsIgnoreCase("Team A")) {
                                                response.body().getPlayerslist().get(i).getTeamName();
                                                response.body().getPlayerslist().get(i).getTeamRuns();
                                                InfoMatchFragment.this.p1details.add(response.body().getPlayerslist().get(i));
                                            } else {
                                                response.body().getPlayerslist().get(i).getTeamName();
                                                response.body().getPlayerslist().get(i).getTeamRuns();
                                                InfoMatchFragment.this.p2details.add(response.body().getPlayerslist().get(i));
                                            }
                                        }
                                        InfoMatchFragment infoMatchFragment = InfoMatchFragment.this;
                                        infoMatchFragment.init(infoMatchFragment.p1details, InfoMatchFragment.this.p2details);
                                        InfoMatchFragment.this.progress.setVisibility(View.GONE);
                                    }
                                    InfoMatchFragment.this.progress.setVisibility(View.GONE);
                                }
                            } catch (Exception unused) {
                            }
                        }

                        @Override
                        public void onFailure(Call<GetAllPlayersPojo> call, Throwable th) {
                            InfoMatchFragment.this.progress.setVisibility(View.GONE);
                        }
                    });
                    return;
                }
                this.progress.setVisibility(View.GONE);
                showToast("No internet! Please try again");
            }
        } catch (Exception unused) {
            Log.d("", "");
            this.progress.setVisibility(View.GONE);
        }
    }

    private void mFetchListFromAPI() {
        RequestHandler.getInstance(getActivity()).addToRequestQueue(new StringRequest(0, url, new com.android.volley.Response.Listener<String>() {


            public void onResponse(String str) {
                Log.d("dataApi", str);
                try {
                    JSONArray jSONArray = new JSONObject(str).getJSONArray("data");
                    Log.d("dataApi", jSONArray.toString());
                    InfoMatchFragment.this.getData(jSONArray);
                    Glide.with(InfoMatchFragment.this.getActivity());
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("MainError", e.getMessage());
                    Toast.makeText(InfoMatchFragment.this.getActivity(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
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
