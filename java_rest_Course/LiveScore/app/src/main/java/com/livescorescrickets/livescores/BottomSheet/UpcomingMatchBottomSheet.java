package com.livescorescrickets.livescores.BottomSheet;
import com.livescorescrickets.livescores.R;

import static com.livescorescrickets.livescores.adsimp.FourData;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.livescorescrickets.livescores.Pojo.JsonDataFiles.MainJsonData;
import com.livescorescrickets.livescores.Pojo.JsonDataFiles.MainJsonRuns;
import com.livescorescrickets.livescores.Pojo.MultiMatch.GetUpcomingMatchesPojo;
import com.livescorescrickets.livescores.Pojo.MultiMatch.MultimatchPojo;

import com.livescorescrickets.livescores.utilities.RequestHandler;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UpcomingMatchBottomSheet extends BottomSheetDialogFragment {
    static final String url = FourData;
    private String TAG;
    private MultimatchPojo data;
    private GetUpcomingMatchesPojo.AllMatch data2;
    String imageUrl;
    ImageView ivTeam1Image;
    ImageView ivTeam2Image;
    LottieAnimationView lottie_animation_view_S;
    TextView timer;
    TextView tvMatchTitle;
    TextView tvTeam1Name;
    TextView tvTeam1Over;
    TextView tvTeam1Score;
    TextView tvTeam2Name;
    TextView tvTeam2Over;
    TextView tvTeam2Score;
    TextView tvdataTime;
    View view;
    String whatsAppurl;

    public UpcomingMatchBottomSheet(MultimatchPojo multimatchPojo) {
        this.TAG = "987654";
        this.data = multimatchPojo;
        this.data2 = null;
    }

    public UpcomingMatchBottomSheet(GetUpcomingMatchesPojo.AllMatch allMatch) {
        this.TAG = "987654";
        this.data = null;
        this.data2 = allMatch;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
        View inflate = layoutInflater.inflate(R.layout.fragment_upcomingmatch_bottom_sheet, viewGroup, false);
        this.view = inflate;
        this.tvTeam1Name = (TextView) inflate.findViewById(R.id.tvTeam1Name);
        this.tvTeam2Name = (TextView) this.view.findViewById(R.id.tvTeam2Name);
        this.tvTeam1Score = (TextView) this.view.findViewById(R.id.tvTeam1Score);
        this.tvTeam2Score = (TextView) this.view.findViewById(R.id.tvTeam2Score);
        this.tvTeam1Over = (TextView) this.view.findViewById(R.id.tvTeam1Over);
        this.tvTeam2Over = (TextView) this.view.findViewById(R.id.tvTeam2Over);
        this.tvdataTime = (TextView) this.view.findViewById(R.id.tvdataTime);
        this.tvMatchTitle = (TextView) this.view.findViewById(R.id.tvMatchTitle);
        this.ivTeam1Image = (ImageView) this.view.findViewById(R.id.ivTeam1Image);
        this.ivTeam2Image = (ImageView) this.view.findViewById(R.id.ivTeam2Image);
        this.timer = (TextView) this.view.findViewById(R.id.timer);
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
        this.tvTeam1Name.setText(multimatchPojo.getTeama());
        this.tvTeam2Name.setText(multimatchPojo.getTeamb());
        this.tvMatchTitle.setText(multimatchPojo.getTitle());
        this.tvdataTime.setText(multimatchPojo.getMatchtime());
        if (multimatchPojo.getJsondata().length() > 0 && (mainJsonData = (MainJsonData) new Gson().fromJson(multimatchPojo.getJsondata(), MainJsonData.class)) != null) {
            mainJsonData.getJsondata();
            ((MainJsonRuns) new Gson().fromJson(multimatchPojo.getJsonruns(), MainJsonRuns.class)).getJsonruns();
        }
        RequestManager with = Glide.with(getActivity());
        ((RequestBuilder) with.load(((BaseActivity) getActivity()).teamURL() + multimatchPojo.getTeamaimage()).circleCrop()).into(this.ivTeam1Image);
        RequestManager with2 = Glide.with(getActivity());
        ((RequestBuilder) with2.load(((BaseActivity) getActivity()).teamURL() + multimatchPojo.getTeambimage()).circleCrop()).into(this.ivTeam2Image);
        this.lottie_animation_view_S.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                Intent intent = new Intent(UpcomingMatchBottomSheet.this.getActivity(), HomeActivity.class);
                intent.putExtra("data", multimatchPojo);
                UpcomingMatchBottomSheet.this.getActivity().startActivity(intent);
            }
        });
        Calendar instance = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        try {
            instance.setTime(simpleDateFormat.parse(multimatchPojo.getMatchdate() + ""));
            TextView textView = this.timer;
            textView.setText(instance.getTimeInMillis() + "");
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), multimatchPojo.getMatchdate(), Toast.LENGTH_SHORT).show();
            this.timer.setText(e.getMessage());
        }
        new CountDownTimer(Calendar.getInstance().getTimeInMillis() - instance.getTimeInMillis(), 1000) {


            public void onTick(long j) {
                DecimalFormat decimalFormat = new DecimalFormat("00");
                TextView textView = UpcomingMatchBottomSheet.this.timer;
                textView.setText(decimalFormat.format((j / 3600000) % 24) + ":" + decimalFormat.format((j / 60000) % 60) + ":" + decimalFormat.format((j / 1000) % 60));
            }

            public void onFinish() {
                UpcomingMatchBottomSheet.this.timer.setText("00:00:00");
            }
        }.start();
    }

    public void setData(final GetUpcomingMatchesPojo.AllMatch allMatch) {
        new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        this.tvTeam1Name.setText(allMatch.getTeamA());
        this.tvTeam2Name.setText(allMatch.getTeamB());
        this.tvMatchTitle.setText(allMatch.getTitle());
        this.tvdataTime.setText(allMatch.getMatchtime());
        RequestManager with = Glide.with(getActivity());
        ((RequestBuilder) with.load(((BaseActivity) getActivity()).teamURL() + allMatch.getTeamAImage()).circleCrop()).into(this.ivTeam1Image);
        RequestManager with2 = Glide.with(getActivity());
        ((RequestBuilder) with2.load(((BaseActivity) getActivity()).teamURL() + allMatch.getTeamBImage()).circleCrop()).into(this.ivTeam2Image);
        Calendar instance = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        try {
            instance.setTime(simpleDateFormat.parse(allMatch.getMatchtime() + ""));
            TextView textView = this.timer;
            textView.setText(instance.getTimeInMillis() + "");
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), allMatch.getMatchtime(), Toast.LENGTH_SHORT).show();
            this.timer.setText(e.getMessage());
        }
        new CountDownTimer(Calendar.getInstance().getTimeInMillis() - instance.getTimeInMillis(), 1000) {


            public void onTick(long j) {
                DecimalFormat decimalFormat = new DecimalFormat("00");
                TextView textView = UpcomingMatchBottomSheet.this.timer;
                textView.setText(decimalFormat.format((j / 3600000) % 24) + ": " + decimalFormat.format((j / 60000) % 60) + ":" + decimalFormat.format((j / 1000) % 60));
                UpcomingMatchBottomSheet.this.timer.setText(allMatch.getMatchtime());
                UpcomingMatchBottomSheet.this.timer.setTextSize(10.0f);
            }

            public void onFinish() {
                UpcomingMatchBottomSheet.this.timer.setText("00:00:00");
                UpcomingMatchBottomSheet.this.timer.setText(allMatch.getMatchtime());
                UpcomingMatchBottomSheet.this.timer.setTextSize(30.0f);
            }
        }.start();
    }

    private void mFetchListFromAPI() {
        RequestHandler.getInstance(getActivity()).addToRequestQueue(new StringRequest(0, url, new Response.Listener<String>() {


            public void onResponse(String str) {
                Log.d("dataApi", str);
                try {
                    JSONArray jSONArray = new JSONObject(str).getJSONArray("data");
                    Log.d("dataApi", jSONArray.toString());
                    UpcomingMatchBottomSheet.this.getData(jSONArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("MainError", e.getMessage());
                    Toast.makeText(UpcomingMatchBottomSheet.this.getActivity(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
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
