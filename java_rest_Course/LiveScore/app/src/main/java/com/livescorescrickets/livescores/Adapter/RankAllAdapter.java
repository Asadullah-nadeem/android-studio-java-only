package com.livescorescrickets.livescores.Adapter;
import com.livescorescrickets.livescores.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.livescorescrickets.livescores.Pojo.RankType;

import java.util.ArrayList;

public class RankAllAdapter extends RecyclerView.Adapter<RankAllAdapter.MyViewHolder> {
    private static String TAGC = "com.livescorecricket.livescore.Adapter.RankAllAdapter";
    private Context ctx;
    ArrayList<RankType> list;

    public RankAllAdapter(ArrayList<RankType> arrayList, Context context) {
        this.list = arrayList;
        this.ctx = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.selection_item, viewGroup, false));
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.setData(this.list.get(i));
    }

    @Override
    public int getItemCount() {
        return Math.min(this.list.size(), 10);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTeamMatches;
        TextView tvTeamName;
        TextView tvTeamPoints;
        TextView tvTeamRank;
        TextView tvTeamRating;

        public MyViewHolder(View view) {
            super(view);
            this.tvTeamName = (TextView) view.findViewById(R.id.tvTeamName);
            this.tvTeamRank = (TextView) view.findViewById(R.id.tvTeamRank);
            this.tvTeamMatches = (TextView) view.findViewById(R.id.tvTeamMatches);
            this.tvTeamPoints = (TextView) view.findViewById(R.id.tvTeamPoints);
            this.tvTeamRating = (TextView) view.findViewById(R.id.tvTeamRating);
        }

        public void setData(RankType rankType) {
            this.tvTeamName.setText(rankType.getTeam());
            this.tvTeamRank.setText(rankType.getRank());
            this.tvTeamMatches.setText(rankType.getMatches());
            this.tvTeamPoints.setText(rankType.getPoints());
            this.tvTeamRating.setText(rankType.getRating());
        }
    }
}
