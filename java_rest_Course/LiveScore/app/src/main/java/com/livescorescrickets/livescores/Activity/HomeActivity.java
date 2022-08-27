package com.livescorescrickets.livescores.Activity;
import com.livescorescrickets.livescores.R;
import static com.livescorescrickets.livescores.adsimp.FourData;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.livescorescrickets.livescores.Pojo.JsonDataFiles.MatchResultModel;
import com.livescorescrickets.livescores.Pojo.MultiMatch.MultimatchPojo;

import com.livescorescrickets.livescores.fragments.InfoMatchFragment;
import com.livescorescrickets.livescores.fragments.LiveMatchFragment;
import com.livescorescrickets.livescores.fragments.OddsHistoryFragment;
import com.livescorescrickets.livescores.fragments.ScorecardMatchFragment;
import com.livescorescrickets.livescores.utilities.RequestHandler;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity {
    int count = 0;
    static final String url = FourData;
    private MultimatchPojo data;
    private MatchResultModel.AllMatch data2;
    String imageUrl;
    private TabLayout tabLayout;
    public ViewPager viewPager;
    String whatsAppurl;
    RelativeLayout adContainerView;
    AdView adViewone;

    private void BannerLoad() {
        AdRequest adRequest = new AdRequest.Builder().build();
        AdSize adSize = BannerGetSize();
        adViewone.setAdSize(adSize);
        adViewone.loadAd(adRequest);
    }

    private AdSize BannerGetSize() {
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;
        int adWidth = (int) (widthPixels / density);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth);
    }

    public void BannerAds() {
        adContainerView = findViewById(R.id.adMobView);
        adViewone = new AdView(getApplicationContext());
        adViewone.setAdUnitId(getString(R.string.BannerAdsAdmob));
        adContainerView.addView(adViewone);
        BannerLoad();
    }

    InterstitialAd mInterstitialAdMob;

    public void loadInterstitialAds() {
        AdmobInterstitial();
    }

    private void showAdmobInterstitial() {
        if (this.mInterstitialAdMob != null) {
            this.mInterstitialAdMob.show(HomeActivity.this);

        }
    }

    public void AdmobInterstitial() {
        AdRequest adRequest = new AdRequest.Builder().build();
        RequestConfiguration configuration = new RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("1ADAD30F02CD84CDE72190C2ABE5EB5E")).build();
        MobileAds.setRequestConfiguration(configuration);
        InterstitialAd.load(getApplicationContext(), getString(R.string.interstitialadsAdmob), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                HomeActivity.this.mInterstitialAdMob = interstitialAd;
                interstitialAd.setFullScreenContentCallback(
                        new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {

                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {

                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                            }
                        });
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
            }
        });

    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_home);
        BannerAds();

        loadInterstitialAds();
        this.viewPager = (ViewPager) findViewById(R.id.viewpager);
        TabLayout tabLayout2 = (TabLayout) findViewById(R.id.tabs);
        this.tabLayout = tabLayout2;
        tabLayout2.setupWithViewPager(this.viewPager);
        this.data = (MultimatchPojo) getIntent().getSerializableExtra("data");
        this.data2 = (MatchResultModel.AllMatch) getIntent().getSerializableExtra("data2");
        setupViewPager(this.viewPager);
        View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.custom_tab, (ViewGroup) null, false);

        final TextView textView = (TextView) inflate.findViewById(R.id.tvtab1);
        final ImageView imageView = (ImageView) inflate.findViewById(R.id.ivTab1);
        this.tabLayout.getTabAt(0).setCustomView((RelativeLayout) inflate.findViewById(R.id.Rl2));
        this.tabLayout.getTabAt(1).setCustomView((RelativeLayout) inflate.findViewById(R.id.Rl1));
        this.tabLayout.getTabAt(2).setCustomView((RelativeLayout) inflate.findViewById(R.id.Rl3));
        this.tabLayout.getTabAt(3).setCustomView((RelativeLayout) inflate.findViewById(R.id.Rl4));
//        mFetchListFromAPI();

        TabLayout tabLayout3 = this.tabLayout;
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (count != 0 && count % 3 == 0) {
                    showAdmobInterstitial();
                }
                count++;
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    private void setupViewPager(ViewPager viewPager2) {
        Bundle bundle = new Bundle();
        if (this.data != null) {
            bundle.putString("key", this.data.getMatchid() + "");
            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
            viewPagerAdapter.addFragment(new LiveMatchFragment(this.data), "Live");
            viewPagerAdapter.addFragment(new InfoMatchFragment(this, this.data), "Info");
            viewPagerAdapter.addFragment(new ScorecardMatchFragment(this.data), "Scorecard");
            viewPagerAdapter.addFragment(new OddsHistoryFragment(this.data), "OddsHistory");
            viewPager2.setAdapter(viewPagerAdapter);
            return;
        }
        ViewPagerAdapter viewPagerAdapter2 = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter2.addFragment(new LiveMatchFragment(this.data2), "Live");
        viewPagerAdapter2.addFragment(new InfoMatchFragment(this, this.data2), "Info");
        viewPagerAdapter2.addFragment(new ScorecardMatchFragment(this.data2), "Scorecard");
        viewPagerAdapter2.addFragment(new OddsHistoryFragment(this.data2), "OddsHistory");
        viewPager2.setAdapter(viewPagerAdapter2);
    }


    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList();
        private final List<String> mFragmentTitleList = new ArrayList();

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int i) {
            return this.mFragmentList.get(i);
        }

        @Override
        public int getCount() {
            return this.mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String str) {
            this.mFragmentList.add(fragment);
            this.mFragmentTitleList.add(str);
        }

        @Override
        public CharSequence getPageTitle(int i) {
            return this.mFragmentTitleList.get(i);
        }
    }

    private void mFetchListFromAPI() {
        RequestHandler.getInstance(this).addToRequestQueue(new StringRequest(0, url, new Response.Listener<String>() {


            public void onResponse(String str) {
                Log.d("dataApi", str);
                try {
                    JSONArray jSONArray = new JSONObject(str).getJSONArray("data");
                    Log.d("dataApi", jSONArray.toString());
                    HomeActivity.this.getData(jSONArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("MainError", e.getMessage());
                    Toast.makeText(HomeActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("dataApi", volleyError.getMessage());
            }
        }));
    }

    public void getData(JSONArray jSONArray) {
        if (jSONArray.length() > 0) {
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    if (jSONObject.getString(AppMeasurementSdk.ConditionalUserProperty.NAME).equals("Guru Exchange")) {
                        Log.d("dataApi", jSONObject.toString());
                        this.imageUrl = jSONObject.getString("banner");
                        this.whatsAppurl = jSONObject.getString(ImagesContract.URL);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
