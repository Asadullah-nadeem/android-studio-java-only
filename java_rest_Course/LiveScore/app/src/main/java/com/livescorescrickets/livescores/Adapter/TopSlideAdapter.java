package com.livescorescrickets.livescores.Adapter;
import com.livescorescrickets.livescores.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;
import com.livescorescrickets.livescores.Activity.BaseActivity;
import com.livescorescrickets.livescores.OnItemClickListener;
import com.livescorescrickets.livescores.Pojo.JsonDataFiles.Jsondata;
import com.livescorescrickets.livescores.Pojo.JsonDataFiles.Jsonruns;
import com.livescorescrickets.livescores.Pojo.JsonDataFiles.MainJsonData;
import com.livescorescrickets.livescores.Pojo.JsonDataFiles.MainJsonRuns;
import com.livescorescrickets.livescores.Pojo.MultiMatch.MultimatchPojo;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import soup.neumorphism.NeumorphCardView;

public class TopSlideAdapter extends RecyclerView.Adapter<TopSlideAdapter.MyViewHolder> {
    private static String TAGC = RecentMatchesHomeAdapter.class.getName();
    public Context ctx;
    ArrayList<MultimatchPojo> list;
    OnItemClickListener onItemClickListener;

    public TopSlideAdapter(ArrayList<MultimatchPojo> arrayList, Context context, OnItemClickListener onItemClickListener2) {
        this.list = arrayList;
        this.ctx = context;
        this.onItemClickListener = onItemClickListener2;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_top_matchs, viewGroup, false);
        inflate.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        return new MyViewHolder(inflate);
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.setData(this.list.get(i));
        myViewHolder.onClickListener(i, this.list.get(i));
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        NeumorphCardView cvMatch;
        View f958v;
        ImageView ivPlayerImage;
        ImageView ivTeam1Image;
        ImageView ivTeam2Image;
        LottieAnimationView lottie_animation_view;
        TextView statusIV;
        TextView tvMatchResult;
        TextView tvMatchTitle;
        TextView tvNeedsRuns;
        TextView tvOddsGreen;
        TextView tvOddsRed;
        TextView tvRR;
        TextView tvTeam1Name;
        TextView tvTeam1Over;
        TextView tvTeam1Score;
        TextView tvTeam2Name;
        TextView tvTeam2Over;
        TextView tvTeam2Score;

        public MyViewHolder(View view) {
            super(view);
            this.ivPlayerImage = (ImageView) view.findViewById(R.id.ivPlayerImage);
            this.tvRR = (TextView) view.findViewById(R.id.tvRR);
            this.tvTeam1Name = (TextView) view.findViewById(R.id.tvTeam1Name);
            this.tvTeam2Name = (TextView) view.findViewById(R.id.tvTeam2Name);
            this.tvOddsRed = (TextView) view.findViewById(R.id.tvOddsRed);
            this.tvNeedsRuns = (TextView) view.findViewById(R.id.tvNeedsRuns);
            this.tvOddsGreen = (TextView) view.findViewById(R.id.tvOddsGreen);
            this.tvTeam1Score = (TextView) view.findViewById(R.id.tvTeam1Score);
            this.tvTeam2Score = (TextView) view.findViewById(R.id.tvTeam2Score);
            this.tvTeam1Over = (TextView) view.findViewById(R.id.tvTeam1Over);
            this.tvTeam2Over = (TextView) view.findViewById(R.id.tvTeam2Over);
            this.statusIV = (TextView) view.findViewById(R.id.statusIV);
            this.tvMatchTitle = (TextView) view.findViewById(R.id.tvMatchTitle);
            this.ivTeam1Image = (ImageView) view.findViewById(R.id.ivTeam1Image);
            this.ivTeam2Image = (ImageView) view.findViewById(R.id.ivTeam2Image);
            this.cvMatch = (NeumorphCardView) view.findViewById(R.id.cvMatch);
            this.tvMatchResult = (TextView) view.findViewById(R.id.tvMatchResult);
            this.lottie_animation_view = (LottieAnimationView) view.findViewById(R.id.lottie_animation_view);
            this.f958v = view;
        }

        public void onClickListener(final int i, final MultimatchPojo multimatchPojo) {
            this.cvMatch.setOnClickListener(new View.OnClickListener() {


                public void onClick(View view) {
                    TopSlideAdapter.this.onItemClickListener.setOnItemClickListener(i, multimatchPojo);
                }
            });
        }

