package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText emailField = findViewById(R.id.email_edt_text);
        EditText passwordField = findViewById(R.id.pass_edt_text);
        Button loginButton = findViewById(R.id.login_btn);
        Button registerButton = findViewById(R.id.signup_btn);
        mAuth = FirebaseAuth.getInstance();

        registerButton.setOnClickListener(view -> {
            String email = emailField.getText().toString();
            String password = passwordField.getText().toString();

            if(email.isEmpty() || password.isEmpty()) {
                new AlertDialog.Builder(this)
                        .setTitle("Fill all the fields")
                        .setPositiveButton("Ok",null)
                        .show();
            }
            else {
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, task -> {
                    if(task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Successfully Registered", Toast. LENGTH_LONG);
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        System.out.println(task.getException().toString());
                        new AlertDialog.Builder(this)
                                .setTitle("Registration Failed")
                                .setPositiveButton("Ok",null)
                                .show();
                    }
                });
            }
        });

        loginButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}