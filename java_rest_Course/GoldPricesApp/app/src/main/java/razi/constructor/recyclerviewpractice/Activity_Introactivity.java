package razi.constructor.recyclerviewpractice;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;

import agency.tango.materialintroscreen.MaterialIntroActivity;
import agency.tango.materialintroscreen.SlideFragmentBuilder;
import agency.tango.materialintroscreen.animations.IViewTranslation;
import es.dmoral.prefs.Prefs;


public class Activity_Introactivity extends MaterialIntroActivity
{

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        enableLastSlideAlphaExitTransition(true);

        getNextButtonTranslationWrapper()
                .setEnterTranslation(new IViewTranslation() {
                    @Override
                    public void translate(View view, @FloatRange(from = 0, to = 1.0) float percentage) {
                        view.setAlpha(percentage);
                    }
                });

        addSlide(new SlideFragmentBuilder()
                        .backgroundColor(R.color.white)
                        .buttonsColor(R.color.colorPrimaryDark)
                        .image(R.drawable.intro_screen_one)
                        .title("Gold Prices")
                        .description("Check Latest Gold Rates!")
                        .build());


        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.white)
                .buttonsColor(R.color.colorPrimaryDark)
                .image(R.drawable.intro_usd_source)
                .title("Source Market Rate Currency : USD")
                .description("All gold rates converted from US Dollar")
                .build());


        addSlide(new SlideFragmentBuilder()
                        .backgroundColor(R.color.white)
                        .buttonsColor(R.color.colorPrimaryDark)
                         .image(R.drawable.intro_screen_two)
                        .title("Gold Rate / Tola")
                        .description("Check live rates with one click!")
                        .build());


        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.white)
                .buttonsColor(R.color.colorPrimaryDark)
                .image(R.drawable.intro_tick_mark)
                .title("That's It!")
                .description("Enjoy This Free Currency Converter")
                .build());

    }


    @Override
    public void onFinish() {
        super.onFinish();
        Prefs.with(Activity_Introactivity.this).writeInt("intro_key", 01);
    }
}
