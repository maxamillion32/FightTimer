package com.fighttimer.stambeto09.fighttimer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

// TODO: Fix the bug with landscape mode
// TODO: Add an icon
// TODO: Design the buttons
// TODO: Add in setting menu common kick-box, muay thai and kids modes
// TODO: Create a new acitivity and pass the timer
// TODO: Add a font for the numbers
// TODO: Add a vibration and sound between the at the start and end of each round
// TODO: Add an option to stop the vibrator or mute the sound
// TODO: Change the theme to Holo Dark
// TODO: Add icon on notification bar when app is running
public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    TextView textView;
    TextView roundView;
    Button startButton;
    Button pauseButton;
    Button stopButton;
    FightTimer fightTimer;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        roundView = (TextView) findViewById(R.id.roundView);
        startButton = (Button) findViewById(R.id.button);
        pauseButton = (Button) findViewById(R.id.button2);
        stopButton = (Button) findViewById(R.id.button3);
        // Minutes, seconds, minutes, seconds
        fightTimer = new FightTimer(0, 20, 0, 10, 8, textView, roundView);
        timer = new Timer(5, 0, textView);
        startButton.setOnClickListener(this);
        pauseButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        finish();

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
