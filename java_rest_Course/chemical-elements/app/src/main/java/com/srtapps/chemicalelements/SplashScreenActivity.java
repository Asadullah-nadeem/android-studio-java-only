package com.srtapps.chemicalelements;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.srtapps.chemicalelements.helper.MobileAdsHelper;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        MobileAdsHelper mobileAdsHelper = new MobileAdsHelper(this);
        mobileAdsHelper.loadBanner();
        mobileAdsHelper.MobileAdsCreator();
        mobileAdsHelper.loadInterstitialAd();

        TextView splashTextView = findViewById(R.id.splashTextView);

        YoYo.with(Techniques.FadeIn)
                .duration(2000)
                .repeat(0)
                .playOn(splashTextView);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                finish();
            }
        }, 3000);
    }
}