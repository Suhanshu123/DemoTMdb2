package com.example.suhanshu.demorecyclerview;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CountryAdapter.OnClickListenerDemo {


    RecyclerView recyclerView;
    User user;
    public static final String URL = "http://api.themoviedb.org/3/discover/movie?%20%E2%86%B5%20sort_by=popularity.desc?&api_key=b64779c994411b177fea8cffceed3df1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);

        StringRequest stringRequest = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                user = gson.fromJson(response, User.class);
                List<Result> results = user.getResults();
                CountryAdapter countryAdapter = new CountryAdapter(results, getApplicationContext(), MainActivity.this);
                recyclerView.setAdapter(countryAdapter);
                recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    @Override
    public void OnClick(Result result) {
        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
        Gson gson = new Gson();
        String myJson = gson.toJson(result);
        intent.putExtra("myjson", myJson);
        startActivity(intent);
    }
}

