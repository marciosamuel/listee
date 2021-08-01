package com.dispositivos.moveis.listee;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import Adapters.ProdutoAdapter;
import Models.ProdutoModel;
import Models.UserModel;

public class StockActivity extends Fragment {
    private static ProdutoAdapter adapter;

    private CollectionReference stock = FirebaseFirestore.getInstance().collection("stock");
    private FirebaseAuth mAuth;
    private FirebaseUser userId;
    private ListView listProducts;

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater,@NonNull ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.activity_stock, container, false);

        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser();

        listProducts = (ListView) view.findViewById(R.id.estoque_listView);
        Button btnAddProduct = view.findViewById(R.id.estoque_Button);

        getProducts(view);

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity(), R.style.popupInspirationTheme);
                View popupAddProduct = LayoutInflater.from(getActivity()).inflate(R.layout.activity_popup_new_product, view.findViewById(R.id.popup_new_product_container));

                EditText newProductName = popupAddProduct.findViewById(R.id.new_product_name);
                EditText newProductQuantity = popupAddProduct.findViewById(R.id.new_product_quantity);
                Button addProduct = popupAddProduct.findViewById(R.id.btn_new_product);

                addProduct.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!newProductName.getText().toString().equals("")){
                            Log.d("newProduct: ", "name");
                            if (!TextUtils.isEmpty(newProductQuantity.getText().toString())) {
                                Log.d("newProduct: ", "quantidade");
                                if (Integer.parseInt(newProductQuantity.getText().toString()) > 0) {
                                    Log.d("newProduct: ", "quantidade");
                                    createProduct(view, newProductName.getText().toString(), Integer.parseInt(newProductQuantity.getText().toString()));
                                    bottomSheetDialog.cancel();
                                } else {
                                    Toast.makeText(getContext(), "Quantidade menor que 1", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getContext(), "Quantidade vazia", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "Nome vazio", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                bottomSheetDialog.setContentView(popupAddProduct);
                bottomSheetDialog.show();
            }
        });

        listProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProdutoModel product = adapter.getItem(position);
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity(), R.style.popupInspirationTheme);
                View popupEditProduct = LayoutInflater.from(getContext()).inflate(R.layout.activity_popup_edit_product, view.findViewById(R.id.popup_edit_product_container));

                EditText editProductName = popupEditProduct.findViewById(R.id.edit_product_name);
                EditText editProductQuantity = popupEditProduct.findViewById(R.id.edit_product_quantity);
                Button editProduct = popupEditProduct.findViewById(R.id.btn_edit_product);
                Button deleteProduct = popupEditProduct.findViewById(R.id.btn_delete_product);

                editProductName.setText(product.getNome());
                editProductQuantity.setText(product.getQuantidade());

                editProduct.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!editProductName.getText().toString().equals("")){
                            Log.d("editProduct: ", "name");
                            if (!TextUtils.isEmpty(editProductQuantity.getText().toString())) {
                                Log.d("editProduct: ", "quantidade");
                                if (Integer.parseInt(editProductQuantity.getText().toString()) > 0) {
                                    Log.d("editProduct: ", "quantidade");
                                    product.setNome(editProductName.getText().toString());
                                    product.setQuantidade(Integer.parseInt(editProductQuantity.getText().toString()));
                                    editProduct(view, product);
                                    bottomSheetDialog.cancel();
                                } else {
                                    Toast.makeText(getContext(), "Quantidade menor que 1", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getContext(), "Quantidade vazia", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "Nome vazio", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                deleteProduct.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        stock.document(product.getId()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<Void> task) {
                                if(task.isSuccessful()) {
                                    Toast.makeText(getContext(), "Produto removido com sucesso", Toast.LENGTH_SHORT).show();
                                    getProducts(view);
                                } else {
                                    Toast.makeText(getContext(), "Falha ao remover produto", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        bottomSheetDialog.cancel();
                    }
                });

                bottomSheetDialog.setContentView(popupEditProduct);
                bottomSheetDialog.show();
            }
        });

        return view;
    }

    private void getProducts(View view) {
        stock.whereEqualTo("userId", userId.getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> taskList) {
                if (taskList.isSuccessful()) {
                    ArrayList<ProdutoModel> products = new ArrayList<>();
                    for(QueryDocumentSnapshot documentList : taskList.getResult()){
                        ProdutoModel product = new ProdutoModel((String) documentList.get("id"), (String) documentList.get("userId"), (String) documentList.get("nome"), Integer.parseInt(documentList.get("quantidade").toString()));
                        products.add(product);
                    }
                    adapter = new ProdutoAdapter(view.getContext(), products);
                    listProducts.setAdapter(adapter);
                }
            }
        });
    }

    private void createProduct(View view, final String name, int quantity) {
        ProdutoModel product = new ProdutoModel(UUID.randomUUID().toString(), userId.getUid(), name, quantity);
        stock.document(product.getId()).set(product).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(getContext(), "Produto adicionado com sucesso", Toast.LENGTH_SHORT).show();
                    getProducts(view);
                } else {
                    Toast.makeText(getContext(), "Falha ao adicionar produto", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void editProduct(View view, ProdutoModel produto) {
        stock.document(produto.getId()).set(produto).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(getContext(), "Produto editado com sucesso", Toast.LENGTH_SHORT).show();
                    getProducts(view);
                } else {
                    Toast.makeText(getContext(), "Falha ao editar produto", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}