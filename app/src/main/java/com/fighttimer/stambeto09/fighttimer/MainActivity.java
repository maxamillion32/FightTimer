package com.fighttimer.stambeto09.fighttimer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

// TODO: Create a new acitivity and pass the parameters with shared preferences
// TODO: Add a vibration and sound between the at the start and end of each round
// TODO: Add an option to stop the vibrator or mute the sound
// TODO: Add icon on notification bar when app is running
// TODO: Decouple ITimer from exceptions

public class MainActivity extends Activity implements View.OnClickListener {

    TextView textView;
    TextView roundView;
    Button startButton;
    Button pauseButton;
    Button stopButton;
    FightTimer fightTimer;
    Timer timer;
    Typeface aftonFont;
    Typeface lobsterFont;
    Typeface redOctober;

    boolean isVibrating = true;
    boolean isMuted = false;
    Vibrator vibrator;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        roundView = (TextView) findViewById(R.id.roundView);
        startButton = (Button) findViewById(R.id.button);
        pauseButton = (Button) findViewById(R.id.button2);
        stopButton = (Button) findViewById(R.id.button3);

        // Shared Preferences
        //SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        //int numberOfRounds = prefs.getInt("round-number", 0);
        //int roundMinutes = prefs.getInt("round-minutes", 0);
        //int roundSeconds = prefs.getInt("round-seconds", 0);
        //int breakSeconds = prefs.getInt("break-seconds", 0);
        //int breakMinutes = prefs.getInt("break-minutes", 0);

        // Minutes, seconds, minutes, seconds
        // fightTimer = new FightTimer(0, 3, 0, 2, 3, textView, roundView);

        Bundle extras = getIntent().getExtras();
        int roundNumber = Integer.parseInt(extras.getString("roundNumber"));
        int roundMinutes = Integer.parseInt(extras.getString("roundMinutes"));
        int roundSeconds = Integer.parseInt(extras.getString("roundSeconds"));
        int breakMinutes = Integer.parseInt(extras.getString("breakMinutes"));
        int breakSeconds = Integer.parseInt(extras.getString("breakSeconds"));
        fightTimer = new FightTimer(roundMinutes, roundSeconds,
                breakMinutes, breakSeconds,
                roundNumber, textView, roundView);
        // timer = new Timer(5, 0, textView);
        startButton.setOnClickListener(this);
        pauseButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);

        //lobsterFont = Typeface.createFromAsset(getAssets(), "fonts/american_captain.otf");
        aftonFont = Typeface.createFromAsset(getAssets(), "fonts/afton_font.ttf");
        lobsterFont = Typeface.createFromAsset(getAssets(), "fonts/lobster_font.otf");
        redOctober = Typeface.createFromAsset(getAssets(), "fonts/red_october.ttf");
        roundView.setTypeface(redOctober);
        textView.setTypeface(redOctober);
        pauseButton.setTypeface(redOctober);
        startButton.setTypeface(redOctober);
        stopButton.setTypeface(redOctober);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Are you sure you want to quit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface arg0, int arg1){
                        finish();
                    }
                }).create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        // Logic for keeping screen on
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.sound:
                if (!isMuted){
                    isMuted = true;
                }
                break;
            case R.id.vibration:
                if (isVibrating){
                    isVibrating = false;
                } else {
                    isVibrating = true;
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                fightTimer.start();
                checkForVibration();
                break;
            case R.id.button2:
                fightTimer.pause();
                checkForVibration();
                break;
            case R.id.button3:
                fightTimer.stop();
                checkForVibration();
                break;
        }
    }

    private void checkForVibration() {
        if (isVibrating){
            vibrator.vibrate(500);
        }
    }
}
