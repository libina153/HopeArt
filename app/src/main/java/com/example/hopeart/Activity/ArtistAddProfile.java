package com.example.hopeart.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hopeart.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ArtistAddProfile extends AppCompatActivity {
    FloatingActionButton btn_moveto_artisthome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artist_addartist_profile_activity);

        btn_moveto_artisthome=findViewById(R.id.btn_moveto_artisthome);

        btn_moveto_artisthome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNextCustomerActivity();
            }

            private void onNextCustomerActivity() {
                Intent i=new Intent(ArtistAddProfile.this, ArtistHomeBottomNav.class);
                startActivity(i);
            }
        });

    }
}
