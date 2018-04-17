package com.kats.proximity_sensor;

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

    private Sensor mySensor;
    private SensorManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       //Create Sensor Manager
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);

        //Proximity Sensor
        mySensor = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if(mySensor == null) {
            Toast.makeText(this, "Proximity Sensor is not present in this device", Toast.LENGTH_LONG).show();
            finish();
        }
        //Register Sensor listener
        sm.registerListener(this , mySensor , 2*1000*1000);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        //if something is detected nearby comparing to the aximun range of this sensor
        if (sensorEvent.values[0] < mySensor.getMaximumRange())
            getWindow().getDecorView().setBackgroundColor(Color.RED);

        //if nothing is detected nearby
        else
            getWindow().getDecorView().setBackgroundColor(Color.BLACK);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }

}
