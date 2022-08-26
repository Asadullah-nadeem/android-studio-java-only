package razi.constructor.recyclerviewpractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.dmoral.prefs.Prefs;
import razi.constructor.recyclerviewpractice.Customization.RecyclerTouchListener;
import razi.constructor.recyclerviewpractice.Models.CurrencyModel;
import razi.constructor.recyclerviewpractice.SharedData.Shared;

public class MainActivity extends AppCompatActivity {

    KProgressHUD hud;
    SearchView packagesearch;
    DrawerLayout drawerLayout;
    int drawerpos = 0;
    ListView optionsLV;
    OptionsAdapter optionsAdapter;
    CustomAdapter adapter;
    RecyclerView countriesRV;
    String current = "", bid = "", ask = "", openprice = "", description = "", ouncevalue = "", tolavalue = "", kgvalue = "",
            gram24value = "", gram22value = "", gram21value = "", gram18value = "", gram14value = "", gram12value = "",
            gram10value = "", datevalue = "", historytitlevalue = "";
    int pos = 0;
    int intro_value;
    ImageView btnBack;
    private AdView mAdView;
    private InterstitialAd interstitial;
    Integer[] opions_icons_blue = {R.drawable.share_filled, R.drawable.star_filled, R.drawable.about_us, R.drawable.app_filled};
    String[] opions = {"Share App", "Rate Us", "About Us", "More Apps"};

