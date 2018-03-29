package com.example.shravanram.greenauction.FarmerSide;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shravanram.greenauction.FarmerBid;
import com.example.shravanram.greenauction.ProfileActivity;
import com.example.shravanram.greenauction.R;
import com.example.shravanram.greenauction.firebase_models.PersonInfo;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainMenu extends BaseMenu {
    private FirebaseAuth fire;
    private DatabaseReference mDatabase;
    private EditText newprice;
    private Button editbut;

    private DatabaseReference mRef;
    FirebaseUser current;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        fire=FirebaseAuth.getInstance();
        newprice = (EditText) findViewById(R.id.newprice);
        editbut = (Button) findViewById(R.id.edit);
        mDatabase = FirebaseDatabase.getInstance().getReference();



        editbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String np = newprice.getText().toString().trim();
                int new_price = Integer.parseInt(np);


                mDatabase.child("Bids").child("1").child("FID").push().setValue(new_price);

                //startActivity(new Intent(getApplicationContext(),ADD ACTIVITY.class));
            }
        });

    }


}

