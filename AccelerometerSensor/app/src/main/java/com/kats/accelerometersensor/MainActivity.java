package com.kats.accelerometersensor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.widget.TextView;

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
        mySensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //Register Sensor listener
        sm.registerListener(this , mySensor , SensorManager.SENSOR_DELAY_NORMAL);

        xtext = (TextView)findViewById(R.id.xtext);
        ytext = (TextView)findViewById(R.id.ytext);
        ztext = (TextView)findViewById(R.id.ztext);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        xtext.setText("X : " + sensorEvent.values[0]);
        ytext.setText("Y : " + sensorEvent.values[1]);
        ztext.setText("Z : " + sensorEvent.values[2]);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

        //not in use

    }
}
