package com.fighttimer.stambeto09.fighttimer;
import android.os.Handler;
import android.widget.TextView;

public class Timer implements ITimer {

    // Fields
    private boolean isRunning = false;
    private boolean isFinished = false;
    protected int milliseconds = 0;
    protected int seconds = 0;
    protected int minutes = 0;
    private Handler handler = new Handler();
    protected int roundMaxMinutes = Integer.MAX_VALUE;
    protected int roundMaxSeconds = Integer.MAX_VALUE;

    // Constructor
    public Timer() {

    }

    public Timer(int maxRoundSeconds, int maxRoundMinutes){
        if (maxRoundSeconds == 0 && maxRoundMinutes == 0){
            roundMaxMinutes = Integer.MAX_VALUE;
            roundMaxSeconds = Integer.MAX_VALUE;
        } else {
            this.roundMaxMinutes = maxRoundMinutes;
            this.roundMaxSeconds = maxRoundSeconds;
        }
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
    public void start(final TextView textView) {
        isRunning = true;
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

                            if (minutes == roundMaxMinutes && seconds == roundMaxSeconds){
                                stop(textView);
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

    @Override
    public void pause() {
        isRunning = false;
    }

    @Override
    public void stop(TextView textView) {
        isRunning = false;
        minutes = 0;
        seconds = 0;
        milliseconds = 0;
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
