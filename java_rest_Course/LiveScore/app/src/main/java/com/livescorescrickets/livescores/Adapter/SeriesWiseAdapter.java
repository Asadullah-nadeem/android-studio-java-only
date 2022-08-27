package com.livescorescrickets.livescores.Adapter;
import com.livescorescrickets.livescores.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.livescorescrickets.livescores.Pojo.MultiMatch.MultimatchPojo;

import java.util.ArrayList;
import java.util.HashMap;

public class SeriesWiseAdapter extends RecyclerView.Adapter<SeriesWiseAdapter.ViewHolder> {
    private ArrayList<MultimatchPojo> allMatches;
    public Context context;
    private HashMap<String, ArrayList<MultimatchPojo>> titleList = new HashMap<>();

    public SeriesWiseAdapter(Context context2, ArrayList<MultimatchPojo> arrayList) {
        this.context = context2;
        this.allMatches = arrayList;
        functionCombine();
    }

    private void functionCombine() {
        for (int i = 0; i < this.allMatches.size(); i++) {
            Log.d("tag", "onResponse: 1321aa workinga" + i);
            String trim = this.allMatches.get(i).getTitle().trim();
            if (this.titleList.get(trim) == null) {
                Log.d("tag", "onResponse: 1321aa workingb" + i);
                this.titleList.put(trim, new ArrayList<>());
            }
            Log.d("tag", "onResponse: 1321aa workingc" + i);
            ArrayList<MultimatchPojo> arrayList = new ArrayList<>();
            arrayList.addAll(this.titleList.get(trim));
            arrayList.add(this.allMatches.get(i));
            this.titleList.put(trim, arrayList);
            Log.d("tag", "onResponse: 1321aa workingd" + i);
        }
        Log.d("tag", "onResponse: 1321aa working x " + this.titleList.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.serieswise_item, viewGroup, false));
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
        RecyclerView recyclerView_serieswise_item;
        TextView title;
        TextView tvTeam1Name;

        public ViewHolder(View view) {
            super(view);
            this.tvTeam1Name = (TextView) view.findViewById(R.id.tvTeam1Name);
            this.title = (TextView) view.findViewById(R.id.title);
            this.collapse = (ImageView) view.findViewById(R.id.collapse);
            this.recyclerView_serieswise_item = (RecyclerView) view.findViewById(R.id.recyclerView_serieswise_item);
        }

        public void setdata(String str, HashMap<String, ArrayList<MultimatchPojo>> hashMap) {
            this.tvTeam1Name.setText(str);
            this.title.setText(str);
            this.recyclerView_serieswise_item.setAdapter(new DateWiseAdapter(SeriesWiseAdapter.this.context, hashMap.get(str)));
            this.recyclerView_serieswise_item.setLayoutManager(new LinearLayoutManager(SeriesWiseAdapter.this.context));
            this.collapse.setOnClickListener(new View.OnClickListener() {


                public void onClick(View view) {
                    if (ViewHolder.this.flag == 0) {
                        ViewHolder.this.collapse.setRotation(0.0f);
                        ViewHolder.this.flag = 8;
                    } else {
                        ViewHolder.this.flag = 0;
                        ViewHolder.this.collapse.setRotation(180.0f);
                    }
                    ViewHolder.this.recyclerView_serieswise_item.setVisibility(ViewHolder.this.flag);
                }
            });
        }
    }
}
