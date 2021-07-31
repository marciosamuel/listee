package com.dispositivos.moveis.listee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

    String nome = "Lista de teste";
    String descricao = "Lista feita apenas para testar o funcionamento e verificar se está tudo certo";
    boolean isInspiration = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_compras);

        List<ProdutoModel> produtos = getProdutos();
        ListView listaDeProdutos = (ListView) findViewById(R.id.sale_list_products);
        Button btnAddProduct = findViewById(R.id.sale_list_btn_add_product);

        TextView name = findViewById(R.id.sale_list_header_text);
        name.setText(nome);
        TextView description = findViewById(R.id.sale_list_description);
        description.setText(descricao);

        ProductListAdapter adapter = new ProductListAdapter(this, produtos);
      
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
                new ProdutoModel( "1", "123",  "feijão", 5),
                new ProdutoModel( "2", "123", "macarrão", 3),
                new ProdutoModel("3", "123", "margarina", 2)
        ));
    }
}