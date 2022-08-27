package com.livescorescrickets.livescores.Adapter;
import com.livescorescrickets.livescores.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;
import com.livescorescrickets.livescores.Activity.BaseActivity;
import com.livescorescrickets.livescores.OnItemClickListener;
import com.livescorescrickets.livescores.Pojo.MultiMatch.GetUpcomingMatchesPojo;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import soup.neumorphism.NeumorphCardView;

public class UpcomingMatchesHomeAdapter extends RecyclerView.Adapter<UpcomingMatchesHomeAdapter.MyViewHolder> {
    private static String TAGC = RecentMatchesHomeAdapter.class.getName();
    public Context ctx;
    ArrayList<GetUpcomingMatchesPojo.AllMatch> list;
    OnItemClickListener onItemClickListener;

    public UpcomingMatchesHomeAdapter(ArrayList<GetUpcomingMatchesPojo.AllMatch> arrayList, Context context, OnItemClickListener onItemClickListener2) {
        this.list = arrayList;
        this.ctx = context;
        this.onItemClickListener = onItemClickListener2;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_top_matchs, viewGroup, false));
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.setData(this.list.get(i));
        myViewHolder.onClickListener(i, this.list.get(i));
    }

    @Override
    public int getItemCount() {
        return Math.min(this.list.size(), 10);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        NeumorphCardView cvMatch;
        View f959v;
        ImageView ivTeam1Image;
        ImageView ivTeam2Image;
        LottieAnimationView lottie_animation_view;
        TextView statusIV;
        TextView tvMatchTitle;
        TextView tvTeam1Name;
        TextView tvTeam1Over;
        TextView tvTeam1Score;
        TextView tvTeam2Name;
        TextView tvTeam2Over;
        TextView tvTeam2Score;

        public MyViewHolder(View view) {
            super(view);
            this.tvTeam1Name = (TextView) view.findViewById(R.id.tvTeam1Name);
            this.tvTeam2Name = (TextView) view.findViewById(R.id.tvTeam2Name);
            this.tvTeam1Score = (TextView) view.findViewById(R.id.tvTeam1Score);
            this.tvTeam2Score = (TextView) view.findViewById(R.id.tvTeam2Score);
            this.tvTeam1Over = (TextView) view.findViewById(R.id.tvTeam1Over);
            this.tvTeam2Over = (TextView) view.findViewById(R.id.tvTeam2Over);
            this.statusIV = (TextView) view.findViewById(R.id.statusIV);
            this.tvMatchTitle = (TextView) view.findViewById(R.id.tvMatchTitle);
            this.ivTeam1Image = (ImageView) view.findViewById(R.id.ivTeam1Image);
            this.ivTeam2Image = (ImageView) view.findViewById(R.id.ivTeam2Image);
            this.cvMatch = (NeumorphCardView) view.findViewById(R.id.cvMatch);
            this.lottie_animation_view = (LottieAnimationView) view.findViewById(R.id.lottie_animation_view);
            this.f959v = view;
        }

        public void onClickListener(final int i, final GetUpcomingMatchesPojo.AllMatch allMatch) {
            this.cvMatch.setOnClickListener(new View.OnClickListener() {


                public void onClick(View view) {
                    UpcomingMatchesHomeAdapter.this.onItemClickListener.setOnItemClickListener(i, allMatch);
                }
            });
        }

        public void setData(GetUpcomingMatchesPojo.AllMatch allMatch) {
            new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
            this.tvTeam1Name.setText(allMatch.getTeamA());
            this.tvTeam2Name.setText(allMatch.getTeamB());
            this.tvMatchTitle.setText(allMatch.getTitle());
            this.statusIV.setBackgroundResource(R.drawable.ic_upcoming_icon);
            this.statusIV.setVisibility(0);
            this.statusIV.setText("Upcoming");
            RequestManager with = Glide.with(UpcomingMatchesHomeAdapter.this.ctx);
            ((RequestBuilder) ((RequestBuilder) with.load(((BaseActivity) UpcomingMatchesHomeAdapter.this.ctx).teamURL() + allMatch.getTeamAImage()).placeholder((int) R.drawable.ic_player_placeholder_dark)).circleCrop()).into(this.ivTeam1Image);
            RequestManager with2 = Glide.with(UpcomingMatchesHomeAdapter.this.ctx);
            ((RequestBuilder) ((RequestBuilder) with2.load(((BaseActivity) UpcomingMatchesHomeAdapter.this.ctx).teamURL() + allMatch.getTeamBImage()).placeholder((int) R.drawable.ic_player_placeholder_dark)).circleCrop()).into(this.ivTeam2Image);
        }
    }
}
