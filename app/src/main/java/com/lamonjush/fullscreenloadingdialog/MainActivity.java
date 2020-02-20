package com.lamonjush.fullscreenloadingdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FullScreenLoadingDialog.getInstance().show(this);
        Timer myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() ->
                        FullScreenLoadingDialog.getInstance().dismiss());
            }

        }, 10000, 10000);

        new Thread(() -> FullScreenLoadingDialog.getInstance().dismiss());
    }
}
