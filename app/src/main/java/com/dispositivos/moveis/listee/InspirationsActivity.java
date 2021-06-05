package com.dispositivos.moveis.listee;

import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
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

//        CardView objCardView = view.findViewById(R.id.popup_inspiration_button);
//        objCardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity(), R.style.popupInspirationTheme);
//                View popupInspiration = LayoutInflater.from(getActivity()).inflate(R.layout.activity_popup_inspirations, view.findViewById(R.id.popup_inspiration_container));
//                ListView listView = popupInspiration.findViewById(R.id.list_view_itens);
//                List<String> itens = new ArrayList<>();
//                itens.add("Macarrão espaguete");
//                itens.add("Molho de tomate");
//                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, itens);
//                listView.setAdapter(arrayAdapter);
//                bottomSheetDialog.setContentView(popupInspiration);
//                bottomSheetDialog.show();
//            }
//        });

        ListView listView = view.findViewById(R.id.list_cards_inspirations);

        InspirationCardAdapter inspirationCardAdapter = new InspirationCardAdapter(view.getContext());
        listView.setAdapter(inspirationCardAdapter);

        inspirationCardAdapter.add(new InspirationCardModel("Brunch de domingo", "Macarronada italiana", "dev_claudio"));
        
        return view;
    }

}
