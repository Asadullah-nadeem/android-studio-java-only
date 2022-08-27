package com.srtapps.chemicalelements;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.srtapps.chemicalelements.helper.SoundHelper;
import com.srtapps.chemicalelements.model.CreateView;
import com.srtapps.chemicalelements.model.CreateElements;

import static com.srtapps.chemicalelements.helper.StaticStore.mAdView;
import static com.srtapps.chemicalelements.helper.StaticStore.sharedPreferences;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    Button[] buttons;
    int[] colorCodes;
    RelativeLayout relativeLayout;
    SoundHelper soundHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CreateElements.xCreateElements(this);
        sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);

        setLayerDefine();
    }

    private void setLayerDefine() {
        relativeLayout = findViewById(R.id.rootMainActivity);
        buttons = new Button[4];
        colorCodes = new int[4];
        soundHelper = new SoundHelper(this, R.raw.main_menu);

        Button option = CreateView.optionButtonCreator(this, soundHelper);
        if(mAdView.getParent() != null) {
            ((ViewGroup)mAdView.getParent()).removeView(mAdView);
        }
        relativeLayout.addView(mAdView);
        relativeLayout.addView(option);

        colorCodes[0] = R.color.alkaline_earth_metal;
        colorCodes[1] = R.color.metalloid;
        colorCodes[2] = R.color.metal;
        colorCodes[3] = R.color.transition_metal;


        ArrayList<String> list = new ArrayList<>();

        list.add(getResources().getString(R.string.basic_elements));
        list.add(getResources().getString(R.string.sophisticated_elements));
        list.add(getResources().getString(R.string.all_elements));
        list.add(getResources().getString(R.string.periodic_table));

        CreateView.mainButtonCreator(this, relativeLayout, buttons, colorCodes, list);

        for(int i = 0; i < 4; i++) {
            final int finalI = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    soundHelper.playClickSound();
                    if (finalI != 3) {
                        startActivity(new Intent(getApplicationContext(), GameOptionActivity.class));
                        finish();
                        sharedPreferences.edit().putInt("element_type", finalI).apply();
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                    } else {
                        startActivity(new Intent(getApplicationContext(), PeriodicTableActivity.class));
                        finish();
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    }
                }
            });
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        //If you want to change default parameters delete app and rerun
        if (sharedPreferences.getBoolean("first_run", true)) {
            // Do first run stuff here then set 'first_run' as false
            // using the following line to edit/commit prefs
            sharedPreferences.edit().putBoolean("first_run", false).apply();
            sharedPreferences.edit().putBoolean("music", true).apply();
            sharedPreferences.edit().putBoolean("volume", true).apply();
            sharedPreferences.edit().putInt("game_type", 0).apply();
            sharedPreferences.edit().putInt("elements_type", 0).apply();
            sharedPreferences.edit().putInt("general_score", 0).apply();
            sharedPreferences.edit().putInt("mc_score", 0).apply();
            sharedPreferences.edit().putInt("qg_score", 0).apply();
            sharedPreferences.edit().putInt("tg_score", 0).apply();
            sharedPreferences.edit().putInt("an_score", 0).apply();
        }
        soundHelper.resumePlayer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundHelper.startFadeOut();
    }

    @Override
    protected void onPause() {
        super.onPause();
        soundHelper.pausePlayer();
    }
}