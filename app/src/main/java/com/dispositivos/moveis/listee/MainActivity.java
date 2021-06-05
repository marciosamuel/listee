package com.dispositivos.moveis.listee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavigationListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new HomeActivity()).commit();
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
                        Toast.makeText(MainActivity.this, "Em desenvolvimento", Toast.LENGTH_SHORT).show();
                    }

                    return true;
                }
            };
}