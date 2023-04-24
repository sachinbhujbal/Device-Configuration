package com.example.deviceconfig;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {
    int numCounter;

    public PageAdapter(@NonNull FragmentManager fm, int numCounter) {
        super(fm);
        this.numCounter = numCounter;
    }

    public PageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                SOC soc=new SOC();
                return soc;
            case 1:
                device device=new device();
                return device;

            case 2:
                system system=new system();
                return system;
            case 3:
                battery battery=new battery();
                return battery;
            case 4:
                camera thermal=new camera();
                return thermal;
            case 5:
                sensors sensors=new sensors();
                return sensors;
            case 6:
               about about=new about();
                return about;

        }
        return null;
    }

    @Override
    public int getCount() {
        return numCounter;
    }
}
