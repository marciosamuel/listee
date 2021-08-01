package com.dispositivos.moveis.listee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import Models.HomeCardModel;
import Models.InspirationCardModel;
import Models.InspirationListModel;

public class MainActivity extends AppCompatActivity {

    private CollectionReference inspirations = FirebaseFirestore.getInstance().collection("inspirations");
    private CollectionReference inspirationList = FirebaseFirestore.getInstance().collection("inspiration_list");
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setContentView(R.layout.activity_main);
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                if(user == null) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }

                BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
                bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavigationListener);
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new HomeActivity()).commit();
            }
        }, 2000);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavigationListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment = null;

                    switch (item.getItemId()){
                        case R.id.menu_home:
                            fragment = new HomeActivity();
                            break;
                        case R.id.menu_inspirations:
                            fragment = new InspirationsActivity();
                            break;
                        case R.id.menu_stock:
                            fragment = new StockActivity();
                            break;
                        case R.id.menu_configurations:
                            fragment = new ConfigurationActivity();
                            break;
                    }
                    if(fragment != null){
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
                    }else{
                        Toast.makeText(MainActivity.this, "Opção invalida", Toast.LENGTH_SHORT).show();
                    }

                    return true;
                }
            };
}