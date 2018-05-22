package com.example.aggel.mypatternlock.pattern;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.RequiresPermission;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.example.aggel.mypatternlock.MainActivity;
import com.example.aggel.mypatternlock.R;
import com.example.aggel.mypatternlock.io.ReadWriteUtils;
import com.example.aggel.mypatternlock.sensors.SensorsUtils;
import com.example.aggel.mypatternlock.statistics.StatisticsActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PatternView extends AppCompatActivity {

    //variables declaration
    private PatternLockView patternLockView;
    private int turn = 0;
    private String final_pattern = "";
    private String save_pattern_key = "pattern_code";
    private ArrayList<String> listPoints = new ArrayList<>();
    private ArrayList<Integer> listPointsActivator = new ArrayList<>();
    private ArrayList<String> listTimestamps = new ArrayList<>();
    private ArrayList<String> listPressures = new ArrayList<>();
    private ArrayList<String> listPatterns = new ArrayList<>();
    private ArrayList<Integer> temp = new ArrayList<>();
    private SensorsUtils s;
    private Button mButton;
    private Button btnSetup;
    private TextView text;
    private int counter;
    private static Context cont;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //inits
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pattern_view);
        mButton = (Button) findViewById(R.id.checkButton);
        mButton.setVisibility(View.INVISIBLE);
        patternLockView = (PatternLockView) findViewById(R.id.pattern_lock_view);
        btnSetup = (Button) findViewById(R.id.btnSetPattern);
        text = (TextView) findViewById(R.id.text);
        text.setVisibility(View.INVISIBLE);
        patternLockView.setTactileFeedbackEnabled(true);
        temp.add(100);
        temp.add(100);
        cont = getApplicationContext();
        
        
        s = new SensorsUtils(this);
        //begin the eavesdropping
        setListeners();

    }

    public void setListeners() {
        //custom listener of the custom view
        patternLockView.addPatternLockListener(new PatternLockViewListener() {
            @Override
            public void onStarted() {  //user start to draw a pattern
                Log.e(getClass().getName(), "Pattern drawing started");
                s.startListen();//sensors start listening
            }


            @Override
            public void onProgress(List<PatternLockView.Dot> progressPattern) {
                //user is on progress of drawing a pattern
                Log.e(getClass().getName(), "Pattern progress: " +
                        PatternLockUtils.patternToString(patternLockView, progressPattern));

            }

            @Override
            public void onComplete(List<PatternLockView.Dot> pattern) {
                //user just finish the pattern
                Log.e(getClass().getName(), "Pattern complete: " +
                        PatternLockUtils.patternToString(patternLockView, pattern));

                final_pattern = PatternLockUtils.patternToString(patternLockView, pattern);


                try {
                    //close the sensors to check and write the data in the raw files
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

        //listener of the submit pattern button
        btnSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check if the pattern t length is >=4 and if is not empty
                if (final_pattern != "" && final_pattern != null && final_pattern.length() >= 4) {

                    //check for duplicate patterns 
                    if ((check(final_pattern) && turn < 10) || (check(final_pattern) && turn > 12 && check2(final_pattern))) {

                        Toast.makeText(PatternView.this, "Pattern " + final_pattern + " saved" + "_turn: " + turn, Toast.LENGTH_SHORT).show();


                        listPatterns.add(final_pattern);
                        try {
                            //all checks passed , so write the raw data file
                            ReadWriteUtils.writeCSV(listPoints, listPressures, listTimestamps, listPointsActivator, turn);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //reinit for the next round
                        turn++;
                        clearList();
                        //change to check mode
                        if ((turn == 10) || (turn == 22)) {
                            btnSetup.setVisibility(View.INVISIBLE);
                            mButton.setVisibility(View.VISIBLE);
                            text.setVisibility(View.VISIBLE);

                        }
                    } else {
                        Toast.makeText(PatternView.this, "DUPLICATE PATTERN", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(PatternView.this, "Something went wrong , try again ...", Toast.LENGTH_SHORT).show();
                }


            }
        });

        patternLockView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent event) {
                //get the point tha the finger touches
                int x = (int) event.getX();
                int y = (int) event.getY();
                //calculate the nearest button of the point
                PatternUtils.calculatePoint(x, y);
                //get the pressure of the finger
                float pressure = event.getPressure();
                //το timestamp
                String timest = String.valueOf(SystemClock.elapsedRealtimeNanos());
                //save the previous data in lists
                listPoints.add(x + ";" + y);
                listPressures.add(String.valueOf(pressure));
                listTimestamps.add(timest);
                listPointsActivator.add(PatternUtils.calculatePoint(x, y));
             
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                    case MotionEvent.ACTION_UP:
                }
                return false;
            }
        });


    }

    //check if there is a duplicate pattern 
    public Boolean check(String pat) {
        if (turn < 13) {//ifor the first round

            for (int i = 0; i < listPatterns.size(); i++) {

                if (pat.equals(listPatterns.get(i))) {

                    return false;
                }
                return true;

            }


        } else {//for the second
            for (int i = 13; i < listPatterns.size(); i++) {
                if (pat.equals(listPatterns.get(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    //check for duplicated between the two rounds
    public Boolean check2(String pat) {

        for (int i = 0; i < 10; i++) {
            if (pat.equals(listPatterns.get(i))) {
                counter++;
            }
            if (counter == 2) {
                counter = 1;
                return false;

            }
        }
        return true;
    }

    //check the pattern in the check mode
    public Boolean check3(String pat) {
        if (turn < 13) {

            for (int i = 0; i < 10; i++) {

                if (pat.equals(listPatterns.get(i))) {

                    if (turn == 11) {
                        if (pat.equals(listPatterns.get(10))) {
                            return true;
                        }
                    } else if (turn == 12) {
                        if (pat.equals(listPatterns.get(10)) || pat.equals(listPatterns.get(11))) {
                            return true;
                        }
                    }
                    return false;
                }


            }
            return true;
        } else {
            for (int i = 13; i < 23; i++) {

                if (pat.equals(listPatterns.get(i))) {

                    if (turn == 24) {
                        if (pat.equals(listPatterns.get(23))) {
                            return true;
                        }
                    } else if (turn == 25) {
                        if (pat.equals(listPatterns.get(23)) || pat.equals(listPatterns.get(24))) {
                            return true;
                        }
                    }
                    return false;
                }


            }
        }
        return true;
    }

    //onClick method for the button in check mode
    public void box3(View view) throws IOException {

        if (!check3(final_pattern)) {

            Toast.makeText(PatternView.this, "Correct", Toast.LENGTH_SHORT).show();

            listPatterns.add(final_pattern);
            try {
                ReadWriteUtils.writeCSV(listPoints, listPressures, listTimestamps, listPointsActivator, turn);
            } catch (IOException e) {
                e.printStackTrace();
            }
            clearList();
            turn++;

            //change mode
            if (turn == 13) {
                btnSetup.setVisibility(View.VISIBLE);
                mButton.setVisibility(View.INVISIBLE);
                text.setVisibility(View.INVISIBLE);
                text.setVisibility(View.INVISIBLE);
                clearList();

                counter = 0;
            } else if (turn == 25) {
                // end of the procedure
                //change activity
                //send the pattern
                Intent c = new Intent(this, StatisticsActivity.class);
                c.putStringArrayListExtra("patternsList", this.listPatterns);
                startActivity(c);
            }
        } else {
            Toast.makeText(PatternView.this, "Better luck next time.Start Over!!", Toast.LENGTH_SHORT).show();
            resetRound();
        }


    }

    public ArrayList<String> getListPatterns() {
        return listPatterns;
    }

    public static Context getCont() {
        return cont;
    }

    public void clearList() {
        patternLockView.clearPattern();
        listTimestamps.clear();
        listPressures.clear();
        listPoints.clear();
        listPointsActivator.clear();
    }

    public void resetRound() throws IOException {
        ReadWriteUtils.deleteDir();
        ReadWriteUtils.makeDir(MainActivity.getUser().getUsername());
        Intent c2 = new Intent(this, PatternView.class);
        startActivity(c2);
    }

}


