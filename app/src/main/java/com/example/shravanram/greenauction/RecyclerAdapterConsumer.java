package com.example.shravanram.greenauction;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shravanram.greenauction.CompletedConsumerSide;
import com.example.shravanram.greenauction.FarmerSide.RecyclerAdapter;
import com.example.shravanram.greenauction.R;
import com.example.shravanram.greenauction.firebase_models.FarmerInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by Shravan ram on 3/27/2018.
 */

public class RecyclerAdapterConsumer extends RecyclerView.Adapter<RecyclerAdapterConsumer.MyHoder>{
    List<FarmerInfo>  list;
    Context context;
    String auctionID="";
    FirebaseDatabase database;
    DatabaseReference myRef;
    public RecyclerAdapterConsumer(){




    }
    public RecyclerAdapterConsumer(List<FarmerInfo> list,Context context,String s)
    {
        this.list=list;
        this.context=context;
        this.auctionID=s;
        myRef = FirebaseDatabase.getInstance().getReference().child("auction").child(this.auctionID);
    }
    @Override
    public MyHoder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.blog_row_bid,parent,false);
        MyHoder myHoder=new MyHoder(view);
        return myHoder;
    }
    @Override
    public int getItemCount(){
        int arr=0;
        try{
            if(list.size()==0)
            {
                arr=0;
            }
            else
            {
                arr=list.size();
            }
        }
        catch (Exception e )
        {

        }
        Log.d("count",""+arr);
        return arr;
    }

    @Override
    public void onBindViewHolder(MyHoder holder,int position)
    {
        FarmerInfo mylist=list.get(position);
        holder.name.setText(mylist.getFname());

        holder.price.setText("Rating "+ mylist.getRating());
        holder.rating.setText(mylist.getPrice());
    }
    class MyHoder extends RecyclerView.ViewHolder{
        TextView name,price,rating;
        public View view;
        public ClipData.Item currentItem;
        public MyHoder(final View itemView){
            super(itemView);
            view = itemView;
            view.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Log.d("inside","clikced");
                    //...
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                    builder1.setMessage("Are you sure you want to select farmer "+name.getText()+"?");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    myRef.child("chosen").setValue("yes");
                                    Intent i=new Intent(context,CompletedConsumerSide.class);
                                    context.startActivity(i);
                                }
                            });

                    builder1.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();


                }
            });


            name=(TextView)itemView.findViewById(R.id.fname);
            price=(TextView)itemView.findViewById(R.id.price);
            rating=(TextView)itemView.findViewById(R.id.rating);
        }
    }
}