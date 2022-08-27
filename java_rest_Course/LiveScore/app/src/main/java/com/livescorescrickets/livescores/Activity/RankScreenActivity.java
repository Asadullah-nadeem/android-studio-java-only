package com.livescorescrickets.livescores.Activity;
import com.livescorescrickets.livescores.R;import com.livescorescrickets.livescores.R;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

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
import com.livescorescrickets.livescores.BottomSheet.SelectBottomSheetFragment;
import com.livescorescrickets.livescores.Pojo.RankType;

import com.livescorescrickets.livescores.fragments.AllRounderFragment;
import com.livescorescrickets.livescores.fragments.BatsmanFragment;
import com.livescorescrickets.livescores.fragments.BowlerFragment;
import com.livescorescrickets.livescores.fragments.TeamRankingFragment;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphCardView;

public class RankScreenActivity extends AppCompatActivity {
    int count = 0;
    public static TextView title;
    NeumorphButton btnOdi;
    NeumorphButton btnT20;
    NeumorphButton btnTest;
    NeumorphCardView cvCheck;
    ArrayList<RankType> playerDetails1;
    RecyclerView rvRank;
    private TabLayout tabLayout;
    public ViewPager viewPager;

    public void gotoRanks(View view) {
    }

    RelativeLayout adContainerView;
    AdView adViewone;
    String TAG = "bannerload";
    RelativeLayout adContainer;
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
           this.mInterstitialAdMob.show(RankScreenActivity.this);
       }

    }
    public void AdmobInterstitial() {
        AdRequest adRequest = new AdRequest.Builder().build();
        RequestConfiguration configuration = new RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("1ADAD30F02CD84CDE72190C2ABE5EB5E")).build();
        MobileAds.setRequestConfiguration(configuration);
        InterstitialAd.load(getApplicationContext(), getString(R.string.interstitialadsAdmob), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                RankScreenActivity.this.mInterstitialAdMob = interstitialAd;
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
        setContentView(R.layout.activity_rank_screen);
        BannerAds();

        loadInterstitialAds();

        this.playerDetails1 = new ArrayList<>();
        ViewPager viewPager2 = (ViewPager) findViewById(R.id.viewpager);
        this.viewPager = viewPager2;
        setupViewPager(viewPager2);
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
        this.cvCheck = (NeumorphCardView) findViewById(R.id.ballbyball);
        title = (TextView) findViewById(R.id.title);
        TabLayout tabLayout2 = (TabLayout) findViewById(R.id.tabs);
        this.tabLayout = tabLayout2;
        tabLayout2.setupWithViewPager(this.viewPager);
        View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.matchs_custom_tab, (ViewGroup) null, false);

        final TextView textView = (TextView) inflate.findViewById(R.id.tvtab1);
        final TextView textView2 = (TextView) inflate.findViewById(R.id.tvtab2);
        final TextView textView3 = (TextView) inflate.findViewById(R.id.tvtab3);
        final TextView textView4 = (TextView) inflate.findViewById(R.id.tvtab4);
        final ImageView imageView = (ImageView) inflate.findViewById(R.id.ivTab1);
        final ImageView imageView3 = (ImageView) inflate.findViewById(R.id.ivTab2);
        final ImageView imageView4 = (ImageView) inflate.findViewById(R.id.ivTab3);
        final ImageView imageView5 = (ImageView) inflate.findViewById(R.id.ivTab4);
        this.tabLayout.getTabAt(1).setCustomView((RelativeLayout) inflate.findViewById(R.id.Rl2));
        this.tabLayout.getTabAt(0).setCustomView((RelativeLayout) inflate.findViewById(R.id.Rl1));
        this.tabLayout.getTabAt(2).setCustomView((RelativeLayout) inflate.findViewById(R.id.Rl3));
        this.tabLayout.getTabAt(3).setCustomView((RelativeLayout) inflate.findViewById(R.id.Rl4));
        imageView.setVisibility(View.VISIBLE);
        textView.setTextColor(ContextCompat.getColor(this, R.color.color_grey));
        this.tabLayout.setOnTabSelectedListener((TabLayout.OnTabSelectedListener) new TabLayout.OnTabSelectedListener() {


            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0) {
                    Typeface typeface = null;
                    textView.setTypeface(typeface, Typeface.BOLD);
                    textView2.setTypeface(typeface, Typeface.NORMAL);
                    textView3.setTypeface(typeface, Typeface.NORMAL);
                    textView4.setTypeface(typeface, Typeface.NORMAL);
                    textView.setTextColor(ContextCompat.getColor(RankScreenActivity.this, R.color.black));
                    textView2.setTextColor(ContextCompat.getColor(RankScreenActivity.this, R.color.color_grey));
                    textView3.setTextColor(ContextCompat.getColor(RankScreenActivity.this, R.color.color_grey));
                    textView4.setTextColor(ContextCompat.getColor(RankScreenActivity.this, R.color.color_grey));
                    imageView.setVisibility(View.VISIBLE);
                    imageView3.setVisibility(View.GONE);
                    imageView4.setVisibility(View.GONE);
                    imageView5.setVisibility(View.GONE);
                } else if (position == 1) {
                    Typeface typeface2 = null;
                    textView.setTypeface(typeface2, Typeface.NORMAL);
                    textView2.setTypeface(typeface2, Typeface.BOLD);
                    textView3.setTypeface(typeface2, Typeface.NORMAL);
                    textView4.setTypeface(typeface2, Typeface.NORMAL);
                    textView.setTextColor(ContextCompat.getColor(RankScreenActivity.this, R.color.color_grey));
                    textView2.setTextColor(ContextCompat.getColor(RankScreenActivity.this, R.color.black));
                    textView3.setTextColor(ContextCompat.getColor(RankScreenActivity.this, R.color.color_grey));
                    textView4.setTextColor(ContextCompat.getColor(RankScreenActivity.this, R.color.color_grey));
                    imageView.setVisibility(View.GONE);
                    imageView3.setVisibility(View.VISIBLE);
                    imageView4.setVisibility(View.GONE);
                    imageView5.setVisibility(View.GONE);
                } else if (position == 2) {
                    Typeface typeface3 = null;
                    textView.setTypeface(typeface3, Typeface.NORMAL);
                    textView2.setTypeface(typeface3, Typeface.NORMAL);
                    textView3.setTypeface(typeface3, Typeface.BOLD);
                    textView4.setTypeface(typeface3, Typeface.NORMAL);
                    textView.setTextColor(ContextCompat.getColor(RankScreenActivity.this, R.color.color_grey));
                    textView2.setTextColor(ContextCompat.getColor(RankScreenActivity.this, R.color.color_grey));
                    textView3.setTextColor(ContextCompat.getColor(RankScreenActivity.this, R.color.black));
                    textView4.setTextColor(ContextCompat.getColor(RankScreenActivity.this, R.color.color_grey));
                    imageView.setVisibility(View.GONE);
                    imageView3.setVisibility(View.GONE);
                    imageView4.setVisibility(View.VISIBLE);
                    imageView5.setVisibility(View.GONE);
                } else if (position == 3) {
                    Typeface typeface4 = null;
                    textView.setTypeface(typeface4, Typeface.NORMAL);
                    textView2.setTypeface(typeface4, Typeface.NORMAL);
                    textView3.setTypeface(typeface4, Typeface.NORMAL);
                    textView4.setTypeface(typeface4, Typeface.BOLD);
                    textView.setTextColor(ContextCompat.getColor(RankScreenActivity.this, R.color.color_grey));
                    textView2.setTextColor(ContextCompat.getColor(RankScreenActivity.this, R.color.color_grey));
                    textView3.setTextColor(ContextCompat.getColor(RankScreenActivity.this, R.color.color_grey));
                    textView4.setTextColor(ContextCompat.getColor(RankScreenActivity.this, R.color.black));
                    imageView.setVisibility(View.GONE);
                    imageView3.setVisibility(View.GONE);
                    imageView4.setVisibility(View.GONE);
                    imageView5.setVisibility(View.VISIBLE);
                }
            }
        });

        this.cvCheck.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                RankScreenActivity.this.showSelectLanguageBottomSheet();
            }
        });
    }

    public void showSelectLanguageBottomSheet() {
        new SelectBottomSheetFragment().show(getSupportFragmentManager(), "NotificationsBottomSheetFragment");
    }

    private void setupViewPager(ViewPager viewPager2) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new TeamRankingFragment(), "Team");
        viewPagerAdapter.addFragment(new BatsmanFragment(), "Batsman");
        viewPagerAdapter.addFragment(new BowlerFragment(), "Bowler");
        viewPagerAdapter.addFragment(new AllRounderFragment(), "All Rounder");
        viewPager2.setAdapter(viewPagerAdapter);
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

    public void gotoSettings(View view) {
        startActivity(new Intent(this, SettingActivity.class));
    }

    public void gotoMatches(View view) {
        startActivity(new Intent(this, MatchesActivity.class));
    }

    public void gotoNews(View view) {
        startActivity(new Intent(this, NewsActivity.class));
    }

    public void gotoHome(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
