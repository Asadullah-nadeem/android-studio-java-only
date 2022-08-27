package com.srtapps.chemicalelements;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.srtapps.chemicalelements.helper.MobileAdsHelper;
import com.srtapps.chemicalelements.helper.SoundHelper;
import com.srtapps.chemicalelements.model.CreateView;

import java.util.ArrayList;

import static com.srtapps.chemicalelements.helper.StaticStore.mAdView;
import static com.srtapps.chemicalelements.helper.StaticStore.mInterstitialAd;
import static com.srtapps.chemicalelements.helper.StaticStore.sharedPreferences;

public class GameOptionActivity extends AppCompatActivity {

    RelativeLayout rootGameOption;
    TextView titleView;
    SoundHelper soundHelper;
    int generalI = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_option);
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                startActivity(new Intent(getApplicationContext(), GameActivity.class));
                sharedPreferences.edit().putInt("game_type", generalI).apply();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
            }
        });

        soundHelper = new SoundHelper(this, R.raw.game_option);

        rootGameOption = findViewById(R.id.rootGameOption);

        Button option = CreateView.optionButtonCreator(this, soundHelper);
        rootGameOption.addView(option);
        int[] colorCodes = new int[4];
        colorCodes[0] = R.color.post_transition_metal;
        colorCodes[1] = R.color.metalloid;
        colorCodes[2] = R.color.actinoid;
        colorCodes[3] = R.color.transition_metal;


        ArrayList<String> list = new ArrayList<>();
        list.add(getResources().getString(R.string.multiple_choice));
        list.add(getResources().getString(R.string.quiz));
        list.add(getResources().getString(R.string.time_game));
        list.add(getResources().getString(R.string.atomic_numbers));

        Button[] buttons = new Button[4];
        CreateView.gameOptionButtonCreator(this, rootGameOption, buttons, colorCodes, list);

        for(int i = 0; i < 4; i++) {
            final int finalI = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    soundHelper.playClickSound();
                    generalI = finalI;
                    if(mInterstitialAd.isLoaded()) mInterstitialAd.show();
                    else {
                        startActivity(new Intent(getApplicationContext(), GameActivity.class));
                        sharedPreferences.edit().putInt("game_type", finalI).apply();
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                    }
                }
            });
        }

        titleView = new TextView(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.setMargins(0, (int) getResources().getDimension(R.dimen.twenty_dp), 0, 0);
        titleView.setTextSize(getResources().getDimension(R.dimen.text_size_13));
        titleView.setTextColor(getResources().getColor(R.color.colorBlack));
        titleView.setGravity(Gravity.CENTER);
        titleView.setText(mGetTitle());
        titleView.setLayoutParams(params);

        rootGameOption.addView(titleView);
        if(mAdView.getParent() != null) {
            ((ViewGroup)mAdView.getParent()).removeView(mAdView); // <- fix
        }
        rootGameOption.addView(mAdView);
    }

    private String mGetTitle() {
        String title;
        int titleCode = sharedPreferences.getInt("element_type", 0);
        if (titleCode == 0) title = getResources().getString(R.string.game_option_title_1);
        else if (titleCode == 1) title = getResources().getString(R.string.game_option_title_2);
        else title = getResources().getString(R.string.game_option_title_3);
        return title;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        soundHelper.playClickSound();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundHelper.startFadeOut();
        soundHelper = null;
    }

    @Override
    protected void onPause() {
        super.onPause();
        soundHelper.pausePlayer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        soundHelper.resumePlayer();
    }
}