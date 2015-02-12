package com.fighttimer.stambeto09.fighttimer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    TextView textView;
    Timer myTimer;
    Button startButton;
    Button pauseButton;
    Button stopButton;
    FightTimer fightTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        startButton = (Button) findViewById(R.id.button);
        pauseButton = (Button) findViewById(R.id.button2);
        stopButton = (Button) findViewById(R.id.button3);
        // Minutes, seconds, minutes, seconds
        fightTimer = new FightTimer(0, 5, 0, 3, 10, textView);

        // myTimer = new Timer(0, 0, textView);
        startButton.setOnClickListener(this);
        pauseButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                fightTimer.start();
                break;
            case R.id.button2:
                fightTimer.pause();
                break;
            case R.id.button3:
                fightTimer.stop();
                break;
        }
    }
}
