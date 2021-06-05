package com.dispositivos.moveis.listee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListaDeComprasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_compras);

        ImageView imagemTopo = findViewById(R.id.lista_compras_imageView);
        TextView nomeLista = findViewById(R.id.lista_compras_textView);
        ListView listaProdutos = findViewById(R.id.lista_compras_listView);
        Button adicionarProduto = findViewById(R.id.lista_compras_Button);

        Produto produto = new Produto();

        produto.setContainer(findViewById(R.id.produto_cardview));
        produto.setNome(findViewById(R.id.produto_nome));
        produto.setImagem(findViewById(R.id.produto_image));
        produto.setQuantidade(findViewById(R.id.produto_quantidade_valor));
        produto.setUnidade(findViewById(R.id.produto_unidade));
        produto.setAumentarQuantidade(findViewById(R.id.produto_botao_aumentar));
        produto.setDiminuirQuantidade(findViewById(R.id.produto_botao_diminuir));
        produto.setRemoverProduto(findViewById(R.id.produto_botao_remover));
    }
}