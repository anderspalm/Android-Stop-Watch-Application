package com.anders.stopwatch;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.LoginFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Chronometer;

public class StopWatchActivity extends AppCompatActivity implements View.OnClickListener {

    Button mSWB;
    Chronometer mCmeter;
    Boolean mRunning;
    private long mTimePassed;
    private static final String TAG = "StopWatchActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mCmeter = (Chronometer) findViewById(R.id.chron_meter);
        mCmeter.setText("00.00");
//        mCmeter.getOnChronometerTickListener(new ONn)

        mCmeter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.i(TAG, "onTextChanged: " + mCmeter.getText());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mRunning = false;
        mTimePassed = 0;
//        mSWB = (Button) findViewById(R.id.stop_watch_start);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.stop_watch_start:
                mCmeter.setBase(SystemClock.elapsedRealtime() + mTimePassed);
                mCmeter.start();
                break;
            case R.id.stop_watch_pause:
                mTimePassed = mCmeter.getBase() - SystemClock.elapsedRealtime();
                mCmeter.setText(mCmeter.getText());
                mCmeter.stop();
                Log.i(TAG, "onClick: paused");
                break;
            case R.id.stop_watch_stop:
                mTimePassed = 0;
                mCmeter.setText("00.00");
                mCmeter.stop();
                Log.i(TAG, "onClick: Stop");
                break;
            default:
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stop_watch, menu);
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
