package com.livescorescrickets.livescores.fragments;
import com.livescorescrickets.livescores.R;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.livescorescrickets.livescores.Adapter.PlayerScoreCardAdapter;
import com.livescorescrickets.livescores.Pojo.JsonDataFiles.Jsondata;
import com.livescorescrickets.livescores.Pojo.JsonDataFiles.MainJsonData;
import com.livescorescrickets.livescores.Pojo.JsonDataFiles.MainJsonRuns;
import com.livescorescrickets.livescores.Pojo.JsonDataFiles.MatchResultModel;
import com.livescorescrickets.livescores.Pojo.MultiMatch.GetAllPlayersPojo;
import com.livescorescrickets.livescores.Pojo.MultiMatch.MultimatchPojo;
import com.livescorescrickets.livescores.Pojo.PlayerScoreCardDetails;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import soup.neumorphism.NeumorphCardView;

public class ScorecardMatchFragment extends BaseFragment {
    TextView cvTotalScore1;
    public int darkColor;
    public MultimatchPojo data;
    private MatchResultModel.AllMatch data2;
    int flag = 0;
    ImageView ivTeamFlag1;
    ImageView ivTeamFlag2;
    public int lightColor;
    ArrayList<GetAllPlayersPojo.Playerslist> p1details = new ArrayList<>();
    ArrayList<GetAllPlayersPojo.Playerslist> p2details = new ArrayList<>();
    ArrayList<PlayerScoreCardDetails> playerDetails1;
    ArrayList<PlayerScoreCardDetails> playerDetails2;
    RecyclerView rvBatsmanTeam1;
    RecyclerView rvBowlerTeam1;
    NeumorphCardView teamAButton;
    NeumorphCardView teamBButton;
    TextView tvTeam1Name;
    TextView tvTeam2Name;
    View view;

    public ScorecardMatchFragment() {
    }

    public ScorecardMatchFragment(MultimatchPojo multimatchPojo) {
        this.data = multimatchPojo;
    }

    public ScorecardMatchFragment(MatchResultModel.AllMatch allMatch) {
        this.data2 = allMatch;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.view = layoutInflater.inflate(R.layout.fragment_match_scorecard, viewGroup, false);
        initUI();
        if (this.data != null) {
            getAllPlayers(this.data.getMatchid() + "");
            setData(this.data);
        } else {
            getAllPlayers(this.data2.getMatchId() + "");
            setData2(this.data2);
        }
        return this.view;
    }

    private void setData2(MatchResultModel.AllMatch allMatch) {
        this.tvTeam1Name.setText(allMatch.getTeamA());
        this.tvTeam2Name.setText(allMatch.getTeamB());
        RequestManager with = Glide.with(getActivity());
        ((RequestBuilder) with.load(teamURL() + allMatch.getTeamAImage()).circleCrop()).into(this.ivTeamFlag1);
        RequestManager with2 = Glide.with(getActivity());
        ((RequestBuilder) with2.load(teamURL() + allMatch.getTeamBImage()).circleCrop()).into(this.ivTeamFlag2);
    }

    private void initUI() {
        this.tvTeam1Name = (TextView) this.view.findViewById(R.id.tvTeam1Name);
        this.tvTeam2Name = (TextView) this.view.findViewById(R.id.tvTeam2Name);
        this.ivTeamFlag1 = (ImageView) this.view.findViewById(R.id.ivTeamFlag1);
        this.ivTeamFlag2 = (ImageView) this.view.findViewById(R.id.ivTeamFlag2);
        this.cvTotalScore1 = (TextView) this.view.findViewById(R.id.cvTotalScore1);
        this.teamAButton = (NeumorphCardView) this.view.findViewById(R.id.teamAButton);
        this.teamBButton = (NeumorphCardView) this.view.findViewById(R.id.teamBButton);
    }

