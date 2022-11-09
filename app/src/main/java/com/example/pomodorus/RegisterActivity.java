package com.example.pomodorus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.text.Format;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private EditText edName, edEmail, edPassword;
    private Button signBtn, backLogin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_tab_fragment);
        mAuth = FirebaseAuth.getInstance();

        edEmail = findViewById(R.id.email);
        edName = findViewById(R.id.name);
        edPassword = findViewById(R.id.passw);
        signBtn = findViewById(R.id.signupBtn);
        signBtn.setOnClickListener(this);
        backLogin = findViewById(R.id.gotAcc);
        backLogin.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.signupBtn:
                registerUser();
                break;
            case R.id.gotAcc:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void registerUser() {
        String email = edEmail.getText().toString().trim();
        String name = edName.getText().toString().trim();
        String passw = edPassword.getText().toString().trim();

        if (name.isEmpty()){
            edName.setError("Name is required!");
            edName.requestFocus();
            return;
        }
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
