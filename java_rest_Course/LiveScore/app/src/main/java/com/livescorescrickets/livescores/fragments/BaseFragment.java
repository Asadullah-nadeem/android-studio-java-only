package com.livescorescrickets.livescores.fragments;
import com.livescorescrickets.livescores.R;

import static com.livescorescrickets.livescores.adsimp.OneData;
import static com.livescorescrickets.livescores.adsimp.ThreeData;
import static com.livescorescrickets.livescores.adsimp.TwoData;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.livescorescrickets.livescores.network.ApiCall;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseFragment extends Fragment {
    public Retrofit f1484retrofit;
    private Context mContext;
    public ProgressDialog mProgressDialog;

    public static String imageURL() {
        return TwoData;
    }

    public String baseURL() {
        return OneData;
    }

    public String baseURLReg() {
        return OneData;
    }

    public String teamURL() {
        return ThreeData;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = getContext();
        Log.e("baseURL ", baseURL());
        Log.e("baseURLReg ", baseURLReg());
        Log.e("imageURL ", imageURL());
        Log.e("teamURL ", teamURL());
    }

    public void showProgress(SwipeRefreshLayout swipeRefreshLayout) {
        if (swipeRefreshLayout != null && !swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    public void mStartProgress(String str) {
        ProgressDialog progressDialog = new ProgressDialog(this.mContext);
        this.mProgressDialog = progressDialog;
        progressDialog.setIndeterminate(true);
        this.mProgressDialog.setMessage(str);
        this.mProgressDialog.setCanceledOnTouchOutside(false);
        this.mProgressDialog.show();
    }

    public void mProgressClose() {
        ProgressDialog progressDialog = this.mProgressDialog;
        if (progressDialog != null && progressDialog.isShowing()) {
            this.mProgressDialog.dismiss();
        }
    }

    public void hideProgress(SwipeRefreshLayout swipeRefreshLayout) {
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        for (NetworkInfo networkInfo : connectivityManager.getAllNetworkInfo()) {
            if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }
        return false;
    }

    public Retrofit mSetRetroFitObject(String str) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Retrofit build = new Retrofit.Builder().baseUrl(str).addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient.Builder().connectTimeout(180, TimeUnit.SECONDS).readTimeout(180, TimeUnit.SECONDS).addInterceptor(httpLoggingInterceptor).retryOnConnectionFailure(true).build()).build();
        this.f1484retrofit = build;
        return build;
    }

    public Retrofit mSetRetroFitObjectMulti() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Retrofit build = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient.Builder().connectTimeout(180, TimeUnit.SECONDS).readTimeout(180, TimeUnit.SECONDS).addInterceptor(httpLoggingInterceptor).retryOnConnectionFailure(true).build()).build();
        this.f1484retrofit = build;
        return build;
    }

    public void showToast(Context context, String str) {
        if (getActivity() != null) {
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        }
    }

    public ApiCall mGetRetroObject(String str) {
        return (ApiCall) mSetRetroFitObject(str).create(ApiCall.class);
    }

    public ApiCall mGetRetroObjectMulti() {
        return (ApiCall) mSetRetroFitObjectMulti().create(ApiCall.class);
    }

    public void showToast(String str) {
        Toast.makeText(this.mContext, str, Toast.LENGTH_LONG).show();
    }

    public void mAlertDialogue(String str, String str2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(str);
        builder.setCancelable(true);
        builder.setPositiveButton("GO", new BaseFragment$$ExternalSyntheticLambda0(this, str2));
        builder.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {


            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.create().show();
    }


    public void m23xd1887b2d(String str, DialogInterface dialogInterface, int i) {
        if (getActivity() != null) {
            mSendIntent(str);
        }
    }

    public void mSendIntent(String str) {
        try {
            if (!TextUtils.isEmpty(str) && str.length() > 0) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(str));
                getActivity().startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
