    package com.dispositivos.moveis.listee;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

public class ConfigurationActivity extends Fragment {

    private CollectionReference users = FirebaseFirestore.getInstance().collection("users");
    private FirebaseAuth mAuth;

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @NonNull ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.activity_configuration, container, false);

        mAuth = FirebaseAuth.getInstance();

        TextView configurationUser = view.findViewById(R.id.configuration_user);
        TextView configuratonEditAccount = view.findViewById(R.id.config_edit_account);
        TextView configuratonDeleteAccount = view.findViewById(R.id.config_delete_account);
        TextView logOut = view.findViewById(R.id.config_logout);

        //Popup edit account
        EditText editUser = view.findViewById(R.id.edit_user);
        EditText editPassword = view.findViewById(R.id.edit_password);
        Button btnEdit = view.findViewById(R.id.button_edit);

        FirebaseUser userId = mAuth.getCurrentUser();
//        DocumentSnapshot nameUser = users.whereEqualTo("id", userId.getUid()).get();
        users.whereEqualTo("id", userId.getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for(QueryDocumentSnapshot document : task.getResult()){
                        configurationUser.setText((String) document.get("user"));
                    }
                }
            }
        });

        configuratonEditAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity(), R.style.popupInspirationTheme);
                View popupEditAccount = LayoutInflater.from(getActivity()).inflate(R.layout.activity_popup_edit_account, view.findViewById(R.id.popup_edit_account_container));
                bottomSheetDialog.setContentView(popupEditAccount);
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