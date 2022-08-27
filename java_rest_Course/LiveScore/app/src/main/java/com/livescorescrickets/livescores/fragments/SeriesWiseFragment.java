package com.livescorescrickets.livescores.fragments;
import com.livescorescrickets.livescores.R;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.livescorescrickets.livescores.Adapter.SeriesWiseAdapter;
import com.livescorescrickets.livescores.Pojo.JsonDataFiles.MainJsonData;
import com.livescorescrickets.livescores.Pojo.MultiMatch.MultimatchPojo;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesWiseFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ArrayList<MultimatchPojo> allMatches = new ArrayList<>();
    private String mParam1;
    private String mParam2;
    RecyclerView rvserisewise;
    public final ShimmerFrameLayout shimmerLayout1;

    public SeriesWiseFragment(ShimmerFrameLayout shimmerFrameLayout) {
        this.shimmerLayout1 = shimmerFrameLayout;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.mParam1 = getArguments().getString(ARG_PARAM1);
            this.mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_series_wise, viewGroup, false);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.rvserisewise = (RecyclerView) view.findViewById(R.id.rvserisewise);
        getAllMatches();
    }

    public void getAllMatches() {
        try {
            this.shimmerLayout1.setVisibility(View.VISIBLE);
            if (isNetworkAvailable()) {
                mGetRetroObject(baseURL()).getAllLiveMatchs().enqueue(new Callback<ArrayList<MultimatchPojo>>() {


                    @Override
                    public void onFailure(Call<ArrayList<MultimatchPojo>> call, Throwable th) {
                    }

                    @Override
                    public void onResponse(Call<ArrayList<MultimatchPojo>> call, Response<ArrayList<MultimatchPojo>> response) {
                        MainJsonData mainJsonData;
                        try {
                            if (response.code() == 200) {
                                Log.d("tag", "onResponse: 1321a workingb" + SeriesWiseFragment.this.allMatches.size());
                                SeriesWiseFragment.this.allMatches.clear();
                                for (int i = 0; i < response.body().size(); i++) {
                                    MultimatchPojo multimatchPojo = response.body().get(i);
                                    if (multimatchPojo.getJsondata().length() > 0 && (mainJsonData = (MainJsonData) new Gson().fromJson(multimatchPojo.getJsondata(), MainJsonData.class)) != null) {
                                        mainJsonData.getJsondata();
                                        new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                                    }
                                    SeriesWiseFragment.this.allMatches.add(multimatchPojo);
                                    Log.d("tag", "onResponse: 1321a working");
                                }
                                Log.d("tag", "onResponse: 1321sa workingb" + SeriesWiseFragment.this.allMatches.size());
                                SeriesWiseFragment.this.rvserisewise.setAdapter(new SeriesWiseAdapter(SeriesWiseFragment.this.getContext(), SeriesWiseFragment.this.allMatches));
                                SeriesWiseFragment.this.shimmerLayout1.setVisibility(View.GONE);
                            }
                        } catch (Exception e) {
                            Log.e("onFailure ", "" + e.getMessage());
                        }
                    }
                });
            }
        } catch (Exception unused) {
        }
    }
}
