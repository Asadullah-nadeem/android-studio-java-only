package com.srtapps.chemicalelements.helper;

import android.app.Activity;
import android.content.Context;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import com.srtapps.chemicalelements.R;

import static com.google.android.gms.ads.mediation.MediationAdRequest.TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE;
import static com.srtapps.chemicalelements.helper.StaticStore.mRewardedVideoAd;
import static com.srtapps.chemicalelements.helper.StaticStore.mInterstitialAd;
import static com.srtapps.chemicalelements.helper.StaticStore.mAdView;

public class MobileAdsHelper {
    private Activity activity;


    public MobileAdsHelper(Activity activity) {
        this.activity = activity;
    }


    public void MobileAdsCreator() {
        //If your application for children:
//        RequestConfiguration requestConfiguration = MobileAds.getRequestConfiguration().toBuilder()
//                .setTagForChildDirectedTreatment(TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE)
//                .build();
//        MobileAds.setRequestConfiguration(requestConfiguration);
        MobileAds.initialize(activity, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(activity);
    }

    public void loadInterstitialAd() {
        mInterstitialAd = new InterstitialAd(activity);
        mInterstitialAd.setAdUnitId(activity.getResources().getString(R.string.INTERSTITIAL_AD_ID));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }


    public void loadRewardedVideoAdAd() {
        mRewardedVideoAd.loadAd(activity.getResources().getString(R.string.REWARD_AD_ID),
                new AdRequest.Builder().build());
    }

    public void loadBanner() {
        mAdView = new AdView(activity);
        mAdView.setAdSize(AdSize.BANNER);
        mAdView.setAdUnitId(activity.getResources().getString(R.string.BANNER_AD_ID));

        mAdView.loadAd(new AdRequest.Builder().build());
        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mAdView.setLayoutParams(params);
    }
}
