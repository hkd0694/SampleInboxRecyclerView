package com.example.sampleinboxrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.sampleinboxrecyclerview.adapter.ThreadAdapter;

import me.saket.inboxrecyclerview.InboxRecyclerView;
import me.saket.inboxrecyclerview.dimming.TintPainter;

public class MainActivity extends AppCompatActivity {

    private InboxRecyclerView recyclerView;

    private ThreadAdapter threadAdapter = new ThreadAdapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setTintPainter(TintPainter.uncoveredArea(Color.WHITE,0.65f));



    }
}
