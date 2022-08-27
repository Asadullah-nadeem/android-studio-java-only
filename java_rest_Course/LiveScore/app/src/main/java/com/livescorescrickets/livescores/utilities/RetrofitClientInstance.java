package com.livescorescrickets.livescores.utilities;

import static com.livescorescrickets.livescores.adsimp.EightData;

import android.content.Context;
import android.widget.Toast;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClientInstance {
    public static String BASE_URL = EightData;
    private static Retrofit f1485retrofit;

    public static Retrofit getRetrofitInstance(Context context) {
        if (f1485retrofit == null) {
            try {
                f1485retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(ScalarsConverterFactory.create()).addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create())).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
            } catch (Exception unused) {
                Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show();
            }
        }
        return f1485retrofit;
    }
}
