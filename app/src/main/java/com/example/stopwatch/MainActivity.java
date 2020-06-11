package com.example.stopwatch;

import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private int mSeconds = 0;
    private boolean mIsRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState !=null) {
            mSeconds = savedInstanceState.getInt("seconds");
            mIsRunning = savedInstanceState.getBoolean("isRunning");
        }

        runTimer();
    }

    public void onStartClick(View view) {

        mIsRunning = true;
    }

    public void onStopClick(View view) {

        mIsRunning = false;
    }

    public void onResetClick(View view) {

        mIsRunning = false;
        mSeconds = 0;
    }

    private void runTimer(){
        final TextView timeTextView = (TextView) findViewById(R.id.textViewTime);

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = mSeconds / 3600;
                int minutes = (mSeconds % 3600) / 60;
                int seconds = mSeconds % 60;

                String time = String.format("%d,%02d,%02d",hours,minutes,seconds);
                timeTextView.setText(time);

                if (mIsRunning){
                    mSeconds++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("seconds",mSeconds);
        outState.putBoolean("isRunning",mIsRunning);
    }
}
