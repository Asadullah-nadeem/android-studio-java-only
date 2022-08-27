package com.livescorescrickets.livescores.BottomSheet;
import com.livescorescrickets.livescores.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import soup.neumorphism.NeumorphCardView;

public class NotificationsBottomSheetFragment extends BottomSheetDialogFragment {
    NeumorphCardView cvmcrocss;
    View view;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_notifications_bottom_sheet, viewGroup, false);
        this.view = inflate;
        NeumorphCardView neumorphCardView = (NeumorphCardView) inflate.findViewById(R.id.cvmcrocss);
        this.cvmcrocss = neumorphCardView;
        neumorphCardView.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                NotificationsBottomSheetFragment.this.dismiss();
            }
        });
        return this.view;
    }
}
