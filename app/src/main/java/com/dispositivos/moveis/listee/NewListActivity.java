package com.dispositivos.moveis.listee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import Models.HomeCardModel;

public class NewListActivity extends AppCompatActivity {

    private CollectionReference lists = FirebaseFirestore.getInstance().collection("lists");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);

        Button buttonAdd = findViewById(R.id.button_add);
        Button buttonCancel = findViewById(R.id.button_cancel);
        EditText inputTitle = findViewById(R.id.input_title);
        EditText inputSubtitle = findViewById(R.id.input_subtitle);

        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    lists.add(new HomeCardModel(sharedPreferences.getString("id", ""), inputTitle.getText().toString(), inputSubtitle.getText().toString(),"Itens selecionados: 0", "Itens restantes: 0"));
                    Toast.makeText(NewListActivity.this, "Lista cadastrada", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(NewListActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }catch(Exception exception){
                    Toast.makeText(NewListActivity.this, "Não foi possível salvar a lista", Toast.LENGTH_SHORT).show();
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