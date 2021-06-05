package com.dispositivos.moveis.listee;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import Adapters.HomeCardAdapter;
import Models.HomeCardModel;

public class HomeActivity extends Fragment {

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater,@NonNull ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.activity_home, container, false);

        ImageView imageView = view.findViewById(R.id.add_item);
        ListView listView = view.findViewById(R.id.list_cards);

        HomeCardAdapter homeCardAdapter = new HomeCardAdapter(view.getContext());
        listView.setAdapter(homeCardAdapter);

        homeCardAdapter.add(new HomeCardModel("Compras do Mês", "Itens selecionados: 10", "Itens restantes: 10"));

        return view;
    }
}
