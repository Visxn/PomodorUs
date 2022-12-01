package com.example.pomodorus;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * A simple {@link Fragment
 * create an instance of this fragment.
 */
public class SoloTimerFragment extends Fragment {

    protected Button timer25, timer50, yesMusic, noMusic, startSession;
    protected Boolean time25 = true;
    protected Boolean music =false;
    protected Button startTimer;
    protected EditText sessionName;
    FirebaseFirestore db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_solo_timer, container, false);

        timer25 = view.findViewById(R.id.time25);
        timer50 = view.findViewById(R.id.timer50);
        yesMusic = view.findViewById(R.id.yesBtn);
        noMusic = view.findViewById(R.id.noMusicBtn);
        startTimer = view.findViewById(R.id.SessionBtn);
        sessionName = view.findViewById(R.id.session_name);
         db = FirebaseFirestore.getInstance();
        //Used to select minuts of the counter
        timer25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "You have choosen 25 minutes session"
                        , Toast.LENGTH_SHORT).show();
                time25 = true;
            }
        });
        timer50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "You have choosen 50 minutes session"
                        , Toast.LENGTH_SHORT).show();
                time25 = false;
            }
        });

        //To know if music will be played
        yesMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Music enabled"
                        , Toast.LENGTH_SHORT).show();
                music = true;
            }
        });
        noMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Music disabled"
                        , Toast.LENGTH_SHORT).show();
                music = false;
            }
        });

        startTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                adInfoToDb();

                if (time25 == true && music == true) {
                    Intent intent = new Intent(getActivity(), TimerActivity.class);
                    intent.putExtra("duration", 1500);
                    intent.putExtra("music", true);
                    startActivity(intent);
                }else if (time25 == false && music == true) {
                    Intent intent = new Intent(getActivity(), TimerActivity.class);
                    intent.putExtra("duration", 3000);
                    intent.putExtra("music", true);
                    startActivity(intent);
                }else if (time25 == false && music == false) {
                    Intent intent = new Intent(getActivity(), TimerActivity.class);
                    intent.putExtra("duration", 3000);
                    intent.putExtra("music", false);
                    startActivity(intent);
                }else if (time25 == true && music == false) {
                    Intent intent = new Intent(getActivity(), TimerActivity.class);
                    intent.putExtra("duration", 1500);
                    intent.putExtra("music", false);
                    startActivity(intent);
                }
            }
        });
        return view;
    }

    private void adInfoToDb() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmm", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        String StudySession = sessionName.getText().toString();
        Map<String, Object> sesInfo = new HashMap<>();
        sesInfo.put("sessionName", StudySession);
        sesInfo.put("date", currentDateandTime);

        db.collection("Sessions")
                .add(sesInfo)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getActivity(), "Session Added Successfully", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Session Added Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}



