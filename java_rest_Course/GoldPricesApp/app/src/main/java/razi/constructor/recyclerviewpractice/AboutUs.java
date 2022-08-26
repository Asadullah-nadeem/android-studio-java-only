package razi.constructor.recyclerviewpractice;

import android.content.Intent;
import android.content.res.Configuration;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;
import razi.constructor.recyclerviewpractice.Customization.FontsOverride;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        simulateDayNight(/* DAY */ 0);

        Element adsElement = new Element();
        adsElement.setTitle("Advertise with us");

        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/montserrat_semi_bold.ttf");
        String playstore = "https://play.google.com/store/apps/details?id=" + getPackageName();

        android.view.View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.logo)
                .addItem(new Element().setTitle("Version 1.0"))
                .addItem(adsElement)
                .addGroup("Connect with us")
                .addEmail("info@orderitservices.com")
                .setDescription(AboutUs.this.getResources().getString(R.string.about_us))
                .addWebsite("http://www.orderitservices.com")
                .addFacebook("Order IT Services")
                .addTwitter("Order IT Services")
                .addPlayStore(playstore)
                .addInstagram("")
                .addItem(getCopyRightsElement())
                .create();

        setContentView(aboutPage);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    Element getCopyRightsElement() {
        Element copyRightsElement = new Element();
        final String copyrights = String.format(getString(R.string.copy_right), Calendar.getInstance().get(Calendar.YEAR));
        copyRightsElement.setTitle(copyrights);
        copyRightsElement.setIconDrawable(R.drawable.about_icon_copy_right);
        copyRightsElement.setIconTint(mehdi.sakout.aboutpage.R.color.about_item_icon_color);
        copyRightsElement.setIconNightTint(android.R.color.white);
        copyRightsElement.setGravity(Gravity.CENTER);
        copyRightsElement.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Toast.makeText(AboutUs.this, copyrights, Toast.LENGTH_SHORT).show();
            }
        });
        return copyRightsElement;
    }

    void simulateDayNight(int currentSetting) {
        final int DAY = 0;
        final int NIGHT = 1;
        final int FOLLOW_SYSTEM = 3;

        int currentNightMode = getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK;
        if (currentSetting == DAY && currentNightMode != Configuration.UI_MODE_NIGHT_NO) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        } else if (currentSetting == NIGHT && currentNightMode != Configuration.UI_MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
        } else if (currentSetting == FOLLOW_SYSTEM) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}
