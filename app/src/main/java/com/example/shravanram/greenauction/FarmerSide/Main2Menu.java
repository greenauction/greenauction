package com.example.shravanram.greenauction.FarmerSide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.shravanram.greenauction.R;

public class Main2Menu extends BaseMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_menu);
        //ADD ACTIVITY FOR MY AUCTIONS
        //startActivity(new Intent(getApplicationContext(),ADD ACTIVITY.class));
    }
}
