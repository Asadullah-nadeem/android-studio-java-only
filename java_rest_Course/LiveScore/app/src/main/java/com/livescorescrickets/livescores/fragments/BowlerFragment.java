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

public class BowlerFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    static ArrayList<RankType> data = new ArrayList<>();
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

    public static BowlerFragment newInstance(String str, String str2) {
        BowlerFragment bowlerFragment = new BowlerFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM1, str);
        bundle.putString(ARG_PARAM2, str2);
        bowlerFragment.setArguments(bundle);
        return bowlerFragment;
    }

    public static void fillRanks(int i, Context context) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rvteam);
        ArrayList arrayList = new ArrayList();
        if (i == 0) {
            arrayList.add(new RankType("1", " Cummins", "18", "2,955", "164"));
            arrayList.add(new RankType("2", " Ashwin", "24", "24", "2,828"));
            arrayList.add(new RankType("3", " Southee", "17", "1,993", "117"));
            arrayList.add(new RankType("4", " Hazlewood ", "20", "2,226", "111"));
            arrayList.add(new RankType("5", " Wagner ", "21", "21", "1,947"));
            arrayList.add(new RankType("6", " Rabada ", "Indies", "12", "1,025"));
            arrayList.add(new RankType("7", " Broad ", "15", "1,101", "73"));
            arrayList.add(new RankType("8", " Anderson ", "5", "306", "61"));
            arrayList.add(new RankType("9", " Starc ", "5", "11", "519"));
        }
        if (i == 1) {
            arrayList.add(new RankType("1", " Tammy ", "18", "2,955", "164"));
            arrayList.add(new RankType("2", " Lizelle ", "Africa", "24", "2,828"));
            arrayList.add(new RankType("3", " Alyssa ", "17", "1,993", "117"));
            arrayList.add(new RankType("4", " Stafanie ", "20", "2,226", "111"));
            arrayList.add(new RankType("5", " Meg ", "21", "21", "1,947"));
            arrayList.add(new RankType("6", " Amy ", "12", "12", "1,025"));
            arrayList.add(new RankType("7", " Smriti ", "15", "1,101", "73"));
            arrayList.add(new RankType("8", " Mithali ", "5", "306", "61"));
            arrayList.add(new RankType("9", " Natalie ", "5", "11", "519"));
        }
        recyclerView.setAdapter(new RankAllAdapter(arrayList, context));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.mParam1 = getArguments().getString(ARG_PARAM1);
            this.mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
                BowlerFragment.this.changeMode();
                BowlerFragment.this.btnTest.setShadowColorLight(BowlerFragment.this.lightColor);
                BowlerFragment.this.btnTest.setShadowColorDark(BowlerFragment.this.darkColor);
                BowlerFragment.this.btnT20.setShadowColorLight(BowlerFragment.this.lightColor);
                BowlerFragment.this.btnT20.setShadowColorDark(BowlerFragment.this.darkColor);
                BowlerFragment.this.btnOdi.setShadowColorLight(BowlerFragment.this.getResources().getColor(R.color.color_dark_red));
                BowlerFragment.data.clear();
                BowlerFragment.this.btnOdi.setShadowColorDark(BowlerFragment.this.getResources().getColor(R.color.color_dark_red));
                RecyclerView recyclerView = (RecyclerView) BowlerFragment.view.findViewById(R.id.rvteam);
                if (BowlerFragment.type2 == 0) {
                    BowlerFragment.data.add(new RankType("1", " Dawid Malan", "18", "2,955", "164"));
                    BowlerFragment.data.add(new RankType("2", " Aaron Finch", "Africa", "24", "2,828"));
                    BowlerFragment.data.add(new RankType("3", " Babar Azam", "17", "1,993", "117"));
                    BowlerFragment.data.add(new RankType("4", " Devon Conway", "20", "2,226", "111"));
                    BowlerFragment.data.add(new RankType("5", " Virat Kohli", "Zealand", "21", "1,947"));
                    BowlerFragment.data.add(new RankType("6", " Rishabh Pant", "Indies", "12", "1,025"));
                    BowlerFragment.data.add(new RankType("7", " Rohit Sharma", "15", "1,101", "73"));
                    BowlerFragment.data.add(new RankType("8", " Henry Nicholls", "5", "306", "61"));
                    BowlerFragment.data.add(new RankType("9", " David Warner", "5", "11", "519"));
                    BowlerFragment.data.add(new RankType("14", " Scotland", "11", "2,035", "185"));
                    BowlerFragment.data.add(new RankType("15", " UAE", "11", "2,023", "184"));
                    BowlerFragment.data.add(new RankType("16", " PapuaNew", "Guinea", "14", "2,501"));
                    BowlerFragment.data.add(new RankType("17", " Netherlands", "20", "3,504", "175"));
                    BowlerFragment.data.add(new RankType("18", " Oman", "10", "1,732", "173"));
                    BowlerFragment.data.add(new RankType("19", " Namibia", "14", "2,204", "157"));
                    BowlerFragment.data.add(new RankType("20", " Singapore", "12", "1,678", "140"));
                    BowlerFragment.data.add(new RankType("21", " Qatar", "11", "1,422", "129"));
                    BowlerFragment.data.add(new RankType("22", " Canada", "10", "1,263", "126"));
                    BowlerFragment.data.add(new RankType("23", " HongKong", "13", "1,572", "121"));
                    BowlerFragment.data.add(new RankType("24", " Jersey", "13", "1,481", "114"));
                    BowlerFragment.data.add(new RankType("25", " Kenya", "8", "894", "112"));
                    BowlerFragment.data.add(new RankType("26", " Italy", "6", "663", "111"));
                    BowlerFragment.data.add(new RankType("27", " Kuwait", "8", "866", "108"));
                    BowlerFragment.data.add(new RankType("28", " SaudiArabia", "4", "428", "107"));
                    BowlerFragment.data.add(new RankType("29", " Denmark", "6", "606", "101"));
                    BowlerFragment.data.add(new RankType("30", " Bermuda", "6", "568", "95"));
                    BowlerFragment.data.add(new RankType("31", " Malaysia", "20", "1,723", "86"));
                    BowlerFragment.data.add(new RankType("32", " Uganda", "10", "847", "85"));
                    BowlerFragment.data.add(new RankType("33", " Germany", "9", "759", "84"));
                    BowlerFragment.data.add(new RankType("34", " UnitedStates", "8", "644", "81"));
                    BowlerFragment.data.add(new RankType("35", " Botswana", "10", "786", "79"));
                    BowlerFragment.data.add(new RankType("36", " Nigeria", "5", "375", "75"));
                    BowlerFragment.data.add(new RankType("37", " Guernsey", "9", "645", "72"));
                    BowlerFragment.data.add(new RankType("38", " Norway", "5", "355", "71"));
                    BowlerFragment.data.add(new RankType("39", " Austria", "10", "650", "65"));
                    BowlerFragment.data.add(new RankType("40", " Spain", "8", "457", "57"));
                    BowlerFragment.data.add(new RankType("41", " Bahrain", "4", "227", "57"));
                    BowlerFragment.data.add(new RankType("42", " Romania", "8", "453", "57"));
                    BowlerFragment.data.add(new RankType("43", " Belgium", "9", "502", "56"));
                    BowlerFragment.data.add(new RankType("44", " Tanzania", "3", "167", "56"));
                    BowlerFragment.data.add(new RankType("45", " Philippines", "5", "241", "48"));
                    BowlerFragment.data.add(new RankType("46", " Mexico", "7", "313", "45"));
                    BowlerFragment.data.add(new RankType("47", " CaymanIslands", "3", "132", "44"));
                    BowlerFragment.data.add(new RankType("48", " Vanuatu", "10", "435", "44"));
                    BowlerFragment.data.add(new RankType("49", " Belize", "5", "209", "42"));
                    BowlerFragment.data.add(new RankType("50", " Argentina", "5", "206", "41"));
                    BowlerFragment.data.add(new RankType("51", " Peru", "5", "179", "36"));
                    BowlerFragment.data.add(new RankType("52", " Fiji", "3", "105", "35"));
                    BowlerFragment.data.add(new RankType("53", " Malawi", "9", "312", "35"));
                    BowlerFragment.data.add(new RankType("54", " Panama", "5", "162", "32"));
                    BowlerFragment.data.add(new RankType("55", " Samoa", "5", "159", "32"));
                    BowlerFragment.data.add(new RankType("56", " Japan", "4", "126", "32"));
                    BowlerFragment.data.add(new RankType("57", " CostaRica", "4", "126", "32"));
                    BowlerFragment.data.add(new RankType("58", " Malta", "7", "214", "31"));
                    BowlerFragment.data.add(new RankType("59", " Luxembourg", "12", "358", "30"));
                    BowlerFragment.data.add(new RankType("60", " Thailand", "7", "175", "25"));
                    BowlerFragment.data.add(new RankType("61", " Portugal", "5", "119", "24"));
                    BowlerFragment.data.add(new RankType("62", " CzechRepublic", "16", "356", "22"));
                    BowlerFragment.data.add(new RankType("63", " Finland", "5", "106", "21"));
                    BowlerFragment.data.add(new RankType("64", " SouthKorea", "4", "78", "20"));
                    BowlerFragment.data.add(new RankType("65", " Mozambique", "9", "175", "19"));
                    BowlerFragment.data.add(new RankType("66", " Isleof", "Man", "4", "77"));
                    BowlerFragment.data.add(new RankType("67", " Bulgaria", "9", "159", "18"));
                    BowlerFragment.data.add(new RankType("68", " Bhutan", "4", "47", "12"));
                    BowlerFragment.data.add(new RankType("69", " Maldives", "7", "65", "9"));
                    BowlerFragment.data.add(new RankType("70", " SaintHelena", "6", "55", "9"));
                    BowlerFragment.data.add(new RankType("71", " Brazil", "5", "39", "8"));
                    BowlerFragment.data.add(new RankType("72", " Chile", "5", "19", "4"));
                    BowlerFragment.data.add(new RankType("73", " Gibraltar", "5", "13", "3"));
                    BowlerFragment.data.add(new RankType("74", " Myanmar", "3", "1", "0"));
                    BowlerFragment.data.add(new RankType("75", " Indonesia", "3", "0", "0"));
                    BowlerFragment.data.add(new RankType("76", " Lesotho", "3", "0", "0"));
                    BowlerFragment.data.add(new RankType("77", " Rwanda", "3", "0", "0"));
                    BowlerFragment.data.add(new RankType("78", " Eswatini", "3", "0", "0"));
                    BowlerFragment.data.add(new RankType("79", " Turkey", "3", "0", "0"));
                    BowlerFragment.data.add(new RankType("80", " China", "4", "0", "0"));
                }
                if (BowlerFragment.type2 == 1) {
                    BowlerFragment.data.add(new RankType("1", " Tammy Beaumont", "18", "2,955", "164"));
                    BowlerFragment.data.add(new RankType("2", " Lizelle Lee", "Africa", "24", "2,828"));
                    BowlerFragment.data.add(new RankType("3", " Alyssa Healy", "17", "1,993", "117"));
                    BowlerFragment.data.add(new RankType("4", " Stafanie Taylor", "20", "2,226", "111"));
                    BowlerFragment.data.add(new RankType("5", " Meg Lanning", "Zealand", "21", "1,947"));
                    BowlerFragment.data.add(new RankType("6", " Amy Satterthwaite", "Indies", "12", "1,025"));
                    BowlerFragment.data.add(new RankType("7", " Smriti Mandhana", "15", "1,101", "73"));
                    BowlerFragment.data.add(new RankType("8", " Mithali Raj", "5", "306", "61"));
                    BowlerFragment.data.add(new RankType("9", " Natalie Sciver", "5", "11", "519"));
                }
                recyclerView.setAdapter(new RankAllAdapter(BowlerFragment.data, BowlerFragment.this.getContext()));
                recyclerView.setLayoutManager(new LinearLayoutManager(BowlerFragment.this.getContext()));
            }
        });
        this.btnT20.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                BowlerFragment.this.changeMode();
                BowlerFragment.this.btnTest.setShadowColorLight(BowlerFragment.this.lightColor);
                BowlerFragment.this.btnTest.setShadowColorDark(BowlerFragment.this.darkColor);
                BowlerFragment.this.btnOdi.setShadowColorLight(BowlerFragment.this.lightColor);
                BowlerFragment.this.btnOdi.setShadowColorDark(BowlerFragment.this.darkColor);
                BowlerFragment.this.btnT20.setShadowColorLight(BowlerFragment.this.getResources().getColor(R.color.color_dark_red));
                BowlerFragment.this.btnT20.setShadowColorDark(BowlerFragment.this.getResources().getColor(R.color.color_dark_red));
                BowlerFragment.data.clear();
                RecyclerView recyclerView = (RecyclerView) BowlerFragment.view.findViewById(R.id.rvteam);
                if (BowlerFragment.type2 == 0) {
                    BowlerFragment.data.add(new RankType("1", " Dawid Malan", "18", "2,955", "164"));
                    BowlerFragment.data.add(new RankType("2", " Aaron Finch", "Africa", "24", "2,828"));
                    BowlerFragment.data.add(new RankType("3", " Babar Azam", "17", "1,993", "117"));
                    BowlerFragment.data.add(new RankType("4", " Devon Conway", "20", "2,226", "111"));
                    BowlerFragment.data.add(new RankType("5", " Virat Kohli", "Zealand", "21", "1,947"));
                    BowlerFragment.data.add(new RankType("6", " Rishabh Pant", "Indies", "12", "1,025"));
                    BowlerFragment.data.add(new RankType("7", " Rohit Sharma", "15", "1,101", "73"));
                    BowlerFragment.data.add(new RankType("8", " Henry Nicholls", "5", "306", "61"));
                    BowlerFragment.data.add(new RankType("9", " David Warner", "5", "11", "519"));
                    BowlerFragment.data.add(new RankType("12", " Ireland", "21", "897", "43"));
                    BowlerFragment.data.add(new RankType("13", " Zimbabwe", "15", "588", "39"));
                    BowlerFragment.data.add(new RankType("14", " Scotland", "7", "258", "37"));
                    BowlerFragment.data.add(new RankType("15", " Oman", "7", "240", "34"));
                    BowlerFragment.data.add(new RankType("16", " Nepal", "5", "119", "24"));
                    BowlerFragment.data.add(new RankType("17", " UAE", "9", "190", "21"));
                    BowlerFragment.data.add(new RankType("18", " Namibia", "6", "97", "16"));
                    BowlerFragment.data.add(new RankType("19", " UnitedStates", "8", "93", "12"));
                }
                if (BowlerFragment.type2 == 1) {
                    BowlerFragment.data.add(new RankType("1", " Tammy Beaumont", "18", "2,955", "164"));
                    BowlerFragment.data.add(new RankType("2", " Lizelle Lee", "Africa", "24", "2,828"));
                    BowlerFragment.data.add(new RankType("3", " Alyssa Healy", "17", "1,993", "117"));
                    BowlerFragment.data.add(new RankType("4", " Stafanie Taylor", "20", "2,226", "111"));
                    BowlerFragment.data.add(new RankType("5", " Meg Lanning", "Zealand", "21", "1,947"));
                    BowlerFragment.data.add(new RankType("6", " Amy Satterthwaite", "Indies", "12", "1,025"));
                    BowlerFragment.data.add(new RankType("7", " Smriti Mandhana", "15", "1,101", "73"));
                    BowlerFragment.data.add(new RankType("8", " Mithali Raj", "5", "306", "61"));
                    BowlerFragment.data.add(new RankType("9", " Natalie Sciver", "5", "11", "519"));
                    BowlerFragment.data.add(new RankType("19", " Tanzania", "11", "1,191", "108"));
                    BowlerFragment.data.add(new RankType("20", " Indonesia", "13", "1,129", "87"));
                    BowlerFragment.data.add(new RankType("21", " Netherlands", "10", "832", "83"));
                    BowlerFragment.data.add(new RankType("22", " Kenya", "8", "654", "82"));
                    BowlerFragment.data.add(new RankType("23", " Namibia", "16", "1,099", "69"));
                    BowlerFragment.data.add(new RankType("24", " HongKong", "13", "875", "67"));
                    BowlerFragment.data.add(new RankType("25", " Germany", "11", "727", "66"));
                    BowlerFragment.data.add(new RankType("26", " China", "11", "698", "63"));
                    BowlerFragment.data.add(new RankType("27", " Brazil", "11", "599", "54"));
                    BowlerFragment.data.add(new RankType("28", " Vanuatu", "6", "324", "54"));
                    BowlerFragment.data.add(new RankType("29", " Japan", "5", "260", "52"));
                    BowlerFragment.data.add(new RankType("30", " France", "3", "143", "48"));
                    BowlerFragment.data.add(new RankType("31", " UnitedStates", "4", "186", "47"));
                    BowlerFragment.data.add(new RankType("32", " Belize", "6", "269", "45"));
                    BowlerFragment.data.add(new RankType("33", " Argentina", "9", "398", "44"));
                    BowlerFragment.data.add(new RankType("34", " Rwanda", "10", "426", "43"));
                    BowlerFragment.data.add(new RankType("35", " Myanmar", "5", "212", "42"));
                    BowlerFragment.data.add(new RankType("36", " Kuwait", "8", "337", "42"));
                    BowlerFragment.data.add(new RankType("37", " SierraLeone", "6", "245", "41"));
                    BowlerFragment.data.add(new RankType("38", " Malaysia", "17", "687", "40"));
                    BowlerFragment.data.add(new RankType("39", " Jersey", "4", "160", "40"));
                    BowlerFragment.data.add(new RankType("40", " Botswana", "11", "431", "39"));
                    BowlerFragment.data.add(new RankType("41", " Nigeria", "9", "284", "32"));
                    BowlerFragment.data.add(new RankType("42", " Oman", "9", "233", "26"));
                    BowlerFragment.data.add(new RankType("43", " Bhutan", "4", "91", "23"));
                    BowlerFragment.data.add(new RankType("44", " SouthKorea", "4", "73", "18"));
                    BowlerFragment.data.add(new RankType("45", " Malawi", "10", "158", "16"));
                    BowlerFragment.data.add(new RankType("46", " Chile", "10", "124", "12"));
                    BowlerFragment.data.add(new RankType("47", " Singapore", "6", "61", "10"));
                    BowlerFragment.data.add(new RankType("48", " CostaRica", "7", "68", "10"));
                    BowlerFragment.data.add(new RankType("49", " Mozambique", "12", "81", "7"));
                    BowlerFragment.data.add(new RankType("50", " Mexico", "7", "33", "5"));
                    BowlerFragment.data.add(new RankType("51", " Austria", "8", "9", "1"));
                    BowlerFragment.data.add(new RankType("52", " Norway", "3", "0", "0"));
                    BowlerFragment.data.add(new RankType("53", " Lesotho", "3", "0", "0"));
                    BowlerFragment.data.add(new RankType("54", " Fiji", "6", "0", "0"));
                    BowlerFragment.data.add(new RankType("55", " Mali", "3", "0", "0"));
                    BowlerFragment.data.add(new RankType("56", " Peru", "8", "0", "0"));
                }
                recyclerView.setAdapter(new RankAllAdapter(BowlerFragment.data, BowlerFragment.this.getContext()));
                recyclerView.setLayoutManager(new LinearLayoutManager(BowlerFragment.this.getContext()));
            }
        });
        this.btnTest.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                BowlerFragment.this.changeMode();
                BowlerFragment.this.btnOdi.setShadowColorLight(BowlerFragment.this.lightColor);
                BowlerFragment.this.btnOdi.setShadowColorDark(BowlerFragment.this.darkColor);
                BowlerFragment.this.btnT20.setShadowColorLight(BowlerFragment.this.lightColor);
                BowlerFragment.this.btnT20.setShadowColorDark(BowlerFragment.this.darkColor);
                BowlerFragment.this.btnTest.setShadowColorLight(BowlerFragment.this.getResources().getColor(R.color.color_dark_red));
                BowlerFragment.this.btnTest.setShadowColorDark(BowlerFragment.this.getResources().getColor(R.color.color_dark_red));
                RecyclerView recyclerView = (RecyclerView) BowlerFragment.view.findViewById(R.id.rvteam);
                BowlerFragment.data.clear();
                if (BowlerFragment.type2 == 0) {
                    BowlerFragment.data.add(new RankType("1", " Dawid ", "18", "2,955", "164"));
                    BowlerFragment.data.add(new RankType("2", " Aaron ", "Africa", "24", "2,828"));
                    BowlerFragment.data.add(new RankType("3", " Babar ", "17", "1,993", "117"));
                    BowlerFragment.data.add(new RankType("4", " Devon ", "20", "2,226", "111"));
                    BowlerFragment.data.add(new RankType("5", " Virat ", "21", "21", "1,947"));
                    BowlerFragment.data.add(new RankType("6", " Rishabh ", "12", "12", "1,025"));
                    BowlerFragment.data.add(new RankType("7", " Rohit ", "15", "1,101", "73"));
                    BowlerFragment.data.add(new RankType("8", " Henry ", "5", "306", "61"));
                    BowlerFragment.data.add(new RankType("9", " David ", "5", "11", "519"));
                }
                if (BowlerFragment.type2 == 1) {
                    BowlerFragment.data.add(new RankType("1", " Tammy ", "18", "2,955", "164"));
                    BowlerFragment.data.add(new RankType("2", " Lizelle ", "Africa", "24", "2,828"));
                    BowlerFragment.data.add(new RankType("3", " Alyssa ", "17", "1,993", "117"));
                    BowlerFragment.data.add(new RankType("4", " Stafanie ", "20", "2,226", "111"));
                    BowlerFragment.data.add(new RankType("5", " Meg ", "21", "21", "1,947"));
                    BowlerFragment.data.add(new RankType("6", " Amy ", "12", "12", "1,025"));
                    BowlerFragment.data.add(new RankType("7", " Smriti ", "15", "1,101", "73"));
                    BowlerFragment.data.add(new RankType("8", " Mithali ", "5", "306", "61"));
                    BowlerFragment.data.add(new RankType("9", " Natalie ", "5", "11", "519"));
                }
                recyclerView.setAdapter(new RankAllAdapter(BowlerFragment.data, BowlerFragment.this.getContext()));
                recyclerView.setLayoutManager(new LinearLayoutManager(BowlerFragment.this.getContext()));
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_bowler, viewGroup, false);
    }
}
