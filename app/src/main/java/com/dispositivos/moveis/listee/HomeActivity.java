package com.dispositivos.moveis.listee;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import Adapters.HomeCardAdapter;
import Models.HomeCardModel;

public class HomeActivity extends Fragment {

    private CollectionReference lists = FirebaseFirestore.getInstance().collection("lists");

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater,@NonNull ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.activity_home, container, false);

        ImageView imageView = view.findViewById(R.id.add_item);
        ListView listView = view.findViewById(R.id.list_cards);

        HomeCardAdapter homeCardAdapter = new HomeCardAdapter(view.getContext());
        listView.setAdapter(homeCardAdapter);
        lists.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        homeCardAdapter.add(new HomeCardModel((String) document.get("title"), (String) document.get("selectedItems"), (String) document.get("remainingItems")));
                    }
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(view.getContext(), ListaDeComprasActivity.class);
                i.putExtra("ID_PRODUCT", "01");
                startActivity(i);
            }
        });

        return view;
    }
}
