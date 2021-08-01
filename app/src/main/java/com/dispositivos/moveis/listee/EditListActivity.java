package com.dispositivos.moveis.listee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import Adapters.ProductListAdapter;
import Models.InspirationCardModel;
import Models.InspirationListModel;
import Models.ListModel;
import Models.ProductListModel;

public class EditListActivity extends AppCompatActivity {
    private CollectionReference lists = FirebaseFirestore.getInstance().collection("lists");
    private CollectionReference products_list = FirebaseFirestore.getInstance().collection("products_list");
    private ListModel lista = new ListModel();
    Button inspiration;
    TextView name;
    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);
        // Recebendo os dados enviados pela intent
        Intent intent = getIntent();
        String id = intent.getStringExtra("LIST_ID");

        name = findViewById(R.id.edit_list_nome_input);
        description = findViewById(R.id.edit_list_description_input);
        inspiration = findViewById(R.id.edit_list_inspiration);

        getList(id);

        inspiration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CollectionReference lists = FirebaseFirestore.getInstance().collection("inspirations");
                FirebaseFirestore.getInstance().collection("users").document(lista.getUser_id()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        InspirationCardModel inspirationModel = new InspirationCardModel(lista.getId(), lista.getTitle(), lista.getSubTitle(), "");
                        inspirationModel.setAuthor(documentSnapshot.get("user").toString());
                        DocumentReference novaLista = lists.document();
                        novaLista.set(inspirationModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                inspirationProducts(lista.getId(), novaLista.getId());
                                Toast.makeText(EditListActivity.this, "Lista adicionada às inspirações", Toast.LENGTH_SHORT).show();
                                inspiration.setVisibility(View.INVISIBLE);
                            }
                        });
                    }
                });
            }
        });


        Button save = findViewById(R.id.edit_list_btn_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().length() <= 3) {
                    Toast.makeText(EditListActivity.this, "O nome da lista precisa ter mais que 3 caracteres", Toast.LENGTH_SHORT).show();
                } else if (description.getText().toString().length() == 0) {
                    Toast.makeText(EditListActivity.this, "Você precisa adicionar uma descrição", Toast.LENGTH_SHORT).show();
                } else {
                    lista.setTitle(name.getText().toString());
                    lista.setSubTitle(description.getText().toString());
                    editList(lista);
                }
            }
        });

        Button cancel = findViewById(R.id.edit_list_btn_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditListActivity.this, ListaDeComprasActivity.class);
                intent.putExtra("LIST_ID", id);
                startActivity(intent);
            }
        });

        ImageButton delete = findViewById(R.id.edit_list_btn_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteList(id);
            }
        });

    }

    private void getList(String id) {
        lists.document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    lista.setId(id);
                    lista.setTitle(task.getResult().get("title").toString());
                    lista.setSubTitle(task.getResult().get("subTitle").toString());
                    lista.setUser_id(task.getResult().get("user_id").toString());
                    lista.setQuantity(Integer.parseInt(task.getResult().get("quantity").toString()));

                    name.setText(task.getResult().get("title").toString());
                    description.setText(task.getResult().get("subTitle").toString());
                    if (Integer.parseInt(task.getResult().get("quantity").toString()) > 0) {
                        inspiration.setVisibility(View.VISIBLE);
                    } else {
                        inspiration.setVisibility(View.INVISIBLE);
                    }
                    exists(id);
                }
            }
        });
    }

    private void editList(ListModel lista) {
        lists.document(lista.getId()).set(lista).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(EditListActivity.this, "Lista editada com sucesso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditListActivity.this, ListaDeComprasActivity.class);
                    intent.putExtra("LIST_ID", lista.getId());
                    startActivity(intent);
                } else {
                    Toast.makeText(EditListActivity.this, "Falha ao editar lista", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void deleteList(String id) {
        CollectionReference list = FirebaseFirestore.getInstance().collection("products_list");
        list.whereEqualTo("listId", id).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> taskList) {
                if (taskList.isSuccessful()) {
                    ArrayList<ProductListModel> products = new ArrayList<>();
                    for (QueryDocumentSnapshot documentList : taskList.getResult()) {
                        try {
                            list.document(documentList.getId()).delete().addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull @NotNull Exception e) {
                                    throw new Error("Falhou");
                                }
                            });
                        } catch (Error err) {
                            Toast.makeText(EditListActivity.this, "Falha ao deletar lista", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                    lists.document(id).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(EditListActivity.this, "Lista deletada com sucesso", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(EditListActivity.this, "Falha ao deletar lista", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Intent intent = new Intent(EditListActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(EditListActivity.this, "Falha ao deletar lista", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void inspirationProducts(String listId, String inspirationId) {
        products_list.whereEqualTo("listId", listId).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> taskList) {
                if (taskList.isSuccessful()) {
                    for (QueryDocumentSnapshot documentList : taskList.getResult()) {
                        InspirationListModel product = new InspirationListModel(inspirationId, documentList.get("nome").toString(), Integer.parseInt(documentList.get("quantidade").toString()));

                        FirebaseFirestore.getInstance().collection("inspiration_list").add(product);
                    }
                }
            }
        });
    }

    private void exists(String listId) {
        FirebaseFirestore.getInstance().collection("inspirations").whereEqualTo("id", listId).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if (document.exists()) {
                            inspiration.setVisibility(View.INVISIBLE);
                        }
                    }
                }
            }
        });
    }
}