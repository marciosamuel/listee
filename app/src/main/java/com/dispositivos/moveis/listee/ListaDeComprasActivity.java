package com.dispositivos.moveis.listee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Adapters.ProdutoAdapter;
import Models.ProdutoModel;

public class ListaDeComprasActivity extends AppCompatActivity {


    String nome = "Lista de teste";
    String descricao = "Lista feita apenas para testar o funcionamento e verificar se está tudo certo";
    boolean isInspiration = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_compras);

        List<ProdutoModel> produtos = getProdutos();
        ListView listaDeProdutos = (ListView) findViewById(R.id.sale_list_products);

        TextView name = findViewById(R.id.sale_list_header_text);
        name.setText(nome);
        TextView description = findViewById(R.id.sale_list_description);
        description.setText(descricao);

        ProdutoAdapter adapter = new ProdutoAdapter(this, produtos);
        listaDeProdutos.setAdapter(adapter);

        Bundle data = getIntent().getExtras();
        String idProduct = data.getString("ID_PRODUCT");

        Context context = this;

        ImageButton editBtn = findViewById(R.id.sale_list_btn_edit);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, EditListActivity.class);
                i.putExtra("LIST_NAME", nome);
                i.putExtra("LIST_DESCRIPTION", descricao);
                i.putExtra("LIST_INSPIRATION", isInspiration);
                startActivity(i);
            }
        });
    }

    protected List<ProdutoModel> getProdutos(){
        return new ArrayList<>(Arrays.asList(
                new ProdutoModel( "feijão", "Kg", 5, 2),
                new ProdutoModel( "arroz", "g", 4, 500),
                new ProdutoModel( "macarrão", "Kg", 3, 1),
                new ProdutoModel( "óleo", "ml", 3, 500),
                new ProdutoModel("margarina", "g", 2, 500)
        ));
    }
}