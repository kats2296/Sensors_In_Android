package com.kats.gyroscope;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView xtext, ytext, ztext;
    private Sensor mySensor;
    private SensorManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create Sensor Manager
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);

        //Accelerometer Sensor
        mySensor = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        //Register Sensor listener
        sm.registerListener(this , mySensor , SensorManager.SENSOR_DELAY_NORMAL);

        if(mySensor == null)
            Toast.makeText(this , "The device has no gyroscope" , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        // going to use the raw data sent by the sensor
        // the data here consists of 3 float values specifying angular velocity along X,Y,Z direction
        // each value is in radians per second
        // The value for anticlockwise rotation along any direction will be positive

        if(sensorEvent.values[2] > 0.5f)
            getWindow().getDecorView().setBackgroundColor(Color.BLUE);

        else if (sensorEvent.values[2] < -0.5f)
            getWindow().getDecorView().setBackgroundColor(Color.YELLOW);

        if(sensorEvent.values[1] > 0.5f)
            getWindow().getDecorView().setBackgroundColor(Color.BLUE);

        else if (sensorEvent.values[1] < -0.5f)
            getWindow().getDecorView().setBackgroundColor(Color.YELLOW);

        if(sensorEvent.values[0] > 0.5f)
            getWindow().getDecorView().setBackgroundColor(Color.BLUE);

        else if (sensorEvent.values[0] < -0.5f)
            getWindow().getDecorView().setBackgroundColor(Color.YELLOW);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
