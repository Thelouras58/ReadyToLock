package com.example.aggel.mypatternlock.pattern;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PatternUtils extends Service {
    public PatternUtils() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
