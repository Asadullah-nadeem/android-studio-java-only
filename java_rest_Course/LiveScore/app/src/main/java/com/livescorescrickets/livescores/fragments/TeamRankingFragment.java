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
import com.livescorescrickets.livescores.Adapter.TeamRVAdapter;
import com.livescorescrickets.livescores.Pojo.RankType;
import com.livescorescrickets.livescores.Pojo.TeamRanking;

import java.util.ArrayList;
import soup.neumorphism.NeumorphButton;

public class TeamRankingFragment extends Fragment {
    public static ArrayList<RankType> data = new ArrayList<>();
    static int type2;
    static View view;
    NeumorphButton btnOdi;
    NeumorphButton btnT20;
    NeumorphButton btnTest;
    public int darkColor;
    public int lightColor;
    ArrayList<TeamRanking> playerDetails1;
    RecyclerView rvteam;
    TeamRVAdapter teamRVAdapter;

    public static void fillRanks(int i, Context context) {
        ArrayList arrayList = new ArrayList();
        type2 = i;
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rvteam);
        if (i == 0) {
            arrayList.add(new RankType("1", "New ", "21", "2,593", "123"));
            arrayList.add(new RankType("2", "India", "24", "2,914", "121"));
            arrayList.add(new RankType("3", "Australia", "17", "1,844", "108"));
            arrayList.add(new RankType("4", "England", "35", "3,753", "107"));
            arrayList.add(new RankType("5", "Pakistan", "24", "2,247", "94"));
            arrayList.add(new RankType("6", "WestIndies", "24", "2,024", "84"));
            arrayList.add(new RankType("7", "SouthAfrica", "16", "1,273", "80"));
            arrayList.add(new RankType("8", "SriLanka", "27", "2,095", "78"));
            arrayList.add(new RankType("9", "Bangladesh", "15", "694", "46"));
            arrayList.add(new RankType("10", "Zimbabwe", "10", "346", "35"));
        }
        if (i == 1) {
            arrayList.add(new RankType("1", " Australia", "18", "2,955", "164"));
            arrayList.add(new RankType("2", " South", "19", "24", "2,828"));
            arrayList.add(new RankType("3", " England", "17", "1,993", "117"));
            arrayList.add(new RankType("4", " India", "20", "2,226", "111"));
            arrayList.add(new RankType("5", " New", "21", "1947", "1,947"));
            arrayList.add(new RankType("6", " West", "12", "1025", "1,025"));
            arrayList.add(new RankType("7", " Pakistan", "15", "1,101", "73"));
            arrayList.add(new RankType("8", " Bangladesh", "5", "306", "61"));
            arrayList.add(new RankType("9", " Sri", "11", "11", "519"));
        }
        recyclerView.setAdapter(new RankAllAdapter(arrayList, context));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

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
                TeamRankingFragment.this.changeMode();
                TeamRankingFragment.this.btnTest.setShadowColorLight(TeamRankingFragment.this.lightColor);
                TeamRankingFragment.this.btnTest.setShadowColorDark(TeamRankingFragment.this.darkColor);
                TeamRankingFragment.this.btnT20.setShadowColorLight(TeamRankingFragment.this.lightColor);
                TeamRankingFragment.this.btnT20.setShadowColorDark(TeamRankingFragment.this.darkColor);
                TeamRankingFragment.this.btnOdi.setShadowColorLight(TeamRankingFragment.this.getResources().getColor(R.color.color_dark_red));
                TeamRankingFragment.data.clear();
                TeamRankingFragment.this.btnOdi.setShadowColorDark(TeamRankingFragment.this.getResources().getColor(R.color.color_dark_red));
                RecyclerView recyclerView = (RecyclerView) TeamRankingFragment.view.findViewById(R.id.rvteam);
                if (TeamRankingFragment.type2 == 0) {
                    TeamRankingFragment.data.add(new RankType("2", "India", "25", "6,811", "272"));
                    TeamRankingFragment.data.add(new RankType("3", "NewZe", "23", "6,048", "263"));
                    TeamRankingFragment.data.add(new RankType("4", "Pakistan", "30", "7,818", "261"));
                    TeamRankingFragment.data.add(new RankType("5", "Australia", "23", "5,930", "258"));
                    TeamRankingFragment.data.add(new RankType("6", "SouthAfri", "19", "4,703", "248"));
                    TeamRankingFragment.data.add(new RankType("7", "Afghanistan", "12", "2,826", "236"));
                    TeamRankingFragment.data.add(new RankType("8", "SriLanka", "13", "2,957", "227"));
                    TeamRankingFragment.data.add(new RankType("9", "Bangladesh", "13", "2,921", "225"));
                    TeamRankingFragment.data.add(new RankType("10", "WestIndies", "18", "3,992", "222"));
                    TeamRankingFragment.data.add(new RankType("11", "Zimbabwe", "19", "3,628", "191"));
                    TeamRankingFragment.data.add(new RankType("12", "Ireland", "18", "3,388", "188"));
                    TeamRankingFragment.data.add(new RankType("13", " Nepal", "19", "3,556", "187"));
                    TeamRankingFragment.data.add(new RankType("14", " Scotland", "11", "2,035", "185"));
                    TeamRankingFragment.data.add(new RankType("15", " UAE", "11", "2,023", "184"));
                    TeamRankingFragment.data.add(new RankType("16", " PapuaNew", "14", "14", "2,501"));
                    TeamRankingFragment.data.add(new RankType("17", " Netherlands", "20", "3,504", "175"));
                    TeamRankingFragment.data.add(new RankType("18", " Oman", "10", "1,732", "173"));
                    TeamRankingFragment.data.add(new RankType("19", " Namibia", "14", "2,204", "157"));
                    TeamRankingFragment.data.add(new RankType("20", " Singapore", "12", "1,678", "140"));
                    TeamRankingFragment.data.add(new RankType("21", " Qatar", "11", "1,422", "129"));
                    TeamRankingFragment.data.add(new RankType("22", " Canada", "10", "1,263", "126"));
                    TeamRankingFragment.data.add(new RankType("23", " HongKong", "13", "1,572", "121"));
                    TeamRankingFragment.data.add(new RankType("24", " Jersey", "13", "1,481", "114"));
                    TeamRankingFragment.data.add(new RankType("25", " Kenya", "8", "894", "112"));
                    TeamRankingFragment.data.add(new RankType("26", " Italy", "6", "663", "111"));
                    TeamRankingFragment.data.add(new RankType("27", " Kuwait", "8", "866", "108"));
                    TeamRankingFragment.data.add(new RankType("28", " SaudiArabia", "4", "428", "107"));
                    TeamRankingFragment.data.add(new RankType("29", " Denmark", "6", "606", "101"));
                    TeamRankingFragment.data.add(new RankType("30", " Bermuda", "6", "568", "95"));
                    TeamRankingFragment.data.add(new RankType("31", " Malaysia", "20", "1,723", "86"));
                    TeamRankingFragment.data.add(new RankType("32", " Uganda", "10", "847", "85"));
                    TeamRankingFragment.data.add(new RankType("33", " Germany", "9", "759", "84"));
                    TeamRankingFragment.data.add(new RankType("34", " UnitedStates", "8", "644", "81"));
                    TeamRankingFragment.data.add(new RankType("35", " Botswana", "10", "786", "79"));
                    TeamRankingFragment.data.add(new RankType("36", " Nigeria", "5", "375", "75"));
                    TeamRankingFragment.data.add(new RankType("37", " Guernsey", "9", "645", "72"));
                    TeamRankingFragment.data.add(new RankType("38", " Norway", "5", "355", "71"));
                    TeamRankingFragment.data.add(new RankType("39", " Austria", "10", "650", "65"));
                    TeamRankingFragment.data.add(new RankType("40", " Spain", "8", "457", "57"));
                    TeamRankingFragment.data.add(new RankType("41", " Bahrain", "4", "227", "57"));
                    TeamRankingFragment.data.add(new RankType("42", " Romania", "8", "453", "57"));
                    TeamRankingFragment.data.add(new RankType("43", " Belgium", "9", "502", "56"));
                    TeamRankingFragment.data.add(new RankType("44", " Tanzania", "3", "167", "56"));
                    TeamRankingFragment.data.add(new RankType("45", " Philippines", "5", "241", "48"));
                    TeamRankingFragment.data.add(new RankType("46", " Mexico", "7", "313", "45"));
                    TeamRankingFragment.data.add(new RankType("47", " CaymanIslands", "3", "132", "44"));
                    TeamRankingFragment.data.add(new RankType("48", " Vanuatu", "10", "435", "44"));
                    TeamRankingFragment.data.add(new RankType("49", " Belize", "5", "209", "42"));
                    TeamRankingFragment.data.add(new RankType("50", " Argentina", "5", "206", "41"));
                    TeamRankingFragment.data.add(new RankType("51", " Peru", "5", "179", "36"));
                    TeamRankingFragment.data.add(new RankType("52", " Fiji", "3", "105", "35"));
                    TeamRankingFragment.data.add(new RankType("53", " Malawi", "9", "312", "35"));
                    TeamRankingFragment.data.add(new RankType("54", " Panama", "5", "162", "32"));
                    TeamRankingFragment.data.add(new RankType("55", " Samoa", "5", "159", "32"));
                    TeamRankingFragment.data.add(new RankType("56", " Japan", "4", "126", "32"));
                    TeamRankingFragment.data.add(new RankType("57", " CostaRica", "4", "126", "32"));
                    TeamRankingFragment.data.add(new RankType("58", " Malta", "7", "214", "31"));
                    TeamRankingFragment.data.add(new RankType("59", " Luxembourg", "12", "358", "30"));
                    TeamRankingFragment.data.add(new RankType("60", " Thailand", "7", "175", "25"));
                    TeamRankingFragment.data.add(new RankType("61", " Portugal", "5", "119", "24"));
                    TeamRankingFragment.data.add(new RankType("62", " CzechRepublic", "16", "356", "22"));
                    TeamRankingFragment.data.add(new RankType("63", " Finland", "5", "106", "21"));
                    TeamRankingFragment.data.add(new RankType("64", " SouthKorea", "4", "78", "20"));
                    TeamRankingFragment.data.add(new RankType("65", " Mozambique", "9", "175", "19"));
                    TeamRankingFragment.data.add(new RankType("66", " Isleof", "Man", "4", "77"));
                    TeamRankingFragment.data.add(new RankType("67", " Bulgaria", "9", "159", "18"));
                    TeamRankingFragment.data.add(new RankType("68", " Bhutan", "4", "47", "12"));
                    TeamRankingFragment.data.add(new RankType("69", " Maldives", "7", "65", "9"));
                    TeamRankingFragment.data.add(new RankType("70", " SaintHelena", "6", "55", "9"));
                    TeamRankingFragment.data.add(new RankType("71", " Brazil", "5", "39", "8"));
                    TeamRankingFragment.data.add(new RankType("72", " Chile", "5", "19", "4"));
                    TeamRankingFragment.data.add(new RankType("73", " Gibraltar", "5", "13", "3"));
                    TeamRankingFragment.data.add(new RankType("74", " Myanmar", "3", "1", "0"));
                    TeamRankingFragment.data.add(new RankType("75", " Indonesia", "3", "0", "0"));
                    TeamRankingFragment.data.add(new RankType("76", " Lesotho", "3", "0", "0"));
                    TeamRankingFragment.data.add(new RankType("77", " Rwanda", "3", "0", "0"));
                    TeamRankingFragment.data.add(new RankType("78", " Eswatini", "3", "0", "0"));
                    TeamRankingFragment.data.add(new RankType("79", " Turkey", "3", "0", "0"));
                    TeamRankingFragment.data.add(new RankType("80", " China", "4", "0", "0"));
                }
                if (TeamRankingFragment.type2 == 1) {
                    TeamRankingFragment.data.add(new RankType("1", "Australia", "18", "2,955", "164"));
                    TeamRankingFragment.data.add(new RankType("2", "South", "Africa", "24", "2,828"));
                    TeamRankingFragment.data.add(new RankType("3", "England", "17", "1,993", "117"));
                    TeamRankingFragment.data.add(new RankType("4", "India", "20", "2,226", "111"));
                    TeamRankingFragment.data.add(new RankType("5", "New", "21", "21", "1,947"));
                    TeamRankingFragment.data.add(new RankType("6", "West", "12", "12", "1,025"));
                    TeamRankingFragment.data.add(new RankType("7", "Pakistan", "15", "1,101", "73"));
                    TeamRankingFragment.data.add(new RankType("8", "Bangladesh", "5", "306", "61"));
                    TeamRankingFragment.data.add(new RankType("9", "Sri", "11", "11", "519"));
                }
                recyclerView.setAdapter(new RankAllAdapter(TeamRankingFragment.data, TeamRankingFragment.this.getContext()));
                recyclerView.setLayoutManager(new LinearLayoutManager(TeamRankingFragment.this.getContext()));
            }
        });
        this.btnT20.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                TeamRankingFragment.this.changeMode();
                TeamRankingFragment.this.btnTest.setShadowColorLight(TeamRankingFragment.this.lightColor);
                TeamRankingFragment.this.btnTest.setShadowColorDark(TeamRankingFragment.this.darkColor);
                TeamRankingFragment.this.btnOdi.setShadowColorLight(TeamRankingFragment.this.lightColor);
                TeamRankingFragment.this.btnOdi.setShadowColorDark(TeamRankingFragment.this.darkColor);
                TeamRankingFragment.this.btnT20.setShadowColorLight(TeamRankingFragment.this.getResources().getColor(R.color.color_dark_red));
                TeamRankingFragment.this.btnT20.setShadowColorDark(TeamRankingFragment.this.getResources().getColor(R.color.color_dark_red));
                TeamRankingFragment.data.clear();
                RecyclerView recyclerView = (RecyclerView) TeamRankingFragment.view.findViewById(R.id.rvteam);
                if (TeamRankingFragment.type2 == 0) {
                    TeamRankingFragment.data.add(new RankType("2", " Australia", "25", "2,945", "118"));
                    TeamRankingFragment.data.add(new RankType("3", " India", "29", "3,344", "115"));
                    TeamRankingFragment.data.add(new RankType("4", " England", "27", "3,100", "115"));
                    TeamRankingFragment.data.add(new RankType("5", " SouthAfrica", "20", "2,137", "107"));
                    TeamRankingFragment.data.add(new RankType("6", " Pakistan", "24", "2,323", "97"));
                    TeamRankingFragment.data.add(new RankType("7", " Bangladesh", "27", "2,438", "90"));
                    TeamRankingFragment.data.add(new RankType("8", " WestIndies", "27", "2,222", "82"));
                    TeamRankingFragment.data.add(new RankType("9", " SriLanka", "24", "1,876", "78"));
                    TeamRankingFragment.data.add(new RankType("10", " Afghanistan", "17", "1,054", "62"));
                    TeamRankingFragment.data.add(new RankType("11", " Netherlands", "7", "336", "48"));
                    TeamRankingFragment.data.add(new RankType("12", " Ireland", "21", "897", "43"));
                    TeamRankingFragment.data.add(new RankType("13", " Zimbabwe", "15", "588", "39"));
                    TeamRankingFragment.data.add(new RankType("14", " Scotland", "7", "258", "37"));
                    TeamRankingFragment.data.add(new RankType("15", " Oman", "7", "240", "34"));
                    TeamRankingFragment.data.add(new RankType("16", " Nepal", "5", "119", "24"));
                    TeamRankingFragment.data.add(new RankType("17", " UAE", "9", "190", "21"));
                    TeamRankingFragment.data.add(new RankType("18", " Namibia", "6", "97", "16"));
                    TeamRankingFragment.data.add(new RankType("19", " UnitedStates", "8", "93", "12"));
                }
                if (TeamRankingFragment.type2 == 1) {
                    TeamRankingFragment.data.add(new RankType("2", " England", "33", "9,358", "284"));
                    TeamRankingFragment.data.add(new RankType("3", " India", "35", "9,344", "267"));
                    TeamRankingFragment.data.add(new RankType("4", " NewZealand", "28", "7,474", "267"));
                    TeamRankingFragment.data.add(new RankType("5", " SouthAfrica", "30", "7,569", "252"));
                    TeamRankingFragment.data.add(new RankType("6", " WestIndies", "26", "6,126", "236"));
                    TeamRankingFragment.data.add(new RankType("7", " Pakistan", "27", "6,216", "230"));
                    TeamRankingFragment.data.add(new RankType("8", " SriLanka", "18", "3,631", "202"));
                    TeamRankingFragment.data.add(new RankType("9", " Bangladesh", "26", "5,001", "192"));
                    TeamRankingFragment.data.add(new RankType("10", " Ireland", "17", "2,885", "170"));
                    TeamRankingFragment.data.add(new RankType("11", " Thailand", "26", "4,145", "159"));
                    TeamRankingFragment.data.add(new RankType("12", " Zimbabwe", "11", "1,711", "156"));
                    TeamRankingFragment.data.add(new RankType("13", " Scotland", "14", "2,056", "147"));
                    TeamRankingFragment.data.add(new RankType("14", " Nepal", "11", "1,457", "132"));
                    TeamRankingFragment.data.add(new RankType("15", " PapuaNew", "Guinea", "11", "1,423"));
                    TeamRankingFragment.data.add(new RankType("16", " Samoa", "6", "749", "125"));
                    TeamRankingFragment.data.add(new RankType("17", " UAE", "11", "1,330", "121"));
                    TeamRankingFragment.data.add(new RankType("18", " Uganda", "13", "1,563", "120"));
                    TeamRankingFragment.data.add(new RankType("19", " Tanzania", "11", "1,191", "108"));
                    TeamRankingFragment.data.add(new RankType("20", " Indonesia", "13", "1,129", "87"));
                    TeamRankingFragment.data.add(new RankType("21", " Netherlands", "10", "832", "83"));
                    TeamRankingFragment.data.add(new RankType("22", " Kenya", "8", "654", "82"));
                    TeamRankingFragment.data.add(new RankType("23", " Namibia", "16", "1,099", "69"));
                    TeamRankingFragment.data.add(new RankType("24", " HongKong", "13", "875", "67"));
                    TeamRankingFragment.data.add(new RankType("25", " Germany", "11", "727", "66"));
                    TeamRankingFragment.data.add(new RankType("26", " China", "11", "698", "63"));
                    TeamRankingFragment.data.add(new RankType("27", " Brazil", "11", "599", "54"));
                    TeamRankingFragment.data.add(new RankType("28", " Vanuatu", "6", "324", "54"));
                    TeamRankingFragment.data.add(new RankType("29", " Japan", "5", "260", "52"));
                    TeamRankingFragment.data.add(new RankType("30", " France", "3", "143", "48"));
                    TeamRankingFragment.data.add(new RankType("31", " UnitedStates", "4", "186", "47"));
                    TeamRankingFragment.data.add(new RankType("32", " Belize", "6", "269", "45"));
                    TeamRankingFragment.data.add(new RankType("33", " Argentina", "9", "398", "44"));
                    TeamRankingFragment.data.add(new RankType("34", " Rwanda", "10", "426", "43"));
                    TeamRankingFragment.data.add(new RankType("35", " Myanmar", "5", "212", "42"));
                    TeamRankingFragment.data.add(new RankType("36", " Kuwait", "8", "337", "42"));
                    TeamRankingFragment.data.add(new RankType("37", " SierraLeone", "6", "245", "41"));
                    TeamRankingFragment.data.add(new RankType("38", " Malaysia", "17", "687", "40"));
                    TeamRankingFragment.data.add(new RankType("39", " Jersey", "4", "160", "40"));
                    TeamRankingFragment.data.add(new RankType("40", " Botswana", "11", "431", "39"));
                    TeamRankingFragment.data.add(new RankType("41", " Nigeria", "9", "284", "32"));
                    TeamRankingFragment.data.add(new RankType("42", " Oman", "9", "233", "26"));
                    TeamRankingFragment.data.add(new RankType("43", " Bhutan", "4", "91", "23"));
                    TeamRankingFragment.data.add(new RankType("44", " SouthKorea", "4", "73", "18"));
                    TeamRankingFragment.data.add(new RankType("45", " Malawi", "10", "158", "16"));
                    TeamRankingFragment.data.add(new RankType("46", " Chile", "10", "124", "12"));
                    TeamRankingFragment.data.add(new RankType("47", " Singapore", "6", "61", "10"));
                    TeamRankingFragment.data.add(new RankType("48", " CostaRica", "7", "68", "10"));
                    TeamRankingFragment.data.add(new RankType("49", " Mozambique", "12", "81", "7"));
                    TeamRankingFragment.data.add(new RankType("50", " Mexico", "7", "33", "5"));
                    TeamRankingFragment.data.add(new RankType("51", " Austria", "8", "9", "1"));
                    TeamRankingFragment.data.add(new RankType("52", " Norway", "3", "0", "0"));
                    TeamRankingFragment.data.add(new RankType("53", " Lesotho", "3", "0", "0"));
                    TeamRankingFragment.data.add(new RankType("54", " Fiji", "6", "0", "0"));
                    TeamRankingFragment.data.add(new RankType("55", " Mali", "3", "0", "0"));
                    TeamRankingFragment.data.add(new RankType("56", " Peru", "8", "0", "0"));
                }
                recyclerView.setAdapter(new RankAllAdapter(TeamRankingFragment.data, TeamRankingFragment.this.getContext()));
                recyclerView.setLayoutManager(new LinearLayoutManager(TeamRankingFragment.this.getContext()));
            }
        });
        this.btnTest.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                TeamRankingFragment.this.changeMode();
                TeamRankingFragment.this.btnOdi.setShadowColorLight(TeamRankingFragment.this.lightColor);
                TeamRankingFragment.this.btnOdi.setShadowColorDark(TeamRankingFragment.this.darkColor);
                TeamRankingFragment.this.btnT20.setShadowColorLight(TeamRankingFragment.this.lightColor);
                TeamRankingFragment.this.btnT20.setShadowColorDark(TeamRankingFragment.this.darkColor);
                TeamRankingFragment.this.btnTest.setShadowColorLight(TeamRankingFragment.this.getResources().getColor(R.color.color_dark_red));
                TeamRankingFragment.this.btnTest.setShadowColorDark(TeamRankingFragment.this.getResources().getColor(R.color.color_dark_red));
                RecyclerView recyclerView = (RecyclerView) TeamRankingFragment.view.findViewById(R.id.rvteam);
                TeamRankingFragment.data.clear();
                if (TeamRankingFragment.type2 == 0) {
                    TeamRankingFragment.data.add(new RankType("1", " New ", "21", "2,593", "123"));
                    TeamRankingFragment.data.add(new RankType("2", " India", "24", "2,914", "121"));
                    TeamRankingFragment.data.add(new RankType("3", " Australia", "17", "1,844", "108"));
                    TeamRankingFragment.data.add(new RankType("4", " England", "35", "3,753", "107"));
                    TeamRankingFragment.data.add(new RankType("5", " Pakistan", "24", "2,247", "94"));
                    TeamRankingFragment.data.add(new RankType("6", " WestIndies", "24", "2,024", "84"));
                    TeamRankingFragment.data.add(new RankType("7", " SouthAfrica", "16", "1,273", "80"));
                    TeamRankingFragment.data.add(new RankType("8", " SriLanka", "27", "2,095", "78"));
                    TeamRankingFragment.data.add(new RankType("9", " Bangladesh", "15", "694", "46"));
                    TeamRankingFragment.data.add(new RankType("10", " Zimbabwe", "10", "346", "35"));
                }
                int i = TeamRankingFragment.type2;
                recyclerView.setAdapter(new RankAllAdapter(TeamRankingFragment.data, TeamRankingFragment.this.getContext()));
                recyclerView.setLayoutManager(new LinearLayoutManager(TeamRankingFragment.this.getContext()));
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_team_ranking, viewGroup, false);
    }
}
