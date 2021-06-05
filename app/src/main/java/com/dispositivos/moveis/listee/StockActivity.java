package com.dispositivos.moveis.listee;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StockActivity extends Fragment {

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @NonNull ViewGroup container, @Nullable Bundle savedInstanceState) {
        return layoutInflater.inflate(R.layout.activity_stock, container, false);

    }
}