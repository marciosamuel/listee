package com.dispositivos.moveis.listee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Adapters.ProductListAdapter;
import Models.ProdutoModel;

public class ListaDeComprasActivity extends AppCompatActivity {
    private static ProductListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_compras);

        List<ProdutoModel> produtos = getProdutos();
        ListView listaDeProdutos = (ListView) findViewById(R.id.sale_list_products);
        Button btnAddProduct = findViewById(R.id.sale_list_btn_add_product);

        adapter = new ProductListAdapter(this, produtos);
        listaDeProdutos.setAdapter(adapter);

        Bundle data = getIntent().getExtras();
        String idProduct = data.getString("ID_PRODUCT");

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ListaDeComprasActivity.this, R.style.popupInspirationTheme);
                View popupAddProduct = LayoutInflater.from(ListaDeComprasActivity.this).inflate(R.layout.activity_popup_new_product, findViewById(R.id.popup_new_product_container));

                EditText newProductName = findViewById(R.id.new_product_name);
                EditText newProductQuantity = findViewById(R.id.new_product_quantity);
                Button addProduct = findViewById(R.id.btn_new_product);

                bottomSheetDialog.setContentView(popupAddProduct);
                bottomSheetDialog.show();
            }
        });
    }

    protected List<ProdutoModel> getProdutos(){
        return new ArrayList<>(Arrays.asList(
                new ProdutoModel( "feijão", 5),
                new ProdutoModel( "arroz", 4),
                new ProdutoModel( "macarrão", 3),
                new ProdutoModel( "óleo", 3),
                new ProdutoModel("margarina", 2)
        ));
    }
}