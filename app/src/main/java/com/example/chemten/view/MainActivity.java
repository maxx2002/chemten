package com.example.chemten.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;

import com.example.chemten.R;

public class MainActivity extends AppCompatActivity {

    NavHostFragment navHostFragment;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);

        AppBarConfiguration configuration = new AppBarConfiguration.Builder(R.id.loginFragment2, R.id.homeFragment, R.id.profileFragment, R.id.register).build();
        navController = navHostFragment.getNavController();

//        NavigationUI.setupActionBarWithNavController(MainActivity.this, navController, configuration);
    }
}