package com.fighttimer.stambeto09.fighttimer;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayDeque;


public class AssignActivity extends Activity implements View.OnClickListener {

    Spinner roundNumberSpinner;
    Spinner roundMinutesSpinner;
    Spinner roundSecondsSpinner;
    Spinner breakMinutesSpinner;
    Spinner breakSecondsSpinner;
    String[] spinnerValues;
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign);
        spinnerValues = new String[] {
                "0", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"
        };

        roundNumberSpinner = (Spinner) findViewById(R.id.round_spinner);
        roundMinutesSpinner = (Spinner) findViewById(R.id.minutes_spinner);
        roundSecondsSpinner = (Spinner) findViewById(R.id.round_seconds_spinner);
        breakMinutesSpinner = (Spinner) findViewById(R.id.break_minutes_spinner);
        breakSecondsSpinner = (Spinner) findViewById(R.id.break_seconds_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                spinnerValues);
        roundNumberSpinner.setAdapter(adapter);
        roundMinutesSpinner.setAdapter(adapter);
        roundSecondsSpinner.setAdapter(adapter);
        breakMinutesSpinner.setAdapter(adapter);
        breakSecondsSpinner.setAdapter(adapter);

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