    private void setData(MultimatchPojo multimatchPojo) {
        MainJsonData mainJsonData;
        try {
            if (multimatchPojo.getJsondata().length() > 0) {
                mainJsonData = (MainJsonData) new Gson().fromJson(multimatchPojo.getJsondata(), MainJsonData.class);
                this.tvTeam1Name.setText(mainJsonData.getJsondata().getTeamA());
                this.tvTeam2Name.setText(mainJsonData.getJsondata().getTeamB());
            } else {
                mainJsonData = null;
            }
            RequestManager with = Glide.with(getActivity());
            ((RequestBuilder) with.load(teamURL() + mainJsonData.getJsondata().getTeamABanner()).circleCrop()).into(this.ivTeamFlag1);
            RequestManager with2 = Glide.with(getActivity());
            ((RequestBuilder) with2.load(teamURL() + mainJsonData.getJsondata().getTeamBBanner()).circleCrop()).into(this.ivTeamFlag2);
        } catch (Exception unused) {
            this.tvTeam1Name.setText(multimatchPojo.getTeama());
            this.tvTeam2Name.setText(multimatchPojo.getTeamb());
            RequestManager with3 = Glide.with(getActivity());
            ((RequestBuilder) with3.load(teamURL() + multimatchPojo.getTeamaimage()).circleCrop()).into(this.ivTeamFlag1);
            RequestManager with4 = Glide.with(getActivity());
            ((RequestBuilder) with4.load(teamURL() + multimatchPojo.getTeambimage()).circleCrop()).into(this.ivTeamFlag2);
        }
    }

    public void init(ArrayList<GetAllPlayersPojo.Playerslist> arrayList) {
        this.rvBatsmanTeam1 = (RecyclerView) this.view.findViewById(R.id.rvBatsmanTeam1);
        this.rvBowlerTeam1 = (RecyclerView) this.view.findViewById(R.id.rvBowlerTeam1);
        this.playerDetails1 = new ArrayList<>();
        this.playerDetails2 = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            GetAllPlayersPojo.Playerslist playerslist = arrayList.get(i);
            this.playerDetails1.add(new PlayerScoreCardDetails(playerslist.getSeqno() + "", playerslist.getPlayerName(), playerslist.getRuns() + "", playerslist.getBalls() + "", playerslist.getFour().toString(), playerslist.getSix().toString(), ""));
        }
        this.rvBatsmanTeam1.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.rvBatsmanTeam1.setHasFixedSize(true);
        this.rvBatsmanTeam1.setAdapter(new PlayerScoreCardAdapter(this.playerDetails1, getActivity()));
        this.rvBowlerTeam1.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.rvBowlerTeam1.setHasFixedSize(true);
        this.rvBowlerTeam1.setAdapter(new PlayerScoreCardAdapter(this.playerDetails2, getActivity()));
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
                        public void onFailure(Call<GetAllPlayersPojo> call, Throwable th) {
                        }

