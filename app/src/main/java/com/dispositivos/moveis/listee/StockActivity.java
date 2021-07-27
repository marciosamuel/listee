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
import java.util.Arrays;
import java.util.List;

import Adapters.ProdutoAdapter;
import Models.ProdutoModel;

public class StockActivity extends Fragment {
    private static ProdutoAdapter adapter;

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater,@NonNull ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.activity_stock, container, false);

        List<ProdutoModel> produtos = getProdutos();
        ListView listaDeProdutos = (ListView) view.findViewById(R.id.estoque_listView);

        adapter = new ProdutoAdapter(this.getContext(), produtos);
        listaDeProdutos.setAdapter(adapter);

        return view;
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