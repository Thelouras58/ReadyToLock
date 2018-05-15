package com.example.aggel.mypatternlock.io;

import android.content.Context;
import android.os.Environment;
import android.util.AtomicFile;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.example.aggel.mypatternlock.MainActivity;
import com.example.aggel.mypatternlock.pattern.PatternUtils;
import com.example.aggel.mypatternlock.pattern.PatternView;
import com.example.aggel.mypatternlock.pattern.Point;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

// κλάση με της μεθόδους για την εγγραφή των csv αρχείων , raw και metadata
public class ReadWriteUtils {

    private static File dir, file;
    private static Float maxPresOver, minPresOver;
    private static ArrayList<Float> avgSpeedOver;

    public static void makeDir(String username) throws IOException {
        //δημιουργία του πρωσοπικού φακέλου του χρήστη
        dir = new File(Environment.getExternalStorageDirectory() + "/users/" + username);
        dir.mkdirs();

    }


    //create Raw pattern file
    public static void writeCSV(ArrayList<String> data, ArrayList<String> data2, ArrayList<String> data3, ArrayList<Integer> data4, int turn) throws IOException {

        final String filename = dir.toString() + "/" + MainActivity.getUser().getUsername() + "_" + turn + "_raw.csv";
        FileWriter fw = new FileWriter(filename, true);
        // fw.write("number_of_activated_point;xpoint;ypoint;timestamp;pressure");
        // fw.write("\n");
        for (int i = 0; i < data.size(); i++) {
            // Log.e("DEBUGYO", String.valueOf(data4.get(i)));
            fw.write(data4.get(i).toString());
            // Log.e("DEBUGYO", String.valueOf(data4.size() + "_" + data.size()));
            fw.write(";");
            fw.write(data3.get(i));
            fw.write(";");
            fw.write(data.get(i));
            fw.write(";");
            fw.write(data2.get(i));
            fw.write("\n");
        }
        fw.close();
    }

    //create Sensor data file
    public static void writeCSV(int turn, ArrayList<String> data, ArrayList<String> data1, ArrayList<String> data2, ArrayList<String> data3) throws IOException {

        final String filename = dir.toString() + "/" + MainActivity.getUser().getUsername() + "_" + turn + "_sensors.csv";
        FileWriter fw = new FileWriter(filename, true);
        // fw.write("timestamp;accel_x;accel_y;accel_z;gyro_x;gyro_y;gyro_z;laccel_x;laccel_y;laccel_z");
        //  fw.write("\n");
        for (int i = 0; i < data2.size(); i++) {

            fw.write(data3.get(i));
            fw.write(";");
            fw.write(data.get(i));
            fw.write(";");
            fw.write(data1.get(i));
            fw.write(";");
            fw.write(data2.get(i));
            fw.write("\n");
        }
        fw.close();
    }

