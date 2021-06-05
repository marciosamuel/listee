package com.dispositivos.moveis.listee;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class ConfigurationActivity extends Fragment {
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @NonNull ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.activity_configuration, container, false);

        TextView linkToEdit = view.findViewById(R.id.config_edit_account);

        linkToEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity(), R.style.popupInspirationTheme);
                View popupEditAccount = LayoutInflater.from(getActivity()).inflate(R.layout.activity_popup_edit_account, view.findViewById(R.id.popup_edit_account_container));
                bottomSheetDialog.setContentView(popupEditAccount);
                bottomSheetDialog.show();
            }
        });

        return view;
    }
}