package com.example.pomodorus.Logins;

import static android.content.ContentValues.TAG;


import android.content.Intent;
import android.util.Log;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.pomodorus.Menus.MainMenuActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import static android.content.ContentValues.TAG;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.pomodorus.Menus.MainMenuActivity;
import com.example.pomodorus.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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
