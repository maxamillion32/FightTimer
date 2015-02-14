package com.fighttimer.stambeto09.fighttimer;

import android.os.Handler;
import android.widget.TextView;

public class FightTimer implements ITimer {

    public boolean isRunning = false;
    public boolean isFinished = false;
    private boolean isTriggered = true;
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
    public int roundNumber = 1;

    Thread thread;

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
        if (isTriggered) {
            isTriggered = false;
            isFinished = false;
            roundView.setText(String.format("%d/%d", roundNumber, maxRoundNumber));
            currentTimer.start();
            Runnable brunnable = new Runnable() {
                @Override
                public void run() {
                    while (isRunning && !isFinished) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                decideStrategy();
                            }
                        });
                    }
                }
            };

            thread = new Thread(brunnable);
            thread.start();
        }
    }

    @Override
    public void pause() {
        isRunning = false;
        isTriggered = true;
        currentTimer.pause();

    }

    @Override
    public void stop() {
        currentTimer.stop();
        resetCurrentTimer();
        roundView.setText(String.format("0/%d", maxRoundNumber));
    }
    // TODO: Extract set text to method
    private void decideStrategy(){
        if (currentTimer.isFinished && currentTimer == fightTimer && roundNumber != maxRoundNumber + 1){
            roundNumber++;
            currentTimer.stop();
            currentTimer = breakTimer;
            breakTimer.isFinished = false;
            currentTimer.start();
        } else if (currentTimer.isFinished && currentTimer == breakTimer && roundNumber != maxRoundNumber + 1) {
            currentTimer.stop();
            currentTimer = fightTimer;
            fightTimer.isFinished = false;
            roundView.setText(String.format("%d/%d", roundNumber, maxRoundNumber));
            currentTimer.start();
        } else if (roundNumber == maxRoundNumber + 1){
            resetCurrentTimer();
        }
    }

    private void resetCurrentTimer(){
        this.isRunning = false;
        isFinished = true;
        fightTimer = new Timer(roundSeconds, roundMinutes, textView);
        breakTimer = new Timer(restSeconds, restMinutes, textView);
        roundNumber = 1;
        currentTimer = fightTimer;
        thread.interrupt();
        isTriggered = true;
    }

}
