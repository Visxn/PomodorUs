package com.example.pomodorus.Models;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pomodorus.Menus.MainMenuActivity;
import com.example.pomodorus.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private EditText edName, edEmail, edPassword;
    private Button signBtn, backLogin;
    FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_tab_fragment);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

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
                String email = edEmail.getText().toString();
                String name = edName.getText().toString();
                String passw = edPassword.getText().toString();
                Map<String, Object> user = new HashMap<>();
                user.put("Email", email);
                user.put("Name", name);
                user.put("Passw", passw);
                db.collection("user").add(user);
                registerUser(edEmail, edName, edPassword);
                break;
            case R.id.gotAcc:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void registerUser(EditText edEmail, EditText edName, EditText edPassword) {
        String email = edEmail.getText().toString();
        String name = edName.getText().toString();
        String passw = edPassword.getText().toString();


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
        mAuth.createUserWithEmailAndPassword(email, passw)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(RegisterActivity.this, "Sign up sucessfuly, WELCOME!", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(RegisterActivity.this, MainMenuActivity.class));
                        finish();
                    }
                });
    }


}
