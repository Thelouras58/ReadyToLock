package com.example.aggel.mypatternlock.statistics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.aggel.mypatternlock.R;
import com.example.aggel.mypatternlock.io.ReadWriteUtils;

import java.io.IOException;
import java.util.ArrayList;

public class StatisticsActivity extends AppCompatActivity {
    ArrayList patterns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        patterns = getIntent().getStringArrayListExtra("patternsList");
        for (int i = 0; i < patterns.size(); i++) {
            Log.e("PATPAT", (String) patterns.get(i));
        }
    }

    public void createMetadata(View view) throws IOException {
        ReadWriteUtils.writeMetaData(patterns);
    }

    public void statisticalAnalysis(View view) {
    }

    public void show(View view) {
    }
}
