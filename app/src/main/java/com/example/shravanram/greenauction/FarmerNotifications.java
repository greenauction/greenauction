package com.example.shravanram.greenauction;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FarmerNotifications extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private FirebaseAuth fire;
    String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_notifications);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        fire = FirebaseAuth.getInstance();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                msg = dataSnapshot.child("Notifications").
                        child("farmer1@gmail").child("1").child("msg").getValue().toString();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onStart() {

            super.onStart();
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentTitle("Results");
            builder.setContentText("View results");
            Intent intent = new Intent(getApplicationContext(), SecondClass.class);
            //add back stack
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
            stackBuilder.addParentStack(SecondClass.class);
            //add intent to stack
            stackBuilder.addNextIntent(intent);
            PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            //add pending intent to builder
            builder.setContentIntent(pendingIntent);

            //to create obj of notification manager
            NotificationManager NM = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NM.notify(0, builder.build());

        }

    }
