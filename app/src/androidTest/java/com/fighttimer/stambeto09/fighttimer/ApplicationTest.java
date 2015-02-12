package com.fighttimer.stambeto09.fighttimer;

import android.app.Application;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.TextView;
import com.fighttimer.stambeto09.fighttimer.MainActivity;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ActivityUnitTestCase<MainActivity> {
    private MainActivity myActivity;
    TextView textView;

    protected void setUp() throws Exception{
        super.setUp();
        myActivity = getActivity();
    }
    public ApplicationTest() {
        super(MainActivity.class);
        Intent intent = new Intent(getInstrumentation().getTargetContext(), MainActivity.class);
        startActivity(intent, null, null);
        myActivity = getActivity();
    }

    public void testTextView(){
        int textViewID =  R.id.textView;
        assertNotNull(textViewID);
    }
}