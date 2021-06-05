package com.dispositivos.moveis.listee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RegisterActivity extends AppCompatActivity {

    private EditText registerUser;
    private EditText registerEmail;
    private EditText registerPassword;
    private Button btnRegister;
    private TextView linkToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerUser = findViewById(R.id.register_user);
        registerEmail = findViewById(R.id.register_email);
        registerPassword = findViewById(R.id.register_password);

        linkToLogin = findViewById(R.id.link_to_login);
        btnRegister = findViewById(R.id.buttom_register);

        linkToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
}