package com.example.pomodorus;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TimerActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView progressText;
    private TextView progressText_sec;
    private int i, length;
    private int duration;
    private Boolean music = false;
    private ImageView muteMusic;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        progressBar = findViewById(R.id.progress_bar);
        progressText = findViewById(R.id.progress_text);
        progressText_sec = findViewById(R.id.progress_text_sec);
        muteMusic = findViewById(R.id.mute_music);

        Bundle bundle = getIntent().getExtras();
        duration = bundle.getInt("duration");
        music = bundle.getBoolean("music");
        MediaPlayer mediaPlayer = new MediaPlayer();

        muteMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopMusic(mediaPlayer);
            }
        });
        playSong(music, mediaPlayer);


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

        new CountDownTimer(10 * 1000, 1000) {
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
                Intent intent = new Intent(TimerActivity.this, RestTimerFragment.class);
                startActivity(intent);
            }
        }.start();

    }

    private void putDataDb() {

    }

    private void stopMusic(MediaPlayer mediaPlayer) {
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            length = mediaPlayer.getCurrentPosition();
        }else{
            mediaPlayer.seekTo(length);
            mediaPlayer.start();
        }
    }



    private void onDestroy(MediaPlayer mediaPlayer) {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();

}

    private void playSong(Boolean music, MediaPlayer mediaPlayer) {
        if(music) {
            try {
                mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/pomodorus-3f72d.appspot.com/o/Y2Mate.is%20-%204%20A.M%20Study%20Session%20%F0%9F%93%9A%20-%20%5Blofi%20hip%20hopchill%20beats%5D-TURbeWK2wwg-64k-1659861798742.mp3?alt=media&token=2e009403-0bae-4706-a4a4-b52eaeb9f404");
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mediaPlayer.start();
                    }
                });
                mediaPlayer.prepare();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

