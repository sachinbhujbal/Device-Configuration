package com.example.deviceconfig;

import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class system extends Fragment {
    TextView systemTextView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.system,container,false);
        systemTextView=view.findViewById(R.id.systemTextView);
        systemTextView.setText(getSystemInfo());


        return  view;

    }
    public String getSystemInfo(){
        String support_64_bits= Arrays.toString(Build.SUPPORTED_64_BIT_ABIS);
        String support_32_bits= Arrays.toString(Build.SUPPORTED_32_BIT_ABIS);
        String support_ABIS= Arrays.toString(Build.SUPPORTED_ABIS);
        String tags=Build.TAGS;
        String time= String.valueOf(Build.TIME);
        String type=Build.TYPE;
        String user=Build.USER;
        String arch=System.getProperty("os.arch");
        String kernalVersion=System.getProperty("os.version");
        long uptime=System.currentTimeMillis()- SystemClock.elapsedRealtime();
        int apiLevel=Build.VERSION.SDK_INT;
        String androidVersion=Build.VERSION.RELEASE;
        String securityPatch=Build.VERSION.SECURITY_PATCH;

        return (
                "API Level: "+apiLevel+"\n"+
                "Android Version: "+androidVersion+"\n"+
                "System Uptime: "+uptime+"\n"+
                "Security Patch: "+securityPatch+"\n"+
                "Supprot_64_Bits : "+support_64_bits+"\n"+
                "Support_32_Bits : "+support_32_bits+"\n"+
                "Support_ABIS : "+support_ABIS+"\n"+
                "Tags : "+tags+"\n"+
                "Time : "+time+"\n"+
                "Type : "+type+"\n"+
                "User : "+user+"\n"+
                "Kernal Architecture: "+arch+"\n"+
                "Kernal Version: "+kernalVersion+"\n"
                );
    }


}
