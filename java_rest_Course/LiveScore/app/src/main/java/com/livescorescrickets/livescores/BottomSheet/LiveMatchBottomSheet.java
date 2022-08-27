package com.livescorescrickets.livescores.BottomSheet;
import com.livescorescrickets.livescores.R;

import static com.livescorescrickets.livescores.adsimp.FourData;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
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
import com.livescorescrickets.livescores.Pojo.MultiMatch.MultimatchPojo;

import com.livescorescrickets.livescores.utilities.RequestHandler;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import soup.neumorphism.NeumorphCardView;

public class LiveMatchBottomSheet extends BottomSheetDialogFragment {
    static final String url = FourData;
    NeumorphCardView Speaker;
    ImageView Speakerimg;
    TextView ballUpdate;
    BottomSheetDialog bottomSheet;
    MultimatchPojo data;
    boolean flag = true;
    String imageUrl;
    public boolean isSpeak = true;
    ImageView ivTeam1Image;
    ImageView ivTeam2Image;
    LottieAnimationView lottie_4;
    LottieAnimationView lottie_6;
    LottieAnimationView lottie_animation_view_S;
    LottieAnimationView lottie_w;
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
    public TextToSpeech t1;
    TextView tvMatchResult;
    TextView tvMatchTitle;
    TextView tvNeedsRuns;
    TextView tvRR;
    TextView tvTeam1Name;
    TextView tvTeam1Over;
    TextView tvTeam1Score;
    TextView tvTeam2Name;
    TextView tvTeam2Over;
    TextView tvTeam2Score;
    View view;
    String whatsAppurl;

    private void methods() {
    }

    public LiveMatchBottomSheet(MultimatchPojo multimatchPojo) {
        this.data = multimatchPojo;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
        View inflate = layoutInflater.inflate(R.layout.fragment_livematch_bottom_sheet, viewGroup, false);
        this.view = inflate;
        this.tvTeam1Name = (TextView) inflate.findViewById(R.id.tvTeam1Name);
        this.tvTeam2Name = (TextView) this.view.findViewById(R.id.tvTeam2Name);
        this.Speakerimg = (ImageView) this.view.findViewById(R.id.Speakerimg);
        this.ballUpdate = (TextView) this.view.findViewById(R.id.ballUpdate);
        this.tvTeam1Score = (TextView) this.view.findViewById(R.id.tvTeam1Score);
        this.tvTeam2Score = (TextView) this.view.findViewById(R.id.tvTeam2Score);
        this.tvTeam1Over = (TextView) this.view.findViewById(R.id.tvTeam1Over);
        this.tvTeam2Over = (TextView) this.view.findViewById(R.id.tvTeam2Over);
        this.tvMatchTitle = (TextView) this.view.findViewById(R.id.tvMatchTitle);
        this.over_1 = (TextView) this.view.findViewById(R.id.over_1);
        this.tvNeedsRuns = (TextView) this.view.findViewById(R.id.tvNeedsRuns);
        this.over_2 = (TextView) this.view.findViewById(R.id.over_2);
        this.over_3 = (TextView) this.view.findViewById(R.id.over_3);
        this.over_4 = (TextView) this.view.findViewById(R.id.over_4);
        this.over_5 = (TextView) this.view.findViewById(R.id.over_5);
        this.over_6 = (TextView) this.view.findViewById(R.id.over_6);
        this.over_1c = (NeumorphCardView) this.view.findViewById(R.id.over_1c);
        this.over_2c = (NeumorphCardView) this.view.findViewById(R.id.over_2c);
        this.over_3c = (NeumorphCardView) this.view.findViewById(R.id.over_3c);
        this.over_4c = (NeumorphCardView) this.view.findViewById(R.id.over_4c);
        this.over_5c = (NeumorphCardView) this.view.findViewById(R.id.over_5c);
        this.Speaker = (NeumorphCardView) this.view.findViewById(R.id.Speaker);
        this.over_6c = (NeumorphCardView) this.view.findViewById(R.id.over_6c);
        this.overstv = (TextView) this.view.findViewById(R.id.overstv);
        this.lottie_animation_view_S = (LottieAnimationView) this.view.findViewById(R.id.lottie_animation_view_S);
        this.lottie_4 = (LottieAnimationView) this.view.findViewById(R.id.lottie_4);
        this.lottie_6 = (LottieAnimationView) this.view.findViewById(R.id.lottie_6);
        this.lottie_w = (LottieAnimationView) this.view.findViewById(R.id.lottie_w);
        this.ivTeam1Image = (ImageView) this.view.findViewById(R.id.ivTeam1Image);
        this.ivTeam2Image = (ImageView) this.view.findViewById(R.id.ivTeam2Image);
        this.tvMatchResult = (TextView) this.view.findViewById(R.id.tvMatchResult);
        this.tvRR = (TextView) this.view.findViewById(R.id.tvRR);
        setData(this.data);
        methods();
//        mFetchListFromAPI();
        return this.view;
    }

