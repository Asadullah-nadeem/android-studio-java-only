package com.livescorescrickets.livescores.fragments;
import com.livescorescrickets.livescores.R;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.livescorescrickets.livescores.Adapter.OddsHistoryAdapter;
import com.livescorescrickets.livescores.Pojo.JsonDataFiles.MatchOddsModel;
import com.livescorescrickets.livescores.Pojo.JsonDataFiles.MatchResultModel;
import com.livescorescrickets.livescores.Pojo.MultiMatch.MultimatchPojo;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import soup.neumorphism.NeumorphButton;

public class OddsHistoryFragment extends BaseFragment {
    RecyclerView RvTeamA;
    NeumorphButton btn1stInning;
    NeumorphButton btn2stInning;
    public int darkColor;
    public MultimatchPojo data;
    private MatchResultModel.AllMatch data2;
    int flag = 0;
    public ArrayList<MatchOddsModel.Matchst> innigsA;
    public ArrayList<MatchOddsModel.Matchst> innigsB;
    public int lightColor;
    TextView tvSquadsTeam1Name;

    public OddsHistoryFragment(MultimatchPojo multimatchPojo) {
        this.data = multimatchPojo;
    }

    public OddsHistoryFragment(MatchResultModel.AllMatch allMatch) {
        this.data2 = allMatch;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.item_not_data, viewGroup, false);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initUI(view);
        methods();
    }

    private void initUI(View view) {
        this.RvTeamA = (RecyclerView) view.findViewById(R.id.RvTeamA);
        this.tvSquadsTeam1Name = (TextView) view.findViewById(R.id.tvSquadsTeam1Name);
        this.btn1stInning = (NeumorphButton) view.findViewById(R.id.btn1stInning);
        this.btn2stInning = (NeumorphButton) view.findViewById(R.id.btn2stInning);
    }

    private void methods() {
        getMatchOdds();
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

    public void getMatchOdds() {
        try {
            if (getActivity() != null) {
                if (isNetworkAvailable()) {
                    HashMap hashMap = new HashMap();
                    if (this.data != null) {
                        hashMap.put("MatchId", "" + this.data.getMatchid());
                    } else {
                        hashMap.put("MatchId", "" + this.data2.getMatchId());
                    }
                    mGetRetroObject(baseURL()).getMatchOdds(hashMap).enqueue(new Callback<MatchOddsModel>() {


                        @Override
                        public void onFailure(Call<MatchOddsModel> call, Throwable th) {
                        }

                        @Override
                        public void onResponse(Call<MatchOddsModel> call, Response<MatchOddsModel> response) {
                            try {
                                Log.e(" response.body() ", "" + response.body());
                                if (response.body().getSuccess().booleanValue()) {
                                    OddsHistoryFragment.this.innigsA = new ArrayList<>();
                                    OddsHistoryFragment.this.innigsB = new ArrayList<>();
                                    int size = response.body().getPlayerslist().size();
                                    if (size > 0) {
                                        for (int i = 0; i < size; i++) {
                                            if (response.body().getPlayerslist().get(i).getIsfirstinning().equalsIgnoreCase("1")) {
                                                OddsHistoryFragment.this.innigsA.add(response.body().getPlayerslist().get(i));
                                            } else {
                                                OddsHistoryFragment.this.innigsB.add(response.body().getPlayerslist().get(i));
                                            }
                                        }
                                    }
                                    if (OddsHistoryFragment.this.flag == 0) {
                                        OddsHistoryFragment.this.tvSquadsTeam1Name.setText(OddsHistoryFragment.this.data.getTeama());
                                        OddsHistoryFragment.this.btn1stInning.setShadowColorDark(OddsHistoryFragment.this.getResources().getColor(R.color.color_dark_red));
                                        OddsHistoryFragment.this.btn1stInning.setShadowColorLight(OddsHistoryFragment.this.getResources().getColor(R.color.color_dark_red));
                                        OddsHistoryFragment.this.btn2stInning.setShadowColorDark(OddsHistoryFragment.this.darkColor);
                                        OddsHistoryFragment.this.btn2stInning.setShadowColorLight(OddsHistoryFragment.this.lightColor);
                                        OddsHistoryFragment oddsHistoryFragment = OddsHistoryFragment.this;
                                        oddsHistoryFragment.setOddsData(oddsHistoryFragment.innigsA);
                                        OddsHistoryFragment.this.flag = -1;
                                    }
                                    OddsHistoryFragment.this.btn1stInning.setOnClickListener(new View.OnClickListener() {


                                        public void onClick(View view) {
                                            OddsHistoryFragment.this.tvSquadsTeam1Name.setText(OddsHistoryFragment.this.data.getTeama());
                                            OddsHistoryFragment.this.btn1stInning.setShadowColorDark(OddsHistoryFragment.this.getResources().getColor(R.color.color_dark_red));
                                            OddsHistoryFragment.this.btn1stInning.setShadowColorLight(OddsHistoryFragment.this.getResources().getColor(R.color.color_dark_red));
                                            OddsHistoryFragment.this.btn2stInning.setShadowColorDark(OddsHistoryFragment.this.darkColor);
                                            OddsHistoryFragment.this.btn2stInning.setShadowColorLight(OddsHistoryFragment.this.lightColor);
                                            OddsHistoryFragment.this.setOddsData(OddsHistoryFragment.this.innigsA);
                                        }
                                    });
                                    OddsHistoryFragment.this.btn2stInning.setOnClickListener(new View.OnClickListener() {


                                        public void onClick(View view) {
                                            OddsHistoryFragment.this.tvSquadsTeam1Name.setText(OddsHistoryFragment.this.data.getTeamb());
                                            OddsHistoryFragment.this.btn2stInning.setShadowColorDark(OddsHistoryFragment.this.getResources().getColor(R.color.color_dark_red));
                                            OddsHistoryFragment.this.btn2stInning.setShadowColorLight(OddsHistoryFragment.this.getResources().getColor(R.color.color_dark_red));
                                            OddsHistoryFragment.this.btn1stInning.setShadowColorDark(OddsHistoryFragment.this.darkColor);
                                            OddsHistoryFragment.this.btn1stInning.setShadowColorLight(OddsHistoryFragment.this.lightColor);
                                            OddsHistoryFragment.this.setOddsData(OddsHistoryFragment.this.innigsB);
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

    public void setOddsData(ArrayList<MatchOddsModel.Matchst> arrayList) {
        this.RvTeamA.setAdapter(new OddsHistoryAdapter(getContext(), arrayList));
        this.RvTeamA.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
