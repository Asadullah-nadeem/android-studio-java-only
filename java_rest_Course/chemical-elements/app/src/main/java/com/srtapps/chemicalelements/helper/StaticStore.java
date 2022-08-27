package com.srtapps.chemicalelements.helper;

import android.content.SharedPreferences;
import android.content.res.Resources;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.srtapps.chemicalelements.model.ChemicalElements;

import java.util.ArrayList;
import java.util.List;

public class StaticStore {

    public static ChemicalElements chemicalElements = new ChemicalElements();
    public static boolean alreadyExecuted = false;
    public static List<List<Object>> listOfLists = new ArrayList<>();

    public static List<List<Object>> basicElements = new ArrayList<>();
    public static List<List<Object>> sophisticatedElements = new ArrayList<>();

    public static SharedPreferences sharedPreferences;

    public static RewardedVideoAd mRewardedVideoAd;
    public static InterstitialAd mInterstitialAd;
    public static AdView mAdView;

    public static String query;

    public static int textSize = (Resources.getSystem().getDisplayMetrics().widthPixels / 60);

}
