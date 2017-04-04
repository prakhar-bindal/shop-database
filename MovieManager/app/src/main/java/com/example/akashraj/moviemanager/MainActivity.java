package com.example.akashraj.moviemanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView movieTextView = (TextView) findViewById(R.id.circle_movies);
        final TextView theatresTextView = (TextView) findViewById(R.id.circle_theatres);
        final TextView distributerTextView = (TextView) findViewById(R.id.circle_distributers);
        final TextView customerTextView = (TextView) findViewById(R.id.circle_customer);
        final TextView buysTextView = (TextView) findViewById(R.id.circle_buys);

        movieTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent launchMovieActivity = new Intent(MainActivity.this,CatalogActivity.class);
                startActivity(launchMovieActivity);
            }
        });

        theatresTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent launchTheatreActivity = new Intent(MainActivity.this,TheatreActivity.class);
                startActivity(launchTheatreActivity);
            }
        });

        distributerTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent launchDistributerActivity = new Intent(MainActivity.this,ShowsActivity.class);
                startActivity(launchDistributerActivity);
            }
        });
        customerTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent launchDistributerActivity = new Intent(MainActivity.this,CustomerActivity.class);
                startActivity(launchDistributerActivity);
            }
        });
        buysTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent launchDistributerActivity = new Intent(MainActivity.this,BuysActivity.class);
                startActivity(launchDistributerActivity);
            }
        });
    }




}
