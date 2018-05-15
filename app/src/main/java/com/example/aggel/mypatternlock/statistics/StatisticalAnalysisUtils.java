package com.example.aggel.mypatternlock.statistics;

import android.util.Log;

import java.util.ArrayList;

public class StatisticalAnalysisUtils {


    public static int longRuns(String pattern) {
        char[] pat = pattern.toCharArray();
        int counter = 0;
        for (int i = 0; i < pat.length - 2; i++) {
            if ((distance(Character.getNumericValue(pat[i]), Character.getNumericValue(pat[i + 1])) == 3 && distance(Character.getNumericValue(pat[i + 1]), Character.getNumericValue(pat[i + 2])) == 3) || (distance(Character.getNumericValue(pat[i]), Character.getNumericValue(pat[i + 1])) == 1 && distance(Character.getNumericValue(pat[i + 1]), Character.getNumericValue(pat[i + 2])) == 1) || (distance(Character.getNumericValue(pat[i]), Character.getNumericValue(pat[i + 1])) == 4 && distance(Character.getNumericValue(pat[i + 1]), Character.getNumericValue(pat[i + 2])) == 4)) {
                counter++;
            }
        }
        return counter;
    }

    public static int closedCurves(String pattern) {
        char[] pat = pattern.toCharArray();
        int counter = 0;
        for (int i = 0; i < pat.length - 3; i++) {
            if (((distance(Character.getNumericValue(pat[i]), Character.getNumericValue(pat[i + 1])) == 1) && (distance(Character.getNumericValue(pat[i + 1]), Character.getNumericValue(pat[i + 2])) == 3) && (distance(Character.getNumericValue(pat[i + 2]), Character.getNumericValue(pat[i + 3])) == 1)) || ((distance(Character.getNumericValue(pat[i]), Character.getNumericValue(pat[i + 1])) == 3) && (distance(Character.getNumericValue(pat[i + 1]), Character.getNumericValue(pat[i + 2])) == 1) && (distance(Character.getNumericValue(pat[i + 2]), Character.getNumericValue(pat[i + 3])) == 3))) {
                counter++;
            }

        }
        return counter;
    }

    public static int longCurves(String pattern) {
        char[] pat = pattern.toCharArray();
        int counter = 0;
        for (int i = 0; i < pat.length - 5; i++) {
            if (((distance(Character.getNumericValue(pat[i]), Character.getNumericValue(pat[i + 1])) == 1) && (distance(Character.getNumericValue(pat[i + 1]), Character.getNumericValue(pat[i + 2])) == 1) && (distance(Character.getNumericValue(pat[i + 2]), Character.getNumericValue(pat[i + 3])) == 3) && (distance(Character.getNumericValue(pat[i + 3]), Character.getNumericValue(pat[i + 4])) == 1) && (distance(Character.getNumericValue(pat[i + 4]), Character.getNumericValue(pat[i + 5])) == 1)) || ((distance(Character.getNumericValue(pat[i]), Character.getNumericValue(pat[i + 1])) == 3) && (distance(Character.getNumericValue(pat[i + 1]), Character.getNumericValue(pat[i + 2])) == 3) && (distance(Character.getNumericValue(pat[i + 2]), Character.getNumericValue(pat[i + 3])) == 1) && (distance(Character.getNumericValue(pat[i + 3]), Character.getNumericValue(pat[i + 4])) == 3) && (distance(Character.getNumericValue(pat[i + 4]), Character.getNumericValue(pat[i + 5])) == 3))) {
                counter++;
            }

        }
        return counter;
    }

