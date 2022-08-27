package com.livescorescrickets.livescores.fragments;
import com.livescorescrickets.livescores.R;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.livescorescrickets.livescores.Adapter.RankAllAdapter;
import com.livescorescrickets.livescores.Pojo.RankType;

import java.util.ArrayList;
import soup.neumorphism.NeumorphButton;

public class BatsmanFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static ArrayList<RankType> data = new ArrayList<>();
    static int type2 = 0;
    static View view;
    NeumorphButton btnOdi;
    NeumorphButton btnT20;
    NeumorphButton btnTest;
    public int darkColor;
    public int lightColor;
    private String mParam1;
    private String mParam2;

    public void changeMode() {
        int i = getResources().getConfiguration().uiMode & 48;
        if (i == 16) {
            this.darkColor = getResources().getColor(R.color.color_white_dark_shadow);
            this.lightColor = getResources().getColor(R.color.color_white_light_shadow);
        } else if (i == 32) {
            this.darkColor = getResources().getColor(R.color.color_dark_shadow);
            this.lightColor = getResources().getColor(R.color.color_light_shadow);
        }
    }

    public static BatsmanFragment newInstance(String str, String str2) {
        BatsmanFragment batsmanFragment = new BatsmanFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM1, str);
        bundle.putString(ARG_PARAM2, str2);
        batsmanFragment.setArguments(bundle);
        return batsmanFragment;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.mParam1 = getArguments().getString(ARG_PARAM1);
            this.mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public static void fillRanks(int i, Context context) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rvteam);
        ArrayList arrayList = new ArrayList();
        if (i == 0) {
            arrayList.add(new RankType("1", " Kane ", "18", "2,955", "164"));
            arrayList.add(new RankType("2", " Steven ", "24", "2,828", "3"));
            arrayList.add(new RankType("3", " Marnus ", "17", "1,993", "117"));
            arrayList.add(new RankType("4", " Joe Root", "20", "2,226", "111"));
            arrayList.add(new RankType("5", " Virat ", "35", "1947", "147"));
            arrayList.add(new RankType("6", " Rishabh ", "12", "1025", "1,67"));
            arrayList.add(new RankType("7", " Rohit ", "15", "1,101", "73"));
            arrayList.add(new RankType("8", " Henry ", "5", "306", "61"));
            arrayList.add(new RankType("9", " David ", "5", "11", "519"));
        }
        if (i == 1) {
            arrayList.add(new RankType("1", " Tammy ", "18", "2,955", "164"));
            arrayList.add(new RankType("2", " Lizelle ", "12", "24", "2,828"));
            arrayList.add(new RankType("3", " Alyssa ", "17", "1,993", "117"));
            arrayList.add(new RankType("4", " Stafanie ", "20", "2,226", "111"));
            arrayList.add(new RankType("5", " Meg ", "11", "21", "1,947"));
            arrayList.add(new RankType("6", " Amy ", "15", "12", "1,025"));
            arrayList.add(new RankType("7", " Smriti ", "15", "1,101", "73"));
            arrayList.add(new RankType("8", " Mithali ", "5", "306", "61"));
            arrayList.add(new RankType("9", " Natalie ", "5", "11", "519"));
        }
        recyclerView.setAdapter(new RankAllAdapter(arrayList, context));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public void onViewCreated(View view2, Bundle bundle) {
        super.onViewCreated(view2, bundle);
        view = view2;
        this.btnOdi = (NeumorphButton) view2.findViewById(R.id.btnOdi);
        this.btnT20 = (NeumorphButton) view2.findViewById(R.id.btnT20);
        this.btnTest = (NeumorphButton) view2.findViewById(R.id.btnTest);
        fillRanks(0, getContext());
        changeMode();
        setOtherData();
    }

    private void setOtherData() {
        this.btnOdi.setShadowColorLight(this.lightColor);
        this.btnOdi.setShadowColorDark(this.darkColor);
        this.btnT20.setShadowColorLight(this.lightColor);
        this.btnT20.setShadowColorDark(this.darkColor);
        this.btnTest.setShadowColorLight(getResources().getColor(R.color.color_dark_red));
        this.btnTest.setShadowColorDark(getResources().getColor(R.color.color_dark_red));
        data.clear();
        this.btnOdi.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                BatsmanFragment.this.changeMode();
                BatsmanFragment.this.btnTest.setShadowColorLight(BatsmanFragment.this.lightColor);
                BatsmanFragment.this.btnTest.setShadowColorDark(BatsmanFragment.this.darkColor);
                BatsmanFragment.this.btnT20.setShadowColorLight(BatsmanFragment.this.lightColor);
                BatsmanFragment.this.btnT20.setShadowColorDark(BatsmanFragment.this.darkColor);
                BatsmanFragment.this.btnOdi.setShadowColorLight(BatsmanFragment.this.getResources().getColor(R.color.color_dark_red));
                BatsmanFragment.data.clear();
                BatsmanFragment.this.btnOdi.setShadowColorDark(BatsmanFragment.this.getResources().getColor(R.color.color_dark_red));
                RecyclerView recyclerView = (RecyclerView) BatsmanFragment.view.findViewById(R.id.rvteam);
                if (BatsmanFragment.type2 == 0) {
                    BatsmanFragment.data.add(new RankType("1", " Babar ", "18", "2,955", "164"));
                    BatsmanFragment.data.add(new RankType("2", " Virat ", "Africa", "24", "2,828"));
                    BatsmanFragment.data.add(new RankType("3", " Rohit ", "17", "1,993", "117"));
                    BatsmanFragment.data.add(new RankType("4", " Ross ", "20", "2,226", "111"));
                    BatsmanFragment.data.add(new RankType("5", " Virat ", "16", "21", "1,947"));
                    BatsmanFragment.data.add(new RankType("6", " Rishabh ", "12", "12", "1,025"));
                    BatsmanFragment.data.add(new RankType("7", " Rohit ", "15", "1,101", "73"));
                    BatsmanFragment.data.add(new RankType("8", " Henry ", "5", "306", "61"));
                    BatsmanFragment.data.add(new RankType("9", " David ", "5", "11", "519"));
                    BatsmanFragment.data.add(new RankType("22", " Canada", "10", "1,263", "126"));
                    BatsmanFragment.data.add(new RankType("23", " HongKong", "13", "1,572", "121"));
                    BatsmanFragment.data.add(new RankType("24", " Jersey", "13", "1,481", "114"));
                    BatsmanFragment.data.add(new RankType("25", " Kenya", "8", "894", "112"));
                    BatsmanFragment.data.add(new RankType("26", " Italy", "6", "663", "111"));
                    BatsmanFragment.data.add(new RankType("27", " Kuwait", "8", "866", "108"));
                    BatsmanFragment.data.add(new RankType("28", " SaudiArabia", "4", "428", "107"));
                    BatsmanFragment.data.add(new RankType("29", " Denmark", "6", "606", "101"));
                    BatsmanFragment.data.add(new RankType("30", " Bermuda", "6", "568", "95"));
                    BatsmanFragment.data.add(new RankType("31", " Malaysia", "20", "1,723", "86"));
                    BatsmanFragment.data.add(new RankType("32", " Uganda", "10", "847", "85"));
                    BatsmanFragment.data.add(new RankType("33", " Germany", "9", "759", "84"));
                    BatsmanFragment.data.add(new RankType("34", " UnitedStates", "8", "644", "81"));
                    BatsmanFragment.data.add(new RankType("35", " Botswana", "10", "786", "79"));
                    BatsmanFragment.data.add(new RankType("36", " Nigeria", "5", "375", "75"));
                    BatsmanFragment.data.add(new RankType("37", " Guernsey", "9", "645", "72"));
                    BatsmanFragment.data.add(new RankType("38", " Norway", "5", "355", "71"));
                    BatsmanFragment.data.add(new RankType("39", " Austria", "10", "650", "65"));
                    BatsmanFragment.data.add(new RankType("40", " Spain", "8", "457", "57"));
                    BatsmanFragment.data.add(new RankType("41", " Bahrain", "4", "227", "57"));
                    BatsmanFragment.data.add(new RankType("42", " Romania", "8", "453", "57"));
                    BatsmanFragment.data.add(new RankType("43", " Belgium", "9", "502", "56"));
                    BatsmanFragment.data.add(new RankType("44", " Tanzania", "3", "167", "56"));
                    BatsmanFragment.data.add(new RankType("45", " Philippines", "5", "241", "48"));
                    BatsmanFragment.data.add(new RankType("46", " Mexico", "7", "313", "45"));
                    BatsmanFragment.data.add(new RankType("47", " CaymanIslands", "3", "132", "44"));
                    BatsmanFragment.data.add(new RankType("48", " Vanuatu", "10", "435", "44"));
                    BatsmanFragment.data.add(new RankType("49", " Belize", "5", "209", "42"));
                    BatsmanFragment.data.add(new RankType("50", " Argentina", "5", "206", "41"));
                    BatsmanFragment.data.add(new RankType("51", " Peru", "5", "179", "36"));
                    BatsmanFragment.data.add(new RankType("52", " Fiji", "3", "105", "35"));
                    BatsmanFragment.data.add(new RankType("53", " Malawi", "9", "312", "35"));
                    BatsmanFragment.data.add(new RankType("54", " Panama", "5", "162", "32"));
                    BatsmanFragment.data.add(new RankType("55", " Samoa", "5", "159", "32"));
                    BatsmanFragment.data.add(new RankType("56", " Japan", "4", "126", "32"));
                    BatsmanFragment.data.add(new RankType("57", " CostaRica", "4", "126", "32"));
                    BatsmanFragment.data.add(new RankType("58", " Malta", "7", "214", "31"));
                    BatsmanFragment.data.add(new RankType("59", " Luxembourg", "12", "358", "30"));
                    BatsmanFragment.data.add(new RankType("60", " Thailand", "7", "175", "25"));
                    BatsmanFragment.data.add(new RankType("61", " Portugal", "5", "119", "24"));
                    BatsmanFragment.data.add(new RankType("62", " CzechRepublic", "16", "356", "22"));
                    BatsmanFragment.data.add(new RankType("63", " Finland", "5", "106", "21"));
                    BatsmanFragment.data.add(new RankType("64", " SouthKorea", "4", "78", "20"));
                    BatsmanFragment.data.add(new RankType("65", " Mozambique", "9", "175", "19"));
                    BatsmanFragment.data.add(new RankType("66", " Isleof", "Man", "4", "77"));
                    BatsmanFragment.data.add(new RankType("67", " Bulgaria", "9", "159", "18"));
                    BatsmanFragment.data.add(new RankType("68", " Bhutan", "4", "47", "12"));
                    BatsmanFragment.data.add(new RankType("69", " Maldives", "7", "65", "9"));
                    BatsmanFragment.data.add(new RankType("70", " SaintHelena", "6", "55", "9"));
                    BatsmanFragment.data.add(new RankType("71", " Brazil", "5", "39", "8"));
                    BatsmanFragment.data.add(new RankType("72", " Chile", "5", "19", "4"));
                    BatsmanFragment.data.add(new RankType("73", " Gibraltar", "5", "13", "3"));
                    BatsmanFragment.data.add(new RankType("74", " Myanmar", "3", "1", "0"));
                    BatsmanFragment.data.add(new RankType("75", " Indonesia", "3", "0", "0"));
                    BatsmanFragment.data.add(new RankType("76", " Lesotho", "3", "0", "0"));
                    BatsmanFragment.data.add(new RankType("77", " Rwanda", "3", "0", "0"));
                    BatsmanFragment.data.add(new RankType("78", " Eswatini", "3", "0", "0"));
                    BatsmanFragment.data.add(new RankType("79", " Turkey", "3", "0", "0"));
                    BatsmanFragment.data.add(new RankType("80", " China", "4", "0", "0"));
                }
                if (BatsmanFragment.type2 == 1) {
                    BatsmanFragment.data.add(new RankType("1", " Tammy ", "18", "2,955", "164"));
                    BatsmanFragment.data.add(new RankType("2", " Lizelle ", "Africa", "24", "2,828"));
                    BatsmanFragment.data.add(new RankType("3", " Alyssa ", "17", "1,993", "117"));
                    BatsmanFragment.data.add(new RankType("4", " Stafanie ", "20", "2,226", "111"));
                    BatsmanFragment.data.add(new RankType("5", " Meg ", "21", "21", "1,947"));
                    BatsmanFragment.data.add(new RankType("6", " Amy ", "15", "12", "1,025"));
                    BatsmanFragment.data.add(new RankType("7", " Smriti ", "15", "1,101", "73"));
                    BatsmanFragment.data.add(new RankType("8", " Mithali ", "5", "306", "61"));
                    BatsmanFragment.data.add(new RankType("9", " Natalie ", "5", "11", "519"));
                }
                recyclerView.setAdapter(new RankAllAdapter(BatsmanFragment.data, BatsmanFragment.this.getContext()));
                recyclerView.setLayoutManager(new LinearLayoutManager(BatsmanFragment.this.getContext()));
            }
        });
        this.btnT20.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                BatsmanFragment.this.changeMode();
                BatsmanFragment.this.btnTest.setShadowColorLight(BatsmanFragment.this.lightColor);
                BatsmanFragment.this.btnTest.setShadowColorDark(BatsmanFragment.this.darkColor);
                BatsmanFragment.this.btnOdi.setShadowColorLight(BatsmanFragment.this.lightColor);
                BatsmanFragment.this.btnOdi.setShadowColorDark(BatsmanFragment.this.darkColor);
                BatsmanFragment.this.btnT20.setShadowColorLight(BatsmanFragment.this.getResources().getColor(R.color.color_dark_red));
                BatsmanFragment.this.btnT20.setShadowColorDark(BatsmanFragment.this.getResources().getColor(R.color.color_dark_red));
                BatsmanFragment.data.clear();
                RecyclerView recyclerView = (RecyclerView) BatsmanFragment.view.findViewById(R.id.rvteam);
                if (BatsmanFragment.type2 == 0) {
                    BatsmanFragment.data.add(new RankType("1", " Dawid ", "18", "2,955", "164"));
                    BatsmanFragment.data.add(new RankType("2", " Aaron ", "Africa", "24", "2,828"));
                    BatsmanFragment.data.add(new RankType("3", " Babar ", "17", "1,993", "117"));
                    BatsmanFragment.data.add(new RankType("4", " Devon ", "20", "2,226", "111"));
                    BatsmanFragment.data.add(new RankType("5", " Virat ", "14", "21", "1,947"));
                    BatsmanFragment.data.add(new RankType("6", " Rishabh ", "18", "12", "1,025"));
                    BatsmanFragment.data.add(new RankType("7", " Rohit ", "15", "1,101", "73"));
                    BatsmanFragment.data.add(new RankType("8", " Henry ", "5", "306", "61"));
                    BatsmanFragment.data.add(new RankType("9", " David ", "5", "11", "519"));
                    BatsmanFragment.data.add(new RankType("15", " Oman", "7", "240", "34"));
                    BatsmanFragment.data.add(new RankType("16", " Nepal", "5", "119", "24"));
                    BatsmanFragment.data.add(new RankType("17", " UAE", "9", "190", "21"));
                    BatsmanFragment.data.add(new RankType("18", " Namibia", "6", "97", "16"));
                    BatsmanFragment.data.add(new RankType("19", " UnitedStates", "8", "93", "12"));
                }
                if (BatsmanFragment.type2 == 1) {
                    BatsmanFragment.data.add(new RankType("1", " Tammy ", "18", "2,955", "164"));
                    BatsmanFragment.data.add(new RankType("2", " Lizelle ", "24", "24", "2,828"));
                    BatsmanFragment.data.add(new RankType("3", " Alyssa ", "17", "1,993", "117"));
                    BatsmanFragment.data.add(new RankType("4", " Stafanie ", "20", "2,226", "111"));
                    BatsmanFragment.data.add(new RankType("5", " Meg ", "21", "21", "1,947"));
                    BatsmanFragment.data.add(new RankType("6", " Amy ", "23", "12", "1,025"));
                    BatsmanFragment.data.add(new RankType("7", " Smriti ", "15", "1,101", "73"));
                    BatsmanFragment.data.add(new RankType("8", " Mithali ", "5", "306", "61"));
                    BatsmanFragment.data.add(new RankType("9", " Natalie ", "5", "11", "519"));
                    BatsmanFragment.data.add(new RankType("28", " Vanuatu", "6", "324", "54"));
                    BatsmanFragment.data.add(new RankType("29", " Japan", "5", "260", "52"));
                    BatsmanFragment.data.add(new RankType("30", " France", "3", "143", "48"));
                    BatsmanFragment.data.add(new RankType("31", " UnitedStates", "4", "186", "47"));
                    BatsmanFragment.data.add(new RankType("32", " Belize", "6", "269", "45"));
                    BatsmanFragment.data.add(new RankType("33", " Argentina", "9", "398", "44"));
                    BatsmanFragment.data.add(new RankType("34", " Rwanda", "10", "426", "43"));
                    BatsmanFragment.data.add(new RankType("35", " Myanmar", "5", "212", "42"));
                    BatsmanFragment.data.add(new RankType("36", " Kuwait", "8", "337", "42"));
                    BatsmanFragment.data.add(new RankType("37", " SierraLeone", "6", "245", "41"));
                    BatsmanFragment.data.add(new RankType("38", " Malaysia", "17", "687", "40"));
                    BatsmanFragment.data.add(new RankType("39", " Jersey", "4", "160", "40"));
                    BatsmanFragment.data.add(new RankType("40", " Botswana", "11", "431", "39"));
                    BatsmanFragment.data.add(new RankType("41", " Nigeria", "9", "284", "32"));
                    BatsmanFragment.data.add(new RankType("42", " Oman", "9", "233", "26"));
                    BatsmanFragment.data.add(new RankType("43", " Bhutan", "4", "91", "23"));
                    BatsmanFragment.data.add(new RankType("44", " SouthKorea", "4", "73", "18"));
                    BatsmanFragment.data.add(new RankType("45", " Malawi", "10", "158", "16"));
                    BatsmanFragment.data.add(new RankType("46", " Chile", "10", "124", "12"));
                    BatsmanFragment.data.add(new RankType("47", " Singapore", "6", "61", "10"));
                    BatsmanFragment.data.add(new RankType("48", " CostaRica", "7", "68", "10"));
                    BatsmanFragment.data.add(new RankType("49", " Mozambique", "12", "81", "7"));
                    BatsmanFragment.data.add(new RankType("50", " Mexico", "7", "33", "5"));
                    BatsmanFragment.data.add(new RankType("51", " Austria", "8", "9", "1"));
                    BatsmanFragment.data.add(new RankType("52", " Norway", "3", "0", "0"));
                    BatsmanFragment.data.add(new RankType("53", " Lesotho", "3", "0", "0"));
                    BatsmanFragment.data.add(new RankType("54", " Fiji", "6", "0", "0"));
                    BatsmanFragment.data.add(new RankType("55", " Mali", "3", "0", "0"));
                    BatsmanFragment.data.add(new RankType("56", " Peru", "8", "0", "0"));
                }
                recyclerView.setAdapter(new RankAllAdapter(BatsmanFragment.data, BatsmanFragment.this.getContext()));
                recyclerView.setLayoutManager(new LinearLayoutManager(BatsmanFragment.this.getContext()));
            }
        });
        this.btnTest.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                BatsmanFragment.this.changeMode();
                BatsmanFragment.this.btnOdi.setShadowColorLight(BatsmanFragment.this.lightColor);
                BatsmanFragment.this.btnOdi.setShadowColorDark(BatsmanFragment.this.darkColor);
                BatsmanFragment.this.btnT20.setShadowColorLight(BatsmanFragment.this.lightColor);
                BatsmanFragment.this.btnT20.setShadowColorDark(BatsmanFragment.this.darkColor);
                BatsmanFragment.this.btnTest.setShadowColorLight(BatsmanFragment.this.getResources().getColor(R.color.color_dark_red));
                BatsmanFragment.this.btnTest.setShadowColorDark(BatsmanFragment.this.getResources().getColor(R.color.color_dark_red));
                RecyclerView recyclerView = (RecyclerView) BatsmanFragment.view.findViewById(R.id.rvteam);
                BatsmanFragment.data.clear();
                if (BatsmanFragment.type2 == 0) {
                    BatsmanFragment.data.add(new RankType("1", " Kane ", "18", "2,955", "164"));
                    BatsmanFragment.data.add(new RankType("2", " Steven ", "24", "24", "2,828"));
                    BatsmanFragment.data.add(new RankType("3", " Marnus ", "17", "1,993", "117"));
                    BatsmanFragment.data.add(new RankType("4", " Joe Root", "20", "2,226", "111"));
                    BatsmanFragment.data.add(new RankType("5", " Virat ", "20", "21", "1,947"));
                    BatsmanFragment.data.add(new RankType("6", " Rishabh ", "21", "12", "1,025"));
                    BatsmanFragment.data.add(new RankType("7", " Rohit ", "15", "1,101", "73"));
                    BatsmanFragment.data.add(new RankType("8", " Henry ", "5", "306", "61"));
                    BatsmanFragment.data.add(new RankType("9", " David ", "5", "11", "519"));
                }
                int i = BatsmanFragment.type2;
                recyclerView.setAdapter(new RankAllAdapter(BatsmanFragment.data, BatsmanFragment.this.getContext()));
                recyclerView.setLayoutManager(new LinearLayoutManager(BatsmanFragment.this.getContext()));
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_batsman, viewGroup, false);
    }
}
