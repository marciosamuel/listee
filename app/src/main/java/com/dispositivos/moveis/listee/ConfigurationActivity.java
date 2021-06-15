package com.dispositivos.moveis.listee;

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

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class ConfigurationActivity extends Fragment {

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @NonNull ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.activity_configuration, container, false);

        TextView configurationUser = view.findViewById(R.id.configuration_user);
        TextView configuratonEditAccount = view.findViewById(R.id.config_edit_account);
        TextView configuratonDeleteAccount = view.findViewById(R.id.config_delete_account);

        //Popup edit account
        EditText editUser = view.findViewById(R.id.edit_user);
        EditText editPassword = view.findViewById(R.id.edit_password);
        Button btnEdit = view.findViewById(R.id.button_edit);

        configuratonEditAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity(), R.style.popupInspirationTheme);
                View popupEditAccount = LayoutInflater.from(getActivity()).inflate(R.layout.activity_popup_edit_account, view.findViewById(R.id.popup_edit_account_container));
                bottomSheetDialog.setContentView(popupEditAccount);
                bottomSheetDialog.show();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configurationUser.setText(editUser.getText());
            }
        });

        return view;
    }
}