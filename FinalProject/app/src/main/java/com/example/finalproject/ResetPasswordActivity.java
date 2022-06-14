package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        EditText emailField = findViewById(R.id.emailText);
        Button resetButton = findViewById(R.id.resetPasswordButton);
        Button cancelButton = findViewById(R.id.cancelButton);
        mAuth = FirebaseAuth.getInstance();

        resetButton.setOnClickListener(view -> {
            String email = emailField.getText().toString();

            if(email.isEmpty()) {
                new AlertDialog.Builder(this)
                        .setTitle("Fill all the fields")
                        .setPositiveButton("Ok",null)
                        .show();
            }
            else {
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        new AlertDialog.Builder(this)
                                .setTitle("Reset Link Sent")
                                .setPositiveButton("Ok",null)
                                .show();
                        finish();
                    } else {
                        new AlertDialog.Builder(this)
                                .setTitle("Failed to send email")
                                .setPositiveButton("Ok",null)
                                .show();
                        finish();
                    }
                });
            }
        });

        cancelButton.setOnClickListener(view -> {
            finish();
        });
    }
}