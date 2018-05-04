package com.example.aggel.mypatternlock.io;

import android.os.Environment;
import android.util.Log;

import com.example.aggel.mypatternlock.MainActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ReadWriteUtils {
    private static File dir, file;

    public static void makeDir(String username) throws IOException {

        dir = new File(Environment.getExternalStorageDirectory() + "/users/" + username);
        dir.mkdirs();

    }

    public static void writeCSV(ArrayList<String> data, ArrayList<String> data2, ArrayList<String> data3, int turn) throws IOException {

        final String filename = dir.toString() + "/" + MainActivity.getUser().getUsername() + "_" + turn + "_raw.csv";
        FileWriter fw = new FileWriter(filename, true);

        for (int i = 0; i < data.size(); i++) {
            fw.write(data.get(i));
            fw.write(";");
            fw.write(data3.get(i));
            fw.write(";");
            fw.write(data2.get(i));
            fw.write("\n");
        }


        fw.close();


    }

    public static void writeCSV(int turn, ArrayList<String> data, ArrayList<String> data1, ArrayList<String> data2) throws IOException {

        final String filename = dir.toString() + "/" + MainActivity.getUser().getUsername() + "_" + turn + "_sensors.csv";
        FileWriter fw = new FileWriter(filename, true);

        for (int i = 0; i < data.size(); i++) {
            fw.write(data2.get(i));
            fw.write(";");
            fw.write(data.get(i));
            fw.write(";");
            fw.write(data1.get(i));
            fw.write("\n");
        }


        fw.close();


    }

}
