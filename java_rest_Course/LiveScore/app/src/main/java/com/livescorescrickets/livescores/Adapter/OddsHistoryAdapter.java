package com.livescorescrickets.livescores.Adapter;
import com.livescorescrickets.livescores.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.livescorescrickets.livescores.Pojo.JsonDataFiles.MatchOddsModel;

import java.util.ArrayList;
import soup.neumorphism.NeumorphCardView;

public class OddsHistoryAdapter extends RecyclerView.Adapter<OddsHistoryAdapter.ViewHolder> {
    public final Context context;
    private final ArrayList<MatchOddsModel.Matchst> innings;

    public OddsHistoryAdapter(Context context2, ArrayList<MatchOddsModel.Matchst> arrayList) {
        this.context = context2;
        this.innings = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.teama_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.setData(this.innings.get(i));
    }

    @Override
    public int getItemCount() {
        return this.innings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView MatchAOdds;
        TextView MatchBOdds;
        TextView Over;
        TextView Run;
        TextView TeamRateBOdds;
        TextView TeamrateAOdds;
        NeumorphCardView runBG;

        public ViewHolder(View view) {
            super(view);
            initUI(view);
        }

        private void initUI(View view) {
            this.Run = (TextView) view.findViewById(R.id.Run);
            this.Over = (TextView) view.findViewById(R.id.Over);
            this.TeamrateAOdds = (TextView) view.findViewById(R.id.TeamrateAOdds);
            this.TeamRateBOdds = (TextView) view.findViewById(R.id.TeamRateBOdds);
            this.MatchAOdds = (TextView) view.findViewById(R.id.MatchAOdds);
            this.MatchBOdds = (TextView) view.findViewById(R.id.MatchBOdds);
            this.runBG = (NeumorphCardView) view.findViewById(R.id.runBG);
        }

        public void setData(MatchOddsModel.Matchst matchst) {
            this.Run.setText(matchst.getScore());
            char charAt = matchst.getScore().toLowerCase().trim().charAt(0);
            this.Over.setText(matchst.getOvers());
            this.TeamrateAOdds.setText(matchst.getMrateA());
            this.TeamRateBOdds.setText(matchst.getMrateB());
            this.MatchAOdds.setText(matchst.getSessionA());
            this.MatchBOdds.setText(matchst.getSessionB());
            if (charAt == '1') {
                this.runBG.setBackgroundColor(OddsHistoryAdapter.this.context.getResources().getColor(R.color.color_grey));
            } else if (charAt == '4') {
                this.runBG.setBackgroundColor(OddsHistoryAdapter.this.context.getResources().getColor(R.color.color_text_orange));
            } else if (charAt == '6') {
                this.runBG.setBackgroundColor(OddsHistoryAdapter.this.context.getResources().getColor(R.color.color_dark_green));
            } else if (charAt == 'w') {
                this.runBG.setBackgroundColor(OddsHistoryAdapter.this.context.getResources().getColor(R.color.color_dark_red));
            }
        }
    }
}
