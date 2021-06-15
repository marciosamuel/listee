package com.dispositivos.moveis.listee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Adapters.ProdutoAdapter;
import Models.ProdutoModel;

public class ListaDeComprasActivity extends AppCompatActivity {
    private static ProdutoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_compras);

        List<ProdutoModel> produtos = getProdutos();
        ListView listaDeProdutos = (ListView) findViewById(R.id.lista_compras_listView);

        adapter = new ProdutoAdapter(this, produtos);
        listaDeProdutos.setAdapter(adapter);

        Bundle data = getIntent().getExtras();
        String idProduct = data.getString("ID_PRODUCT");
    }

    protected List<ProdutoModel> getProdutos(){
        return new ArrayList<>(Arrays.asList(
                new ProdutoModel( "feijão", "1 Kg", "5"),
                new ProdutoModel( "arroz", "1 Kg", "4"),
                new ProdutoModel( "macarrão", "1 Kg", "3"),
                new ProdutoModel( "óleo", "1 L", "1"),
                new ProdutoModel("margarina", "500g", "2")
        ));
    }
}