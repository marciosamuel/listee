package com.dispositivos.moveis.listee;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);

        lists.whereEqualTo("user_id", sharedPreferences.getString("id", "")).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        HomeCardModel homeCardModel = new HomeCardModel((String) document.get("user_id"), (String) document.get("title"), (String) document.get("subTitle"), (Integer) document.get("quantity"));
                        homeCardModel.setId(document.getId());
                        homeCardAdapter.add(homeCardModel);
                    }
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(view.getContext(), ListaDeComprasActivity.class);
                i.putExtra("LIST_ID", homeCardAdapter.getItem(position).getId());
                startActivity(i);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), NewListActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
