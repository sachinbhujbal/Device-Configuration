package com.example.deviceconfig;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class device extends Fragment {
    TextView deviceTextView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.device,container,false);

        deviceTextView=view.findViewById(R.id.deviceTextView);
        deviceTextView.setText(getDeviceInfo());

        return view;
    }
    public String getDeviceInfo() {
        String hardware = Build.HARDWARE;
        String device = Build.DEVICE;
        String soc_model=Build.SOC_MODEL;
        String brand=Build.BRAND;
        String bootloader=Build.BOOTLOADER;
        String cpu_abi=Build.CPU_ABI;
        String cpu_abi2=Build.CPU_ABI2;
        String display=Build.DISPLAY;
        String fingerprint=Build.FINGERPRINT;
        String host=Build.HOST;
        String id=Build.ID;
        String manufacturer=Build.MANUFACTURER;
        String model=Build.MODEL;
        String odm_sku=Build.ODM_SKU;
        String product=Build.PRODUCT;
        String radio=Build.RADIO;
        String serial=Build.SERIAL;
        String sku=Build.SKU;
        String soc_manufacturer=Build.SOC_MANUFACTURER;

        return ("Hardware: "+hardware + "\n"+
                "Device: "+ device+"\n"+
                "Soc Model: "+soc_model+"\n"+
                "Brand: "+brand+"\n"+
                "Bootloader: "+bootloader+"\n"+
                "CPU_ABI: "+cpu_abi+"\n"+
                "CPU_ABI2: "+cpu_abi2 + "\n"+
                "Display: "+display + "\n"+
                "Fingerprint: "+fingerprint + "\n"+
                "Host: "+host + "\n"+
                "ID: "+id + "\n"+
                "Manufacture: "+manufacturer + "\n"+
                "Model: "+model + "\n"+
                "ODM_SKU: "+odm_sku + "\n"+
                "Product: "+product + "\n"+
                "Radio: "+radio + "\n"+
                "Serial: "+serial + "\n"+
                "SKU: "+sku + "\n"+
                "SOC_Manufacture: "+soc_manufacturer + "\n"
        );
    }
}
