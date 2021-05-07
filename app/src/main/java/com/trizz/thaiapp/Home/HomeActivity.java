package com.trizz.thaiapp.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.trizz.thaiapp.Adapter.HomeRecyclerAdapter;
import com.trizz.thaiapp.R;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView topicRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        topicRecycler = findViewById(R.id.topic_recycler_view);
        ArrayList<Integer> imgData = new ArrayList<>();
        imgData.add(R.drawable.ic_education);
        imgData.add(R.drawable.ic_society);
        imgData.add(R.drawable.ic_business);
        imgData.add(R.drawable.ic_technology);
        imgData.add(R.drawable.ic_space);
        imgData.add(R.drawable.ic_animal);
        imgData.add(R.drawable.ic_plant);
        imgData.add(R.drawable.ic_art);
        imgData.add(R.drawable.ic_sport);
        imgData.add(R.drawable.ic_nature);
        HomeRecyclerAdapter adapter = new HomeRecyclerAdapter(imgData);
        topicRecycler.setLayoutManager(new GridLayoutManager(this.getApplicationContext(), 3));
        topicRecycler.setAdapter(adapter);
    }
}