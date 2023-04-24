package com.example.deviceconfig;

import android.content.Context;
import android.graphics.ImageFormat;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Bundle;
import android.util.Log;
import android.util.Range;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Locale;

public class camera extends Fragment {
    private CameraManager cameraManager;
    private TextView cameraInfoTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cameraManager = (CameraManager) getActivity().getSystemService(Context.CAMERA_SERVICE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.camera, container, false);
        cameraInfoTextView = view.findViewById(R.id.cameraTextView);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateCameraInfo();
    }

    private void updateCameraInfo() {
        try {
            String[] cameraIds = cameraManager.getCameraIdList();
            for (String cameraId : cameraIds) {
                CameraCharacteristics characteristics = cameraManager.getCameraCharacteristics(cameraId);
                StreamConfigurationMap streamConfigurationMap = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                Size[] outputSizes = streamConfigurationMap.getOutputSizes(ImageFormat.JPEG);
                float megapixel = (float) (outputSizes[0].getWidth() * outputSizes[0].getHeight()) / 1000000.0f;

                // Calculate aperture value using focal length and sensor size
                float focalLength = characteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS)[0];
                float horizontalSensorSize = characteristics.get(CameraCharacteristics.SENSOR_INFO_PHYSICAL_SIZE).getWidth();
                float aperture = focalLength / horizontalSensorSize;
                aperture = (float) Math.round(aperture * 10) / 10;

                String cameraInfo = "Camera " + cameraId + "\nMegapixel: " + megapixel + "\nAperture: f/" + aperture;
                cameraInfoTextView.setText(cameraInfo);
            }
        } catch (CameraAccessException e) {
            Log.e("tag", "Failed to get camera info: " + e.getMessage());
            cameraInfoTextView.setText(e.getMessage());
        }
    }
}
