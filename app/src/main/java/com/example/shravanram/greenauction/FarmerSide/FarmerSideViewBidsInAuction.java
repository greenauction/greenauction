package com.example.shravanram.greenauction.FarmerSide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.shravanram.greenauction.MainActivity;
import com.example.shravanram.greenauction.R;
import com.example.shravanram.greenauction.firebase_models.FarmerInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import  com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FarmerSideViewBidsInAuction extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    List<FarmerInfo> list;
    RecyclerView recycle;
    Button view;
    RecyclerAdapter recyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_side_view_bids_in_auction);
        recycle=(RecyclerView)findViewById(R.id.farmersideviewbids);
        database=FirebaseDatabase.getInstance();

        recycle.setHasFixedSize(true);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        Intent i=getIntent();
        String AuctionSelected=i.getStringExtra("auctionClicked");
        myRef=database.getReference().child("Bids").child(AuctionSelected);


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list=new ArrayList<FarmerInfo>();
                Iterable<DataSnapshot> AllAuctions=dataSnapshot.getChildren();
                for(DataSnapshot dataSnapshot1:AllAuctions){

                    FarmerInfo value=dataSnapshot1.getValue(FarmerInfo.class);
                    Log.d("Key",dataSnapshot1.getValue().toString());
//                    value.setFname(dataSnapshot1.child("fname").getValue().toString());
//                    value.setPrice(dataSnapshot1.child("price").getValue().toString());
//                    dataSnapshot1.child("rating").getValue().toString();
                    FarmerInfo fire=new FarmerInfo();
                    String fname=value.getFname();
                    Log.d("name",fname);
                    String price=value.getPrice();
                    Log.d("price",price);
                    String rating=value.getRating();
                    fire.setFname(fname);
                    fire.setPrice(price);
                    fire.setRating(rating);
                    list.add(fire);
                    recyclerAdapter =new RecyclerAdapter(list, FarmerSideViewBidsInAuction.this);
                    recycle.setAdapter(recyclerAdapter);
                    recyclerAdapter.notifyDataSetChanged();

                }

//                RecyclerView.LayoutManager recyce= new GridLayoutManager(FarmerSideViewBidsInAuction.this,1);
//                recycle.setLayoutManager(recyce);
//                recycle.setItemAnimator(new DefaultItemAnimator());
//                recycle.setAdapter(recyclerAdapter);


              //  ourlist=(RecyclerView)findViewById(R.id.bids);

                //change this line if you make changes in chikoo.xml

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




//
//        Toast.makeText(getApplicationContext(), "Item clicked at "+ i.getStringExtra("auctionClicked") , Toast.LENGTH_SHORT).show();
    }
}
