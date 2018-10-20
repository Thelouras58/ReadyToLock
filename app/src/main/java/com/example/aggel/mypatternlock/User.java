package com.example.aggel.mypatternlock;


import com.example.aggel.mypatternlock.io.ReadWriteUtils;

import java.io.IOException;
public class User {
    private String username;

    private int hand, finger;


    protected User(String username, int hand, int finger) throws IOException {
        this.username = username;
        this.finger = finger;
        this.hand = hand;
        ReadWriteUtils.makeDir(username);
    }

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public int getHand() {
        return hand;
    }

    public void setHand(int hand) {
        this.hand = hand;
    }

    public int getFinger() {
        return finger;
    }

    public void setFinger(int finger) {
        this.finger = finger;
    }
}
