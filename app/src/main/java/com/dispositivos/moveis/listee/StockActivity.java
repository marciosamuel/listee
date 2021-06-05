package com.dispositivos.moveis.listee;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class StockActivity extends Fragment {

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater,@NonNull ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.activity_stock, container, false);

        ListView listaProdutos = view.findViewById(R.id.estoque_listView);
        Button adicionarProduto = view.findViewById(R.id.estoque_Button);

        Produto produto = new Produto();

        produto.setContainer(view.findViewById(R.id.produto_cardview));
        produto.setNome(view.findViewById(R.id.produto_nome));
        produto.setImagem(view.findViewById(R.id.produto_image));
        produto.setQuantidade(view.findViewById(R.id.produto_quantidade_valor));
        produto.setUnidade(view.findViewById(R.id.produto_unidade));
        produto.setAumentarQuantidade(view.findViewById(R.id.produto_botao_aumentar));
        produto.setDiminuirQuantidade(view.findViewById(R.id.produto_botao_diminuir));
        produto.setRemoverProduto(view.findViewById(R.id.produto_botao_remover));

        return view;
    }
}