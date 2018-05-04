package com.example.aggel.mypatternlock.sensors;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class SensorsUtils implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor sensor1, sensor2, sensor3;

    public SensorsUtils() {
    }

    public SensorsUtils(Activity activity) {
        mSensorManager = (SensorManager) activity.getSystemService(Activity.SENSOR_SERVICE);
        sensor1 = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensor2 = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensor3 = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        mSensorManager.registerListener(this, sensor1, SensorManager.SENSOR_DELAY_GAME);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_GYROSCOPE) {

        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