    public static int longEdge(String pattern) {
        char[] pat = pattern.toCharArray();
        int counter = 0;
        for (int i = 0; i < pat.length - 4; i++) {

            if (((distance(Character.getNumericValue(pat[i]), Character.getNumericValue(pat[i + 1])) == 3) && (distance(Character.getNumericValue(pat[i + 1]), Character.getNumericValue(pat[i + 2])) == 3) && (distance(Character.getNumericValue(pat[i + 2]), Character.getNumericValue(pat[i + 3])) == 4) && (distance(Character.getNumericValue(pat[i + 3]), Character.getNumericValue(pat[i + 4])) == 4)) || ((distance(Character.getNumericValue(pat[i]), Character.getNumericValue(pat[i + 1])) == 4) && (distance(Character.getNumericValue(pat[i + 1]), Character.getNumericValue(pat[i + 2])) == 4) && (distance(Character.getNumericValue(pat[i + 2]), Character.getNumericValue(pat[i + 3])) == 3) && (distance(Character.getNumericValue(pat[i + 3]), Character.getNumericValue(pat[i + 4])) == 3)) || ((distance(Character.getNumericValue(pat[i]), Character.getNumericValue(pat[i + 1])) == 1) && (distance(Character.getNumericValue(pat[i + 1]), Character.getNumericValue(pat[i + 2])) == 1) && (distance(Character.getNumericValue(pat[i + 2]), Character.getNumericValue(pat[i + 3])) == 4) && (distance(Character.getNumericValue(pat[i + 3]), Character.getNumericValue(pat[i + 4])) == 4)) || ((distance(Character.getNumericValue(pat[i]), Character.getNumericValue(pat[i + 1])) == 4) && (distance(Character.getNumericValue(pat[i + 1]), Character.getNumericValue(pat[i + 2])) == 4) && (distance(Character.getNumericValue(pat[i + 2]), Character.getNumericValue(pat[i + 3])) == 1) && (distance(Character.getNumericValue(pat[i + 3]), Character.getNumericValue(pat[i + 4])) == 1)) || ((distance(Character.getNumericValue(pat[i]), Character.getNumericValue(pat[i + 1])) == 3) && (distance(Character.getNumericValue(pat[i + 1]), Character.getNumericValue(pat[i + 2])) == 3) && (distance(Character.getNumericValue(pat[i + 2]), Character.getNumericValue(pat[i + 3])) == 2) && (distance(Character.getNumericValue(pat[i + 3]), Character.getNumericValue(pat[i + 4])) == 2)) || ((distance(Character.getNumericValue(pat[i]), Character.getNumericValue(pat[i + 1])) == 1) && (distance(Character.getNumericValue(pat[i + 1]), Character.getNumericValue(pat[i + 2])) == 1) && (distance(Character.getNumericValue(pat[i + 2]), Character.getNumericValue(pat[i + 3])) == 2) && (distance(Character.getNumericValue(pat[i + 3]), Character.getNumericValue(pat[i + 4])) == 2)) || ((distance(Character.getNumericValue(pat[i]), Character.getNumericValue(pat[i + 1])) == 2) && (distance(Character.getNumericValue(pat[i + 1]), Character.getNumericValue(pat[i + 2])) == 2) && (distance(Character.getNumericValue(pat[i + 2]), Character.getNumericValue(pat[i + 3])) == 1) && (distance(Character.getNumericValue(pat[i + 3]), Character.getNumericValue(pat[i + 4])) == 1)) || ((distance(Character.getNumericValue(pat[i]), Character.getNumericValue(pat[i + 1])) == 2) && (distance(Character.getNumericValue(pat[i + 1]), Character.getNumericValue(pat[i + 2])) == 2) && (distance(Character.getNumericValue(pat[i + 2]), Character.getNumericValue(pat[i + 3])) == 3) && (distance(Character.getNumericValue(pat[i + 3]), Character.getNumericValue(pat[i + 4])) == 3))) {
                counter++;
            }

        }
        return counter;
    }

