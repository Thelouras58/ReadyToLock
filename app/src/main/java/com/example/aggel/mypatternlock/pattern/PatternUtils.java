package com.example.aggel.mypatternlock.pattern;

import java.util.ArrayList;

public class PatternUtils {
    public static int calculatePoint(int x, int y) {
        ArrayList<Point> table = new ArrayList();
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
        if (x < y) {
            return true;
        }
        return false;

    }
}
