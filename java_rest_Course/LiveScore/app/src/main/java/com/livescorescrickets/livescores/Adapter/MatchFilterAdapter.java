package com.livescorescrickets.livescores.Adapter;
import com.livescorescrickets.livescores.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.livescorescrickets.livescores.Pojo.MatchFilterData;

import java.util.ArrayList;
import java.util.List;

public class MatchFilterAdapter extends RecyclerView.Adapter<MatchFilterAdapter.MyViewHolder> {
    public List<MatchFilterData> invoiceFilterKeyData;
    public boolean isRefresh = false;
    private int lastPosition = -1;
    public OnItemClickListener listener;
    private Context mContext;
    private int previousItem;
    public int selectedItem;

    public interface OnItemClickListener {
        void onClickKeyword(String str);
    }

    public MatchFilterAdapter(Context context, ArrayList<MatchFilterData> arrayList) {
        this.invoiceFilterKeyData = arrayList;
        this.mContext = context;
        this.selectedItem = 0;
    }

    public void setSelectedAll() {
        this.isRefresh = true;
        this.invoiceFilterKeyData.get(this.selectedItem).setTexthold(false);
        this.invoiceFilterKeyData.get(0).setTexthold(true);
        notifyItemChanged(this.selectedItem);
        notifyItemChanged(0);
        this.selectedItem = 0;
    }

    public OnItemClickListener getListener() {
        return this.listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.matchs_custom_tab, viewGroup, false));
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, final int i) {
        myViewHolder.tvBusinessName.setText(this.invoiceFilterKeyData.get(i).getTeam1Name());
        if (this.invoiceFilterKeyData.get(i).isTexthold()) {
            myViewHolder.tvBusinessName.setTextColor(this.mContext.getResources().getColor(R.color.color_white));
            myViewHolder.tvBusinessName.setTextColor(this.mContext.getResources().getColor(R.color.white));
        } else {
            myViewHolder.tvBusinessName.setTextColor(this.mContext.getResources().getColor(R.color.color_text_secondary));
            myViewHolder.tvBusinessName.setBackgroundResource(0);
        }
        if (!this.isRefresh && this.selectedItem == i) {
            this.invoiceFilterKeyData.get(i).setTexthold(true);
            this.invoiceFilterKeyData.get(this.selectedItem).setTexthold(false);
            myViewHolder.tvBusinessName.setTextColor(this.mContext.getResources().getColor(R.color.white));
            myViewHolder.tvBusinessName.setBackgroundResource(R.drawable.ic_title_line);
        }
        myViewHolder.tvBusinessName.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                MatchFilterAdapter.this.isRefresh = false;
                int i = MatchFilterAdapter.this.selectedItem;
                MatchFilterAdapter.this.selectedItem = i;
                MatchFilterAdapter.this.invoiceFilterKeyData.get(i).setTexthold(true);
                MatchFilterAdapter.this.invoiceFilterKeyData.get(i).setTexthold(false);
                MatchFilterAdapter.this.notifyItemChanged(i);
                MatchFilterAdapter.this.notifyItemChanged(i);
                MatchFilterAdapter.this.listener.onClickKeyword(MatchFilterAdapter.this.invoiceFilterKeyData.get(i).getTeam1Name());
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.invoiceFilterKeyData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvBusinessName;

        public MyViewHolder(View view) {
            super(view);
            this.tvBusinessName = (TextView) view.findViewById(R.id.tvtab1);
        }
    }
}
