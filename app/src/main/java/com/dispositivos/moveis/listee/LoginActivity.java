package com.dispositivos.moveis.listee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        EditText loginEmail = findViewById(R.id.login_email);
        EditText loginPassword = findViewById(R.id.login_password);

        Button btnLogin = findViewById(R.id.button_login);
        TextView linkToRegister = findViewById(R.id.link_to_register);

        linkToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!loginEmail.getText().toString().equals("")) {
                    Log.d("Btn: ", "email");
                    if (!loginPassword.getText().toString().equals("")) {
                        Log.d("Btn", "password");
                        SingIn(loginEmail.getText().toString(), loginPassword.getText().toString());
                    } else {
                        Toast.makeText(LoginActivity.this, "Senha vazio", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "E-mail vazio", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void SingIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "Falha na autenticação", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}