package com.example.aggel.mypatternlock.pattern;

import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;

//κλάση με διάφορες μεθόδους
public class PatternUtils {


    //μέθοδος η οποία πέρνει σαν είσοδο το σημείο x,y και υπολογίζει σε πιο κυκλάκι του pattern βρίσκεται πιο κοντά, το number_of_activated_point του raw pattern
    protected static int calculatePoint(int x, int y) {
        ArrayList<Point> table = new ArrayList<>();
        table.add(new Point(190, 150));
        table.add(new Point(530, 150));
        table.add(new Point(870, 150));
        table.add(new Point(190, 430));
        table.add(new Point(530, 430));
        table.add(new Point(870, 430));
        table.add(new Point(190, 750));
        table.add(new Point(530, 750));
        table.add(new Point(870, 750));

        double temp = 0;
        int index = 0;
        double temp2 = 37890;
        for (int i = 0; i < 9; i++) {

            temp = Math.pow(table.get(i).getX() - x, 2) + Math.pow(table.get(i).getY() - y, 2);
            temp = Math.sqrt(temp);
            if (min(temp, temp2)) {
                temp2 = temp;
                index = i;
            }


        }

        return index;
    }

    public static Boolean min(double x, double y) {
        if (x < y)
            return true;
        return false;

    }

    public static Boolean max(double x, double y) {
        if (x > y)
            return true;
        return false;

    }


    public static Float avg(ArrayList<Float> list) {
        float sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum = sum + list.get(i);
        }
        return sum / list.size();
    }

    public static float calculatePatternLength(ArrayList<Point> list) {

        float distance = 0, count = 0;    //gia na paroume to 60% twn simeiwn pou apexoun ises apostaseis
        count = list.size() * 60 / 100;    //arkei na broume to poso tha prepei na apexoun ta simeia metaksy tous to opoio einai size/size*60%
        float a = list.size() / count;
        Log.e("check", "d=60/size = " + a);
        for (double i = 0; i < (double) list.size() - a; i += a) {
            distance += Math.sqrt(Math.pow(list.get((int) i).getX() - list.get((int) (i + a)).getX(), 2) + Math.pow(list.get((int) i).getY() - list.get((int) (i + a)).getY(), 2));
        }
        return distance;
    }

    public static Point centralPointOf(Integer x) {
        if (x.equals(0)) {
            return new Point(190, 150);
        } else if (x.equals(1)) {
            return new Point(530, 150);
        } else if (x.equals(2)) {
            return new Point(870, 150);
        } else if (x.equals(3)) {
            return new Point(190, 430);
        } else if (x.equals(4)) {
            return new Point(530, 430);
        } else if (x.equals(5)) {
            return new Point(870, 430);
        } else if (x.equals(6)) {
            return new Point(190, 750);
        } else if (x.equals(7)) {
            return new Point(530, 750);
        } else {
            return new Point(870, 750);
        }


    }
}
