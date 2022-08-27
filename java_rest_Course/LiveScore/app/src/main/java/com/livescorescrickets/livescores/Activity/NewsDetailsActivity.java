package com.livescorescrickets.livescores.Activity;
import com.livescorescrickets.livescores.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.livescorescrickets.livescores.Pojo.NewsPackage.Article;

import com.livescorescrickets.livescores.Utillity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import soup.neumorphism.NeumorphCardView;

public class NewsDetailsActivity extends AppCompatActivity {
    private static String TAGC = "com.livescorecricket.livescore.Activity.NewsDetailsActivity";
    ImageView back_btn;
    ImageView ivShare;
    ImageView ivTopImage;
    NeumorphCardView nmImage;
    TextView tvContent;
    TextView tvDate;
    TextView tvTitle;
    TextView tvtag;
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

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_news_details);
        BannerAds();
        this.nmImage = (NeumorphCardView) findViewById(R.id.nmImage);
        this.ivTopImage = (ImageView) findViewById(R.id.newsImage);
        this.back_btn = (ImageView) findViewById(R.id.ivBack);
        this.ivShare = (ImageView) findViewById(R.id.ivShare);
        this.tvDate = (TextView) findViewById(R.id.latestTime);
        this.tvTitle = (TextView) findViewById(R.id.news_title);
        this.tvContent = (TextView) findViewById(R.id.news_description);
        Article article = (Article) getIntent().getSerializableExtra("data");
        if (article != null) {
            Utillity.m887p(TAGC + " ", " newsItem.getImage() =" + article.getUrlToImage());
            ((RequestBuilder) ((RequestBuilder) Glide.with((FragmentActivity) this).load(article.getUrlToImage()).placeholder(getResources().getDrawable(R.drawable.player_image))).centerCrop()).into(this.ivTopImage);
            try {
                this.tvDate.setText(new SimpleDateFormat("MMM d, h:mm a").format(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(article.getPublishedAt())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.tvTitle.setText(article.getTitle() + "");
            this.tvContent.setText(article.getContent() + "");
        }
        this.back_btn.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                NewsDetailsActivity.this.finish();
            }
        });
        this.ivShare.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.SUBJECT", "Insert Subject here");
                intent.putExtra("android.intent.extra.TEXT", "");
                NewsDetailsActivity.this.startActivity(Intent.createChooser(intent, "Share via"));
            }
        });
    }
}