    //create Pattern metadata file
    public static void writeMetaData(ArrayList<String> pat) throws IOException {

        final String filename = dir.toString() + "/" + MainActivity.getUser().getUsername() + "_metadata.csv";

        FileWriter fw = new FileWriter(filename, true);
        ArrayList<Float> pressures = new ArrayList<>();
        ArrayList<Float> times = new ArrayList<>();
        ArrayList<Point> points = new ArrayList<>();
        avgSpeedOver = new ArrayList<>();
        //fw.write("Username; Attempt_number; Sequence; Seq_length; Time_to_complete; PatternLength; Avg_speed; Avg_pressure; Highest_pressure; Lowest_pressure; HandNum; FingerNum");
        //  fw.write("\n");
        maxPresOver = Float.valueOf(-1);
        minPresOver = Float.valueOf(10);
        File raw;

        BufferedReader br;
        for (int i = 0; i < pat.size(); i++) {
            raw = new File(Environment.getExternalStorageDirectory() + "/users/" + MainActivity.getUser().getUsername(), MainActivity.getUser().getUsername() + "_" + i + "_raw.csv");
            br = new BufferedReader(new FileReader(raw));


            //διάβασμα raw file για να πάρουμε τα δεδομένα που χρηαζόμαστε
            String line = br.readLine();
            while (line != null) {
                String[] myStrings = line.split("\\;");
                if (myStrings.length > 1) {
                    times.add(Float.parseFloat((myStrings[1])));
                    pressures.add(Float.parseFloat(myStrings[4]));
                    Point point = new Point(Integer.parseInt(myStrings[2]), Integer.parseInt(myStrings[3]));
                    points.add(point);
                }

                line = br.readLine();
            }

            // Log.e("check", "points list length =" + points.size());
            Float timeToComplete = times.get(times.size() - 1) - times.get(0);
            Float length = PatternUtils.calculatePatternLength(points);
            Float avgPres = PatternUtils.avg(pressures);
            Float avgSpeed = length / timeToComplete;
            Float maxPres = Collections.max(pressures);
            Float minPres = Collections.min(pressures);

            if (PatternUtils.max(maxPres, maxPresOver)) {
               // Log.e("MAXMAX", maxPresOver + "");
                maxPresOver = maxPres;
            }
            if (PatternUtils.min(minPres, minPresOver)) {
                minPresOver = minPres;
            }
            avgSpeedOver.add(avgSpeed);

            fw.write(MainActivity.getUser().getUsername());
            fw.write(";");
            fw.write(String.valueOf(i));
            fw.write(";");
            fw.write(pat.get(i));
            fw.write(";");
            fw.write(String.valueOf(pat.get(i).length()));
            fw.write(";");
            fw.write(timeToComplete.toString());
            fw.write(";");
            fw.write(String.format("%.2f", length));
            fw.write(";");
            fw.write(avgSpeed.toString());
            fw.write(";");
            fw.write(avgPres.toString());
            fw.write(";");
            fw.write(maxPres.toString());
            fw.write(";");
            fw.write(minPres.toString());
            fw.write(";");
            fw.write(MainActivity.getUser().getHand() + "");
            fw.write(";");
            fw.write(MainActivity.getUser().getFinger() + "");
            fw.write("\n");


            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: " + e);
            }

            points.clear();
            times.clear();
            pressures.clear();
        }
        fw.close();
    }


    //create Pair metadata file
    public static void writeMetaData2(ArrayList<String> pat) throws IOException {


        for (int i = 0; i < pat.size(); i++) {
            final String filename = dir.toString() + "/" + MainActivity.getUser().getUsername() + i + "_pairs.csv";
            FileWriter fw = new FileWriter(filename, true);
            File raw;
            Integer A = 0, B = 0;
            Point central1, central2;
            ArrayList<Point> activatorsOfA = new ArrayList<>();
            ArrayList<Point> activatorsOfB = new ArrayList<>();
            ArrayList<Float> presuresAB = new ArrayList<>();
            ArrayList<Float> times = new ArrayList<>();


            BufferedReader br;
            raw = new File(Environment.getExternalStorageDirectory() + "/users/" + MainActivity.getUser().getUsername(), MainActivity.getUser().getUsername() + "_" + i + "_raw.csv");
            Log.e("METADATA", pat.get(i));
            Log.e("METADATA", pat.get(i).charAt(0) + "");
            // fw.write("Username;Attempt_number;Screen_Resolution;Pattern_number_A; Pattern_number_B; Xcoord_of_central_Point_of_A; Ycoord_of_central_Point_of_A;Xcoord_of_central_Point_of_B;Ycoord_of_central_Point_of_B;First_Xcoord_of_A; First_Ycoord_of_A;Last_ Xcoord_of_B;Last_Ycoord_of_B;Distance_AB;Intertime_AB;Avg_speeadAB;Avg_pressure");
            //  fw.write("\n");
            //για κάθε pattern πέρνουμε τα ζευγάρια του
            for (int j = 1; j < pat.get(i).length(); j++) {

                A = Character.getNumericValue(pat.get(i).charAt(j - 1));
                B = Character.getNumericValue(pat.get(i).charAt(j));
                Log.e("METADATA", A + "???" + B);
                central1 = PatternUtils.centralPointOf(A);
                central2 = PatternUtils.centralPointOf(B);
                br = new BufferedReader(new FileReader(raw));
                String line = br.readLine();
                while (line != null) {
                    String[] myStrings = line.split("\\;");
                    if (myStrings[0].equals(A.toString())) {
                        Point p = new Point(Integer.parseInt(myStrings[2]), Integer.parseInt(myStrings[3]));
                        activatorsOfA.add(p);
                        presuresAB.add(Float.parseFloat(myStrings[4]));
                        times.add(Float.parseFloat(myStrings[1]));
                    }
                    if (myStrings[0].equals(B.toString())) {
                        Point p = new Point(Integer.parseInt(myStrings[2]), Integer.parseInt(myStrings[3]));
                        activatorsOfB.add(p);
                        presuresAB.add(Float.parseFloat(myStrings[4]));
                        times.add(Float.parseFloat(myStrings[1]));
                    }

                    line = br.readLine();
                }
                Integer firstXofA = activatorsOfA.get(0).getX();
                Integer firstYofA = activatorsOfA.get(0).getY();
                Integer firstXofB = activatorsOfB.get(0).getX();
                Integer firstYofB = activatorsOfB.get(0).getY();
                Integer lastXofA = activatorsOfA.get(activatorsOfA.size() - 1).getX();
                Integer lastYofA = activatorsOfA.get(activatorsOfA.size() - 1).getY();
                Integer lastYofB = activatorsOfB.get(activatorsOfB.size() - 1).getX();
                Integer lastXofB = activatorsOfB.get(activatorsOfB.size() - 1).getY();

                Double distanceAB = Math.sqrt((Math.pow((firstXofA - lastXofB), 2) + Math.pow(firstYofA - lastYofB, 2)));
                Float intertimeAB = times.get(times.size() - 1) - times.get(0);
                double avgSpeed = distanceAB / intertimeAB;
                Float avgPressure = PatternUtils.avg(presuresAB);
                br.close();
                //write all this to pair metada file
                fw.write(MainActivity.getUser().getUsername() + ";" + i + ";" + PatternView.getCont().getResources().getDisplayMetrics().heightPixels + "/" + PatternView.getCont().getResources().getDisplayMetrics().widthPixels + ";" + A + ";" + B + ";" + central1.getX() + ";" + central1.getY() + ";" + central2.getX() + ";" + central2.getY());
                fw.write(";" + firstXofA + ";" + firstYofA + ";" + firstXofB + ";" + firstYofB + ";" + lastXofA + ";" + lastYofA + ";" + lastXofB + ";" + lastYofB);
                fw.write(";" + distanceAB + ";" + intertimeAB + ";" + avgSpeed + ";" + avgPressure);
                fw.write("\n");


            }
            fw.close();


        }

    }

    public static void deleteDir() throws IOException {
        //για όταν χάνει και ξεκινάει από την αρχή
        dir.delete();

    }

    public static Float getMaxPresOver() {
        return maxPresOver;
    }

    public static Float getMinPresOver() {
        return minPresOver;
    }

    public static ArrayList<Float> getAvgSpeedOver() {
        return avgSpeedOver;
    }


}
