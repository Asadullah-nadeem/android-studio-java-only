package com.livescorescrickets.livescores.Adapter;
import com.livescorescrickets.livescores.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.livescorescrickets.livescores.Pojo.PlayerDetails;

import com.livescorescrickets.livescores.fragments.BaseFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import java.util.ArrayList;

public class RVPlayerAdapter extends RecyclerView.Adapter<RVPlayerAdapter.MyViewHolder> {
    private static String TAGC = "com.livescorecricket.livescore.Adapter.RVPlayerAdapter";
    private Context ctx;
    ArrayList<PlayerDetails> list;

    public RVPlayerAdapter(ArrayList<PlayerDetails> arrayList, Context context) {
        this.list = arrayList;
        this.ctx = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_player, viewGroup, false));
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        RequestManager with = Glide.with(this.ctx);
        ((RequestBuilder) ((RequestBuilder) with.load(BaseFragment.imageURL() + this.list.get(i).getPlayerImage()).circleCrop()).placeholder(R.drawable.ic_player_placeholder_dark)).into(myViewHolder.ivPlayerImage);
        myViewHolder.tvPlayerName.setText(this.list.get(i).getPlayerName());
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPlayerImage;
        TextView tvPlayerName;

        public MyViewHolder(View view) {
            super(view);
            this.ivPlayerImage = (ImageView) view.findViewById(R.id.ivPlayerImage);
            this.tvPlayerName = (TextView) view.findViewById(R.id.tvPlayerName);
        }
    }
}
