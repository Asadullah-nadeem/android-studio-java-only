package com.srtapps.chemicalelements.model;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.srtapps.chemicalelements.R;
import com.srtapps.chemicalelements.helper.SoundHelper;

import static com.srtapps.chemicalelements.helper.StaticStore.sharedPreferences;
import static com.srtapps.chemicalelements.helper.StaticStore.textSize;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
    multipleChoiceGameSetup and atomicNumbersGameSetup using same functions
    srtApps
 **/
public class CreateView {

    public static void gameOptionButtonCreator(Context c, RelativeLayout rootRelativeLayout,
                                               Button[] buttons, int[] colorCodes,
                                               ArrayList<String> buttonText) {
        GridLayout gridLayout = gridLayoutCreator(c);


        for (int i = 0; i < 4; i++) {
            String[] arrOfStr = buttonText.get(i).split("/", 2);


            LinearLayout linearLayout = new LinearLayout(c);

            linearLayout.setOrientation(LinearLayout.VERTICAL);

            TextView textView = new TextView(c);
            textView.setGravity(Gravity.CENTER);
            String s = arrOfStr[0];
            textView.setText(s);
            textView.setTextColor(c.getResources().getColor(R.color.colorBlack));

            buttons[i] = new Button(c);
            buttons[i].setGravity(Gravity.CENTER);
            RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(
                    (int) c.getResources().getDimension(R.dimen.ninety_dp),
                    (int) c.getResources().getDimension(R.dimen.hundred_dp));
            params1.setMargins(0,
                    (int) c.getResources().getDimension(R.dimen.ten_dp),
                    0,
                    0);
            buttons[i].setTextSize(textSize);
            String s1 = arrOfStr[1];
            buttons[i].setText(s1);
            buttons[i].setAllCaps(false);
            buttons[i].setLayoutParams(params1);
            linearLayout.addView(textView);
            linearLayout.addView(buttons[i]);
            gridLayout.addView(linearLayout);
        }
        buttonBackgroundSet(c, buttons, colorCodes);
        rootRelativeLayout.addView(gridLayout);


    }

    public static void mainButtonCreator(Context c, RelativeLayout rootRelativeLayout,
                                         Button[] buttons, int[] colorCodes,
                                         ArrayList<String> buttonText) {
        GridLayout gridLayout = gridLayoutCreator(c);
        for (int i = 0; i < 4; i++) {
            buttons[i] = new Button(c);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    (int) c.getResources().getDimension(R.dimen.hundred_twenty_dp),
                    (int) c.getResources().getDimension(R.dimen.hundred_fifty_dp));
            params.setMargins((int) c.getResources().getDimension(R.dimen.ten_dp),
                    (int) c.getResources().getDimension(R.dimen.ten_dp),
                    (int) c.getResources().getDimension(R.dimen.ten_dp),
                    (int) c.getResources().getDimension(R.dimen.ten_dp));

            buttons[i].setLayoutParams(params);
            buttons[i].setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);
            buttons[i].setAllCaps(false);

