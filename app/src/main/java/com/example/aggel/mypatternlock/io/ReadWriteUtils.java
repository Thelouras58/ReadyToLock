package com.example.aggel.mypatternlock.io;

import android.os.Environment;
import android.util.Log;

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

    public static void writeCSV(int type, ArrayList<String> data) {
        //final String filename = dir.toString() + "/"+username+".csv";
        // FileWriter fw = new FileWriter(filename);
        //  fw.append("No");
        //  fw.close();
        //  file = new File(filename);
        if (type == 1) {

        } else if (type == 2) {

        } else {

        }


    }

}
