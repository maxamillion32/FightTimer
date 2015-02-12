package com.fighttimer.stambeto09.fighttimer;

import android.widget.TextView;

public class FightTimer implements ITimer {

    public boolean isRunning = false;
    public boolean isFinished = false;
    Timer breakTimer;
    Timer fightTimer;
    Timer currentTimer;
    TextView textView;

    private int roundMinutes = 0;
    private int roundSeconds = 0;
    private int restMinutes = 0;
    private int restSeconds = 0;

    public FightTimer(int roundMinutes, int roundSeconds,
                      int restMinutes, int restSeconds, final TextView textView){
        this.textView = textView;
        this.roundMinutes = roundMinutes;
        this.roundSeconds = roundSeconds;
        this.restMinutes = restMinutes;
        this.restSeconds = restSeconds;

        fightTimer = new Timer(roundSeconds, roundMinutes, textView);
        breakTimer = new Timer(restSeconds, restMinutes, textView);
        currentTimer = fightTimer;
    }

    @Override
    public void start(){
        isRunning = true;
        fightTimer.start();

    }

    @Override
    public void pause() {
        isRunning = false;
        fightTimer.pause();
    }

    @Override
    public void stop() {
        isRunning = false;
        fightTimer.stop();
    }
}
