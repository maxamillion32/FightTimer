package com.fighttimer.stambeto09.fighttimer;
import android.os.Handler;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Timer implements ITimer {

    // Fields
    private boolean isRunning = false;
    public boolean isFinished = false;
    private boolean isTriggered = true;
    protected int milliseconds = 0;
    protected int seconds = 0;
    protected int minutes = 0;
    private Handler handler = new Handler();
    protected int roundMaxMinutes = Integer.MAX_VALUE;
    protected int roundMaxSeconds = Integer.MAX_VALUE;
    TextView textView;

    // Constructor
    public Timer(int maxRoundSeconds, int maxRoundMinutes, final TextView textView){
        if (maxRoundSeconds == 0 && maxRoundMinutes == 0){
            roundMaxMinutes = Integer.MAX_VALUE;
            roundMaxSeconds = Integer.MAX_VALUE;
        } else {
            this.roundMaxMinutes = maxRoundMinutes;
            this.roundMaxSeconds = maxRoundSeconds;
        }
        this.textView = textView;
    }

    // Getters
    public int getMilliseconds() {
        return this.milliseconds;

    }

    public int getSeconds() {
        return this.seconds;
    }

    public int getMinutes() {
        return this.minutes;
    }

    public boolean getIsRunning(){ return this.isRunning; }

    // Setters
    public void setMilliseconds(int amountInMilliseconds) {
        this.milliseconds += amountInMilliseconds;
    }

    public void setSeconds(int amountInSeconds) {
        this.seconds += amountInSeconds;
    }

    public void setMinutes(int amountInMinutes) {
        this.minutes += amountInMinutes;
    }

    public void setIsRunning(boolean running){
        this.isRunning = running;
    }


    @Override
    public void start() {
        isRunning = true;
        if (isTriggered) {
            isTriggered = false;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    while (isRunning) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                milliseconds++;
                                if (milliseconds >= 100) {
                                    milliseconds = 0;
                                    seconds++;
                                    textView.setText(printResult());
                                    if (seconds == 60) {
                                        seconds = 0;
                                        minutes++;
                                    }
                                }

                                if (minutes == roundMaxMinutes && seconds == roundMaxSeconds) {
                                    stop();
                                    isFinished = true;
                                    isRunning = false;
                                    resetTimer();
                                }
                            }
                        });
                    }
                }
            };
            new Thread(runnable).start();
        }
    }

    @Override
    public void pause() {
        isRunning = false;
        isTriggered = true;
    }

    @Override
    public void stop() {
        isRunning = false;
        isTriggered = true;
        minutes = 0;
        seconds = 0;
        milliseconds = 0;
        resetTimer();
        textView.setText(printResult());
    }

    public String printResult(){
        return String.format("%02d:%02d", minutes, seconds);
    }

    private void resetTimer(){
        this.seconds = 0;
        this.milliseconds = 0;
        this.minutes = 0;
    }
}
