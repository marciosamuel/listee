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
        ListView listaDeProdutos = (ListView) findViewById(R.id.sale_list_products);

        adapter = new ProdutoAdapter(this, produtos);
        listaDeProdutos.setAdapter(adapter);

        Bundle data = getIntent().getExtras();
        String idProduct = data.getString("ID_PRODUCT");
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