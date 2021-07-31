package com.dispositivos.moveis.listee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import Adapters.ProductListAdapter;
import Adapters.ProdutoAdapter;
import Models.ProductListModel;
import Models.ProdutoModel;

public class ListaDeComprasActivity extends AppCompatActivity {
    boolean isInspiration = true;
    private static ProductListAdapter adapter;

    private CollectionReference lists = FirebaseFirestore.getInstance().collection("lists");
    private CollectionReference products_list = FirebaseFirestore.getInstance().collection("products_list");

    private ListView listProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_compras);

        listProducts = (ListView) findViewById(R.id.sale_list_products);
        Button btnAddProduct = findViewById(R.id.sale_list_btn_add_product);

        TextView name = findViewById(R.id.sale_list_header_text);
        TextView description = findViewById(R.id.sale_list_description);

        Bundle data = getIntent().getExtras();
        String listId = data.getString("LIST_ID");

        getProducts(listId);

        lists.document(listId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    name.setText(task.getResult().get("title").toString());
                    description.setText(task.getResult().get("subTitle").toString());
                }
            }
        });

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ListaDeComprasActivity.this, R.style.popupInspirationTheme);
                View popupAddProduct = LayoutInflater.from(ListaDeComprasActivity.this).inflate(R.layout.activity_popup_new_product, findViewById(R.id.popup_new_product_container));

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
                                    createProduct(listId, newProductName.getText().toString(), Integer.parseInt(newProductQuantity.getText().toString()));
                                    bottomSheetDialog.cancel();
                                } else {
                                    Toast.makeText(ListaDeComprasActivity.this, "Quantidade menor que 1", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(ListaDeComprasActivity.this, "Quantidade vazia", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(ListaDeComprasActivity.this, "Nome vazio", Toast.LENGTH_SHORT).show();
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
                ProductListModel product = adapter.getItem(position);
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ListaDeComprasActivity.this, R.style.popupInspirationTheme);
                View popupEditProduct = LayoutInflater.from(ListaDeComprasActivity.this).inflate(R.layout.activity_popup_edit_product, view.findViewById(R.id.popup_edit_product_container));

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
                                    editProduct(listId, product);
                                    bottomSheetDialog.cancel();
                                } else {
                                    Toast.makeText(ListaDeComprasActivity.this, "Quantidade menor que 1", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(ListaDeComprasActivity.this, "Quantidade vazia", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(ListaDeComprasActivity.this, "Nome vazio", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                deleteProduct.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        products_list.document(product.getId()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<Void> task) {
                                if(task.isSuccessful()) {
                                    Toast.makeText(ListaDeComprasActivity.this, "Produto removido com sucesso", Toast.LENGTH_SHORT).show();
                                    getProducts(listId);
                                } else {
                                    Toast.makeText(ListaDeComprasActivity.this, "Falha ao remover produto", Toast.LENGTH_SHORT).show();
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

        Context context = this;

        ImageButton editBtn = findViewById(R.id.sale_list_btn_edit);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, EditListActivity.class);
                i.putExtra("LIST_NAME", name.getText());
                i.putExtra("LIST_DESCRIPTION", description.getText());
                i.putExtra("LIST_INSPIRATION", isInspiration);
                startActivity(i);
            }
        });
    }

    private void getProducts(String listId){
        products_list.whereEqualTo("listId", listId).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> taskList) {
                if (taskList.isSuccessful()) {
                    ArrayList<ProductListModel> products = new ArrayList<>();
                    for(QueryDocumentSnapshot documentList : taskList.getResult()){
                        ProductListModel product = new ProductListModel((String) documentList.get("id"), (String) documentList.get("listId"), (String) documentList.get("nome"), Integer.parseInt(documentList.get("quantidade").toString()), (Boolean) documentList.get("checked"));
                        products.add(product);
                    }
                    adapter = new ProductListAdapter(ListaDeComprasActivity.this, products);
                    listProducts.setAdapter(adapter);
                }
            }
        });
    }

    private void createProduct(String listId, final String name, int quantity) {
        ProductListModel product = new ProductListModel(UUID.randomUUID().toString(), listId, name, quantity, false);
        products_list.document(product.getId()).set(product).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(ListaDeComprasActivity.this, "Produto adicionado com sucesso", Toast.LENGTH_SHORT).show();
                    getProducts(listId);
                } else {
                    Toast.makeText(ListaDeComprasActivity.this, "Falha ao adicionar produto", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void editProduct(String listId, ProductListModel produto) {
        products_list.document(produto.getId()).set(produto).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(ListaDeComprasActivity.this, "Produto editado com sucesso", Toast.LENGTH_SHORT).show();
                    getProducts(listId);
                } else {
                    Toast.makeText(ListaDeComprasActivity.this, "Falha ao editar produto", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}