package com.kats.ambient_temperature;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private Sensor mySensor;
    private SensorManager sm;
    private TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create sensor manager
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);

        //Accelerometer Sensor
        mySensor = sm.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        txt1 = (TextView)findViewById(R.id.txt1);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        txt1.setText("" + sensorEvent.values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Register Sensor listener
        sm.registerListener(this , mySensor , SensorManager.SENSOR_DELAY_NORMAL);
    }
}
