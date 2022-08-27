package com.livescorescrickets.livescores.Adapter;
import com.livescorescrickets.livescores.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.livescorescrickets.livescores.Activity.BaseActivity;
import com.livescorescrickets.livescores.Pojo.MultiMatch.MultimatchPojo;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import java.util.ArrayList;
import java.util.HashMap;

public class TeamWiseAdapter extends RecyclerView.Adapter<TeamWiseAdapter.ViewHolder> {
    private ArrayList<MultimatchPojo> allMatches;
    public Context context;
    private HashMap<String, ArrayList<MultimatchPojo>> titleList = new HashMap<>();

    public TeamWiseAdapter(Context context2, ArrayList<MultimatchPojo> arrayList) {
        this.context = context2;
        this.allMatches = arrayList;
        functionCombine();
    }

    private void functionCombine() {
        for (int i = 0; i < this.allMatches.size(); i++) {
            String trim = this.allMatches.get(i).getTeama().trim();
            if (this.titleList.get(trim) == null) {
                this.titleList.put(trim, new ArrayList<>());
            }
            ArrayList<MultimatchPojo> arrayList = new ArrayList<>();
            arrayList.addAll(this.titleList.get(trim));
            arrayList.add(this.allMatches.get(i));
            this.titleList.put(trim, arrayList);
        }
        for (int i2 = 0; i2 < this.allMatches.size(); i2++) {
            String trim2 = this.allMatches.get(i2).getTeamb().trim();
            if (this.titleList.get(trim2) == null) {
                this.titleList.put(trim2, new ArrayList<>());
            }
            ArrayList<MultimatchPojo> arrayList2 = new ArrayList<>();
            arrayList2.addAll(this.titleList.get(trim2));
            arrayList2.add(this.allMatches.get(i2));
            this.titleList.put(trim2, arrayList2);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.teamwise_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.setdata((String) this.titleList.keySet().toArray()[i], this.titleList);
    }

    @Override
    public int getItemCount() {
        return this.titleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView collapse;
        int flag = 8;
        ImageView ivTeamFlag1;
        RecyclerView recyclerView_teamwise_item;
        TextView title;

        public ViewHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.title);
            this.recyclerView_teamwise_item = (RecyclerView) view.findViewById(R.id.recyclerView_teamwise_item);
            this.collapse = (ImageView) view.findViewById(R.id.collapse);
            this.ivTeamFlag1 = (ImageView) view.findViewById(R.id.ivTeamFlag1);
        }

        public void setdata(String str, HashMap<String, ArrayList<MultimatchPojo>> hashMap) {
            this.title.setText(str);
            this.recyclerView_teamwise_item.setAdapter(new DateWiseAdapter(TeamWiseAdapter.this.context, hashMap.get(str)));
            this.recyclerView_teamwise_item.setLayoutManager(new LinearLayoutManager(TeamWiseAdapter.this.context));
            if (hashMap.get(str).get(0).getTeama().equals(str)) {
                RequestManager with = Glide.with(TeamWiseAdapter.this.context);
                ((RequestBuilder) ((RequestBuilder) with.load(((BaseActivity) TeamWiseAdapter.this.context).teamURL() + hashMap.get(str).get(0).getTeamaimage()).placeholder((int) R.drawable.ic_player_placeholder_dark)).circleCrop()).into(this.ivTeamFlag1);
            } else {
                RequestManager with2 = Glide.with(TeamWiseAdapter.this.context);
                ((RequestBuilder) ((RequestBuilder) with2.load(((BaseActivity) TeamWiseAdapter.this.context).teamURL() + hashMap.get(str).get(0).getTeambimage()).placeholder((int) R.drawable.ic_player_placeholder_dark)).circleCrop()).into(this.ivTeamFlag1);
            }
            this.collapse.setOnClickListener(new View.OnClickListener() {


                public void onClick(View view) {
                    if (ViewHolder.this.flag == 0) {
                        ViewHolder.this.collapse.setRotation(0.0f);
                        ViewHolder.this.flag = 8;
                    } else {
                        ViewHolder.this.flag = 0;
                        ViewHolder.this.collapse.setRotation(180.0f);
                    }
                    ViewHolder.this.recyclerView_teamwise_item.setVisibility(ViewHolder.this.flag);
                }
            });
        }
    }
}
