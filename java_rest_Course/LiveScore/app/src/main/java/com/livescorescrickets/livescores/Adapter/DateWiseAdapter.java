package com.livescorescrickets.livescores.Adapter;
import com.livescorescrickets.livescores.R;
import com.livescorescrickets.livescores.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.livescorescrickets.livescores.Activity.BaseActivity;
import com.livescorescrickets.livescores.Pojo.MultiMatch.MultimatchPojo;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;


import java.util.ArrayList;

public class DateWiseAdapter extends RecyclerView.Adapter<DateWiseAdapter.ViewHolder> {
    Context context;
    private final ArrayList<MultimatchPojo> data;

    public DateWiseAdapter(Context context2, ArrayList<MultimatchPojo> arrayList) {
        this.context = context2;
        this.data = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.datewise_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.setData(this.data.get(i));
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivTeam1Image;
        ImageView ivTeam2Image;
        TextView tvDateTime;
        TextView tvMatchTitle;
        TextView tvTeam1Name;
        TextView tvTeam2Name;

        public ViewHolder(View view) {
            super(view);
            this.tvDateTime = (TextView) view.findViewById(R.id.tvDateTime);
            this.tvTeam1Name = (TextView) view.findViewById(R.id.tvTeam1Name);
            this.tvTeam2Name = (TextView) view.findViewById(R.id.tvTeam2Name);
            this.tvMatchTitle = (TextView) view.findViewById(R.id.tvMatchTitle);
            this.ivTeam1Image = (ImageView) view.findViewById(R.id.ivTeam1Image);
            this.ivTeam2Image = (ImageView) view.findViewById(R.id.ivTeam2Image);
        }

        public void setData(MultimatchPojo multimatchPojo) {
            this.tvDateTime.setText(multimatchPojo.getMatchdate());
            this.tvTeam1Name.setText(multimatchPojo.getTeama());
            this.tvTeam2Name.setText(multimatchPojo.getTeamb());
            this.tvMatchTitle.setText(multimatchPojo.getTitle());
            RequestManager with = Glide.with(DateWiseAdapter.this.context);
            ((RequestBuilder) with.load(((BaseActivity) DateWiseAdapter.this.context).teamURL() + multimatchPojo.getTeamaimage()).circleCrop()).into(this.ivTeam1Image);
            RequestManager with2 = Glide.with(DateWiseAdapter.this.context);
            ((RequestBuilder) with2.load(((BaseActivity) DateWiseAdapter.this.context).teamURL() + multimatchPojo.getTeambimage()).circleCrop()).into(this.ivTeam2Image);
        }
    }
}
