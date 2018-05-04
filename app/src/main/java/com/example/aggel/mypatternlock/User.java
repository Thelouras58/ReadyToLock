package com.example.aggel.mypatternlock;

import android.util.Log;

import com.example.aggel.mypatternlock.io.ReadWriteUtils;

import java.io.IOException;

public class User {
    String username;
    int id;

    public User(String username) throws IOException {
        this.username = username;
        id = 1;
        ReadWriteUtils.makeDir(username);
    }


}
