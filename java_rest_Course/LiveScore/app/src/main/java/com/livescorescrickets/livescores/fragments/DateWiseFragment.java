package com.livescorescrickets.livescores.fragments;
import com.livescorescrickets.livescores.R;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;
import com.livescorescrickets.livescores.Adapter.DateWiseAdapter;
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

public class DateWiseFragment extends BaseFragment {
    ArrayList<MultimatchPojo> allMatches = new ArrayList<>();
    LottieAnimationView progress;
    RecyclerView rvdatewise;
    public final ShimmerFrameLayout shimmerLayout1;

    public DateWiseFragment(ShimmerFrameLayout shimmerFrameLayout) {
        this.shimmerLayout1 = shimmerFrameLayout;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_date_wise, viewGroup, false);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.rvdatewise = (RecyclerView) view.findViewById(R.id.rvdatewise);
        this.progress = (LottieAnimationView) view.findViewById(R.id.progress);
        this.rvdatewise.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.d("tag", "onResponse: 1321a work3ingb" + this.allMatches.size());
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
                                Log.d("tag", "onResponse: 1321a workingb" + DateWiseFragment.this.allMatches.size());
                                DateWiseFragment.this.allMatches.clear();
                                for (int i = 0; i < response.body().size(); i++) {
                                    MultimatchPojo multimatchPojo = response.body().get(i);
                                    if (multimatchPojo.getJsondata().length() > 0 && (mainJsonData = (MainJsonData) new Gson().fromJson(multimatchPojo.getJsondata(), MainJsonData.class)) != null) {
                                        mainJsonData.getJsondata();
                                        new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                                    }
                                    DateWiseFragment.this.allMatches.add(multimatchPojo);
                                    Log.d("tag", "onResponse: 1321a working");
                                }
                                Log.d("tag", "onResponse: 1321a workingb" + DateWiseFragment.this.allMatches.size());
                                DateWiseFragment.this.progress.setVisibility(View.GONE);
                                DateWiseFragment.this.rvdatewise.setAdapter(new DateWiseAdapter(DateWiseFragment.this.getContext(), DateWiseFragment.this.allMatches));
                                DateWiseFragment.this.shimmerLayout1.setVisibility(View.GONE);
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
