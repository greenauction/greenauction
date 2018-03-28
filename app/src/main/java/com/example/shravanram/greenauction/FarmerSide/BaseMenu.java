package com.example.shravanram.greenauction.FarmerSide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.shravanram.greenauction.R;

public class BaseMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_menu);
    }


        public boolean onCreateOptionMenu(Menu menu){
            getMenuInflater().inflate(R.menu.menu,menu);
            return super.onCreateOptionsMenu(menu);
        }

    @Override
        public boolean onOptionsItemSelected(MenuItem item){
            int id=item.getItemId();
            if(id==R.id.edit){
                Toast.makeText(this,"Edit",Toast.LENGTH_SHORT).show();
                Intent intentedit= new Intent(this,MainMenu.class);
                startActivity(intentedit);

                return true;
            }
            else if(id==R.id.myauctions){
                Toast.makeText(this,"My Auctions",Toast.LENGTH_SHORT).show();
                Intent intentmyauctions= new Intent(this,Main2Menu.class);
                startActivity(intentmyauctions);
                return true;
            }
            else if(id==R.id.ongoing){
                Toast.makeText(this,"Ongoing Auctions",Toast.LENGTH_SHORT).show();
                Intent intentongoing= new Intent(this,Main3Menu.class);
                startActivity(intentongoing);

                return true;
            }
            else if(id==R.id.logout){
                Toast.makeText(this,"Logout",Toast.LENGTH_SHORT).show();
                Intent intentlogout= new Intent(this,Main4Menu.class);
                startActivity(intentlogout);

                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }

