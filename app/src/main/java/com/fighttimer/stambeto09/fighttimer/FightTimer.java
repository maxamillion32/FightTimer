package com.fighttimer.stambeto09.fighttimer;

import android.os.Handler;
import android.widget.TextView;

public class FightTimer implements ITimer {
    // TODO: Fix the bug with multiple start clicking
    // TODO: Fix the bug when the timer is finish, to initialize again

    public boolean isRunning = false;
    public boolean isFinished = false;
    Timer breakTimer;
    Timer fightTimer;
    Timer currentTimer;
    TextView textView;
    TextView roundView;
    Handler handler = new Handler();

    private int roundMinutes = 0;
    private int roundSeconds = 0;
    private int restMinutes = 0;
    private int restSeconds = 0;

    public int maxRoundNumber = 0;
    public int roundNumber = 0;

    public FightTimer(int roundMinutes, int roundSeconds,
                      int restMinutes, int restSeconds, int roundNumber,
                      final TextView textView, final TextView roundView){
        this.textView = textView;
        this.roundView = roundView;
        this.roundMinutes = roundMinutes;
        this.roundSeconds = roundSeconds;
        this.restMinutes = restMinutes;
        this.restSeconds = restSeconds;
        this.maxRoundNumber = roundNumber;

        fightTimer = new Timer(roundSeconds, roundMinutes, textView);
        breakTimer = new Timer(restSeconds, restMinutes, textView);
        currentTimer = fightTimer;
    }

    @Override
    public void start(){
        isRunning = true;
        isFinished = false;
        roundView.setText(String.format("%d/%d", roundNumber + 1, maxRoundNumber));
        currentTimer.start();

        Runnable brunnable = new Runnable() {
            @Override
            public void run() {
                while (isRunning && !isFinished){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            decideStrategy();
                        }
                    });
                }
            }
        };
        new Thread(brunnable).start();
    }

    @Override
    public void pause() {
        isRunning = false;
        currentTimer.pause();
    }

    @Override
    public void stop() {
        isRunning = false;
        isFinished = true;
        currentTimer.stop();
    }
    // TODO: Extract set text to method
    private void decideStrategy(){
        if (currentTimer.isFinished && currentTimer == fightTimer && roundNumber != maxRoundNumber - 1){
            roundNumber++;
            roundView.setText(String.format("%d/%d", roundNumber + 1, maxRoundNumber));
            currentTimer.stop();
            currentTimer = breakTimer;
            breakTimer.isFinished = false;
            currentTimer.start();
        } else if (currentTimer.isFinished && currentTimer == breakTimer) {
            roundView.setText(String.format("%d/%d", roundNumber + 1, maxRoundNumber));
            currentTimer.stop();
            currentTimer = fightTimer;
            fightTimer.isFinished = false;
            currentTimer.start();
        } else if (roundNumber == maxRoundNumber){
            this.isRunning = false;
            isFinished = true;
        }
    }
}
