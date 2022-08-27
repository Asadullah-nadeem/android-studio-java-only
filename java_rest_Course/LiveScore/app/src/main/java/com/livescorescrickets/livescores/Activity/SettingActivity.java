package com.livescorescrickets.livescores.Activity;
import com.livescorescrickets.livescores.R;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.livescorescrickets.livescores.BottomSheet.NotificationsBottomSheetFragment;
import com.livescorescrickets.livescores.BottomSheet.ThemeBottomSheetFragment;

import soup.neumorphism.NeumorphCardView;

public class SettingActivity extends AppCompatActivity {
    NeumorphCardView cvAbout;
    NeumorphCardView cvInvite;
    NeumorphCardView cvNotification;
    NeumorphCardView cvPrivacy;
    NeumorphCardView cvRateUs;
    NeumorphCardView cvTerms;
    NeumorphCardView cvTheme;

    public void gotoSettings(View view) {
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_setting);

        this.cvNotification = (NeumorphCardView) findViewById(R.id.cvNotification);
        this.cvRateUs = (NeumorphCardView) findViewById(R.id.cvRateUs);
        this.cvTheme = (NeumorphCardView) findViewById(R.id.cvTheme);
        this.cvInvite = (NeumorphCardView) findViewById(R.id.cvInvite);
        this.cvAbout = (NeumorphCardView) findViewById(R.id.cvAbout);
        this.cvTerms = (NeumorphCardView) findViewById(R.id.cvTerms);
        this.cvPrivacy = (NeumorphCardView) findViewById(R.id.cvPrivacy);
        this.cvNotification.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                SettingActivity.this.showNotificationsBottomSheetFragment();
            }
        });
        this.cvAbout.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                new AlertDialog.Builder(SettingActivity.this, R.style.MyDialogTheme).setCancelable(true).setTitle("About Us").setMessage(SettingActivity.this.getString(R.string.about)).setPositiveButton("OK", new DialogInterface.OnClickListener() {


                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == -1) {
                            dialogInterface.dismiss();
                        }
                    }
                }).show();
            }
        });
        this.cvInvite.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.SUBJECT", "Uclicks");
                intent.putExtra("android.intent.extra.TEXT", "https://play.google.com/store/apps/details?id=com.livescorecricket.livescore");
                SettingActivity.this.startActivity(Intent.createChooser(intent, "Complete Action Choosing"));
            }
        });
        this.cvTerms.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                new AlertDialog.Builder(SettingActivity.this, R.style.MyDialogTheme).setCancelable(true).setTitle("Terms of Use").setMessage(SettingActivity.this.getString(R.string.terms_m)).setPositiveButton("OK", new DialogInterface.OnClickListener() {


                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == -1) {
                            dialogInterface.dismiss();
                        }
                    }
                }).show();
            }
        });
        this.cvPrivacy.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                new AlertDialog.Builder(SettingActivity.this, R.style.MyDialogTheme).setCancelable(true).setTitle("Privacy Policy").setMessage(SettingActivity.this.getString(R.string.privacy_m)).setPositiveButton("OK", new DialogInterface.OnClickListener() {


                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == -1) {
                            dialogInterface.dismiss();
                        }
                    }
                }).show();
            }
        });
        this.cvRateUs.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                try {
                    SettingActivity settingActivity = SettingActivity.this;
                    settingActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + SettingActivity.this.getPackageName())));
                } catch (ActivityNotFoundException unused) {
                    Toast.makeText(SettingActivity.this, "App is Not Present in Play Store, Will Be Added Soon", Toast.LENGTH_LONG).show();
                }
            }
        });
        this.cvTheme.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                SettingActivity.this.showThemeBottomSheetFragment();
            }
        });
    }

    public void showNotificationsBottomSheetFragment() {
        new NotificationsBottomSheetFragment().show(getSupportFragmentManager(), "NotificationsBottomSheetFragment");
    }

    public void showThemeBottomSheetFragment() {
        new ThemeBottomSheetFragment().show(getSupportFragmentManager(), "ThemeBottomSheetFragment");
    }

    public void gotoRanks(View view) {
        startActivity(new Intent(this, RankScreenActivity.class));
    }

    public void gotoMatches(View view) {
        startActivity(new Intent(this, MatchesActivity.class));
    }

    public void gotoNews(View view) {
        startActivity(new Intent(this, NewsActivity.class));
    }

    public void gotoHome(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
