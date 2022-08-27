package com.livescorescrickets.livescores.Adapter;
import com.livescorescrickets.livescores.R;

import static com.livescorescrickets.livescores.adsimp.TwoData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.livescorescrickets.livescores.Pojo.TeamRanking;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import java.util.ArrayList;

public class TeamRVAdapter extends RecyclerView.Adapter<TeamRVAdapter.MyViewHolder> {
    private static String TAGC = "com.livescorecricket.livescore.Adapter.TeamRVAdapter";
    private Context ctx;
    ArrayList<TeamRanking> list;

    public TeamRVAdapter(ArrayList<TeamRanking> arrayList, Context context) {
        this.list = arrayList;
        this.ctx = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.teamrank_item, viewGroup, false));
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        RequestManager with = Glide.with(this.ctx);
        ((RequestBuilder) ((RequestBuilder) with.load(TwoData + this.list.get(i).getImageTeam() + "").centerCrop()).placeholder(this.ctx.getResources().getDrawable(R.drawable.ic_india))).into(myViewHolder.teamFlag);
        myViewHolder.tvTeam1Name.setText(this.list.get(i).getNameTeam());
        myViewHolder.matches.setText(this.list.get(i).getTeamMatches());
        myViewHolder.point.setText(this.list.get(i).getTeamPoints());
        myViewHolder.ranking.setText(this.list.get(i).getTeamRanking());
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView matches;
        TextView point;
        TextView ranking;
        ImageView teamFlag;
        TextView tvTeam1Name;

        public MyViewHolder(View view) {
            super(view);
            this.tvTeam1Name = (TextView) view.findViewById(R.id.title);
            this.matches = (TextView) view.findViewById(R.id.matches);
            this.point = (TextView) view.findViewById(R.id.point);
            this.ranking = (TextView) view.findViewById(R.id.ranking);
            this.teamFlag = (ImageView) view.findViewById(R.id.teamFlag);
        }
    }
}
