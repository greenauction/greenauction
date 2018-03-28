package com.example.shravanram.greenauction.FarmerSide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.shravanram.greenauction.R;
import com.google.firebase.auth.FirebaseAuth;

public class Main4Menu extends BaseMenu  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //firebaseAuth= FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4_menu);
        //firebaseAuth.signOut();
    }
}
