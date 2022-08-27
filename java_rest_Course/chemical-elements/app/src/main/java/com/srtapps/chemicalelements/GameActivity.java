package com.srtapps.chemicalelements;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.srtapps.chemicalelements.helper.MobileAdsHelper;
import com.srtapps.chemicalelements.helper.QuestionGenerator;
import com.srtapps.chemicalelements.helper.SoundHelper;
import com.srtapps.chemicalelements.model.CreateShape;
import com.srtapps.chemicalelements.model.CreateView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static com.srtapps.chemicalelements.helper.StaticStore.basicElements;
import static com.srtapps.chemicalelements.helper.StaticStore.listOfLists;
import static com.srtapps.chemicalelements.helper.StaticStore.mRewardedVideoAd;
import static com.srtapps.chemicalelements.helper.StaticStore.query;
import static com.srtapps.chemicalelements.helper.StaticStore.sharedPreferences;
import static com.srtapps.chemicalelements.helper.StaticStore.sophisticatedElements;

public class GameActivity extends AppCompatActivity implements RewardedVideoAdListener {

    int questionCounter = 0;
    int remainingTime;
    int life, counter = 0, score;
    int[] colorCodes = new int[4];
    boolean dragEnd, touchable = true, wrongAnswerBoolean = true, reward = false;
    Button[] buttons = new Button[4];
    Button continueButton, watchVideoButton;
    List<List<Object>> elementList;
    List<Object> prevQuestion = null;
    RelativeLayout topRelativeLayout, midRelativeLayout, botRelativeLayout;
    private CountDownTimer countDownTimer;
    TextView clockTextView, questionCounterTextView, generalScore, generalScoreText;
    TextView[] heartViews, emptyHeartViews;
    String trueAnswer;
    Drawable drawable;
    SoundHelper soundHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        MobileAdsHelper mobileAdsHelper = new MobileAdsHelper(this);
        mobileAdsHelper.MobileAdsCreator();
        mobileAdsHelper.loadRewardedVideoAdAd();
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        soundHelper = new SoundHelper(this, R.raw.game);

        remainingTime = getResources().getInteger(R.integer.game_time);
        life = getResources().getInteger(R.integer.life);

        elementList = new ArrayList<>();

        for(int i = 0; i < 4; i++) {
            colorCodes[i] = R.color.gameButtonType1;
        }


        layouts();


        int elementType = sharedPreferences.getInt("element_type", 0);

        if (elementType == 0) {
            elementList = basicElements;
        }
        else if (elementType == 1) {
            elementList = sophisticatedElements;
        }
        else elementList = listOfLists;

