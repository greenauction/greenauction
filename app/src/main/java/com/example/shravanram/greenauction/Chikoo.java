package com.example.shravanram.greenauction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.example.shravanram.greenauction.firebase_models.AuctionCardView1;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class Chikoo extends AppCompatActivity {
    private DatabaseReference mRef;

    private RecyclerView ourlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chikoo);
        mRef= FirebaseDatabase.getInstance().getReference().child("auction");
        mRef.keepSynced(true);

        ourlist=(RecyclerView)findViewById(R.id.auctions);
        ourlist.setHasFixedSize(true);
        //change this line if you make changes in chikoo.xml
        ourlist.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    protected void onStart(){
        super.onStart();
        FirebaseRecyclerAdapter<AuctionCardView1,BlogviewHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<AuctionCardView1,
                BlogviewHolder>(AuctionCardView1.class,R.layout.blog_row,BlogviewHolder.class,mRef){
          @Override
            protected void populateViewHolder(BlogviewHolder viewHolder,AuctionCardView1 model,int position){
              Log.d("pos",""+position);
             if((position+1)==1)
              {
                  viewHolder.setProd(model.getProd());
                  viewHolder.setLoc(model.getLoc());
              }
          }
        };
        ourlist.setAdapter(firebaseRecyclerAdapter);
    }
    public static class BlogviewHolder extends RecyclerView.ViewHolder{
        View mView;
        public BlogviewHolder(View itemView){
            super(itemView);
            mView=itemView;
        }
        public void setProd(String prod){
            TextView produce=(TextView)mView.findViewById(R.id.t1);
            produce.setText(prod);
        }
        public void setLoc(String loc){
            TextView location=(TextView)mView.findViewById(R.id.t2);
            location.setText(loc);
        }


    }
}