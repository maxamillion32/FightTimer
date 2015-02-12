package com.fighttimer.stambeto09.fighttimer;

import android.widget.TextView;

public interface ITimer {

    void start() throws InterruptedException;

    void pause();

    void stop();
}
