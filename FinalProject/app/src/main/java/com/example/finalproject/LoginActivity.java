package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText emailField = findViewById(R.id.email_edt_text);
        EditText passwordField = findViewById(R.id.pass_edt_text);
        Button loginButton = findViewById(R.id.login_btn);
        Button registerButton = findViewById(R.id.signup_btn);
        TextView resetPassword = findViewById(R.id.reset_pass_tv);
        mAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(view -> {
            String email = emailField.getText().toString();
            String password = passwordField.getText().toString();

            if(email.isEmpty() || password.isEmpty()) {
                new AlertDialog.Builder(this)
                        .setTitle("Fill all the fields")
                        .setPositiveButton("Ok",null)
                        .show();
            }
            else {
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Successfully Logged in", Toast. LENGTH_LONG);
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            new AlertDialog.Builder(LoginActivity.this)
                                    .setTitle("Login Failed")
                                    .setPositiveButton("Ok",null)
                                    .show();
                        }
                    }
                });
//                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, task -> {
//                    System.out.println("IM HERE");
//                    if(task.isSuccessful()) {
//                        new AlertDialog.Builder(this)
//                                .setTitle("Successfully Logged In")
//                                .setPositiveButton("Ok",null)
//                                .show();
//                        Intent intent = new Intent(this, MainActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                    else {
//                        new AlertDialog.Builder(this)
//                                .setTitle("Login Failed")
//                                .setPositiveButton("Ok",null)
//                                .show();
//                    }
//                });
            }
        });

        registerButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });

        resetPassword.setOnClickListener(view -> {
            Intent intent = new Intent(this, ResetPasswordActivity.class);
            startActivity(intent);
        });
    }
}