package com.example.aggel.mypatternlock.sensors;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;

import com.example.aggel.mypatternlock.io.ReadWriteUtils;

import java.io.IOException;
import java.util.ArrayList;

public class SensorsUtils implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor sensor1, sensor2, sensor3;
    private ArrayList<String> sen1, sen2,sen0, time1;

    public SensorsUtils() {
    }

    public SensorsUtils(Activity activity) {
        mSensorManager = (SensorManager) activity.getSystemService(Activity.SENSOR_SERVICE);
        sensor1 = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensor2 = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        sensor3 = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        sen1 = new ArrayList();
        sen2 = new ArrayList();
        sen0 = new ArrayList();
        sen0 = new ArrayList();
        time1 = new ArrayList<>();

    }

    public void startListen() {

        mSensorManager.registerListener(this, sensor1, SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(this, sensor2, SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(this, sensor3, SensorManager.SENSOR_DELAY_GAME);

    }

    public void stopListen(int turn) throws IOException {
        mSensorManager.unregisterListener(this);
        ReadWriteUtils.writeCSV(turn, sen1,sen0, sen2, time1);


    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        time1.add(String.valueOf(SystemClock.elapsedRealtimeNanos()));
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
            updateOrientation(sensorEvent.values);

        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            sen1.add(sensorEvent.values[0] + ";" + sensorEvent.values[1] + ";" + sensorEvent.values[2]);

        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {

            sen2.add(sensorEvent.values[0] + ";" + sensorEvent.values[1] + ";" + sensorEvent.values[2]);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void updateOrientation(float[] rotationVector) {
        float[] rotationMatrix = new float[9];
        SensorManager.getRotationMatrixFromVector(rotationMatrix, rotationVector);
        float[] adjustedRotationMatrix = new float[9];

        final int worldAxisForDeviceAxisX;
        final int worldAxisForDeviceAxisY;
        worldAxisForDeviceAxisX = SensorManager.AXIS_MINUS_X;
        worldAxisForDeviceAxisY = SensorManager.AXIS_MINUS_Z;
        SensorManager.remapCoordinateSystem(rotationMatrix, worldAxisForDeviceAxisX,
                worldAxisForDeviceAxisY, adjustedRotationMatrix);

        // Transform rotation matrix into azimuth/pitch/roll
        float[] orientation = new float[3];
        SensorManager.getOrientation(adjustedRotationMatrix, orientation);

        // Convert radians to degrees
        float azimuth = orientation[0] * -57;
        float pitch = orientation[1] * -57;
        float roll = orientation[2] * -57;
       // Log.e("ORIENT",azimuth+"_"+pitch+"_"+roll);
            sen0.add(azimuth+";"+pitch+";"+roll);

    }
}
