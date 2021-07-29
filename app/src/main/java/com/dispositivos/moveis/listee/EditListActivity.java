package com.dispositivos.moveis.listee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

public class EditListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);

        // Recebendo os dados enviados pela intent
        Intent intent = getIntent();
        String nome = intent.getStringExtra("LIST_NAME");
        String descricao = intent.getStringExtra("LIST_DESCRIPTION");
        boolean checked = intent.getBooleanExtra("LIST_INSPIRATION", false);

        // Setando os dados nos campos
        TextView name = findViewById(R.id.edit_list_nome_input);
        TextView description = findViewById(R.id.edit_list_description_input);
        CheckBox isInspiration = findViewById(R.id.edit_list_inspiration_checkbox);
        name.setText(nome);
        description.setText(descricao);
        isInspiration.setChecked(checked);

    }
}