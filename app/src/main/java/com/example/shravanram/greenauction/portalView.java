package com.example.shravanram.greenauction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.example.shravanram.greenauction.firebase_models.AuctionCardView1;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class portalView extends AppCompatActivity {
    private DatabaseReference tRef;
    private DatabaseReference cRef;
    private TextView time1;
    String emailno[];
    private DatabaseReference mRef;

    private RecyclerView ourlist;
    private FirebaseAuth fire=FirebaseAuth.getInstance();
    Calendar c = Calendar.getInstance();
    Date d1,d2;
    String t;
    ListView myList;
    private ArrayList<String> auctions=new ArrayList<String>();
    private ArrayList<String> auctionsSelect=new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chikoo);
       // myList=(ListView)findViewById((R.id.list1));
        mRef= FirebaseDatabase.getInstance().getReference().child("auction");
        mRef.keepSynced(true);
        time1=(TextView)findViewById(R.id.time);

        tRef = FirebaseDatabase.getInstance().getReference();

        ourlist=(RecyclerView)findViewById(R.id.auctions);
        ourlist.setHasFixedSize(true);
        //change this line if you make changes in chikoo.xml
        ourlist.setLayoutManager(new LinearLayoutManager(this));

       tRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //gives emailno as an array  as beta1@gmail and com as second element
                emailno=fire.getCurrentUser().getEmail().toString().split("\\.");
                String emailofcustomer=emailno[0];//contains beta1@gmail
                DataSnapshot AuctionID=dataSnapshot.child("Customer").child(emailofcustomer).child("AuctionID");
                Iterable<DataSnapshot> AllAuctions=AuctionID.getChildren();
                for(DataSnapshot var1:AllAuctions)
                {
                   String id=var1.getValue().toString();

                    t = dataSnapshot.child("auction").child(id).child("deadline").getValue().toString();
                    Log.d("time",""+t);
                    //selects all auctions of the current user ,can be ongoing or past
                    auctionsSelect.add(id);

                    /*SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm aa");
                    String getCurrentDateTime = sdf.format(c.getTime());
                    try {
                        d1 = sdf.parse(getCurrentDateTime);
                        d2 = sdf.parse(t);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                    if (d2.after(d1))

                    {
                        //ongoing auctions
                      // Log.i("getCurrentDateTime",t);
                    } else {
                    //past auctions
                      //Log.i("getCurrentDateTime", ""+i);
                    }
*/

                }


                }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();
      // for(int j=0;j<auctionsSelect.size();j++) {
            FirebaseRecyclerAdapter<AuctionCardView1, Chikoo.BlogviewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<AuctionCardView1,
                    Chikoo.BlogviewHolder>(AuctionCardView1.class, R.layout.blog_row, Chikoo.BlogviewHolder.class,
                    mRef) {
                @Override
                protected void populateViewHolder(Chikoo.BlogviewHolder viewHolder, AuctionCardView1 model, int position) {
                   // if(mRef.getKey())
                    if(auctionsSelect.contains(position+1)) {
                        Log.i("position", "" + position);
                        viewHolder.setProd(model.getProd());
                        viewHolder.setLoc(model.getLoc());
                    }
                }
            };

            ourlist.setAdapter(firebaseRecyclerAdapter);
       // }
    }


}
