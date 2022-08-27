package com.livescorescrickets.livescores.Adapter;
import com.livescorescrickets.livescores.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.livescorescrickets.livescores.Pojo.PlayerDetails;

import java.util.ArrayList;

public class PlayerRankAdapter extends RecyclerView.Adapter<PlayerRankAdapter.MyViewHolder> {
    private static String TAGC = "com.livescorecricket.livescore.Adapter.PlayerRankAdapter";
    private Context ctx;
    ArrayList<PlayerDetails> list;

    public PlayerRankAdapter(ArrayList<PlayerDetails> arrayList, Context context) {
        this.list = arrayList;
        this.ctx = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_player_cap, viewGroup, false));
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.tvPlayerSno.setText(this.list.get(i).getPlayerSNo());
        myViewHolder.tvPlayerName.setText(this.list.get(i).getPlayerName());
        myViewHolder.tvPlayerTeam.setText(this.list.get(i).getPlayerTeam());
        myViewHolder.tvPlayerRun.setText(this.list.get(i).getPlayerRun());
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvPlayerName;
        TextView tvPlayerRun;
        TextView tvPlayerSno;
        TextView tvPlayerTeam;

        public MyViewHolder(View view) {
            super(view);
            this.tvPlayerSno = (TextView) view.findViewById(R.id.tvPlayerSno);
            this.tvPlayerName = (TextView) view.findViewById(R.id.tvPlayerName);
            this.tvPlayerTeam = (TextView) view.findViewById(R.id.tvPlayerTeam);
            this.tvPlayerRun = (TextView) view.findViewById(R.id.tvPlayerRun);
        }
    }
}
