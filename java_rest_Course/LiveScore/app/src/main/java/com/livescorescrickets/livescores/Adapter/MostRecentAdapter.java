package com.livescorescrickets.livescores.Adapter;
import com.livescorescrickets.livescores.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.livescorescrickets.livescores.Pojo.JsonDataFiles.NewsItems;

import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class MostRecentAdapter extends RecyclerView.Adapter<MostRecentAdapter.MyViewHolder> {
    private static String TAGC = "com.livescorecricket.livescore.Adapter.MostRecentAdapter";
    private Context ctx;
    ArrayList<NewsItems> list;

    public MostRecentAdapter(Context context, ArrayList<NewsItems> arrayList) {
        this.list = arrayList;
        this.ctx = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mostrecent_item, viewGroup, false));
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.tvTeam1Name.setText(this.list.get(i).getNews_title());
        myViewHolder.discription.setText(this.list.get(i).getNews_description());
        Glide.with(this.ctx.getApplicationContext()).load(this.list.get(i).getNews_image()).into(myViewHolder.newsImage);
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView discription;
        ImageView newsImage;
        TextView time;
        TextView tvTeam1Name;

        public MyViewHolder(View view) {
            super(view);
            this.tvTeam1Name = (TextView) view.findViewById(R.id.news_title);
            this.newsImage = (ImageView) view.findViewById(R.id.newsImage);
            this.discription = (TextView) view.findViewById(R.id.news_description);
            this.time = (TextView) view.findViewById(R.id.latestTime);
        }
    }
}
