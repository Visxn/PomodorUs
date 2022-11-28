package com.example.pomodorus;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment
 * create an instance of this fragment.
 */
public class SoloTimerFragment extends Fragment {

    protected Button timer25, timer50, yesMusic, noMusic, startSession;
    protected Boolean time = true;
    protected Boolean music =false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_solo_timer, container,
                false);

        timer25 = view.findViewById(R.id.time25);
        timer50 = view.findViewById(R.id.timer50);
        yesMusic = view.findViewById(R.id.yesBtn);
        noMusic = view.findViewById(R.id.noMusicBtn);


        //Used to select minuts of the counter
        timer25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "You have choosen 25 minutes session"
                        , Toast.LENGTH_SHORT).show();
                time = true;
            }
        });
        timer50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "You have choosen 50 minutes session"
                        , Toast.LENGTH_SHORT).show();
                time = false;
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

        return view;
    }
}



