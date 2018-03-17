package com.example.shravanram.greenauction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Integer.parseInt;

public class PortalCreation extends AppCompatActivity {
    private Spinner dropDown;
    private DatabaseReference mDatabase;
    private EditText produce;
    private EditText location;
    private EditText qty;
    private EditText time;
    private EditText iniprice;
    private Button createbut;
    private DatabaseReference mRef;
    String date;
    String time1;
    private DatabaseReference timeRef;
    private TextView setTime;

    String c="";
    int count=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portal_creation);

        dropDown = (Spinner) findViewById(R.id.quantity1);
        String[] items = new String[]{"kg", "quintal", "litre"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropDown.setAdapter(adapter);
        produce = (EditText) findViewById(R.id.produce);
        location = (EditText) findViewById(R.id.location);
        setTime=(TextView) findViewById(R.id.timeID);
      //  count1=(TextView) findViewById(R.id.count);
        qty = (EditText) findViewById(R.id.qty);
        time = (EditText) findViewById(R.id.timeSlot);
        iniprice = (EditText) findViewById(R.id.iniPrice);
        mDatabase = FirebaseDatabase.getInstance().getReference();
      mRef = FirebaseDatabase.getInstance().getReference().child("count");
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); // Make sure user insert date into edittext in this format.
        Date dateObject;

        try{
            String dob_var=(time.getText().toString());

            dateObject = formatter.parse(dob_var);

            date = new SimpleDateFormat("dd/MM/yyyy").format(dateObject);
            time1 = new SimpleDateFormat("h:mmaa").format(dateObject);
        }

        catch (java.text.ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.i("E11111111111", e.toString());
        }

        Toast.makeText(getBaseContext(), date + time, Toast.LENGTH_LONG).show();


        createbut = (Button) findViewById(R.id.create);
        createbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String prod = produce.getText().toString().trim();
                String loc = location.getText().toString().trim();
                String qq = qty.getText().toString().trim();
                int q = Integer.parseInt(qq);
                String kg = dropDown.getSelectedItem().toString();
                String ts = time.getText().toString().trim();
                String init_pri = iniprice.getText().toString().trim();


                c = "" + count;

               mRef.setValue(c);
                mDatabase.child("auction").child(c).child("prod").setValue(prod);
                mDatabase.child("auction").child(c).child("loc").setValue(loc);
                mDatabase.child("auction").child(c).child("qty").setValue(q);
                mDatabase.child("auction").child(c).child("weight").setValue(kg);
                mDatabase.child("auction").child(c).child("deadline").setValue(date);
                mDatabase.child("auction").child(c).child("time").setValue(time1);
                mDatabase.child("auction").child(c).child("initialbid").setValue(init_pri);
             //   mDatabase.setValue(c);


            }
        });
       mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                c= dataSnapshot.getValue().toString();

                count++;


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
       timeRef.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               timeRef= FirebaseDatabase.getInstance().getReference().child("auction").child(c).child("time");
               String t= dataSnapshot.getValue().toString();
               setTime.setText(t);
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       })


    }

}