    public static int shortEdge(String pattern) {
        char[] pat = pattern.toCharArray();
        int counter = 0;
        for (int i = 0; i < pat.length - 2; i++) {
            if ((distance(Character.getNumericValue(pat[i]), Character.getNumericValue(pat[i + 1])) == 3 && (distance(Character.getNumericValue(pat[i + 1]), Character.getNumericValue(pat[i + 2])) == 4)) || (distance(Character.getNumericValue(pat[i]), Character.getNumericValue(pat[i + 1])) == 4 && (distance(Character.getNumericValue(pat[i + 1]), Character.getNumericValue(pat[i + 2])) == 3)) || (distance(Character.getNumericValue(pat[i]), Character.getNumericValue(pat[i + 1])) == 4 && (distance(Character.getNumericValue(pat[i + 1]), Character.getNumericValue(pat[i + 2])) == 1)) || (distance(Character.getNumericValue(pat[i]), Character.getNumericValue(pat[i + 1])) == 1 && (distance(Character.getNumericValue(pat[i + 1]), Character.getNumericValue(pat[i + 2])) == 4)) || (distance(Character.getNumericValue(pat[i]), Character.getNumericValue(pat[i + 1])) == 3 && (distance(Character.getNumericValue(pat[i + 1]), Character.getNumericValue(pat[i + 2])) == 2)) || (distance(Character.getNumericValue(pat[i]), Character.getNumericValue(pat[i + 1])) == 2 && (distance(Character.getNumericValue(pat[i + 1]), Character.getNumericValue(pat[i + 2])) == 3))) {
                counter++;

            }

        }
        return counter;
    }

    public static int shortOrthEdge(String pattern) {
        char[] pat = pattern.toCharArray();
        int counter = 0;
        for (int i = 0; i < pat.length - 2; i++) {
            if ((distance(Character.getNumericValue(pat[i]), Character.getNumericValue(pat[i + 1])) == 3 && (distance(Character.getNumericValue(pat[i + 1]), Character.getNumericValue(pat[i + 2])) == 1)) || (distance(Character.getNumericValue(pat[i]), Character.getNumericValue(pat[i + 1])) == 1 && (distance(Character.getNumericValue(pat[i + 1]), Character.getNumericValue(pat[i + 2])) == 3)) || (distance(Character.getNumericValue(pat[i]), Character.getNumericValue(pat[i + 1])) == 4 && (distance(Character.getNumericValue(pat[i + 1]), Character.getNumericValue(pat[i + 2])) == 2)) || (distance(Character.getNumericValue(pat[i]), Character.getNumericValue(pat[i + 1])) == 2 && (distance(Character.getNumericValue(pat[i + 1]), Character.getNumericValue(pat[i + 2])) == 4))) {
                counter++;

            }

        }
        return counter;
    }

    public static int longOrthEdge(String pattern) {
        char[] pat = pattern.toCharArray();
        int counter = 0;
        for (int i = 0; i < pat.length - 4; i++) {
            if (((distance(Character.getNumericValue(pat[i]), Character.getNumericValue(pat[i + 1])) == 1) && (distance(Character.getNumericValue(pat[i + 1]), Character.getNumericValue(pat[i + 2])) == 1) && (distance(Character.getNumericValue(pat[i + 2]), Character.getNumericValue(pat[i + 3])) == 3) && (distance(Character.getNumericValue(pat[i + 3]), Character.getNumericValue(pat[i + 4])) == 3)) || ((distance(Character.getNumericValue(pat[i]), Character.getNumericValue(pat[i + 1])) == 3) && (distance(Character.getNumericValue(pat[i + 1]), Character.getNumericValue(pat[i + 2])) == 3) && (distance(Character.getNumericValue(pat[i + 2]), Character.getNumericValue(pat[i + 3])) == 1) && (distance(Character.getNumericValue(pat[i + 3]), Character.getNumericValue(pat[i + 4])) == 1))) {
                counter++;

            }

        }
        return counter;
    }

    public static void numberFrequency(ArrayList<String> pat) {

    }

    public static int distance(int x, int y) {
        if (x > y) {
            return x - y;
        }
        return y - x;
    }
}
