package com.example.pomodorus;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText edEmail, edPassword;
    private Button actLogin;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_tab_fragment);

        edEmail = findViewById(R.id.email);
        edPassword = findViewById(R.id.passw);
        actLogin = findViewById(R.id.loginBtn);


    }

    private void loginUser() {
        String email = edEmail.getText().toString().trim();
        String passw = edPassword.getText().toString().trim();

        if (email.isEmpty()){
            edEmail.setError("Email is required!");
            edEmail.requestFocus();
            return;
        }

        if (passw.isEmpty()){
            edPassword.setError("Password is required!");
            edPassword.requestFocus();
            return;
        }

        if (edPassword.length() < 6){
            edPassword.setError("Password must be at least 6 characters long.");
            edPassword.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edEmail.setError("Please provide a valid email!");
            edEmail.requestFocus();

        }
    }
}
