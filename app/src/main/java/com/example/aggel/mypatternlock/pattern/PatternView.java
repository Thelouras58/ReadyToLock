package com.example.aggel.mypatternlock;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;

import java.util.List;


public class MainActivity extends AppCompatActivity {


    PatternLockView patternLockView;
    String final_pattern="";
    String save_pattern_key="pattern_code";
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        
        patternLockView = (PatternLockView) findViewById(R.id.pattern_lock_view);
        patternLockView.addPatternLockListener(new PatternLockViewListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(List<PatternLockView.Dot> progressPattern) {

            }

            @Override
            public void onComplete(List<PatternLockView.Dot> pattern) {
                final_pattern = PatternLockUtils.patternToString(patternLockView,pattern);

            }

            @Override
            public void onCleared() {

            }
        });
        Button btnSetup = (Button) findViewById(R.id.btnSetPattern);
        btnSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (final_pattern!="" && final_pattern!=null)
                {
                    dbHelper.insertPattern(final_pattern);
                    Toast.makeText(MainActivity.this,"Pattern " + final_pattern + " saved",Toast.LENGTH_SHORT).show();
                    patternLockView.clearPattern();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Please input a pattern first",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void showPatterns() //TODO make a view for patterns
    {
        Cursor cursor = dbHelper.getPatterns();
        if (cursor.moveToFirst())
        {
            Toast.makeText(MainActivity.this,"There are some patterns here!",Toast.LENGTH_SHORT).show();
            do
            {
                Log.d("patterns",cursor.getString(0));
            }while(cursor.moveToNext());
        }


    }
}
