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
import java.util.List;

public class StatisticsActivity extends AppCompatActivity {
    ArrayList patterns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        patterns = getIntent().getStringArrayListExtra("patternsList");

    }

    public void createMetadata(View view) throws IOException {
        ReadWriteUtils.writeMetaData(patterns);
        ReadWriteUtils.writeMetaData2(patterns);


    }

    public void statisticalAnalysis(View view) {

    }

    public static int distance(int x, int y)
    {
        if (x>y)
        {
            return x-y;
        }
        return y-x;
    }

    public static int longRuns(String pattern)
    {
        char[] pat = pattern.toCharArray();
        int counter=0;
        for (int i=0;i<pat.length-2;i++)
        {
            if ((distance(Character.getNumericValue(pat[i]),Character.getNumericValue(pat[i+1]))==3 && distance(Character.getNumericValue(pat[i+1]),Character.getNumericValue(pat[i+2]))==3) || (distance(Character.getNumericValue(pat[i]),Character.getNumericValue(pat[i+1]))==1 && distance(Character.getNumericValue(pat[i+1]),Character.getNumericValue(pat[i+2]))==1) || (distance(Character.getNumericValue(pat[i]),Character.getNumericValue(pat[i+1]))==4 && distance(Character.getNumericValue(pat[i+1]),Character.getNumericValue(pat[i+2]))==4) )
            {
                counter++;
            }
        }
        return counter;
    }

    public static int closedCurves(String pattern)
    {
        char[] pat = pattern.toCharArray();
        int counter=0;
        for (int i=0;i<pat.length-3;i++)
        {
            if (((distance(Character.getNumericValue(pat[i]),Character.getNumericValue(pat[i+1]))==1 ) && (distance(Character.getNumericValue(pat[i+1]),Character.getNumericValue(pat[i+2]))==3) && (distance(Character.getNumericValue(pat[i+2]),Character.getNumericValue(pat[i+3]))==1))  || ((distance(Character.getNumericValue(pat[i]),Character.getNumericValue(pat[i+1]))==3 ) && (distance(Character.getNumericValue(pat[i+1]),Character.getNumericValue(pat[i+2]))==1) && (distance(Character.getNumericValue(pat[i+2]),Character.getNumericValue(pat[i+3]))==3)) )
            {
                counter++;
            }

        }
        return counter;
    }

    public static int longCurves(String pattern)
    {
        char[] pat = pattern.toCharArray();
        int counter=0;
        for (int i=0;i<pat.length-5;i++)
        {
            if (((distance(Character.getNumericValue(pat[i]),Character.getNumericValue(pat[i+1]))==1 ) && (distance(Character.getNumericValue(pat[i+1]),Character.getNumericValue(pat[i+2]))==1) && (distance(Character.getNumericValue(pat[i+2]),Character.getNumericValue(pat[i+3]))==3)  && (distance(Character.getNumericValue(pat[i+3]),Character.getNumericValue(pat[i+4]))==1) && (distance(Character.getNumericValue(pat[i+4]),Character.getNumericValue(pat[i+5]))==1)) || ((distance(Character.getNumericValue(pat[i]),Character.getNumericValue(pat[i+1]))==3 ) && (distance(Character.getNumericValue(pat[i+1]),Character.getNumericValue(pat[i+2]))==3) && (distance(Character.getNumericValue(pat[i+2]),Character.getNumericValue(pat[i+3]))==1)  && (distance(Character.getNumericValue(pat[i+3]),Character.getNumericValue(pat[i+4]))==3) && (distance(Character.getNumericValue(pat[i+4]),Character.getNumericValue(pat[i+5]))==3)) )
            {
                counter++;
            }

        }
        return counter;
    }

    public static int longEdge(String pattern)
    {
        char[] pat = pattern.toCharArray();
        int counter=0;
        for (int i=0;i<pat.length-4;i++)
        {
            Log.e("longcheck", "new");
            if (((distance(Character.getNumericValue(pat[i]),Character.getNumericValue(pat[i+1]))==3 ) && (distance(Character.getNumericValue(pat[i+1]),Character.getNumericValue(pat[i+2]))==3) && (distance(Character.getNumericValue(pat[i+2]),Character.getNumericValue(pat[i+3]))==4)  && (distance(Character.getNumericValue(pat[i+3]),Character.getNumericValue(pat[i+4]))==4)) ||   ((distance(Character.getNumericValue(pat[i]),Character.getNumericValue(pat[i+1]))==4 ) && (distance(Character.getNumericValue(pat[i+1]),Character.getNumericValue(pat[i+2]))==4) && (distance(Character.getNumericValue(pat[i+2]),Character.getNumericValue(pat[i+3]))==3)  && (distance(Character.getNumericValue(pat[i+3]),Character.getNumericValue(pat[i+4]))==3)) || ((distance(Character.getNumericValue(pat[i]),Character.getNumericValue(pat[i+1]))==1 ) && (distance(Character.getNumericValue(pat[i+1]),Character.getNumericValue(pat[i+2]))==1) && (distance(Character.getNumericValue(pat[i+2]),Character.getNumericValue(pat[i+3]))==4)  && (distance(Character.getNumericValue(pat[i+3]),Character.getNumericValue(pat[i+4]))==4)) || ((distance(Character.getNumericValue(pat[i]),Character.getNumericValue(pat[i+1]))==4 ) && (distance(Character.getNumericValue(pat[i+1]),Character.getNumericValue(pat[i+2]))==4) && (distance(Character.getNumericValue(pat[i+2]),Character.getNumericValue(pat[i+3]))==1)  && (distance(Character.getNumericValue(pat[i+3]),Character.getNumericValue(pat[i+4]))==1)) || ((distance(Character.getNumericValue(pat[i]),Character.getNumericValue(pat[i+1]))==3 ) && (distance(Character.getNumericValue(pat[i+1]),Character.getNumericValue(pat[i+2]))==3) && (distance(Character.getNumericValue(pat[i+2]),Character.getNumericValue(pat[i+3]))==2)  && (distance(Character.getNumericValue(pat[i+3]),Character.getNumericValue(pat[i+4]))==2)) || ((distance(Character.getNumericValue(pat[i]),Character.getNumericValue(pat[i+1]))==1 ) && (distance(Character.getNumericValue(pat[i+1]),Character.getNumericValue(pat[i+2]))==1) && (distance(Character.getNumericValue(pat[i+2]),Character.getNumericValue(pat[i+3]))==2)  && (distance(Character.getNumericValue(pat[i+3]),Character.getNumericValue(pat[i+4]))==2)) || ((distance(Character.getNumericValue(pat[i]),Character.getNumericValue(pat[i+1]))==2 ) && (distance(Character.getNumericValue(pat[i+1]),Character.getNumericValue(pat[i+2]))==2) && (distance(Character.getNumericValue(pat[i+2]),Character.getNumericValue(pat[i+3]))==1)  && (distance(Character.getNumericValue(pat[i+3]),Character.getNumericValue(pat[i+4]))==1)) || ((distance(Character.getNumericValue(pat[i]),Character.getNumericValue(pat[i+1]))==2 ) && (distance(Character.getNumericValue(pat[i+1]),Character.getNumericValue(pat[i+2]))==2) && (distance(Character.getNumericValue(pat[i+2]),Character.getNumericValue(pat[i+3]))==3)  && (distance(Character.getNumericValue(pat[i+3]),Character.getNumericValue(pat[i+4]))==3)) )
            {
                counter++;
            }

        }
        return counter;
    }

    public static int shortEdge(String pattern)
    {
        char[] pat = pattern.toCharArray();
        int counter=0;
        for (int i=0;i<pat.length-2;i++)
        {
            if ((distance(Character.getNumericValue(pat[i]),Character.getNumericValue(pat[i+1]))==3 && (distance(Character.getNumericValue(pat[i+1]),Character.getNumericValue(pat[i+2]))==4)) || (distance(Character.getNumericValue(pat[i]),Character.getNumericValue(pat[i+1]))==4 && (distance(Character.getNumericValue(pat[i+1]),Character.getNumericValue(pat[i+2]))==3))  || (distance(Character.getNumericValue(pat[i]),Character.getNumericValue(pat[i+1]))==4 && (distance(Character.getNumericValue(pat[i+1]),Character.getNumericValue(pat[i+2]))==1)) || (distance(Character.getNumericValue(pat[i]),Character.getNumericValue(pat[i+1]))==1 && (distance(Character.getNumericValue(pat[i+1]),Character.getNumericValue(pat[i+2]))==4)) || (distance(Character.getNumericValue(pat[i]),Character.getNumericValue(pat[i+1]))==3 && (distance(Character.getNumericValue(pat[i+1]),Character.getNumericValue(pat[i+2]))==2)) || (distance(Character.getNumericValue(pat[i]),Character.getNumericValue(pat[i+1]))==2 && (distance(Character.getNumericValue(pat[i+1]),Character.getNumericValue(pat[i+2]))==3)))
            {
                counter++;
                Log.e("checktest", counter+" i="+i);
            }

        }
        return counter;
    }

    public static int shortOrthEdge(String pattern)
    {
        char[] pat = pattern.toCharArray();
        int counter=0;
        for (int i=0;i<pat.length-2;i++)
        {
            if ((distance(Character.getNumericValue(pat[i]),Character.getNumericValue(pat[i+1]))==3 && (distance(Character.getNumericValue(pat[i+1]),Character.getNumericValue(pat[i+2]))==1)) || (distance(Character.getNumericValue(pat[i]),Character.getNumericValue(pat[i+1]))==1 && (distance(Character.getNumericValue(pat[i+1]),Character.getNumericValue(pat[i+2]))==3))  || (distance(Character.getNumericValue(pat[i]),Character.getNumericValue(pat[i+1]))==4 && (distance(Character.getNumericValue(pat[i+1]),Character.getNumericValue(pat[i+2]))==2)) || (distance(Character.getNumericValue(pat[i]),Character.getNumericValue(pat[i+1]))==2 && (distance(Character.getNumericValue(pat[i+1]),Character.getNumericValue(pat[i+2]))==4)))
            {
                counter++;
                Log.e("checktest", counter+" i="+i);
            }

        }
        return counter;
    }

    public static int longOrthEdge(String pattern)
    {
        char[] pat = pattern.toCharArray();
        int counter=0;
        for (int i=0;i<pat.length-4;i++)
        {
            if(((distance(Character.getNumericValue(pat[i]),Character.getNumericValue(pat[i+1]))==1 ) && (distance(Character.getNumericValue(pat[i+1]),Character.getNumericValue(pat[i+2]))==1) && (distance(Character.getNumericValue(pat[i+2]),Character.getNumericValue(pat[i+3]))==3)  && (distance(Character.getNumericValue(pat[i+3]),Character.getNumericValue(pat[i+4]))==3)) || ((distance(Character.getNumericValue(pat[i]),Character.getNumericValue(pat[i+1]))==3 ) && (distance(Character.getNumericValue(pat[i+1]),Character.getNumericValue(pat[i+2]))==3) && (distance(Character.getNumericValue(pat[i+2]),Character.getNumericValue(pat[i+3]))==1)  && (distance(Character.getNumericValue(pat[i+3]),Character.getNumericValue(pat[i+4]))==1)))
             {
                counter++;
                Log.e("checktest", counter+" i="+i);
            }

        }
        return counter;
    }

    public void show(View view) {
    }
}
