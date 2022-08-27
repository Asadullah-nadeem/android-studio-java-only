package com.livescorescrickets.livescores.BottomSheet;
import com.livescorescrickets.livescores.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.livescorescrickets.livescores.Activity.RankScreenActivity;

import com.livescorescrickets.livescores.fragments.TeamRankingFragment;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import soup.neumorphism.NeumorphCardView;

public class SelectBottomSheetFragment extends BottomSheetDialogFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static int type;
    private String mParam1;
    private String mParam2;
    View view;

    public static SelectBottomSheetFragment newInstance(String str, String str2) {
        SelectBottomSheetFragment selectBottomSheetFragment = new SelectBottomSheetFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM1, str);
        bundle.putString(ARG_PARAM2, str2);
        selectBottomSheetFragment.setArguments(bundle);
        return selectBottomSheetFragment;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_select_bottom_sheet, viewGroup, false);
        this.view = inflate;
        final NeumorphCardView neumorphCardView = (NeumorphCardView) inflate.findViewById(R.id.mensSelect);
        final NeumorphCardView neumorphCardView2 = (NeumorphCardView) this.view.findViewById(R.id.womensSelect);
        ((NeumorphCardView) inflate.findViewById(R.id.cvmcrocss)).setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                SelectBottomSheetFragment.this.dismiss();
            }
        });
        ((NeumorphCardView) this.view.findViewById(R.id.continueSelect)).setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                SelectBottomSheetFragment.this.dismiss();
            }
        });
        neumorphCardView.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                neumorphCardView2.setShadowColorLight(SelectBottomSheetFragment.this.getResources().getColor(R.color.color_light_shadow));
                neumorphCardView2.setShadowColorDark(SelectBottomSheetFragment.this.getResources().getColor(R.color.color_dark_shadow));
                neumorphCardView.setShadowColorLight(SelectBottomSheetFragment.this.getResources().getColor(R.color.color_dark_red));
                neumorphCardView.setShadowColorDark(SelectBottomSheetFragment.this.getResources().getColor(R.color.color_dark_red));
                RankScreenActivity.title.setText("Men's");
                TeamRankingFragment.fillRanks(0, SelectBottomSheetFragment.this.getContext());
                SelectBottomSheetFragment.type = 0;
            }
        });
        neumorphCardView2.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                neumorphCardView.setShadowColorLight(SelectBottomSheetFragment.this.getResources().getColor(R.color.color_light_shadow));
                neumorphCardView.setShadowColorDark(SelectBottomSheetFragment.this.getResources().getColor(R.color.color_dark_shadow));
                neumorphCardView2.setShadowColorLight(SelectBottomSheetFragment.this.getResources().getColor(R.color.color_dark_red));
                neumorphCardView2.setShadowColorDark(SelectBottomSheetFragment.this.getResources().getColor(R.color.color_dark_red));
                RankScreenActivity.title.setText("Women's");
                SelectBottomSheetFragment.type = 1;
                TeamRankingFragment.fillRanks(1, SelectBottomSheetFragment.this.getContext());
            }
        });
        return this.view;
    }
}