    private void textToSpeech(String str) {
        String str2;
        try {
            if (this.isSpeak) {
                if (str.equalsIgnoreCase("4-4-4")) {
                    str2 = "4 run";
                } else {
                    str2 = str.equalsIgnoreCase("6-6-6") ? "6 run" : str;
                }
                TextToSpeech textToSpeech = this.t1;
                if (!str.equalsIgnoreCase("0") && !str.equalsIgnoreCase("1") && !str.equalsIgnoreCase("2") && !str.equalsIgnoreCase("3") && !str.equalsIgnoreCase("4") && !str.equalsIgnoreCase("5") && !str.equalsIgnoreCase("6")) {
                    textToSpeech.speak(str2 + "", 0, null);
                }
                textToSpeech.speak(str2 + " run", 0, null);
            }
        } catch (Exception unused) {
            Log.d("", "");
        }
    }


    public void setData(final MultimatchPojo multimatchPojo) {
        MainJsonData mainJsonData;
        char c;
        char c2;
        char c3;
        char c4;
        char c5;
        new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        this.tvTeam1Name.setText(multimatchPojo.getTeama());
        this.tvTeam2Name.setText(multimatchPojo.getTeamb());
        this.tvMatchTitle.setText(multimatchPojo.getTitle());
        if (multimatchPojo.getJsondata().length() > 0 && (mainJsonData = (MainJsonData) new Gson().fromJson(multimatchPojo.getJsondata(), MainJsonData.class)) != null) {
            final Jsondata jsondata = mainJsonData.getJsondata();
            ((MainJsonRuns) new Gson().fromJson(multimatchPojo.getJsonruns(), MainJsonRuns.class)).getJsonruns();
            this.tvTeam1Over.setText(jsondata.getOversA());
            this.tvTeam2Over.setText(jsondata.getOversB());
            TextView textView = this.tvTeam1Score;
            textView.setText(jsondata.getWicketA() + "(" + jsondata.getOversA() + ")");
            TextView textView2 = this.tvTeam2Score;
            textView2.setText(jsondata.getWicketB() + "(" + jsondata.getOversB().split("\\|")[0] + ")");
            this.overstv.setText(jsondata.getScore());
            TextView textView3 = this.tvRR;
            textView3.setText("Partnership: " + jsondata.getPartnership());
            if (jsondata.getPartnership().equals("0(0)")) {
//                this.tvRR.setText(jsondata.getTitle().split("Toss -")[1].split("\n")[0]);
            }
            try {
                TextView textView4 = this.tvNeedsRuns;
                textView4.setText("RR " + jsondata.getTitle().split("C.RR:")[1].split("\\|")[0]);
            } catch (Exception unused) {
            }
            if (jsondata.getWicketA().equals("0/0")) {
                this.tvTeam1Name.setText(jsondata.getTeamA());
                this.tvTeam2Name.setText(jsondata.getTeamB());
                RequestManager with = Glide.with(this);
                ((RequestBuilder) ((RequestBuilder) with.load(((BaseActivity) getActivity()).teamURL() + jsondata.getTeamBBanner()).placeholder((int) R.drawable.ic_player_placeholder_dark)).circleCrop()).into(this.ivTeam1Image);
                RequestManager with2 = Glide.with(this);
                ((RequestBuilder) ((RequestBuilder) with2.load(((BaseActivity) getActivity()).teamURL() + jsondata.getTeamABanner()).placeholder((int) R.drawable.ic_player_placeholder_dark)).circleCrop()).into(this.ivTeam2Image);
            } else {
                TextView textView5 = this.tvTeam2Score;
                textView5.setText(jsondata.getWicketB() + "(0,0)");
                this.tvTeam1Name.setText(jsondata.getTeamA());
                this.tvTeam2Name.setText(jsondata.getTeamB());
                RequestManager with3 = Glide.with(this);
                ((RequestBuilder) ((RequestBuilder) with3.load(((BaseActivity) getActivity()).teamURL() + jsondata.getTeamABanner()).placeholder((int) R.drawable.ic_player_placeholder_dark)).circleCrop()).into(this.ivTeam1Image);
                RequestManager with4 = Glide.with(this);
                ((RequestBuilder) ((RequestBuilder) with4.load(((BaseActivity) getActivity()).teamURL() + jsondata.getTeamBBanner()).placeholder((int) R.drawable.ic_player_placeholder_dark)).circleCrop()).into(this.ivTeam2Image);
            }
            this.Speaker.setOnClickListener(new View.OnClickListener() {


                public void onClick(View view) {
                    LiveMatchBottomSheet.this.textToSpeech(jsondata.getScore());
                    if (LiveMatchBottomSheet.this.flag) {
                        LiveMatchBottomSheet.this.Speakerimg.setImageResource(R.drawable.ic_baseline_volume_up_24);
                    } else {
                        LiveMatchBottomSheet.this.Speakerimg.setImageResource(R.drawable.ic_baseline_volume_off_24);
                    }
                    LiveMatchBottomSheet liveMatchBottomSheet = LiveMatchBottomSheet.this;
                    liveMatchBottomSheet.flag = !liveMatchBottomSheet.flag;
                }
            });
            if (jsondata.getLast6Balls() != null || !TextUtils.isEmpty(jsondata.getLast6Balls())) {
                String[] split = jsondata.getLast6Balls().split("-");
                if (split.length > 0) {
                    this.over_1.setText(split[0].toLowerCase());
                    this.ballUpdate.setText(split[0].toLowerCase());
                    this.lottie_4.setVisibility(View.GONE);
                    this.lottie_6.setVisibility(View.GONE);
                    this.lottie_w.setVisibility(View.GONE);
                    String lowerCase = split[0].toLowerCase();
                    lowerCase.hashCode();
                    int hashCode = lowerCase.hashCode();
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
                                                this.lottie_4.setVisibility(View.GONE);
                                                this.lottie_6.setVisibility(View.GONE);
                                                this.lottie_w.setVisibility(View.GONE);
                                                break;
                                            case 1:
                                                this.over_1c.setBackgroundColor(getContext().getResources().getColor(R.color.color_grey));
                                                break;
                                            case 2:
                                                this.over_1c.setBackgroundColor(getContext().getResources().getColor(R.color.color_grey));
                                                break;
                                            case 3:
                                                this.over_1c.setBackgroundColor(getContext().getResources().getColor(R.color.color_green));
                                                this.ballUpdate.setVisibility(View.GONE);
                                                this.lottie_4.setVisibility(View.VISIBLE);;
                                                this.lottie_w.setVisibility(View.GONE);
                                                this.lottie_6.setVisibility(View.GONE);
                                                break;
                                            case 4:
                                                this.over_1c.setBackgroundColor(getContext().getResources().getColor(R.color.color_dark_green));
                                                this.ballUpdate.setVisibility(View.GONE);
                                                this.lottie_4.setVisibility(View.GONE);
                                                this.lottie_w.setVisibility(View.GONE);
                                                this.lottie_6.setVisibility(View.VISIBLE);;
                                                break;
                                            case 5:
                                                this.over_1c.setBackgroundColor(getContext().getResources().getColor(R.color.color_red));
                                                this.ballUpdate.setVisibility(View.GONE);
                                                this.lottie_4.setVisibility(View.GONE);
                                                this.lottie_6.setVisibility(View.GONE);
                                                this.lottie_w.setVisibility(View.VISIBLE);;
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
                                            this.over_2.setText(split[1].toLowerCase());
                                            this.lottie_4.setVisibility(View.GONE);
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
                                                                        this.ballUpdate.setVisibility(View.GONE);
                                                                        this.lottie_4.setVisibility(View.VISIBLE);;
                                                                        this.lottie_w.setVisibility(View.GONE);
                                                                        this.lottie_6.setVisibility(View.GONE);
                                                                        break;
                                                                    case 4:
                                                                        this.over_2c.setBackgroundColor(getContext().getResources().getColor(R.color.color_dark_green));
                                                                        this.ballUpdate.setVisibility(View.GONE);
                                                                        this.lottie_4.setVisibility(View.GONE);
                                                                        this.lottie_w.setVisibility(View.GONE);
                                                                        this.lottie_6.setVisibility(View.VISIBLE);;
                                                                        break;
                                                                    case 5:
                                                                        this.over_2c.setBackgroundColor(getContext().getResources().getColor(R.color.color_red));
                                                                        this.ballUpdate.setVisibility(View.GONE);
                                                                        this.lottie_4.setVisibility(View.GONE);
                                                                        this.lottie_6.setVisibility(View.GONE);
                                                                        this.lottie_w.setVisibility(View.VISIBLE);;
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
                                            this.over_3.setText(split[2].toLowerCase());
                                            this.lottie_4.setVisibility(View.GONE);
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
                                                                        this.ballUpdate.setVisibility(View.GONE);
                                                                        this.lottie_4.setVisibility(View.VISIBLE);;
                                                                        this.lottie_w.setVisibility(View.GONE);
                                                                        this.lottie_6.setVisibility(View.GONE);
                                                                        break;
                                                                    case 4:
                                                                        this.over_3c.setBackgroundColor(getContext().getResources().getColor(R.color.color_dark_green));
                                                                        this.ballUpdate.setVisibility(View.GONE);
                                                                        this.lottie_4.setVisibility(View.GONE);
                                                                        this.lottie_w.setVisibility(View.GONE);
                                                                        this.lottie_6.setVisibility(View.VISIBLE);;
                                                                        break;
                                                                    case 5:
                                                                        this.over_3c.setBackgroundColor(getContext().getResources().getColor(R.color.color_red));
                                                                        this.ballUpdate.setVisibility(View.GONE);
                                                                        this.lottie_4.setVisibility(View.GONE);
                                                                        this.lottie_6.setVisibility(View.GONE);
                                                                        this.lottie_w.setVisibility(View.VISIBLE);;
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
                                            this.over_4.setText(split[3].toLowerCase());
                                            this.lottie_4.setVisibility(View.GONE);
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
                                                                        this.ballUpdate.setVisibility(View.GONE);
                                                                        this.lottie_4.setVisibility(View.VISIBLE);;
                                                                        this.lottie_w.setVisibility(View.GONE);
                                                                        this.lottie_6.setVisibility(View.GONE);
                                                                        break;
                                                                    case 4:
                                                                        this.over_4c.setBackgroundColor(getContext().getResources().getColor(R.color.color_dark_green));
                                                                        this.ballUpdate.setVisibility(View.GONE);
                                                                        this.lottie_4.setVisibility(View.GONE);
                                                                        this.lottie_w.setVisibility(View.GONE);
                                                                        this.lottie_6.setVisibility(View.VISIBLE);;
                                                                        break;
                                                                    case 5:
                                                                        this.over_4c.setBackgroundColor(getContext().getResources().getColor(R.color.color_red));
                                                                        this.ballUpdate.setVisibility(View.GONE);
                                                                        this.lottie_4.setVisibility(View.GONE);
                                                                        this.lottie_6.setVisibility(View.GONE);
                                                                        this.lottie_w.setVisibility(View.VISIBLE);;
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
                                            this.over_5.setText(split[4].toLowerCase());
                                            this.lottie_4.setVisibility(View.GONE);
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
                                                                        this.ballUpdate.setVisibility(View.GONE);
                                                                        this.lottie_4.setVisibility(View.VISIBLE);;
                                                                        this.lottie_w.setVisibility(View.GONE);
                                                                        this.lottie_6.setVisibility(View.GONE);
                                                                        break;
                                                                    case 4:
                                                                        this.over_5c.setBackgroundColor(getContext().getResources().getColor(R.color.color_dark_green));
                                                                        this.ballUpdate.setVisibility(View.GONE);
                                                                        this.lottie_4.setVisibility(View.GONE);
                                                                        this.lottie_w.setVisibility(View.GONE);
                                                                        this.lottie_6.setVisibility(View.VISIBLE);;
                                                                        break;
                                                                    case 5:
                                                                        this.over_5c.setBackgroundColor(getContext().getResources().getColor(R.color.color_red));
                                                                        this.ballUpdate.setVisibility(View.GONE);
                                                                        this.lottie_4.setVisibility(View.GONE);
                                                                        this.lottie_6.setVisibility(View.GONE);
                                                                        this.lottie_w.setVisibility(View.VISIBLE);;
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
                                        char c6 = 5;
                                        if (split.length > 5) {
                                            this.ballUpdate.setText(split[5].toLowerCase());
                                            this.over_6.setText(split[5].toLowerCase());
                                            this.lottie_4.setVisibility(View.GONE);
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
                                                            this.ballUpdate.setVisibility(View.GONE);
                                                            this.lottie_4.setVisibility(View.VISIBLE);;
                                                            break;
                                                        case 4:
                                                            this.over_6c.setBackgroundColor(getContext().getResources().getColor(R.color.color_dark_green));
                                                            this.ballUpdate.setVisibility(View.GONE);
                                                            this.lottie_6.setVisibility(View.VISIBLE);;
                                                            break;
                                                        case 5:
                                                            this.over_6c.setBackgroundColor(getContext().getResources().getColor(R.color.color_red));
                                                            this.ballUpdate.setVisibility(View.GONE);
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
                                                } else if (lowerCase6.equals("6")) {
                                                    c6 = 4;
                                                    switch (c6) {
                                                    }
                                                }
                                            } else if (lowerCase6.equals("4")) {
                                                c6 = 3;
                                                switch (c6) {
                                                }
                                            }
                                            c6 = 65535;
                                            switch (c6) {
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
                                        char c62 = 5;
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
                                    char c622 = 5;
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
                                char c6222 = 5;
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
                            char c62222 = 5;
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
                        char c622222 = 5;
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
                    char c6222222 = 5;
                    if (split.length > 5) {
                    }
                }
            }
        }
        this.lottie_animation_view_S.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(LiveMatchBottomSheet.this.getActivity(), HomeActivity.class);
                intent.putExtra("data", multimatchPojo);
                LiveMatchBottomSheet.this.getActivity().startActivity(intent);
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
                    LiveMatchBottomSheet.this.getData(jSONArray);
                    Glide.with(LiveMatchBottomSheet.this.getActivity());
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("MainError", e.getMessage());
                    Toast.makeText(LiveMatchBottomSheet.this.getActivity(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("dataApi", volleyError.getMessage());
            }
        }));
    }

    private void getData(JSONArray jSONArray) {
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