                        @Override
                        public void onResponse(Call<GetAllPlayersPojo> call, Response<GetAllPlayersPojo> response) {
                            int size;
                            MainJsonData mainJsonData;
                            try {
                                if (response.body().getSuccess().booleanValue() && (size = response.body().getPlayerslist().size()) > 0) {
                                    ScorecardMatchFragment.this.p1details.clear();
                                    ScorecardMatchFragment.this.p2details.clear();
                                    for (int i = 0; i < size; i++) {
                                        if (response.body().getPlayerslist().get(i).getTeamSide().equalsIgnoreCase("Team A")) {
                                            response.body().getPlayerslist().get(i).getTeamName();
                                            response.body().getPlayerslist().get(i).getTeamRuns();
                                            ScorecardMatchFragment.this.p1details.add(response.body().getPlayerslist().get(i));
                                        } else {
                                            response.body().getPlayerslist().get(i).getTeamName();
                                            response.body().getPlayerslist().get(i).getTeamRuns();
                                            ScorecardMatchFragment.this.p2details.add(response.body().getPlayerslist().get(i));
                                        }
                                    }
                                    if (ScorecardMatchFragment.this.flag == 0) {
                                        ScorecardMatchFragment scorecardMatchFragment = ScorecardMatchFragment.this;
                                        scorecardMatchFragment.init(scorecardMatchFragment.p1details);
                                        ScorecardMatchFragment.this.teamAButton.setShadowColorDark(ScorecardMatchFragment.this.getResources().getColor(R.color.color_dark_red));
                                        ScorecardMatchFragment.this.teamAButton.setShadowColorLight(ScorecardMatchFragment.this.getResources().getColor(R.color.color_dark_red));
                                        if (ScorecardMatchFragment.this.data.getJsondata().length() > 0 && (mainJsonData = (MainJsonData) new Gson().fromJson(ScorecardMatchFragment.this.data.getJsondata(), MainJsonData.class)) != null) {
                                            Jsondata jsondata = mainJsonData.getJsondata();
                                            ((MainJsonRuns) new Gson().fromJson(ScorecardMatchFragment.this.data.getJsonruns(), MainJsonRuns.class)).getJsonruns();
                                            ScorecardMatchFragment.this.cvTotalScore1.setText(jsondata.getWicketA());
                                        }
                                        ScorecardMatchFragment.this.flag = -1;
                                    }
                                    ScorecardMatchFragment.this.teamAButton.setOnClickListener(new View.OnClickListener() {


                                        public void onClick(View view) {
                                            MainJsonData mainJsonData;
                                            ScorecardMatchFragment.this.init(ScorecardMatchFragment.this.p1details);
                                            ScorecardMatchFragment.this.teamAButton.setShadowColorDark(ScorecardMatchFragment.this.getResources().getColor(R.color.color_dark_red));
                                            ScorecardMatchFragment.this.teamAButton.setShadowColorLight(ScorecardMatchFragment.this.getResources().getColor(R.color.color_dark_red));
                                            ScorecardMatchFragment.this.teamBButton.setShadowColorDark(ScorecardMatchFragment.this.darkColor);
                                            ScorecardMatchFragment.this.teamBButton.setShadowColorLight(ScorecardMatchFragment.this.lightColor);
                                            if (ScorecardMatchFragment.this.data.getJsondata().length() > 0 && (mainJsonData = (MainJsonData) new Gson().fromJson(ScorecardMatchFragment.this.data.getJsondata(), MainJsonData.class)) != null) {
                                                Jsondata jsondata = mainJsonData.getJsondata();
                                                ((MainJsonRuns) new Gson().fromJson(ScorecardMatchFragment.this.data.getJsonruns(), MainJsonRuns.class)).getJsonruns();
                                                ScorecardMatchFragment.this.cvTotalScore1.setText(jsondata.getWicketA());
                                            }
                                        }
                                    });
                                    ScorecardMatchFragment.this.teamBButton.setOnClickListener(new View.OnClickListener() {


                                        public void onClick(View view) {
                                            MainJsonData mainJsonData;
                                            ScorecardMatchFragment.this.init(ScorecardMatchFragment.this.p2details);
                                            ScorecardMatchFragment.this.teamBButton.setShadowColorDark(ScorecardMatchFragment.this.getResources().getColor(R.color.color_dark_red));
                                            ScorecardMatchFragment.this.teamBButton.setShadowColorLight(ScorecardMatchFragment.this.getResources().getColor(R.color.color_dark_red));
                                            ScorecardMatchFragment.this.teamAButton.setShadowColorDark(ScorecardMatchFragment.this.darkColor);
                                            ScorecardMatchFragment.this.teamAButton.setShadowColorLight(ScorecardMatchFragment.this.lightColor);
                                            if (ScorecardMatchFragment.this.data.getJsondata().length() > 0 && (mainJsonData = (MainJsonData) new Gson().fromJson(ScorecardMatchFragment.this.data.getJsondata(), MainJsonData.class)) != null) {
                                                Jsondata jsondata = mainJsonData.getJsondata();
                                                ((MainJsonRuns) new Gson().fromJson(ScorecardMatchFragment.this.data.getJsonruns(), MainJsonRuns.class)).getJsonruns();
                                                ScorecardMatchFragment.this.cvTotalScore1.setText(jsondata.getWicketB());
                                            }
                                        }
                                    });
                                }
                            } catch (Exception unused) {
                            }
                        }
                    });
                    return;
                }
                showToast("No internet! Please try again");
            }
        } catch (Exception unused) {
            Log.d("", "");
        }
    }
}
