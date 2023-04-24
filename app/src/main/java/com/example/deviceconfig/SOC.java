package com.example.deviceconfig;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.io.InputStream;

public class SOC extends Fragment {
    TextView sosTextView;
    ProcessBuilder processBuilder;
    String Holder = "";
    String[] DATA = {"/system/bin/cat", "/proc/cpuinfo"};
    InputStream inputStream;
    Process process ;
    byte[] byteArry ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.soc,container,false);

        sosTextView=view.findViewById(R.id.socTextView);
        sosTextView.setText(getSoCInfo());

        byteArry = new byte[1024];

        try{
            processBuilder = new ProcessBuilder(DATA);

            process = processBuilder.start();

            inputStream = process.getInputStream();

            while(inputStream.read(byteArry) != -1){

                Holder = Holder + new String(byteArry);
            }

            inputStream.close();

        } catch(IOException ex){

            ex.printStackTrace();
        }

        sosTextView.setText(Holder);
        return view;
    }

    public String getSoCInfo() {
        String hardware = Build.HARDWARE;
        String device = Build.DEVICE;
        String soc_model=Build.SOC_MODEL;
        return ("Hardware: "+hardware + "\n"+
                "Device: "+ device+"\n"+
                "Soc Model: "+soc_model);
    }

}
