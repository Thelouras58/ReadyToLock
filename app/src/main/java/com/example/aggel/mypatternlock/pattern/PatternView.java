package com.example.aggel.mypatternlock.pattern;

import android.annotation.SuppressLint;
import android.os.SystemClock;
import android.support.annotation.RequiresPermission;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.example.aggel.mypatternlock.R;
import com.example.aggel.mypatternlock.io.ReadWriteUtils;
import com.example.aggel.mypatternlock.sensors.SensorsUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PatternView extends AppCompatActivity {


    PatternLockView patternLockView;
    int turn = 1;
    String final_pattern = "";
    String save_pattern_key = "pattern_code";
    ArrayList<String> listPoints = new ArrayList<>();
    ArrayList<String> listTimestamps = new ArrayList<>();
    ArrayList<String> listPressures = new ArrayList<>();
    ArrayList<String> listPatterns = new ArrayList<>();
    SensorsUtils s;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pattern_view);

        patternLockView = (PatternLockView) findViewById(R.id.pattern_lock_view);
        patternLockView.setTactileFeedbackEnabled(true);
        s = new SensorsUtils(this);

        patternLockView.addPatternLockListener(new PatternLockViewListener() {
            @Override
            public void onStarted() {
                Log.e(getClass().getName(), "Pattern drawing started");
                s.startListen();
            }


            @Override
            public void onProgress(List<PatternLockView.Dot> progressPattern) {
                Log.e(getClass().getName(), "Pattern progress: " +
                        PatternLockUtils.patternToString(patternLockView, progressPattern));
            }

            @Override
            public void onComplete(List<PatternLockView.Dot> pattern) {
                Log.e(getClass().getName(), "Pattern complete: " +
                        PatternLockUtils.patternToString(patternLockView, pattern));
                final_pattern = PatternLockUtils.patternToString(patternLockView, pattern);
                try {
                    s.stopListen(turn);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onCleared() {
                Log.d(getClass().getName(), "Pattern has been cleared");
            }
        });
        Button btnSetup = (Button) findViewById(R.id.btnSetPattern);
        btnSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (final_pattern != "" && final_pattern != null) {
                    Toast.makeText(PatternView.this, "Pattern " + final_pattern + " saved", Toast.LENGTH_SHORT).show();
                    listPatterns.add(final_pattern);
                    try {
                        ReadWriteUtils.writeCSV(listPoints, listPressures, listTimestamps, turn);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    turn++;
                    patternLockView.clearPattern();
                    listTimestamps.clear();
                    listPressures.clear();
                    listPoints.clear();
                } else {
                    Toast.makeText(PatternView.this, "Please input a pattern first", Toast.LENGTH_SHORT).show();
                }

                Log.e("LISTS", listPoints.size() + "_" + listPressures.size() + "_" + listTimestamps.size());
            }
        });

        patternLockView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                float pressure = event.getPressure();
                String timest = String.valueOf(SystemClock.elapsedRealtimeNanos());

                listPoints.add(x + ";" + y);
                listPressures.add(String.valueOf(pressure));
                listTimestamps.add(timest);

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                    case MotionEvent.ACTION_UP:
                }
                return false;
            }
        });


    }

    public Boolean check(String pat) {
        for (int i = 0; i < listTimestamps.size(); i++) {
            if (pat.equals(listTimestamps.get(i))) {
                return false;
            }
        }
        return true;

    }
}
