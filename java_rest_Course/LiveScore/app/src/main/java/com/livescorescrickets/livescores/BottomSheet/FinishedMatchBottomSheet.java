package com.livescorescrickets.livescores.BottomSheet;
import com.livescorescrickets.livescores.R;
import com.livescorescrickets.livescores.R;
import static com.livescorescrickets.livescores.adsimp.FourData;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.livescorescrickets.livescores.Activity.BaseActivity;
import com.livescorescrickets.livescores.Activity.HomeActivity;
import com.livescorescrickets.livescores.Pojo.JsonDataFiles.Jsondata;
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
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FinishedMatchBottomSheet extends BottomSheetDialogFragment {
    static final String url = FourData;
    MultimatchPojo data;
    private final MatchResultModel.AllMatch data2;
    String imageUrl;
    ImageView ivTeam1Image;
    ImageView ivTeam2Image;
    LottieAnimationView lottie_animation_view_S;
    TextView tvMatchResult;
    TextView tvMatchTitle;
    TextView tvTeam1Name;
    TextView tvTeam1Over;
    TextView tvTeam1Score;
    TextView tvTeam2Name;
    TextView tvTeam2Over;
    TextView tvTeam2Score;
    TextView tvdate;
    View view;
    String whatsAppurl;

    public FinishedMatchBottomSheet(MultimatchPojo multimatchPojo) {
        this.data = multimatchPojo;
        this.data2 = null;
    }

    public FinishedMatchBottomSheet(MatchResultModel.AllMatch allMatch) {
        this.data2 = allMatch;
        this.data = null;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
        View inflate = layoutInflater.inflate(R.layout.fragment_finishedmatch_bottom_sheet, viewGroup, false);
        this.view = inflate;
        this.tvTeam1Name = (TextView) inflate.findViewById(R.id.tvTeam1Name);
        this.tvTeam2Name = (TextView) this.view.findViewById(R.id.tvTeam2Name);
        this.tvTeam1Score = (TextView) this.view.findViewById(R.id.tvTeam1Score);
        this.tvTeam2Score = (TextView) this.view.findViewById(R.id.tvTeam2Score);
        this.tvTeam1Over = (TextView) this.view.findViewById(R.id.tvTeam1Over);
        this.tvTeam2Over = (TextView) this.view.findViewById(R.id.tvTeam2Over);
        this.tvMatchTitle = (TextView) this.view.findViewById(R.id.tvMatchTitle);
        this.ivTeam1Image = (ImageView) this.view.findViewById(R.id.ivTeam1Image);
        this.ivTeam2Image = (ImageView) this.view.findViewById(R.id.ivTeam2Image);
        this.tvMatchResult = (TextView) this.view.findViewById(R.id.tvMatchResult);
        this.tvdate = (TextView) this.view.findViewById(R.id.tvdate);
        this.lottie_animation_view_S = (LottieAnimationView) this.view.findViewById(R.id.lottie_animation_view_S);
        MultimatchPojo multimatchPojo = this.data;
        if (multimatchPojo != null) {
            setData(multimatchPojo);
        } else {
            setData(this.data2);
        }
//        mFetchListFromAPI();
        return this.view;
    }

    public void setData(final MultimatchPojo multimatchPojo) {
        MainJsonData mainJsonData;
        new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        this.tvdate.setText(multimatchPojo.getMatchtime());
        this.tvMatchTitle.setText(multimatchPojo.getTitle());
        this.tvMatchResult.setText(multimatchPojo.getResult());
        if (multimatchPojo.getJsondata().length() > 0 && (mainJsonData = (MainJsonData) new Gson().fromJson(multimatchPojo.getJsondata(), MainJsonData.class)) != null) {
            Jsondata jsondata = mainJsonData.getJsondata();
            ((MainJsonRuns) new Gson().fromJson(multimatchPojo.getJsonruns(), MainJsonRuns.class)).getJsonruns();
            this.tvTeam1Over.setText(jsondata.getOversA());
            this.tvTeam2Over.setText(jsondata.getOversB());
            this.tvTeam1Score.setText(jsondata.getWicketA());
            this.tvTeam2Score.setText(jsondata.getWicketB());
            this.tvTeam1Name.setText(jsondata.getTeamA());
            this.tvTeam2Name.setText(jsondata.getTeamB());
            RequestManager with = Glide.with(this);
            ((RequestBuilder) ((RequestBuilder) with.load(((BaseActivity) getActivity()).teamURL() + jsondata.getTeamABanner()).placeholder((int) R.drawable.ic_player_placeholder_dark)).circleCrop()).into(this.ivTeam1Image);
            RequestManager with2 = Glide.with(this);
            ((RequestBuilder) ((RequestBuilder) with2.load(((BaseActivity) getActivity()).teamURL() + jsondata.getTeamBBanner()).placeholder((int) R.drawable.ic_player_placeholder_dark)).circleCrop()).into(this.ivTeam2Image);
        }
        this.lottie_animation_view_S.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                Intent intent = new Intent(FinishedMatchBottomSheet.this.getActivity(), HomeActivity.class);
                intent.putExtra("data", multimatchPojo);
                FinishedMatchBottomSheet.this.getActivity().startActivity(intent);
            }
        });
    }

    public void setData(final MatchResultModel.AllMatch allMatch) {
        new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        this.tvTeam1Name.setText(allMatch.getTeamA());
        this.tvTeam2Name.setText(allMatch.getTeamB());
        this.tvMatchTitle.setText(allMatch.getTitle());
        this.tvMatchResult.setText(allMatch.getResult());
        this.tvdate.setText(allMatch.getMatchtime());
        RequestManager with = Glide.with(getActivity());
        ((RequestBuilder) with.load(((BaseActivity) getActivity()).teamURL() + allMatch.getTeamAImage()).circleCrop()).into(this.ivTeam1Image);
        RequestManager with2 = Glide.with(getActivity());
        ((RequestBuilder) with2.load(((BaseActivity) getActivity()).teamURL() + allMatch.getTeamBImage()).circleCrop()).into(this.ivTeam2Image);
        this.lottie_animation_view_S.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                Intent intent = new Intent(FinishedMatchBottomSheet.this.getActivity(), HomeActivity.class);
                intent.putExtra("data2", allMatch);
                FinishedMatchBottomSheet.this.getActivity().startActivity(intent);
            }
        });
    }

    private void mFetchListFromAPI() {
        RequestHandler.getInstance(getActivity()).addToRequestQueue(new StringRequest(0, url, new Response.Listener<String>() {


            public void onResponse(String str) {
                Log.d("dataApi", str);
                try {
                    JSONArray jSONArray = new JSONObject(str).getJSONArray("data");
                    Log.d("dataApi", jSONArray.toString());
                    FinishedMatchBottomSheet.this.getData(jSONArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("MainError", e.getMessage());
                    Toast.makeText(FinishedMatchBottomSheet.this.getActivity(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
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
                    if (jSONObject.getString(AppMeasurementSdk.ConditionalUserProperty.NAME).equals("Liveline11")) {
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
