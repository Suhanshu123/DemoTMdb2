package com.example.suhanshu.demorecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Gson gson = new Gson();
        Result ob = gson.fromJson(getIntent().getStringExtra("myjson"), Result.class);
        getSupportActionBar().setTitle(ob.getTitle());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        TextView adult=findViewById(R.id.Adult);
        TextView overview=findViewById(R.id.overview);
        TextView release=findViewById(R.id.release_date);
        ImageView imageView=findViewById(R.id.image_inner);

        adult.setText("ADULTS : "+ob.getAdult()+"");
        overview.setText(ob.getOverview());
        release.setText("RELEASE DATE : "+ ob.getReleaseDate());
        Glide.with(this).load("https://image.tmdb.org/t/p/w500"+ob.getBackdropPath()).into(imageView);


    }
}
