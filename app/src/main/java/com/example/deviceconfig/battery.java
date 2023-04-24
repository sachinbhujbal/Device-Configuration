package com.example.deviceconfig;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class battery extends Fragment {
    private TextView batteryInfoTextView;
    private BatteryInfoReceiver batteryInfoReceiver;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.battery, container, false);
        batteryInfoTextView = view.findViewById(R.id.batteryTextView);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        batteryInfoReceiver = new BatteryInfoReceiver();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        requireActivity().registerReceiver(batteryInfoReceiver, intentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        requireActivity().unregisterReceiver(batteryInfoReceiver);
    }

    private class BatteryInfoReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 100);
                int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, BatteryManager.BATTERY_HEALTH_UNKNOWN);
                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, BatteryManager.BATTERY_STATUS_UNKNOWN);
                String technology = intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY);

                double batteryPct = level * 100.0 / scale;
                String batteryLevel = String.format("%.0f%%", batteryPct);

                String healthString = getHealthString(health);
                String statusString = getStatusString(status);

                String batteryInfo = "Battery Level: " + batteryLevel + "\n" +
                        "Health: " + healthString + "\n" +
                        "Status: " + statusString + "\n" +
                        "Technology: " + technology;

                batteryInfoTextView.setText(batteryInfo);
            }
        }

        private String getHealthString(int health) {
            switch (health) {
                case BatteryManager.BATTERY_HEALTH_COLD:
                    return "Cold";
                case BatteryManager.BATTERY_HEALTH_DEAD:
                    return "Dead";
                case BatteryManager.BATTERY_HEALTH_GOOD:
                    return "Good";
                case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                    return "Overheat";
                case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                    return "Over voltage";
                case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                    return "Unknown";
                case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                    return "Unspecified failure";
                default:
                    return "Unknown";
            }
        }

        private String getStatusString(int status) {

            switch (status) {
                case BatteryManager.BATTERY_STATUS_CHARGING:
                    return "Charging";
                case BatteryManager.BATTERY_STATUS_DISCHARGING:
                    return "Discharging";
                case BatteryManager.BATTERY_STATUS_FULL:
                    return "Full";
                case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                    return "Not charging";
                case BatteryManager.BATTERY_STATUS_UNKNOWN:
                    return "Unknown";
                default:
                    return "Unknown";
            }
        }
    }
}
