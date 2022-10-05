package com.example.specsdetecto;

import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.jaredrummler.android.device.DeviceName;

public class SpecsActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specs);
        sharedPreferences = getSharedPreferences("com.example.specsdetecto", MODE_PRIVATE);

        /*
            Set application FLAGS
         */
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /*
            Initialize resources
         */
        Button editSpecsButton = findViewById(R.id.editSpecsHiddenButton);
        TextView getCpuModel = findViewById(R.id.getCpuModel);
        TextView getScreenSize = findViewById(R.id.getScreenSize);
        TextView getGpuModel = findViewById(R.id.getGpuModel);
        TextView getBatteryCapacity = findViewById(R.id.getBatteryCapacity);
        TextView getStorageSize = findViewById(R.id.getStorageSize);
        TextView getBackCameraMp = findViewById(R.id.getCameraMp);
        TextView getBrand = findViewById(R.id.getBrand);
        TextView getSimState = findViewById(R.id.getSimState);
        TextView getScreenInches = findViewById(R.id.getScreenDimension);
        TextView getModel = findViewById(R.id.getModel);
        TextView getRamSize = findViewById(R.id.getRamSize);
        TextView getAndroidVersion = findViewById(R.id.getAndroidVersion);
        TextView getWarranty = findViewById(R.id.getWarranty);
        TextView getFrontCameraMp = findViewById(R.id.getFrontCameraMp);

        /*
            Set logo background
         */
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,
                new int[]{
                        ContextCompat.getColor(this, R.color.transparent),
                        ContextCompat.getColor(this, R.color.semi_transparent),
                        ContextCompat.getColor(this, R.color.white),
                        ContextCompat.getColor(this, R.color.white),
                        ContextCompat.getColor(this, R.color.white),
                        ContextCompat.getColor(this, R.color.white),
                        ContextCompat.getColor(this, R.color.white),
                        ContextCompat.getColor(this, R.color.semi_transparent),
                        ContextCompat.getColor(this, R.color.transparent)
                });
        findViewById(R.id.logo_ll).setBackground(gradientDrawable);

        /*
            Set up brand name
         */
        getBrand.setText(String.valueOf(Build.BRAND).toUpperCase());

        /*
            Set up model name using AndroidDeviceNames library
         */
        DeviceName.init(this);
        getModel.setText(DeviceName.getDeviceName());
    }
}