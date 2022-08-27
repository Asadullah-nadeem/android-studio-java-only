package com.srtapps.chemicalelements.helper;

import android.content.Context;
import android.media.MediaPlayer;

import com.srtapps.chemicalelements.R;
import static com.srtapps.chemicalelements.helper.StaticStore.sharedPreferences;

import java.util.Timer;
import java.util.TimerTask;

public class SoundHelper {
    Context context;
    MediaPlayer correctSound, inCorrectSound, clickSound, musicPlayer;
    public SoundHelper(Context context, int musicCode) {
        this.context = context;
        correctSound = MediaPlayer.create(context, R.raw.correct);
        inCorrectSound = MediaPlayer.create(context, R.raw.incorrect);
        clickSound = MediaPlayer.create(context, R.raw.click);
        musicPlayer = MediaPlayer.create(context, musicCode);
        musicPlayer.setLooping(true);
    }

    public void playCorrectSound() {
        if (sharedPreferences.getBoolean("volume", true)) {
            if(correctSound.isPlaying() || correctSound != null) {
                correctSound.stop();
                correctSound.release();
            }
            correctSound = MediaPlayer.create(context, R.raw.correct);
            correctSound.setVolume(100, 100);
            correctSound.start();
        }
    }

    public void playInCorrectSound() {
        if (sharedPreferences.getBoolean("volume", true)) {
            if(inCorrectSound.isPlaying() || inCorrectSound != null) {
                inCorrectSound.stop();
                inCorrectSound.release();
            }
            inCorrectSound = MediaPlayer.create(context, R.raw.incorrect);
            inCorrectSound.setVolume(100, 100);
            inCorrectSound.start();
        }
    }

    public void playClickSound() {
        if (sharedPreferences.getBoolean("sound", true)) {
            if(clickSound.isPlaying() || clickSound != null) {
                clickSound.stop();
                clickSound.release();
            }
            clickSound = MediaPlayer.create(context, R.raw.click);
            clickSound.setVolume(100, 100);
            clickSound.start();
        }
    }

    float volume = 50;
    private void startFadeIn(){
        final int FADE_DURATION = 3000; //The duration of the fade
        //The amount of time between volume changes. The smaller this is, the smoother the fade
        final int FADE_INTERVAL = 250;
        final int MAX_VOLUME = 50; //The volume will increase from 0 to 1
        int numberOfSteps = FADE_DURATION/FADE_INTERVAL; //Calculate the number of fade steps
        //Calculate by how much the volume changes each step
        final float deltaVolume = MAX_VOLUME / (float)numberOfSteps;

        //Create a new Timer and Timer task to run the fading outside the main UI thread
        final Timer timer = new Timer(true);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                fadeInStep(deltaVolume); //Do a fade step
                //Cancel and Purge the Timer if the desired volume has been reached
                if(volume>=1f){
                    timer.cancel();
                    timer.purge();
                }
            }
        };

        timer.schedule(timerTask,FADE_INTERVAL,FADE_INTERVAL);
    }

    private void fadeInStep(float deltaVolume){
        musicPlayer.setVolume(volume, volume);
        volume += deltaVolume;

    }

    float volumeF = 0;
    public void startFadeOut(){

        // The duration of the fade
        final int FADE_DURATION = 3000;

        // The amount of time between volume changes. The smaller this is, the smoother the fade
        final int FADE_INTERVAL = 250;

        // Calculate the number of fade steps
        int numberOfSteps = FADE_DURATION / FADE_INTERVAL;

        // Calculate by how much the volume changes each step
        final float deltaVolume = volumeF / numberOfSteps;

        // Create a new Timer and Timer task to run the fading outside the main UI thread
        final Timer timer = new Timer(true);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                //Do a fade step
                fadeOutStep(deltaVolume);

                //Cancel and Purge the Timer if the desired volume has been reached
                if(volumeF <= 0){
                    timer.cancel();
                    timer.purge();
                    stopPlayer();
                }
            }
        };

        timer.schedule(timerTask,FADE_INTERVAL,FADE_INTERVAL);
    }

    private void fadeOutStep(float deltaVolume){
        musicPlayer.setVolume(volumeF, volumeF);
        volumeF -= deltaVolume;
    }

    // Release the player from memory
    private void stopPlayer() {

        if (musicPlayer != null) {
            musicPlayer.release();
            musicPlayer = null;
        }
    }

    public void pausePlayer() {
        if(musicPlayer.isPlaying() || musicPlayer != null) {
            musicPlayer.pause();
        }
    }

    public void resumePlayer() {
        if (sharedPreferences.getBoolean("music", true)) {
            if(!musicPlayer.isPlaying() || musicPlayer != null) {
                musicPlayer.start();
                startFadeIn();
            }
        }
    }
}
