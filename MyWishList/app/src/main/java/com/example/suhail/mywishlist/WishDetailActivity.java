package com.example.suhail.mywishlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import data.DatabaseHandler;

public class WishDetailActivity extends AppCompatActivity {
    private TextView title,date,content;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_detail);

        title=(TextView)findViewById(R.id.detailsTiltle);
        date=(TextView)findViewById(R.id.detailsDateText);
        content=(TextView)findViewById(R.id.detalsTextView);
        deleteButton=(Button)findViewById(R.id.deleteButton);

        Bundle extras=getIntent().getExtras();

        if (extras!=null){
            title.setText(extras.getString("title"));
            date.setText("Created: "+extras.getString("date"));
            content.setText(" \" "+extras.getString("content")+" \" ");

            final int id=extras.getInt("id");

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseHandler db=new DatabaseHandler(getApplicationContext());
                    db.deleteWish(id);

                    Toast.makeText(getApplicationContext(),"Wish Deleted!",Toast.LENGTH_LONG).show();

                    startActivity(new Intent(WishDetailActivity.this,DisplayWishesActivity.class));
                }
            });

        }

    }

}
