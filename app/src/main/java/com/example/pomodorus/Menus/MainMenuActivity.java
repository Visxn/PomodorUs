package com.example.pomodorus.Menus;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.example.pomodorus.ChatFragment;
import com.example.pomodorus.HistoryFragment;
import com.example.pomodorus.R;
import com.example.pomodorus.SoloTimerFragment;
import com.google.firebase.auth.FirebaseAuth;

//activity for the app menu

public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {
    private ColorStateList def;
    private TextView itemm1, itemm2, itemm3, select;
    private ImageView logout;
    private String email;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_activity);

        user = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        itemm1 =findViewById(R.id.item1);
        itemm2 =findViewById(R.id.item2);
        itemm3 =findViewById(R.id.item3);
        logout = findViewById(R.id.logout_btn);

        itemm1.setOnClickListener(this);
        itemm2.setOnClickListener(this);
        itemm3.setOnClickListener(this);
        logout.setOnClickListener(this);

        select = findViewById(R.id.selector);
        def = itemm2.getTextColors();

    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.item1){
            select.animate().x(0).setDuration(300);
            itemm1.setTextColor(Color.WHITE);
            itemm2.setTextColor(def);
            itemm3.setTextColor(def);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.move_to_right, R.anim.move_to_left)
                    .replace(R.id.fragmentFrame, SoloTimerFragment.class, null)
                    .setReorderingAllowed(true)
                    .commit();

        }else if( view.getId()== R.id.item2){
            itemm1.setTextColor(def);
            itemm2.setTextColor(Color.WHITE);
            itemm3.setTextColor(def);
            int size = itemm2.getWidth();
            select.animate().x(size).setDuration(300);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.move_to_right, R.anim.move_to_left)
                    .replace(R.id.fragmentFrame, HistoryFragment.class, null)
                    .setReorderingAllowed(true)
                    .commit();
        }else if( view.getId() == R.id.item3){
            itemm1.setTextColor(def);
            itemm2.setTextColor(def);
            itemm3.setTextColor(Color.WHITE);
            int size = itemm2.getWidth() * 2;
            select.animate().x(size).setDuration(300);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.move_to_right, R.anim.move_to_left)
                    .replace(R.id.fragmentFrame, ChatFragment.class, null)
                    .setReorderingAllowed(true)
                    .commit();
        }else if( view.getId() == R.id.logout_btn){
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(MainMenuActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}



