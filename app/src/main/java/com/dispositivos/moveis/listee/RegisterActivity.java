package com.dispositivos.moveis.listee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import Models.UserModel;

public class RegisterActivity extends AppCompatActivity {

    private CollectionReference users = FirebaseFirestore.getInstance().collection("users");
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        EditText registerUser = findViewById(R.id.register_user);
        EditText registerEmail = findViewById(R.id.register_email);
        EditText registerPassword = findViewById(R.id.register_password);

        TextView linkToLogin = findViewById(R.id.link_to_login);
        Button btnRegister = findViewById(R.id.buttom_register);

        linkToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!registerEmail.getText().toString().equals("")) {
                    Log.d("Btn: ", "email");
                    if (!registerUser.getText().toString().equals("")) {
                        Log.d("Btn: ", "user");
                        if(registerPassword.getText().length() > 4) {
                            Log.d("Btn", "password");
                            createUser(registerUser.getText().toString(), registerEmail.getText().toString(), registerPassword.getText().toString());
                        } else {
                            Toast.makeText(RegisterActivity.this, "Senha tem que ter mais de 4 caracteres", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Usuário vazio", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "E-mail vazio", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void createUser(final String user, final String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Log.d("Login", "createUserWithEmail:success");
                            FirebaseUser userId = mAuth.getCurrentUser();
                            users.document(userId.getUid()).set(new UserModel(user, email));
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        } else {
                            Toast.makeText(RegisterActivity.this, "Falha na autenticação", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}