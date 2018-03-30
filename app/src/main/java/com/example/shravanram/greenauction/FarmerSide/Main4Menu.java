package com.example.shravanram.greenauction.FarmerSide;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.shravanram.greenauction.MainActivity;
import com.example.shravanram.greenauction.R;
import com.google.firebase.auth.FirebaseAuth;

public class Main4Menu extends BaseMenu  {
    private Button logout;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4_menu);
        firebaseAuth = FirebaseAuth.getInstance();
        logout = (Button) findViewById(R.id.logout);
    }

    public void onClick(View view) {
        if(view==logout) {
            //  finish();
            firebaseAuth.signOut();
            startActivity(new Intent(this, MainActivity.class));
        }

    }
}
