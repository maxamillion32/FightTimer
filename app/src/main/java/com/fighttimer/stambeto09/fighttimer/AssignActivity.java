package com.fighttimer.stambeto09.fighttimer;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayDeque;


public class AssignActivity extends Activity {

    Spinner roundNumberSpinner;
    Spinner roundMinutesSpinner;
    Spinner roundSecondsSpinner;
    Spinner breakMinutesSpinner;
    Spinner breakSecondsSpinner;
    String[] spinnerValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign);
        spinnerValues = new String[] {
                "0", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"
        };

        roundNumberSpinner = (Spinner) findViewById(R.id.round_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                spinnerValues);
        roundNumberSpinner.setAdapter(adapter);
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
