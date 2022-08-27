package com.livescorescrickets.livescores.BottomSheet;
import com.livescorescrickets.livescores.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import soup.neumorphism.NeumorphCardView;

public class ThemeBottomSheetFragment extends BottomSheetDialogFragment {
    NeumorphCardView continueB;
    NeumorphCardView cvmcrocss;
    public boolean flag = false;
    RadioButton rd1;
    RadioButton rd2;
    View view;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_theme_select, viewGroup, false);
        this.view = inflate;
        this.cvmcrocss = (NeumorphCardView) inflate.findViewById(R.id.cvmcrocss);
        this.rd1 = (RadioButton) this.view.findViewById(R.id.rd1);
        this.rd2 = (RadioButton) this.view.findViewById(R.id.rd2);
        this.continueB = (NeumorphCardView) this.view.findViewById(R.id.continueB);
        this.cvmcrocss.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                ThemeBottomSheetFragment.this.dismiss();
            }
        });
        int i = getResources().getConfiguration().uiMode & 48;
        if (i == 16) {
            this.rd2.setChecked(true);
            this.rd1.setChecked(false);
        } else if (i == 32) {
            this.rd1.setChecked(true);
            this.rd2.setChecked(false);
        }
        this.rd1.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                ThemeBottomSheetFragment.this.flag = true;
                ThemeBottomSheetFragment.this.rd2.setChecked(false);
            }
        });
        this.rd2.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                ThemeBottomSheetFragment.this.flag = false;
                ThemeBottomSheetFragment.this.rd1.setChecked(false);
            }
        });
        this.continueB.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                if (ThemeBottomSheetFragment.this.flag) {
                    AppCompatDelegate.setDefaultNightMode(2);
                    ThemeBottomSheetFragment.this.rd1.setChecked(true);
                    ThemeBottomSheetFragment.this.rd2.setChecked(false);
                } else {
                    AppCompatDelegate.setDefaultNightMode(1);
                    ThemeBottomSheetFragment.this.rd2.setChecked(true);
                    ThemeBottomSheetFragment.this.rd1.setChecked(false);
                }
                ThemeBottomSheetFragment.this.dismiss();
            }
        });
        return this.view;
    }
}
