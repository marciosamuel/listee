package com.dispositivos.moveis.listee;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import Adapters.InspirationCardAdapter;
import Models.InspirationCardModel;

public class InspirationsActivity extends Fragment {
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater,@NonNull ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.activity_inspirations, container, false);

        ListView listView = view.findViewById(R.id.list_cards_inspirations);

        InspirationCardAdapter inspirationCardAdapter = new InspirationCardAdapter(view.getContext());
        listView.setAdapter(inspirationCardAdapter);

        inspirationCardAdapter.add(new InspirationCardModel("Brunch de domingo", "Macarronada italiana", "dev_claudio"));
        inspirationCardAdapter.add(new InspirationCardModel("Feira pré-pandemia", "Itens para sobrevivência", "dev_claudio"));

        List<ArrayList> contentItens = new ArrayList<>();
        List<String> itens1 = new ArrayList<>();
        itens1.add("Macarrão espaguete");
        itens1.add("Molho de tomate");
        contentItens.add((ArrayList) itens1);
        List<String> itens2 = new ArrayList<>();
        itens2.add("Papel Higiênico");
        itens2.add("Condimentos");
        contentItens.add((ArrayList) itens2);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity(), R.style.popupInspirationTheme);
                View popupInspiration = LayoutInflater.from(getActivity()).inflate(R.layout.activity_popup_inspirations, view.findViewById(R.id.popup_inspiration_container));
                ListView listView = popupInspiration.findViewById(R.id.list_view_itens);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, contentItens.get(position));
                listView.setAdapter(arrayAdapter);

                TextView titlePopupInspirations = popupInspiration.findViewById(R.id.title_popup_inspirations);
                titlePopupInspirations.setText(inspirationCardAdapter.getItem(position).getTitle());
                TextView subtitlePopupInspirations = popupInspiration.findViewById(R.id.subtitle_popup_inspirations);
                subtitlePopupInspirations.setText(inspirationCardAdapter.getItem(position).getSubTitle());
                TextView authorPopupInspirations = popupInspiration.findViewById(R.id.author_popup_inspirations);
                authorPopupInspirations.setText("Author: "+inspirationCardAdapter.getItem(position).getAuthor());

                bottomSheetDialog.setContentView(popupInspiration);
                bottomSheetDialog.show();
            }
        });

        return view;
    }

}