        gameTypeSetter(true);
    }

    private void gameTypeSetter(boolean setter) {
        int gameType = sharedPreferences.getInt("game_type", 0);


        if (setter) {
            String s = Integer.toString(sharedPreferences.getInt("general_score", 0));
            generalScore = CreateView.generalScoreView(this, "", (int) getResources()
                    .getDimension(R.dimen.twenty_five_dp));
            generalScoreText = CreateView.generalScoreView(this, s, RelativeLayout
                    .LayoutParams.WRAP_CONTENT);
            LinearLayout linearLayout = new LinearLayout(this);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            int margin = (int) getResources().getDimension(R.dimen.ten_dp);
            params.setMargins(margin, margin, margin, margin);
            if(gameType != 2) {
                params.addRule(RelativeLayout.ALIGN_PARENT_END);
                heartViews = CreateView.heartView(this, life,
                        (int) getResources().getDimension(R.dimen.twenty_five_dp), R
                                .drawable.heart);
                emptyHeartViews = CreateView.heartView(this, life,
                        (int) getResources().getDimension(R.dimen.twenty_five_dp), R
                                .drawable.heart_empty);
                for (int i = 0 ; i < heartViews.length; i++) {
                    topRelativeLayout.addView(heartViews[i]);
                    topRelativeLayout.addView(emptyHeartViews[i]);
                }
                linearLayout.addView(generalScoreText);
                linearLayout.addView(generalScore);
            }
            else {
                clockTextView = CreateView.clockView(this);
                topRelativeLayout.addView(clockTextView);
                linearLayout.addView(generalScore);
                linearLayout.addView(generalScoreText);
            }
            linearLayout.setLayoutParams(params);
            topRelativeLayout.addView(linearLayout);
        }


        if (gameType == 0) {
            multipleChoiceGameSetup();
            score = getResources().getInteger(R.integer.true_answer_score_mc);
            query = "mc_score";
        }
        else if (gameType == 1) {
            quizGameSetup();
            score = getResources().getInteger(R.integer.true_answer_score_qg);
            query = "qg_score";
        }
        else if (gameType == 2) {
            timeGameSetup();
            score = getResources().getInteger(R.integer.true_answer_score_tg);
            query = "tg_score";
        }
        else {
            atomicNumbersGameSetup();
            score = getResources().getInteger(R.integer.true_answer_score_an);
            query = "an_score";
        }


    }

    @SuppressLint("DefaultLocale")
    private void layouts() {
        topRelativeLayout = findViewById(R.id.gameTopRelativeLayout);
        midRelativeLayout = findViewById(R.id.gameMiddleRelativeLayout);
        botRelativeLayout = findViewById(R.id.gameBottomRelativeLayout);
        questionCounterTextView = findViewById(R.id.question_counter);
        questionCounterTextView.setText(String.format("#%d", questionCounter + 1));
    }

    private void atomicNumbersGameSetup() {
        List<List<Object>> question = QuestionGenerator.questionMaker(elementList.size(), elementList, prevQuestion);
        prevQuestion = question.get(3);
        TextView textView = CreateView.questionView(this,
                Integer.toString((int) question.get(3).get(1)),
                getResources().getColor(getResources().getIdentifier((String) question.get(3)
                        .get(2), "color", getPackageName())),

                (int) getResources().getDimension(R.dimen.hundred_dp), (int) getResources().getDimension(R.dimen.hundred_dp));


        trueAnswer = (String) question.get(3).get(4);
        midRelativeLayout.addView(textView);

        Collections.shuffle(question);

        GridLayout gridLayout = CreateView.multipleQuestionGridLayout(this,
                question, buttons, colorCodes, false);

        botRelativeLayout.addView(gridLayout);

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);

        midRelativeLayout.setAnimation(anim);
        botRelativeLayout.setAnimation(anim);
        anim.start();
        final String finalTrueAnswer = trueAnswer;
        buttonClickListener(finalTrueAnswer);

    }

    private void timeGameSetup() {
        multipleChoiceGameSetup();
    }

    @SuppressLint("DefaultLocale")
    private void updateView() {
        wrongAnswerBoolean = true;
        questionCounterTextView.setText(String.format("#%d", questionCounter + 1));
        generalScoreText.setText(String.format("%d", sharedPreferences.getInt("general_score", 0)));

    }

    public void multipleChoiceGameSetup() {
        List<List<Object>> question = QuestionGenerator.questionMaker(elementList.size(), elementList, prevQuestion);
        prevQuestion = question.get(3);

        if (questionCounter % 2 == 0) {
            TextView textView = CreateView.questionView(this,
                    (String) question.get(3).get(4),
                    getResources().getColor(getResources().getIdentifier((String) question.get(3)
                            .get(2), "color", getPackageName())),

                    (int) getResources().getDimension(R.dimen.hundred_dp), (int) getResources().getDimension(R.dimen.hundred_dp));
            trueAnswer = (String) question.get(3).get(3);
            midRelativeLayout.addView(textView);

            Collections.shuffle(question);

            GridLayout gridLayout = CreateView.multipleQuestionGridLayout(this,
                    question, buttons, colorCodes, true);

            botRelativeLayout.addView(gridLayout);
        }
        else {
            TextView textView = CreateView.questionView(this,
                    (String) question.get(3).get(3),
                    getResources().getColor(getResources().getIdentifier((String) question.get(3)
                            .get(2), "color", getPackageName())),
                     Resources.getSystem().getDisplayMetrics().widthPixels / 2,
                    (int) getResources().getDimension(R.dimen.hundred_dp));
            trueAnswer = (String) question.get(3).get(4);
            midRelativeLayout.addView(textView);

            Collections.shuffle(question);

            GridLayout gridLayout = CreateView.multipleQuestionGridLayout(this, question,
                    buttons, colorCodes, false);

            botRelativeLayout.addView(gridLayout);
        }

        animation();
        final String finalTrueAnswer = trueAnswer;
        buttonClickListener(finalTrueAnswer);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void quizGameSetup() {
        int random = new Random().nextInt(elementList.size());
        List<Object> question = elementList.get(random);

        while (question == prevQuestion) {
            random = new Random().nextInt(elementList.size());
            question = elementList.get(random);
        }
        prevQuestion = question;

        TextView textView = CreateView.questionView(this,
                (String) question.get(4),
                getResources().getColor(getResources().getIdentifier((String) question.get(2),
                        "color", getPackageName())),
                (int) getResources().getDimension(R.dimen.hundred_dp), (int) getResources().getDimension(R.dimen.hundred_dp));
        midRelativeLayout.addView(textView);

        TextView[] textViewsDrag = new TextView[((String) question.get(3)).length()];
        TextView[] textViewsTouch = new TextView[((String) question.get(3)).length()];

        String answer = (String) question.get(3);
        answer = answer.toUpperCase();
        answer = answer.replaceAll("İ", "I");
        final List<String> answerList = new ArrayList<>();

        for(char ch: answer.toCharArray()) {
            answerList.add(String.valueOf(ch));
        }

        GridLayout gridLayoutDrag = CreateView.quizGameGridLayout(this, answerList,
                textViewsDrag, true, getResources().getColor(getResources()
                        .getIdentifier((String) question.get(2), "color",
                                getPackageName())));

        Collections.shuffle(answerList);

        GridLayout gridLayoutTouch = CreateView.quizGameGridLayout(this, answerList,
                textViewsTouch, false, getResources().getColor(getResources()
                        .getIdentifier((String) question.get(2), "color",
                                getPackageName())));

        botRelativeLayout.addView(gridLayoutDrag);
        botRelativeLayout.addView(gridLayoutTouch);

        animation();


        View.OnDragListener onDragListener = new View.OnDragListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onDrag(View v, DragEvent dragEvent) {
                View view = (View) dragEvent.getLocalState();
                switch (dragEvent.getAction()) {
                    case DragEvent.ACTION_DROP:
                        //TRUE ANSWER
                        if(((TextView) v).getText().equals(((TextView) view).getText())) {
                            soundHelper.playCorrectSound();
                            v.setBackground(view.getBackground());
                            ((TextView) v).setText(((TextView) view).getText());
                            ((TextView) v).setTextColor(getResources().getColor(
                                    R.color.colorBlack));
                            v.setEnabled(false);
                            dragEnd = false;
                            counter++;
                            touchable = true;
                            if(counter == answerList.size()) {
                                if(wrongAnswerBoolean) sharedPreferences.edit().putInt("general_score",
                                        sharedPreferences.getInt("general_score", 0) + score).apply();

                                wrongAnswerBoolean = true;
                                Handler handler = new Handler(Looper.getMainLooper());
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        botRelativeLayout.removeAllViews();
                                        midRelativeLayout.removeAllViews();
                                        quizGameSetup();
                                        counter = 0;
                                        questionCounter++;
                                        updateView();
                                    }
                                }, 800);

                            }
                        }
                        //Wrong answer
                        else {
                            soundHelper.playInCorrectSound();
                            wrongAnswerBoolean = false;
                            life--;
                            topRelativeLayout.removeView(heartViews[life]);
                            if (life <= 0) {
                                openGameOverDialog();
                            }
                        }
                        return true;

                    case DragEvent.ACTION_DRAG_ENDED:
                        if(dragEnd) {
                            view.setVisibility(View.VISIBLE);
                            touchable = true;
                        }
                        return true;

                    case DragEvent.ACTION_DRAG_STARTED:
                        dragEnd = true;
                        return true;
                }
                return true;
            }
        };

        View.OnTouchListener onTouchListener = new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (touchable) {
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                    v.startDrag(data, shadowBuilder, v, 0);
                    v.setVisibility(View.INVISIBLE);
                    touchable = false;
                }
                return true;
            }

        };

        for(int i = 0; i < answerList.size(); i++) {
            textViewsDrag[i].setOnDragListener(onDragListener);
            textViewsTouch[i].setOnTouchListener(onTouchListener);
        }


    }

    private void animation() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);

        midRelativeLayout.setAnimation(anim);
        botRelativeLayout.setAnimation(anim);
        anim.start();
    }

    private void openGameOverDialog() {
        final Dialog alertDialog = new Dialog(this);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(R.layout.dialog_layout);
        Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));

        RelativeLayout adsRoot = alertDialog.findViewById(R.id.adsRoot);

        drawable = ContextCompat.getDrawable(this, R.drawable.general_score_icon);
        String s = getResources().getString(R.string.continue_button);

        continueButton = CreateView.alertDialogButtonCreator(this, String.format(s,
                getResources().getInteger(R.integer.continue_score)),
                R.color.continue_button, drawable);
        if(sharedPreferences.getInt("general_score", 0) < 200) {

            continueButton = CreateView.alertDialogButtonCreator(this, String.format(s,
                    getResources().getInteger(R.integer.continue_score)),
                    R.color.colorGrey, drawable);
            continueButton.setEnabled(false);
        }

        s = getResources().getString(R.string.watch_video_button);
        watchVideoButton = CreateView.alertDialogButtonCreator(this, String.format(s,
                getResources().getInteger(R.integer.reward_general_score)),
                R.color.watch_video_button, drawable);
        if(!mRewardedVideoAd.isLoaded()) {
            watchVideoButton = CreateView.alertDialogButtonCreator(this, String.format(s,
                    getResources().getInteger(R.integer.reward_general_score)),
                    R.color.colorGrey, drawable);
            watchVideoButton.setEnabled(false);
        }

        Button tryAgainButton = CreateView.alertDialogButtonCreator(this, getResources()
                .getString(R.string.try_again_button), R.color.try_again_button);
        Button mainMenuButton = CreateView.alertDialogButtonCreator(this, getResources()
                .getString(R.string.main_menu_button), R.color.main_menu_button);


        LinearLayout linearLayout = new LinearLayout(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        linearLayout.setLayoutParams(params);
        linearLayout.addView(continueButton);
        linearLayout.addView(watchVideoButton);
        linearLayout.addView(tryAgainButton);
        linearLayout.addView(mainMenuButton);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        adsRoot.addView(linearLayout);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                soundHelper.playClickSound();
                alertDialog.cancel();
                alertDialog.dismiss();
                sharedPreferences.edit().putInt("general_score", sharedPreferences.getInt
                        ("general_score", 0) - getResources().getInteger(
                                R.integer.continue_score)).apply();
                generalScoreText.setText(Integer.toString(sharedPreferences.getInt(
                        "general_score", 0)));
                resumeGame();
            }
        });

        watchVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundHelper.playClickSound();
                if (mRewardedVideoAd.isLoaded()) {
                    mRewardedVideoAd.show();
                }
            }
        });

        tryAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundHelper.playClickSound();
                alertDialog.cancel();
                alertDialog.dismiss();
                startActivity(new Intent(getApplicationContext(), GameOptionActivity.class));
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        mainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundHelper.playClickSound();
                alertDialog.cancel();
                alertDialog.dismiss();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        alertDialog.show();
    }

    private void buttonClickListener(final String finalTrueAnswer) {
        for (int i = 0; i < 4; i++) {
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (((Button) view).getText().equals(finalTrueAnswer)) {
                        //True Answer
                        soundHelper.playCorrectSound();
                        for(Button button: buttons) {
                            button.setEnabled(false);
                        }
                        view.setBackground(CreateShape.shapeCreator(getResources()
                                        .getColor(R.color.colorGreen),
                                10,
                                5,
                                getResources().getColor(R.color.colorBlack)));
                        questionCounter++;
                        if(wrongAnswerBoolean) sharedPreferences.edit().putInt("general_score",
                                sharedPreferences.getInt("general_score", 0) + score).apply();


                        wrongAnswerBoolean = true;
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                midRelativeLayout.removeAllViews();
                                botRelativeLayout.removeAllViews();
                                updateView();
                                gameTypeSetter(false);
                            }
                        },800);
                    }
                    else {
                        //Wrong Answer
                        view.setEnabled(false);
                        soundHelper.playInCorrectSound();
                        view.setBackground(CreateShape.shapeCreator(getResources()
                                        .getColor(R.color.colorRed),
                                10,
                                5,
                                getResources().getColor(R.color.colorBlack)));
                        wrongAnswerBoolean = false;
                        if(sharedPreferences.getInt("game_type", 0) != 2) {
                            life--;
                            topRelativeLayout.removeView(heartViews[life]);
                            if(life <= 0) {
                                for(int j = 0; j < 4; j++) {
                                    buttons[j].setEnabled(false);
                                }
                                openGameOverDialog();
                            }
                        }
                    }
                }
            });
        }
    }

    public void startCountDownTimer(int time_) {

        countDownTimer = new CountDownTimer(time_ * 1000 + 100, 1000){

            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                remainingTime = (int) (millisUntilFinished / 1000);
                clockTextView.setText(millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                openGameOverDialog();
            }
        }.start();
    }

    public void resumeGame() {
        for(int i = 0; i < 4; i++) {
            if (sharedPreferences.getInt("game_type", 0) != 1) {
                buttons[i].setEnabled(true);
            }
        }
        if(sharedPreferences.getInt("game_type", 0) == 2) {
            startCountDownTimer(getResources().getInteger(R.integer.reward_time));
        }
        else {
            life = getResources().getInteger(R.integer.reward_life);
            heartViews = CreateView.heartView(this, life,
                    (int) getResources().getDimension(R.dimen.twenty_five_dp), R
                            .drawable.heart);
            for (TextView heartView : heartViews) {
                topRelativeLayout.addView(heartView);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sharedPreferences.getInt("game_type", 0) == 2) countDownTimer.cancel();
        soundHelper.pausePlayer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sharedPreferences.getInt("game_type", 0) == 2) {
            if (remainingTime != 0) startCountDownTimer(remainingTime);
        }
        soundHelper.resumePlayer();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        soundHelper.playClickSound();
        if (sharedPreferences.getInt("game_type", 0) == 2) countDownTimer.cancel();
        startActivity(new Intent(this, GameOptionActivity.class));
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundHelper.startFadeOut();
        soundHelper = null;
    }

    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onRewardedVideoAdClosed() {
        if(reward) {
            sharedPreferences.edit().putInt("general_score", sharedPreferences.getInt(
                    "general_score", 0) + getResources().getInteger(
                            R.integer.reward_general_score)).apply();
            generalScoreText.setText(Integer.toString(sharedPreferences.getInt(
                    "general_score", 0)));
            if(sharedPreferences.getInt("general_score", 0) >= getResources().getInteger(R.integer.continue_score) ){
                CreateView.updateView(this, continueButton, R.color.continue_button);
                continueButton.setEnabled(true);
            }
            reward = false;
        }
        CreateView.updateView(this, watchVideoButton, R.color.colorGrey);
        watchVideoButton.setEnabled(false);
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        reward = true;
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }

    @Override
    public void onRewardedVideoCompleted() {

    }
}