package com.dispositivos.moveis.listee;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import Adapters.InspirationCardAdapter;
import Models.HomeCardModel;
import Models.InspirationCardModel;
import Models.ProductListModel;

public class InspirationsActivity extends Fragment {
    private CollectionReference inspirations = FirebaseFirestore.getInstance().collection("inspirations");
    private CollectionReference inspirationList = FirebaseFirestore.getInstance().collection("inspiration_list");
    private CollectionReference lists = FirebaseFirestore.getInstance().collection("lists");
    private CollectionReference productsList = FirebaseFirestore.getInstance().collection("products_list");

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater,@NonNull ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.activity_inspirations, container, false);

        ListView listView = view.findViewById(R.id.list_cards_inspirations);

        InspirationCardAdapter inspirationCardAdapter = new InspirationCardAdapter(view.getContext());
        listView.setAdapter(inspirationCardAdapter);

        List<ArrayList> contentItens = new ArrayList<>();
        inspirations.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        inspirationCardAdapter.add(new InspirationCardModel((String) document.getId(), (String) document.get("title"), (String) document.get("subTitle"), (String) document.get("author")));
                        List<String> itens = new ArrayList<>();
                        inspirationList.whereEqualTo("inspirationCardId", document.getId()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> taskList) {
                                if(taskList.isSuccessful()){
                                    for(QueryDocumentSnapshot documentList : taskList.getResult()){
                                        itens.add((String) documentList.get("nameProduct"));
                                    }
                                }
                            }
                        });
                        contentItens.add((ArrayList) itens);
                    }
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity(), R.style.popupInspirationTheme);
                View popupInspiration = LayoutInflater.from(getActivity()).inflate(R.layout.activity_popup_inspirations, view.findViewById(R.id.popup_inspiration_container));
                ListView listView = popupInspiration.findViewById(R.id.list_view_itens);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, contentItens.get(position));
                listView.setAdapter(arrayAdapter);

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);

                Button buttonAddToList = popupInspiration.findViewById(R.id.add_to_list);

                buttonAddToList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        inspirationList.whereEqualTo("inspirationCardId", inspirationCardAdapter.getItem(position).getId()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                                int count = 0;
                                String uuid = UUID.randomUUID().toString();
                                if(task.isSuccessful()){
                                    for(QueryDocumentSnapshot documentList : task.getResult()){
                                        count++;
                                        String uuid_product = UUID.randomUUID().toString();
                                        productsList.add(new ProductListModel(uuid_product, uuid, documentList.get("nameProduct").toString(), Integer.parseInt(documentList.get("quantity").toString()), false));
                                    }
                                }
                                lists.document(uuid).set(new HomeCardModel(uuid, sharedPreferences.getString("id", ""), inspirationCardAdapter.getItem(position).getTitle(), inspirationCardAdapter.getItem(position).getSubTitle(),count)).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(view.getContext(), "Item adicionado a sua lista com sucesso.", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(view.getContext(), "Não foi possível adicionar a lista.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        });
                    }
                });

                TextView titlePopupInspirations = popupInspiration.findViewById(R.id.title_popup_inspirations);
                titlePopupInspirations.setText(inspirationCardAdapter.getItem(position).getTitle());
                TextView subtitlePopupInspirations = popupInspiration.findViewById(R.id.subtitle_popup_inspirations);
                subtitlePopupInspirations.setText(inspirationCardAdapter.getItem(position).getSubTitle());
                TextView authorPopupInspirations = popupInspiration.findViewById(R.id.author_popup_inspirations);
                authorPopupInspirations.setText("Autor: "+inspirationCardAdapter.getItem(position).getAuthor());

                bottomSheetDialog.setContentView(popupInspiration);
                bottomSheetDialog.show();
            }
        });

        return view;
    }

}
