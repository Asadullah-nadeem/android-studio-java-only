package com.livescorescrickets.livescores.Adapter;
import com.livescorescrickets.livescores.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.livescorescrickets.livescores.Pojo.PlayerScoreCardDetails;

import java.util.ArrayList;

public class PlayerScoreCardAdapter extends RecyclerView.Adapter<PlayerScoreCardAdapter.MyViewHolder> {
    private static String TAGC = "com.livescorecricket.livescore.Adapter.PlayerScoreCardAdapter";
    private Context ctx;
    ArrayList<PlayerScoreCardDetails> list;

    public PlayerScoreCardAdapter(ArrayList<PlayerScoreCardDetails> arrayList, Context context) {
        this.list = arrayList;
        this.ctx = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_batsman, viewGroup, false));
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.tvBatsmanName.setText(this.list.get(i).getPlayerName());
        myViewHolder.tvBatsman_R.setText(this.list.get(i).getPlayerRun());
        myViewHolder.tvBatsman_B.setText(this.list.get(i).getPlayerBalls());
        myViewHolder.tvBatsman_4s.setText(this.list.get(i).getPlayer4());
        myViewHolder.tvBatsman_6s.setText(this.list.get(i).getPlayer6());
        myViewHolder.tvBatsman_SR.setText(this.list.get(i).getPlayerSR());
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvBatsmanName;
        TextView tvBatsman_4s;
        TextView tvBatsman_6s;
        TextView tvBatsman_B;
        TextView tvBatsman_R;
        TextView tvBatsman_SR;

        public MyViewHolder(View view) {
            super(view);
            this.tvBatsmanName = (TextView) view.findViewById(R.id.tvBatsmanName);
            this.tvBatsman_R = (TextView) view.findViewById(R.id.tvBatsman_R);
            this.tvBatsman_B = (TextView) view.findViewById(R.id.tvBatsman_B);
            this.tvBatsman_4s = (TextView) view.findViewById(R.id.tvBatsman_4s);
            this.tvBatsman_6s = (TextView) view.findViewById(R.id.tvBatsman_6s);
            this.tvBatsman_SR = (TextView) view.findViewById(R.id.tvBatsman_SR);
        }
    }
}
