package com.example.aggel.mypatternlock.statistics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aggel.mypatternlock.R;
import com.example.aggel.mypatternlock.io.ReadWriteUtils;
import com.example.aggel.mypatternlock.pattern.PatternUtils;
import com.example.aggel.mypatternlock.pattern.PatternView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StatisticsActivity extends AppCompatActivity {
    ArrayList<String> patterns;
    int longRuns, closedCurves, longCurves, longEdges, shortEdges, longOrthEdges, shortOrthEdges;
    TextView results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        init();

    }

    public void createMetadata(View view) throws IOException {
        ReadWriteUtils.writeMetaData(patterns);
        ReadWriteUtils.writeMetaData2(patterns);
        Toast.makeText(this, "DONE",Toast.LENGTH_SHORT).show();

    }

    public void statisticalAnalysis(View view) {
        for (int i = 0; i < patterns.size(); i++) {
            longRuns = longRuns + StatisticalAnalysisUtils.longRuns(patterns.get(i));
            closedCurves = closedCurves + StatisticalAnalysisUtils.closedCurves(patterns.get(i));
            longCurves = longCurves + StatisticalAnalysisUtils.longCurves(patterns.get(i));
            longEdges = longEdges + StatisticalAnalysisUtils.longEdge(patterns.get(i));
            shortEdges = shortEdges + StatisticalAnalysisUtils.shortEdge(patterns.get(i));
            longOrthEdges = longOrthEdges + StatisticalAnalysisUtils.longOrthEdge(patterns.get(i));
            shortOrthEdges = shortOrthEdges + StatisticalAnalysisUtils.shortOrthEdge(patterns.get(i));
        }

        Toast.makeText(this, "DONE", Toast.LENGTH_SHORT).show();
    }


    public void show(View view) {
        results.setText("LongRuns: " + longRuns + "\nClosedCurves: " + closedCurves + "\nLongCurves: " + longCurves + "\nLongEdes: " + longEdges + "\nShortEdges: " + shortEdges + "\nLongOrthEdges: " + longOrthEdges + "\nShortOrthEdges: " + shortOrthEdges + "\n----------------------------------------\n");

       // results.append("MaxPressure: " + ReadWriteUtils.getMaxPresOver() + "\nMinPressure: " + ReadWriteUtils.getMinPresOver() + "\nAverage Speed: " + PatternUtils.avg(ReadWriteUtils.getAvgSpeedOver())+" nanoseconds");
        results.setVisibility(View.VISIBLE);
    }

    public void init() {
        patterns = getIntent().getStringArrayListExtra("patternsList");
        this.longRuns = 0;
        this.closedCurves = 0;
        this.longCurves = 0;
        this.longEdges = 0;
        this.shortEdges = 0;
        this.longOrthEdges = 0;
        this.shortOrthEdges = 0;
        results = (TextView) findViewById(R.id.textView);
        results.setVisibility(View.INVISIBLE);
    }
}
