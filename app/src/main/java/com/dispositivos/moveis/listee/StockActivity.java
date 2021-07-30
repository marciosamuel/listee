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

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Adapters.ProdutoAdapter;
import Models.ProdutoModel;
import Models.UserModel;

public class StockActivity extends Fragment {
    private static ProdutoAdapter adapter;

    private CollectionReference stock = FirebaseFirestore.getInstance().collection("stock");
    private FirebaseAuth mAuth;
    private FirebaseUser userId;

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater,@NonNull ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.activity_stock, container, false);

        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser();

        List<ProdutoModel> produtos = getProdutos();
        ListView listaDeProdutos = (ListView) view.findViewById(R.id.estoque_listView);
        Button btnAddProduct = view.findViewById(R.id.estoque_Button);

        adapter = new ProdutoAdapter(this.getContext(), produtos);
        listaDeProdutos.setAdapter(adapter);

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
                                    createProduct(newProductName.getText().toString(), Integer.parseInt(newProductQuantity.getText().toString()));
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

        return view;
    }

    private void createProduct(final String name, int quantity) {
        ProdutoModel product = new ProdutoModel(userId.getUid(), name, quantity);
        stock.add(product).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DocumentReference> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(getContext(), "Produto adicionado com sucesso", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Falha ao adicionar produto", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    protected List<ProdutoModel> getProdutos(){
        return new ArrayList<>(Arrays.asList(
                new ProdutoModel( "123", "feijão", 5),
                new ProdutoModel( "123", "arroz", 4)
        ));
    }
}