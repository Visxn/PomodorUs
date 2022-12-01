package com.example.pomodorus;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pomodorus.API.Bored;
import com.example.pomodorus.API.BoredApi;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestTimerActiviy extends AppCompatActivity {

    private TextView sentence;
    private ProgressBar progressBar;
    private TextView progressText;
    private TextView progressText_sec;
    private int i = 0;
    private int duration = 3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_timer);
        sentence = findViewById(R.id.borinText);
        progressBar = findViewById(R.id.progress_bar);
        progressText = findViewById(R.id.progress_rest_min);
        progressText_sec = findViewById(R.id.progress_rest_sec);

        setBoringSentence();
        setTimer();

    }

    private void setTimer() {
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
                finish();
            }
        }.start();

    }


    //function to get the sentence from the API service
    private void setBoringSentence() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.boredapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BoredApi boredApi = retrofit.create(BoredApi.class);

        Call<Bored> call = boredApi.getBored();
        call.enqueue(new Callback<Bored>() {
            @Override
            public void onResponse(Call<Bored> call, Response<Bored> response) {
                if (!response.isSuccessful()) {
                    sentence.setText("Code : " + response.code());
                    return;
                }
                Bored posts = response.body();
                sentence.setText(posts.getActivity());
            }

            @Override
            public void onFailure(Call<Bored> call, Throwable t) {
                sentence.setText(t.getMessage());
            }
        });
    }
}
