package com.fighttimer.stambeto09.fighttimer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


public class AssignActivity extends Activity implements View.OnClickListener {

    Spinner roundNumberSpinner;
    Spinner roundMinutesSpinner;
    Spinner roundSecondsSpinner;
    Spinner breakMinutesSpinner;
    Spinner breakSecondsSpinner;
    String[] secondsSpinnerAdapter;
    String[] roundValuesAdapter;
    String[] minutesValuesAdapter;
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign);
        secondsSpinnerAdapter = new String[] {
                "0", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"
        };

        roundValuesAdapter = new String[] {
                "0" ,"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
                "14", "15", "16", "17", "18", "19", "20"
        };

        roundNumberSpinner = (Spinner) findViewById(R.id.round_spinner);
        roundMinutesSpinner = (Spinner) findViewById(R.id.minutes_spinner);
        roundSecondsSpinner = (Spinner) findViewById(R.id.round_seconds_spinner);
        breakMinutesSpinner = (Spinner) findViewById(R.id.break_minutes_spinner);
        breakSecondsSpinner = (Spinner) findViewById(R.id.break_seconds_spinner);

        ArrayAdapter<String> secondsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                secondsSpinnerAdapter);
        ArrayAdapter<String> roundAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                roundValuesAdapter);
        roundNumberSpinner.setAdapter(roundAdapter);
        roundMinutesSpinner.setAdapter(roundAdapter);
        roundSecondsSpinner.setAdapter(secondsAdapter);
        breakMinutesSpinner.setAdapter(roundAdapter);
        breakSecondsSpinner.setAdapter(secondsAdapter);

        nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);

        // Parsing the values

        intent.putExtra("roundNumber", roundNumberSpinner.getSelectedItem().toString());
        intent.putExtra("roundMinutes", roundMinutesSpinner.getSelectedItem().toString());
        intent.putExtra("roundSeconds", roundSecondsSpinner.getSelectedItem().toString());
        intent.putExtra("breakMinutes", breakMinutesSpinner.getSelectedItem().toString());
        intent.putExtra("breakSeconds", breakSecondsSpinner.getSelectedItem().toString());

        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_assign, menu);
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
}
