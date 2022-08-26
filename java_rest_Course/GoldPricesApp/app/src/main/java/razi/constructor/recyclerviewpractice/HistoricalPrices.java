package razi.constructor.recyclerviewpractice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import razi.constructor.recyclerviewpractice.SharedData.Shared;

public class HistoricalPrices extends AppCompatActivity {

    ImageView btnBack;
    RecyclerView historicalRV;
    HistoricalCustomAdapter historicalCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historical_prices);
        getSupportActionBar().hide();

        btnBack = findViewById(R.id.btnBack);
        historicalRV = findViewById(R.id.historicalRV);
        historicalRV.setLayoutManager(new LinearLayoutManager(this));
        historicalCustomAdapter = new HistoricalCustomAdapter();
        historicalRV.setAdapter(historicalCustomAdapter);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    public class HistoricalCustomAdapter extends RecyclerView.Adapter<HistoricalCustomAdapter.MyAdapter>{

        private LayoutInflater inflater;

        @NonNull
        @Override
        public MyAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (inflater==null){
                inflater=LayoutInflater.from(parent.getContext());
            }
            View view = inflater.inflate(R.layout.custom_cell, parent, false);
            return new MyAdapter(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter holder, int position) {
            holder.dateTxt.setText(removeFirstChar(Shared.historicalPricesDates.get(position), 20));
            holder.priceTxt.setText(removeFirstCharTwo(Shared.historicalPrices.get(position)));
        }

        @Override
        public int getItemCount() {
            return Shared.historicalPrices.size();
        }

        public class MyAdapter extends RecyclerView.ViewHolder {

            TextView dateTxt, priceTxt;

            public MyAdapter(@NonNull View itemView) {
                super(itemView);
                dateTxt = itemView.findViewById(R.id.dateTxt);
                priceTxt = itemView.findViewById(R.id.priceTxt);
            }
        }
    }

    public String removeFirstChar(String s, int num){
        String a = s.substring(num);
        String str = a.substring(0, a.length() - 5);
        return str;
    }

    public String removeFirstCharTwo(String s){
        String a = s.substring(4);
        String str = a.substring(0, a.length() - 5);
        return str;
    }

}