            buttons[i].setTextSize(textSize);
            String s = buttonText.get(i).replaceAll("/", "\n");
            SpannableString spannableString =  new SpannableString(s);
            spannableString.setSpan(new RelativeSizeSpan(1.5f),
                    s.length() - 3 ,
                    s.length(),
                    0); // set size
            buttons[i].setText(spannableString);
            gridLayout.addView(buttons[i]);
        }
        buttonBackgroundSet(c, buttons, colorCodes);

        rootRelativeLayout.addView(gridLayout);

    }

    private static void buttonBackgroundSet(Context c, Button[] buttons, int[] colorCodes) {
        for(int i = 0 ; i < 4; i++) {
            GradientDrawable statePressed = CreateShape.shapeCreator(c
                            .getResources()
                            .getColor(R.color.colorLightBlack),
                    10,
                    0,
                    0);
            GradientDrawable stateNormal = CreateShape.shapeCreator(c
                            .getResources()
                            .getColor(colorCodes[i]),
                    10,
                    5,
                    c.getResources().getColor(R.color.colorBlack));

            LayerDrawable layerDrawable = CreateShape.layerListCreator(stateNormal, statePressed);

            StateListDrawable stateListDrawable = CreateShape.stateListCreator(layerDrawable, stateNormal);

            buttons[i].setBackground(stateListDrawable);
        }
    }

    private static GridLayout gridLayoutCreator(Context c) {
        GridLayout gridLayout = new GridLayout(c);
        gridLayout.setColumnCount(2);
        gridLayout.setRowCount(2);
        gridLayout.setUseDefaultMargins(true);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        gridLayout.setLayoutParams(params);
        return gridLayout;
    }

    public static TextView questionView(Context c, String s, int colorCode,
                                         int layoutParam, int layoutParam1) {
        TextView textView = new TextView(c);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                layoutParam,
                layoutParam1);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        textView.setLayoutParams(params);
        textView.setAllCaps(false);
        textView.setTextSize(textSize);
        textView.setTextColor(c.getResources().getColor(R.color.colorBlack));
        textView.setText(s);
        textView.setGravity(Gravity.CENTER);
        textView.setBackground(CreateShape.shapeCreator(colorCode,
                10,
                5,
                c.getResources().getColor(R.color.colorBlack)));


        return textView;
    }

    public static GridLayout multipleQuestionGridLayout(Context c, List<List<Object>> answers,
                                                        Button[] buttons,
                                                        int[] colorCode, boolean questionType) {
        GridLayout gridLayout = gridLayoutCreator(c);

        for(int i = 0; i < 4; i++) {
            buttons[i] = new Button(c);
            buttons[i].setAllCaps(false);
            if (questionType) {
                buttons[i].setText((String) answers.get(i).get(3));
            }
            else buttons[i].setText((String) answers.get(i).get(4));
            buttons[i].setGravity(Gravity.CENTER);
            buttons[i].setTextColor(c.getResources().getColor(R.color.colorBlack));
            buttons[i].setTextSize(textSize);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.height = (Resources.getSystem().getDisplayMetrics().widthPixels / 3);
            params.width = (Resources.getSystem().getDisplayMetrics().widthPixels / 3);
            buttons[i].setLayoutParams(params);
            gridLayout.addView(buttons[i]);
        }
        buttonBackgroundSet(c, buttons, colorCode);

        return gridLayout;
    }


    public static GridLayout quizGameGridLayout(Context c, List<String> atomName, TextView[] textViews,
                                                boolean dragOrTouch, int colorCode) {
        GridLayout gridLayout = new GridLayout(c);
        gridLayout.setRowCount(1);
        for(int i = 0; i < atomName.size(); i++) {
            textViews[i] = quizGameTextViews(c, String.valueOf(atomName.get(i)), dragOrTouch,
                    colorCode, atomName.size());
            gridLayout.addView(textViews[i]);
        }
        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        gridLayout.setUseDefaultMargins(true);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        if (dragOrTouch) {
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);
            params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        }
        gridLayout.setLayoutParams(params);
        return gridLayout;
    }

    private static TextView quizGameTextViews(Context c, String s, boolean dragOrTouch,
                                              int colorCode, int size) {
        TextView textView = new TextView(c);
        textView.setText(s);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(c.getResources().getColor(R.color.colorBlack));
        textView.setTextSize(textSize);

        if (dragOrTouch) {
            textView.setTextColor(c.getResources().getColor(R.color.colorTransparent));
            textView.setBackground(CreateShape.shapeCreator(c.getResources()
                            .getColor(R.color.colorTransparent),
                    10,
                    5,
                    c.getResources().getColor(R.color.colorBlack)));
        }
        else {
            textView.setBackground(CreateShape.shapeCreator(colorCode,
                    10,
                    5,
                    c.getResources().getColor(R.color.colorBlack)));
        }
        final GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.height = (Resources.getSystem().getDisplayMetrics().widthPixels / (size * 2));
        params.width = (Resources.getSystem().getDisplayMetrics().widthPixels / (size * 2));

        if(size >= 6 && size < 9) textView.setTextSize(textSize);
        else if(size >= 9) textView.setTextSize(textSize);

        textView.setLayoutParams(params);
        return textView;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public static TextView clockView(Context c) {
        TextView clock = new TextView(c);
        clock.setBackground(c.getResources().getDrawable(R.drawable.clock));
        clock.setTextSize(textSize);
        clock.setGravity(Gravity.CENTER);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int)
                c.getResources().getDimension(R.dimen.sixty_dp) ,
                (int) c.getResources().getDimension(R.dimen.sixty_dp));
        params.addRule(RelativeLayout.ALIGN_PARENT_END);
        int margin = (int) c.getResources().getDimension(R.dimen.ten_dp);
        params.setMargins(0, margin, margin, 0);
        clock.setLayoutParams(params);
        return clock;
    }

    public static TextView[] heartView(Context c, int size, int viewSize, int drawable) {
        TextView[] textViews = new TextView[size];
        int prev = 0;
        for (int i = 0; i < size; i++) {
            textViews[i] = new TextView(c);
            textViews[i].setBackgroundResource(drawable);
            int current = prev + 1;
            textViews[i].setId(current);
            final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(viewSize, viewSize);
            params.setMargins((int) c.getResources().getDimension(R.dimen.five_dp),
                    (int) c.getResources().getDimension(R.dimen.ten_dp),
                    0,
                    (int) c.getResources().getDimension(R.dimen.five_dp));
            params.addRule(RelativeLayout.END_OF, prev);
            prev = current;
            textViews[i].setLayoutParams(params);
        }
        return textViews;
    }

    public static TextView generalScoreView(Context c, String s, int widthHeight) {
        TextView textView = new TextView(c);
        if(!s.equals("")) textView.setText(s);
        else textView.setBackgroundResource(R.drawable.game_score);
        textView.setTextSize(textSize);
        textView.setTextColor(c.getResources().getColor(R.color.colorBlack));
        textView.setGravity(Gravity.CENTER);
        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(widthHeight,
                widthHeight);
        params.setMargins((int) c.getResources().getDimension(R.dimen.five_dp),
                (int) c.getResources().getDimension(R.dimen.five_dp),
                (int) c.getResources().getDimension(R.dimen.five_dp),
                (int) c.getResources().getDimension(R.dimen.five_dp));
        params.addRule(Gravity.CENTER);
        textView.setLayoutParams(params);

        return textView;
    }

    public static void alertDialogButtonView(Context c, Button button, int colorCode, Drawable icon) {
        GradientDrawable statePressed = CreateShape.shapeCreator(c.getResources()
                        .getColor(R.color.colorLightBlack),
                10,
                5,
                c.getResources().getColor(R.color.colorBlack));

        GradientDrawable stateNormal = CreateShape.shapeCreator(c.getResources()
                        .getColor(colorCode),
                10,
                5,
                c.getResources().getColor(R.color.colorBlack));

        LayerDrawable layerDrawable = CreateShape.layerListCreator(stateNormal, icon);

        LayerDrawable layerDrawable1 = CreateShape.layerListCreator(stateNormal, statePressed);

        StateListDrawable stateListDrawable = CreateShape.stateListCreator(layerDrawable1,
                layerDrawable);

        button.setBackground(stateListDrawable);
    }

    public static void alertDialogButtonView(Context c, Button button, int colorCode) {

        GradientDrawable statePressed = CreateShape.shapeCreator(c
                        .getResources()
                        .getColor(R.color.colorLightBlack),
                10,
                10,
                5);
        GradientDrawable stateNormal = CreateShape.shapeCreator(c
                        .getResources()
                        .getColor(colorCode),
                10,
                5,
                c.getResources().getColor(R.color.colorBlack));

        LayerDrawable layerDrawable = CreateShape.layerListCreator(stateNormal, statePressed);

        StateListDrawable stateListDrawable = CreateShape.stateListCreator(layerDrawable, stateNormal);
        button.setBackground(stateListDrawable);

    }

    public static Button alertDialogButtonCreator(Context c, String text, int colorCode) {
        Button button = new Button(c);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
                ((int) c.getResources().getDimension(R.dimen.two_hundred_twenty_dp),
                        (int) c.getResources().getDimension(R.dimen.eighty_dp));
        params.setMargins(10, 10, 10, 10);
        button.setLayoutParams(params);
        button.setText(text);
        button.setGravity(Gravity.CENTER);
        button.setTextSize(textSize);
        button.setAllCaps(false);
        button.setTextColor(c.getResources().getColor(R.color.colorWhite));

        CreateView.alertDialogButtonView(c, button, colorCode);
        return button;
    }

    public static Button alertDialogButtonCreator(Context c, String text, int colorCode, Drawable drawable) {
        Button button = new Button(c);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
                ((int) c.getResources().getDimension(R.dimen.two_hundred_twenty_dp),
                        (int) c.getResources().getDimension(R.dimen.eighty_dp));
        params.setMargins(10, 10, 10, 10);
        button.setLayoutParams(params);
        button.setText(text);
        button.setGravity(Gravity.CENTER);
        button.setTextSize(textSize);
        button.setAllCaps(false);
        button.setTextColor(c.getResources().getColor(R.color.colorWhite));

        CreateView.alertDialogButtonView(c, button, colorCode, drawable);
        return button;
    }

    public static void updateView(Context c, Button button, int colorCode) {
        Drawable drawable = ContextCompat.getDrawable(c, R.drawable.general_score_icon);
        CreateView.alertDialogButtonView(c, button, colorCode, drawable);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public static Dialog createSoundAlertDialog(final Context c, final SoundHelper soundHelper) {
        final Dialog alertDialog = new Dialog(c);
        alertDialog.setCancelable(true);
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(R.layout.sound_option);
        Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));

        final Button musicView = alertDialog.findViewById(R.id.musicView);
        final Button soundView = alertDialog.findViewById(R.id.soundView);

        if(sharedPreferences.getBoolean("music", true)) musicView.setBackground(c.getResources().getDrawable(R.drawable.music_on));
        else musicView.setBackground(c.getResources().getDrawable(R.drawable.music_off));

        if(sharedPreferences.getBoolean("sound", true)) soundView.setBackground(c.getResources().getDrawable(R.drawable.volume_on));
        else soundView.setBackground(c.getResources().getDrawable(R.drawable.volume_off));

        musicView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundHelper.playClickSound();
                if(sharedPreferences.getBoolean("music", true)) {
                    musicView.setBackground(c.getResources().getDrawable(R.drawable.music_off));
                    soundHelper.pausePlayer();
                    sharedPreferences.edit().putBoolean("music", false).apply();
                }
                else {
                    sharedPreferences.edit().putBoolean("music", true).apply();
                    musicView.setBackground(c.getResources().getDrawable(R.drawable.music_on));
                    soundHelper.resumePlayer();
                }
            }
        });

        soundView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sharedPreferences.getBoolean("sound", true)) {
                    soundView.setBackground(c.getResources().getDrawable(R.drawable.volume_off));
                    sharedPreferences.edit().putBoolean("sound", false).apply();
                }
                else {
                    soundView.setBackground(c.getResources().getDrawable(R.drawable.volume_on));
                    sharedPreferences.edit().putBoolean("sound", true).apply();
                    soundHelper.playClickSound();
                }
            }
        });
        return alertDialog;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public static Button optionButtonCreator(Context c, final SoundHelper soundHelper) {
        Button option = new Button(c);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) c.getResources()
                .getDimension(R.dimen.thirty_dp), (int) c.getResources().getDimension
                (R.dimen.thirty_dp));
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.setMargins(25,25,25,25);
        option.setLayoutParams(params);
        option.setBackground(c.getResources().getDrawable(R.drawable.option));
        final Dialog dialog = createSoundAlertDialog(c, soundHelper);
        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                soundHelper.playClickSound();
            }
        });
        return option;
    }
}
