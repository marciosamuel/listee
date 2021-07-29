package com.dispositivos.moveis.listee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import Models.HomeCardModel;
import Models.ListModel;

public class NewListActivity extends AppCompatActivity {

    private CollectionReference lists = FirebaseFirestore.getInstance().collection("lists");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);

        Button buttonAdd = findViewById(R.id.new_list_btn_save);
        Button buttonCancel = findViewById(R.id.new_list_btn_cancel);
        EditText inputTitle = findViewById(R.id.new_list_nome_input);
        EditText inputDescription = findViewById(R.id.new_list_description_input);
        CheckBox addToInspiration = findViewById(R.id.new_list_inspiration_checkbox);

        addToInspiration.setVisibility(View.INVISIBLE);

        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputTitle.getText().toString().length() <= 3) {
                    Toast.makeText(NewListActivity.this, "O nome da lista precisa ter mais que 3 caracteres", Toast.LENGTH_SHORT).show();
                } else if (inputDescription.getText().toString().length() == 0) {
                    Toast.makeText(NewListActivity.this, "Você precisa adicionar uma descrição", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        lists.add(new ListModel(sharedPreferences.getString("id", ""), inputTitle.getText().toString(), inputDescription.getText().toString(), 0));
                        Toast.makeText(NewListActivity.this, "Lista cadastrada", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(NewListActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } catch (Exception exception) {
                        Toast.makeText(NewListActivity.this, "Não foi possível salvar a lista", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewListActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}