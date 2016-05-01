package com.example.suhail.mywishlist;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import data.DatabaseHandler;
import model.MyWish;

public class MainActivity extends Activity {

    private EditText title;
    private EditText content;
    private Button saveButton;
    private DatabaseHandler dba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dba=new DatabaseHandler(MainActivity.this);

        title=(EditText)findViewById(R.id.titleEditText);
        content=(EditText)findViewById(R.id.wishEditText);
        saveButton=(Button)findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDB();
            }
        });

    }
       private  void saveToDB() {

           MyWish wish = new MyWish();
           wish.setTitle(title.getText().toString().trim());
           wish.setContent(content.getText().toString().trim());

           dba.addWishes(wish);
           dba.close();

           //clear
           title.setText("");
           content.setText("");

           Intent i = new Intent(MainActivity.this, DisplayWishesActivity.class);
           startActivity(i);
       }
}
