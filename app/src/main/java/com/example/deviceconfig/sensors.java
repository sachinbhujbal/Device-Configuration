package com.example.deviceconfig;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class sensors extends Fragment implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor sensor;
    private TextView sensorValueTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.sensors, container, false);
        sensorValueTextView = view.findViewById(R.id.sensorsTextView);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        String sensorValue = "Sensor X: " + x + "\nSensor Y: " + y + "\nSensor Z: " + z;
        sensorValueTextView.setText(sensorValue);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        String accuracyString = "";

        switch (accuracy) {
            case SensorManager.SENSOR_STATUS_ACCURACY_HIGH:
                accuracyString = "High";
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM:
                accuracyString = "Medium";
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_LOW:
                accuracyString = "Low";
                break;
            case SensorManager.SENSOR_STATUS_UNRELIABLE:
                accuracyString = "Unreliable";
                break;
            default:
                accuracyString = "Unknown";
                break;
        }

        String message = "Accuracy changed: " + accuracyString;
        sensorValueTextView.setText(message);
        sensorValueTextView.setText(accuracyString);

    }
}

