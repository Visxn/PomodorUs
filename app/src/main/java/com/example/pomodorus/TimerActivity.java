package com.example.pomodorus;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.PersistableBundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TimerActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView progressText;
    private TextView progressText_sec;
    private int i = 0;
    private int duration = 1500;
    private Boolean timmerRunning = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        progressBar = findViewById(R.id.progress_bar);
        progressText = findViewById(R.id.progress_text);
        progressText_sec = findViewById(R.id.progress_text_sec);

       final Handler handler = new Handler();
       handler.postDelayed(new Runnable() {
           @Override
           public void run() {
               if (i <= duration){
                   progressBar.setProgress(i/15);
                   i++;
                   handler.postDelayed(this,1000);
               }else{handler.removeCallbacks(this);}
       }
       }, 1000);

        new CountDownTimer(duration * 1000, 1000) {
            @Override
            public void onTick(long l) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String timee =
                                String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(l),
                                        TimeUnit.MILLISECONDS.toMinutes(l) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(l)),
                                        TimeUnit.MILLISECONDS.toSeconds(l) -
                                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)), Locale.getDefault());

                        final String [] minSec = timee.split(":");
                        progressText.setText(minSec[1]);
                        progressText_sec.setText(minSec[2]);
                    }
                });
            }

            @Override
            public void onFinish() {
                //reset timer duration
                duration = 1500;
            }
        }.start();
    }


    }

