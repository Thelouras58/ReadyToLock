package com.example.aggel.mypatternlock.pattern;

import android.annotation.SuppressLint;
import android.os.SystemClock;
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

import java.util.ArrayList;
import java.util.List;


public class PatternView extends AppCompatActivity {


    PatternLockView patternLockView;
    String final_pattern = "";
    String save_pattern_key = "pattern_code";
    ArrayList<String> list = new ArrayList<>();


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pattern_view);

        patternLockView = (PatternLockView) findViewById(R.id.pattern_lock_view);
        patternLockView.setTactileFeedbackEnabled(true);


        patternLockView.addPatternLockListener(new PatternLockViewListener() {
            @Override
            public void onStarted() {
                Log.e(getClass().getName(), "Pattern drawing started");
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
                    patternLockView.clearPattern();
                } else {
                    Toast.makeText(PatternView.this, "Please input a pattern first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        patternLockView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                float pressure = event.getPressure();
                Log.e("TOUCH",x+"///"+y+".."+ SystemClock.elapsedRealtimeNanos()+"//////////"+pressure);
                  // list.add(x+"_"+y+"__"+SystemClock.elapsedRealtimeNanos());
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                    case MotionEvent.ACTION_UP:
                }
                return false;
            }
        });;

    }


}