        public void setData(MultimatchPojo multimatchPojo) {
            MainJsonData mainJsonData;
            new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
            this.tvMatchTitle.setText(multimatchPojo.getTitle());
            MainJsonData mainJsonData2 = multimatchPojo.getJsondata().length() > 0 ? (MainJsonData) new Gson().fromJson(multimatchPojo.getJsondata(), MainJsonData.class) : null;
            if (multimatchPojo.getResult().trim().length() > 0) {
                this.statusIV.setVisibility(View.VISIBLE);
                this.statusIV.setBackgroundResource(R.drawable.ic_finished_icon);
                this.statusIV.setText("Finished");
            } else {
                this.statusIV.setBackgroundResource(R.drawable.ic_upcoming_icon);
                this.statusIV.setVisibility(View.VISIBLE);
                this.statusIV.setText("Upcoming");
                if (mainJsonData2 != null && !mainJsonData2.getJsondata().getBowler().equalsIgnoreCase("0")) {
                    this.statusIV.setBackgroundResource(R.drawable.ic_live_icon_dark);
                    this.statusIV.setVisibility(View.VISIBLE);
                    this.statusIV.setText("LIVE");
                    this.statusIV.setTextColor(TopSlideAdapter.this.ctx.getResources().getColor(R.color.black));
                    this.lottie_animation_view.setVisibility(View.VISIBLE);
                }
            }
            if (multimatchPojo.getJsondata().length() > 0 && (mainJsonData = (MainJsonData) new Gson().fromJson(multimatchPojo.getJsondata(), MainJsonData.class)) != null) {
                Jsondata jsondata = mainJsonData.getJsondata();
                Jsonruns jsonruns = ((MainJsonRuns) new Gson().fromJson(multimatchPojo.getJsonruns(), MainJsonRuns.class)).getJsonruns();
                this.tvTeam1Over.setText(jsondata.getOversA());
                this.tvTeam2Over.setText(jsondata.getOversB());
                String str = jsondata.getWicketA() + "(" + jsondata.getOversA() + ")";
                String str2 = jsondata.getWicketB() + "(" + jsondata.getOversB().split("\\|")[0] + ")";
                this.tvTeam1Score.setText(str);
                this.tvTeam2Score.setText(str2);
                if (str.contains(",")) {
                    this.tvTeam1Score.setText(jsondata.getWicketA().split(" ")[0] + "(20.0)");
                }
                if (str2.contains(",")) {
                    this.tvTeam2Score.setText(jsondata.getWicketB().split(" ")[0] + "(20.0)");
                }
                try {
                    this.tvNeedsRuns.setText("RR " + jsondata.getTitle().split("C.RR:")[1].split("\\|")[0]);
                } catch (Exception unused) {
                }
                this.tvOddsGreen.setText(jsonruns.getRateA());
                this.tvOddsRed.setText(jsonruns.getRateB());
                this.tvRR.setText("Partnership: " + jsondata.getPartnership());
                if (jsondata.getPartnership().equals("0(0)")) {

//                   this.tvRR.setText(jsondata.getTitle().split("Toss -")[1].split("\n")[0]);
                }
                this.tvMatchResult.setText("Last wkt: " + jsondata.getLastwicket());
                this.tvTeam1Name.setText(jsondata.getTeamA());
                this.tvTeam2Name.setText(jsondata.getTeamB());
                ((RequestBuilder) ((RequestBuilder) Glide.with(TopSlideAdapter.this.ctx).load(((BaseActivity) TopSlideAdapter.this.ctx).teamURL() + jsondata.getTeamABanner()).placeholder((int) R.drawable.ic_player_placeholder_dark)).circleCrop()).into(this.ivTeam1Image);
                ((RequestBuilder) ((RequestBuilder) Glide.with(TopSlideAdapter.this.ctx).load(((BaseActivity) TopSlideAdapter.this.ctx).teamURL() + jsondata.getTeamBBanner()).placeholder((int) R.drawable.ic_player_placeholder_dark)).circleCrop()).into(this.ivTeam2Image);
                if (jsondata.getWicketB().equals("0/0")) {
                    this.tvTeam2Score.setText(jsondata.getWicketB() + "(0,0)");
                }
                new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                if (multimatchPojo.getJsondata().length() > 0) {
                    MainJsonData mainJsonData3 = (MainJsonData) new Gson().fromJson(multimatchPojo.getJsondata(), MainJsonData.class);
                }
            }
        }
    }
}
