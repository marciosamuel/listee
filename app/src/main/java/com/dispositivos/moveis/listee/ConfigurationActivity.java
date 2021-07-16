    package com.dispositivos.moveis.listee;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

public class ConfigurationActivity extends Fragment {

    private CollectionReference users;
    private FirebaseAuth mAuth;

    private TextView configurationUser;
    private TextView configuratonDeleteAccount;
    private TextView configuratonEditAccount;
    private TextView logOut;

    private SharedPreferences sharedPreferences;

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @NonNull ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.activity_configuration, container, false);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser userAuth = mAuth.getCurrentUser();

        users = FirebaseFirestore.getInstance().collection("users");
        DocumentReference user = users.document(userAuth.getUid());

        configurationUser = view.findViewById(R.id.configuration_user);
        configuratonEditAccount = view.findViewById(R.id.config_edit_account);
        configuratonDeleteAccount = view.findViewById(R.id.config_delete_account);
        logOut = view.findViewById(R.id.config_logout);

        sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String nameUser = sharedPreferences.getString("name", "");
        configurationUser.setText(nameUser);

        configuratonEditAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity(), R.style.popupInspirationTheme);
                View popupEditAccount = LayoutInflater.from(getActivity()).inflate(R.layout.activity_popup_edit_account, view.findViewById(R.id.popup_edit_account_container));

                EditText editUser = popupEditAccount.findViewById(R.id.edit_user);
                EditText editPassword = popupEditAccount.findViewById(R.id.edit_password);
                Button btnEdit = popupEditAccount.findViewById(R.id.button_edit);

                editUser.setText(nameUser);

                btnEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!editPassword.getText().toString().isEmpty()) {
                            userAuth.updatePassword(editPassword.getText().toString());
                            Toast.makeText(popupEditAccount.getContext(), "Senha alterada com sucesso", Toast.LENGTH_SHORT).show();
                        }

                        if (editUser.getText().toString().isEmpty()) {
                            Toast.makeText(popupEditAccount.getContext(), "Usuário não pode ser vazio", Toast.LENGTH_SHORT).show();
                        } else {
                            user.update("user", editUser.getText().toString());
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("name", editUser.getText().toString()).apply();
                            Toast.makeText(popupEditAccount.getContext(), "Usuário alterado com sucesso", Toast.LENGTH_SHORT).show();
                        }
                        startActivity(new Intent(view.getContext(), MainActivity.class));
                    }
                });

                bottomSheetDialog.setContentView(popupEditAccount);
                bottomSheetDialog.show();
            }
        });

        configuratonDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity(), R.style.popupInspirationTheme);
                View popupDeleteAccount = LayoutInflater.from(getActivity()).inflate(R.layout.activity_popup_delete_account, view.findViewById(R.id.popup_delete_account_container));

                Button btnCancelDelete = popupDeleteAccount.findViewById(R.id.btn_delete_cancel);
                Button btnDelete = popupDeleteAccount.findViewById(R.id.btn_delete_account);

                btnCancelDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.cancel();
                    }
                });

                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        user.delete();
                        userAuth.delete();
                        startActivity(new Intent(view.getContext(), LoginActivity.class));
                    }
                });

                bottomSheetDialog.setContentView(popupDeleteAccount);
                bottomSheetDialog.show();
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(view.getContext(), LoginActivity.class));
            }
        });

        return view;
    }
}