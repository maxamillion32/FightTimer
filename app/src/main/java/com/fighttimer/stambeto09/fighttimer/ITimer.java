package com.fighttimer.stambeto09.fighttimer;

import android.widget.TextView;

public interface ITimer {

    void start(TextView textView);

    void pause();

    void stop(TextView textView);
}
