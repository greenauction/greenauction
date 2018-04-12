package com.example.shravanram.greenauction;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import android.widget.Toast;

public class RatingFarmer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_farmer);

        final RatingBar ratingRatingBar = (RatingBar) findViewById(R.id.rating_bar);
        Button submitButton = (Button) findViewById(R.id.submit_button);
        final float rate = ratingRatingBar.getRating();






        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RatingFarmer.this,FarmerBid.class);

                //Set your data using putExtra method which take
                //any key and value which we want to send
                intent.putExtra("rate",rate);

                //Use startActivity or startActivityForResult for Starting New Activity
                RatingFarmer.this.startActivity(intent);

                Toast.makeText(getApplicationContext(), "You have successfully rated farmer", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),ConsumerProfileActivity.class));

            }
        });

    }
}











