package com.srtapps.chemicalelements;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.srtapps.chemicalelements.helper.ElementViewAdapter;
import com.srtapps.chemicalelements.helper.SoundHelper;
import com.srtapps.chemicalelements.model.CreateShape;
import com.srtapps.chemicalelements.model.CreateView;

import java.util.ArrayList;
import java.util.List;

import static com.srtapps.chemicalelements.helper.StaticStore.listOfLists;
import static com.srtapps.chemicalelements.helper.StaticStore.mAdView;
import static com.srtapps.chemicalelements.helper.StaticStore.sharedPreferences;
import static com.srtapps.chemicalelements.helper.StaticStore.textSize;

public class PeriodicTableActivity extends AppCompatActivity {
    TextView tv, tv1;
    int margin;
    SoundHelper soundHelper;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periodic_table);


        soundHelper = new SoundHelper(this, R.raw.periodic_table);
        Button option = CreateView.optionButtonCreator(this, soundHelper);
        margin = Resources.getSystem().getDisplayMetrics().widthPixels;
        GridView gridView = new GridView(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,margin / 18,0,0);
        gridView.setLayoutParams(params);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        gridView.setNumColumns(18);
        gridView.setHorizontalSpacing(1);
        gridView.setVerticalSpacing(1);
        ElementViewAdapter elementViewAdapter = new ElementViewAdapter(this, 126,
                true);

        gridView.setId(1);
        gridView.setAdapter(elementViewAdapter);

        RelativeLayout root = findViewById(R.id.roots);

        root.addView(option);
        if(mAdView.getParent() != null) {
            ((ViewGroup)mAdView.getParent()).removeView(mAdView);
        }
        root.addView(mAdView);
        root.addView(gridView);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                soundHelper.playClickSound();
                List<Object> objects = atomInfo((String) ((TextView) view).getText());
                atomInfoSetter(objects);
            }
        });


        GridView gridView1 = new GridView(this);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        params1.setMargins(margin / 9,20,margin / 9,0);
        gridView1.setLayoutParams(params1);

        params1.addRule(RelativeLayout.BELOW, gridView.getId());
        gridView1.setNumColumns(14);
        gridView.setHorizontalSpacing(1);
        gridView1.setVerticalSpacing(1);
        ElementViewAdapter elementViewAdapter1 = new ElementViewAdapter(this, 28,
                false);

        gridView1.setAdapter(elementViewAdapter1);

        root.addView(gridView1);

        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                soundHelper.playClickSound();
                List<Object> objects = atomInfo((String) ((TextView) view).getText());
                atomInfoSetter(objects);
            }
        });

        LinearLayout linearLayout = new LinearLayout(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ABOVE, gridView.getId());
        layoutParams.setMargins((int) getResources().getDimension(R.dimen.ten_dp), 0, 0, 0);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(layoutParams);

        tv = new TextView(this);
        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams((margin / 18) * 4 , (margin / 18) * 5);
        layoutParams1.gravity = Gravity.CENTER;
        tv.setLayoutParams(layoutParams1);
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(getResources().getColor(R.color.colorBlack));
        tv.setTextSize(textSize);

        tv1 = new TextView(this);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams2.gravity = Gravity.CENTER;
        layoutParams2.setMargins((int) getResources().getDimension(R.dimen.ten_dp), 0, 0, 0);
        tv1.setLayoutParams(layoutParams2);
        tv1.setGravity(Gravity.CENTER);
        tv1.setTextColor(getResources().getColor(R.color.colorBlack));
        tv1.setTextSize(textSize);
        tv1.setLayoutParams(layoutParams2);

        linearLayout.addView(tv);
        linearLayout.addView(tv1);
        root.addView(linearLayout);
    }

    @SuppressLint("SetTextI18n")
    private void atomInfoSetter(List<Object> objects) {
        tv.setText( objects.get(1) + "\n" +  objects.get(4)
                + "\n" +  objects.get(0));
        tv.setBackground(CreateShape.shapeCreator(getResources()
                        .getColor(getResources().getIdentifier((String) objects.get(2),
                                "color", getPackageName())),
                10,
                5,
                getResources().getColor(R.color.colorBlack)));

        tv1.setText((String) objects.get(3));
    }

    public List<Object> atomInfo (String symbol) {
        List<Object> objects = new ArrayList<>();
        for(int i = 0; i < listOfLists.size(); i++) {
            if(symbol.equals(listOfLists.get(i).get(4))) {
                objects = listOfLists.get(i);
            }
        }
        return objects;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        soundHelper.playClickSound();
        startActivity(new Intent(this, MainActivity.class));
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
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