package com.example.hopeart.Activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.hopeart.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ActivityArtistHomeBottomNav extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfigration;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_artisthome_bottomnav);
        BottomNavigationView bottom_nav_home = findViewById(R.id.bottom_navhome);

        mAppBarConfigration = new AppBarConfiguration.Builder(R.id.artist_homemenu, R.id.artist_addartworkmenu, R.id.artist_ordermenu,R.id.artist_profilemenu, R.id.artist_paymentmenu).build();

        NavController navController = Navigation.findNavController(this,R.id.host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfigration);
        NavigationUI.setupWithNavController(bottom_nav_home, navController);

    }
}


