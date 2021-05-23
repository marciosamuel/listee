package com.dispositivos.moveis.listee;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ConfigurationActivity extends Fragment {
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @NonNull ViewGroup container, @Nullable Bundle savedInstanceState) {
        return layoutInflater.inflate(R.layout.activity_configuration, container, false);
    }
}