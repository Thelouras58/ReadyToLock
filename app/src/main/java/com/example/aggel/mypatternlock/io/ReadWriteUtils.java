package com.example.aggel.mypatternlock.io;

import android.os.Environment;
import android.util.Log;

import com.example.aggel.mypatternlock.MainActivity;
import com.example.aggel.mypatternlock.pattern.PatternView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ReadWriteUtils {
    private static File dir, file;

    public static void makeDir(String username) throws IOException {

        dir = new File(Environment.getExternalStorageDirectory() + "/users/" + username);
        dir.mkdirs();

    }

    //Raw pattern files
    public static void writeCSV(ArrayList<String> data, ArrayList<String> data2, ArrayList<String> data3, ArrayList<Integer> data4, int turn) throws IOException {

        final String filename = dir.toString() + "/" + MainActivity.getUser().getUsername() + "_" + turn + "_raw.csv";
        FileWriter fw = new FileWriter(filename, true);
        fw.write("number_of_activated_point; xpoint; ypoint; timestamp; pressure");
        fw.write("\n");
        for (int i = 0; i < data.size(); i++) {
            fw.write(data4.get(i).toString());
            fw.write(";");
            fw.write(data.get(i));
            fw.write(";");
            fw.write(data3.get(i));
            fw.write(";");
            fw.write(data2.get(i));
            fw.write("\n");
        }
        fw.close();
    }

    //Sensor data files
    public static void writeCSV(int turn, ArrayList<String> data, ArrayList<String> data1, ArrayList<String> data2, ArrayList<String> data3) throws IOException {

        final String filename = dir.toString() + "/" + MainActivity.getUser().getUsername() + "_" + turn + "_sensors.csv";
        FileWriter fw = new FileWriter(filename, true);
        fw.write("timestamp; accel_x; accel_y; accel_z; gyro_x; gyro_y; gyro_z; laccel_x; laccel_y; laccel_z");
        fw.write("\n");
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

    //Pattern metadata file
    public static void writeMetaData(ArrayList<String> p) throws IOException {
        final String filename = dir.toString() + "/" + MainActivity.getUser().getUsername() + "_metadata.csv";
        FileWriter fw = new FileWriter(filename, true);
        fw.write("Username; Attempt_number; Sequence; Seq_length; Time_to_complete; PatternLength; Avg_speed; Avg_pressure; Highest_pressure; Lowest_pressure; HandNum; FingerNum");
        fw.write("\n");
        File raw;
        String text ;
        Log.d("lines",p.size()+"");
       for (int i = 0; i < p.size(); i++) {
           raw   = new File(dir,   MainActivity.getUser().getUsername() + "_" + i+ "_raw.csv");
            try {
                BufferedReader br = new BufferedReader(new FileReader(raw));
                String line;
                while ((line = br.readLine()) != null) {
                    text=line;
                    Log.d("lines",text);
                }
                br.close();
            }
            catch (IOException e) {
                //You'll need to add proper error handling here
            }
        }
        fw.close();
    }

    //Pair metadata
    public static void writeMetaData2() {

    }

}