    List<CurrencyModel> currencyData = new ArrayList<>();
    List<CurrencyModel> filterCurrencyData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, getResources().getString(R.string.app_id));
        mAdView = findViewById(R.id.adViewOne);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId(getResources().getString(R.string.interstitial_id));
        interstitial.loadAd(new AdRequest.Builder().build());

        intro_value= Prefs.with(MainActivity.this).readInt("intro_key");

        if(intro_value==01)
        {

        }else
        {
            Intent intent = new Intent(MainActivity.this, Activity_Introactivity.class);
            startActivity(intent);
        }

        if (getIntent().getBooleanExtra("LOGOUT", false))
        {
            finish();
        }

        currencyData.clear();
        filterCurrencyData.clear();
        for (int i = 0; i< Shared.currencies.length; i++) {
            CurrencyModel currencyModel = new CurrencyModel(Shared.currencies[i], Shared.countrieswithcurrency[i]);
            currencyData.add(currencyModel);
            filterCurrencyData.add(currencyModel);
        }

        btnBack = findViewById(R.id.btnBack);
        drawerLayout = findViewById(R.id.main_drawer_layout);
        countriesRV = findViewById(R.id.countriesRV);
        countriesRV.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CustomAdapter(MainActivity.this, null);
        countriesRV.setAdapter(adapter);

        packagesearch = findViewById(R.id.packagesearch);
        packagesearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });

        if (isNetworkAvailable()) {

            countriesRV.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), countriesRV, new RecyclerTouchListener.ClickListener() {
                @Override
                public void onClick(View view, int position) {
                    pos = position;

                    hud = KProgressHUD.create(MainActivity.this)
                            .setStyle(KProgressHUD.Style.PIE_DETERMINATE)
                            .setLabel("Please wait")
                            .setCancellable(false)
                            .show();

                    if (interstitial.isLoaded()) {
                        interstitial.show();
                    } else {
                        // none
                    }

                    String[] arr = Shared.countries[position].split(" ");
                    String oldcountryname = "";
                    String newcountryname = "";
                    if (arr.length>1) {
                        for (int i = 0; i < arr.length; i++) {
                            oldcountryname = oldcountryname + arr[i] + "_";
                        }
                        newcountryname = removeLastChars(oldcountryname, 1);
                    }else{
                        newcountryname = Shared.countries[position];
                    }
                    Log.d("razaaaa", newcountryname);
                    new GoldRates().execute(Shared.state[position], newcountryname.toLowerCase());
                }

                @Override
                public void onLongClick(View view, int position) {

                }
            }));

        } else {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Internet Connection")
                    .setMessage("Please check your internet connection")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("LOGOUT", true);
                            startActivity(intent);
                            finish();
                        }
                    })

                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

        optionsLV = findViewById(R.id.optionsLV);
        optionsAdapter = new OptionsAdapter();
        optionsLV.setAdapter(optionsAdapter);
        optionsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                drawerpos = i;
                optionsAdapter.notifyDataSetChanged();
                switch (i) {
                    case 0:
                        shareApp();
                        break;
                    case 1:
                        rateapp();
                        break;
                    case 2:
                        startActivity(new Intent(getApplicationContext(), AboutUs.class));
                        finish();
                        break;
                    case 3:
                        moreapps();
                        break;
                }
                drawerLayout.closeDrawer((int) Gravity.START);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer((int) Gravity.START);
            }
        });

    }

    public static String removeLastChars(String str, int chars) {
        return str.substring(0, str.length() - chars);
    }

    public void filter(String text) {
        currencyData.clear();

        for (int i = 0; i < filterCurrencyData.size(); i++) {

            if (filterCurrencyData.get(i).getCountry().toLowerCase().contains(text)) {

                CurrencyModel packagesDataModel = new CurrencyModel();
                packagesDataModel.setCode(filterCurrencyData.get(i).getCode());
                packagesDataModel.setCountry(filterCurrencyData.get(i).getCountry());
                if (!currencyData.contains(packagesDataModel.getCountry())) {
                    currencyData.add(packagesDataModel);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) MainActivity.this.getSystemService(MainActivity.this.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private class GoldRates extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                // Connect to the web site
                String url = "https://www.goldrate24.com/gold-prices/"+params[0]+"/"+params[1]+"/tola/24K/";
                Document mBlogDocument = Jsoup.connect(url).get();

                Element currentname = null;
                Element bidname = null;
                Element askname = null;
                Element openname = null;

                Element desc = null;
                Element ounce = null;
                Element tola = null;
                Element kg = null;
                Element gram24 = null;
                Element gram22 = null;
                Element gram21 = null;
                Element gram18 = null;
                Element gram14 = null;
                Element gram12 = null;
                Element gram10 = null;
                Element date = null;
                Element historytitle = null;
                try {
                    currentname = mBlogDocument.select("table[class=table table-sm table-striped table-hover]").select("tr").select("td").get(1);
                    bidname = mBlogDocument.select("table[class=table table-sm table-striped table-hover]").select("tr").get(1).select("td").get(1);
                    askname = mBlogDocument.select("table[class=table table-sm table-striped table-hover]").select("tr").get(2).select("td").get(1);
                    openname = mBlogDocument.select("table[class=table table-sm table-striped table-hover]").select("tr").get(3).select("td").get(1);

                    desc = mBlogDocument.select("div[id=gr24_gold_main]").select("p").get(2);
                    ounce = mBlogDocument.select("table[class=now table table-bordered table-striped table-info]").select("tr").get(1).select("td").get(0);
                    tola = mBlogDocument.select("table[class=now table table-bordered table-striped table-info]").select("tr").get(2).select("td").get(0);
                    kg = mBlogDocument.select("table[class=now table table-bordered table-striped table-info]").select("tr").get(3).select("td").get(0);
                    gram24 = mBlogDocument.select("table[class=now table table-bordered table-striped table-info]").select("tr").get(4).select("td").get(0);
                    gram22 = mBlogDocument.select("table[class=now table table-bordered table-striped table-info]").select("tr").get(5).select("td").get(0);
                    gram21 = mBlogDocument.select("table[class=now table table-bordered table-striped table-info]").select("tr").get(6).select("td").get(0);
                    gram18 = mBlogDocument.select("table[class=now table table-bordered table-striped table-info]").select("tr").get(7).select("td").get(0);
                    gram14 = mBlogDocument.select("table[class=now table table-bordered table-striped table-info]").select("tr").get(8).select("td").get(0);
                    gram12 = mBlogDocument.select("table[class=now table table-bordered table-striped table-info]").select("tr").get(9).select("td").get(0);
                    gram10 = mBlogDocument.select("table[class=now table table-bordered table-striped table-info]").select("tr").get(10).select("td").get(0);
                    date = mBlogDocument.select("table[class=now table table-bordered table-striped table-info]").select("span").get(0);

                    for (int i=1; i<30; i++){
                        Shared.historicalPrices.add(mBlogDocument.select("table[class=table table-striped table-hover table-bordered table-sm table-light]").get(0)
                                .select("tr").get(i).select("td").get(0).toString());

                        Shared.historicalPricesDates.add(mBlogDocument.select("table[class=table table-striped table-hover table-bordered table-sm table-light]").get(0)
                                .select("tr").get(i).select("th").get(0).toString());
                    }

                    historytitle = mBlogDocument.select("div[class=col-md-6]").select("h3").get(0);
                }catch (Exception e){

                }

                current = currentname.text();
                bid = bidname.text();
                ask = askname.text();
                openprice = openname.text();
                description = desc.text();
                ouncevalue = ounce.text();
                tolavalue = tola.text();
                kgvalue = kg.text();
                gram24value = gram24.text();
                gram22value = gram22.text();
                gram21value = gram21.text();
                gram18value = gram18.text();
                gram14value = gram14.text();
                gram12value = gram12.text();
                gram10value = gram10.text();
                datevalue = date.text();
                historytitlevalue = historytitle.text();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            hud.dismiss();
            Log.d("nana", current+"\n"+bid+"\n"+ask+"\n"+openprice+"\n"+description+"\n"+ouncevalue+"\n"+tolavalue+"\n"+kgvalue+"\n"+
                    gram24value+"\n"+gram22value+"\n"+gram21value+"\n"+gram18value+"\n"+gram14value+"\n"+gram12value+"\n"+gram10value
                    +"\n"+datevalue+"\n"+historytitlevalue+"\n"+Shared.historicalPricesDates.get(0)+"\n"+Shared.historicalPricesDates.get(1));

            showFromAmount(current, bid, ask, openprice, description,
                    ouncevalue, tolavalue, kgvalue, gram24value, gram22value, gram21value,
                    gram18value, gram14value, gram12value, gram10value, datevalue);

        }
    }

    public void showFromAmount(String current, String bid, String ask, String openprice, String description,
                               final String ouncevalue, final String tolavalue, final String kgvalue, final String gram24value,
                               final String gram22value, final String gram21value, final String gram18value, final String gram14value,
                               final String gram12value, final String gram10value, final String datevalue) {
        final Dialog dialogView = new Dialog(MainActivity.this);
        dialogView.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogView.setCancelable(false);
        dialogView.setContentView(R.layout.conversion_layout_single);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialogView.getWindow().getAttributes());
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
        lp.width = width;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogView.getWindow().setAttributes(lp);

        final TextView headingTxt = dialogView.findViewById(R.id.headingTxt);
        final TextView txtCurrent = dialogView.findViewById(R.id.txtCurrent);
        final TextView txtBid = dialogView.findViewById(R.id.txtBid);
        final TextView txtAsk = dialogView.findViewById(R.id.txtAsk);
        final TextView txtOpen = dialogView.findViewById(R.id.txtOpen);
        Button btnHistory = (Button) dialogView.findViewById(R.id.btnHistory);
        Button btnSpotRate = (Button) dialogView.findViewById(R.id.btnSpotRate);

        headingTxt.setText(description);
        txtCurrent.setText(current);
        txtBid.setText(bid);
        txtAsk.setText(ask);
        txtOpen.setText(openprice);


        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistoricalPrices.class);
                startActivity(intent);
                dialogView.dismiss();
            }
        });

        btnSpotRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SpotRate.class);
                intent.putExtra("ouncevalue", ouncevalue);
                intent.putExtra("tolavalue", tolavalue);
                intent.putExtra("kgvalue", kgvalue);
                intent.putExtra("gram24value", gram24value);
                intent.putExtra("gram22value", gram22value);
                intent.putExtra("gram21value", gram21value);
                intent.putExtra("gram18value", gram18value);
                intent.putExtra("gram14value", gram14value);
                intent.putExtra("gram12value", gram12value);
                intent.putExtra("gram10value", gram10value);
                intent.putExtra("datevalue", datevalue);
                startActivity(intent);
                dialogView.dismiss();
            }
        });

        dialogView.show();
    }

    public void moreapps() {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:Order+IT+Services")));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/developer?id=Order+IT+Services")));
        }
    }

    private void rateapp() {
        Uri uri = Uri.parse("market://details?id=" + getPackageName());
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, " unable to find market app", Toast.LENGTH_LONG).show();
        }
    }

    private void shareApp() {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Interviews Preparation App");
            String shareMessage = "\nEasiest way to check your interviews preparation app\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch (Exception e) {
            //e.toString();
        }
    }

    public class OptionsAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return opions.length;
        }

        @Override
        public Object getItem(int i) {
            return opions[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View rowView = View.inflate(MainActivity.this, R.layout.drawer_categories_cell, null);

            LinearLayout cell = rowView.findViewById(R.id.cell);
            TextView optionname = rowView.findViewById(R.id.optionname);
            optionname.setText(opions[i]);
            optionname.setCompoundDrawablesWithIntrinsicBounds(opions_icons_blue[i], 0, 0, 0);

            if (drawerpos == i) {
                cell.setBackgroundResource(R.drawable.btn_bg_signin_solid);
                optionname.setTextColor(MainActivity.this.getResources().getColor(R.color.colorPrimary));
                setTextViewDrawableColor(optionname, R.color.colorPrimary);
            }

            return rowView;
        }
    }

    private void setTextViewDrawableColor(TextView textView, int color) {
        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(textView.getContext(), color), PorterDuff.Mode.SRC_IN));
            }
        }
    }

    public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyAdapter> {

        Context context;
        List<String> currencies;
        public CustomAdapter(Context context, List<String> currencies){
            this.context = context;
            this.currencies=currencies;
        }
        LayoutInflater inflater;

        @NonNull
        @Override
        public CustomAdapter.MyAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            if (inflater==null){
                inflater = LayoutInflater.from(parent.getContext());
            }
            View obj = inflater.inflate(R.layout.row_custom, parent, false);
            return new CustomAdapter.MyAdapter(obj);
        }

        @Override
        public void onBindViewHolder(@NonNull CustomAdapter.MyAdapter holder, int position) {

            int resId = context.getResources().getIdentifier(currencyData.get(position).getCode().toLowerCase(), "drawable",context.getPackageName());
            holder.imageView.setImageResource(resId);
            holder.title.setText(currencyData.get(position).getCode());
            holder.description.setText(currencyData.get(position).getCountry());

        }

        @Override
        public int getItemCount() {
            return currencyData.size();
        }

        public class MyAdapter extends RecyclerView.ViewHolder {

            // Declaration
            ImageView imageView;
            TextView title, description;

            public MyAdapter(@NonNull View itemView) {
                super(itemView);

                // Initialization
                imageView = itemView.findViewById(R.id.imageView);
                title = itemView.findViewById(R.id.title);
                description = itemView.findViewById(R.id.description);

            }
        }
    }

}