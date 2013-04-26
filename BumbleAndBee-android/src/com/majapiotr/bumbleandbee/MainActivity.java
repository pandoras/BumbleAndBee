package com.majapiotr.bumbleandbee;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = true;
        cfg.useCompass = false;
        // don't use accelerometer
        cfg.useAccelerometer = false;
        // to keep screen on
        cfg.useWakelock = true;
        initialize(new BumbleAndBee(new PrzyciskStrzalu()), cfg);
    }
}