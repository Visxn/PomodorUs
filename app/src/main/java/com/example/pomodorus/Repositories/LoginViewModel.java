package com.example.pomodorus.Repositories;


import android.util.Patterns;
import android.widget.EditText;

import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

public class LoginViewModel extends ViewModel {
    private EditText edEmail, edPassword;
    private FirebaseAuth mAuth;

// Errors when fields are not filled correctly

    public void loginUser(String email, String passw){
        if (email.isEmpty()) {
            edEmail.setError("Email is required!");
            edEmail.requestFocus();
            return;
        }

        if (passw.isEmpty()) {
            edPassword.setError("Password is required!");
            edPassword.requestFocus();
            return;
        }

        if (edPassword.length() < 6) {
            edPassword.setError("Password must be at least 6 characters long.");
            edPassword.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edEmail.setError("Please provide a valid email!");
            edEmail.requestFocus();
            return;
        }
    }
}
