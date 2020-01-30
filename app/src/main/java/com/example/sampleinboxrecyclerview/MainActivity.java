package com.example.sampleinboxrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.sampleinboxrecyclerview.adapter.ThreadAdapter;
import com.jakewharton.rxrelay2.PublishRelay;

import me.saket.inboxrecyclerview.InboxRecyclerView;
import me.saket.inboxrecyclerview.dimming.TintPainter;
import me.saket.inboxrecyclerview.page.ExpandablePageLayout;

public class MainActivity extends AppCompatActivity {

    private InboxRecyclerView recyclerView;

    private ExpandablePageLayout layout;
    private PublishRelay<Object> onDestroy =PublishRelay.create();

    private ThreadAdapter threadAdapter = new ThreadAdapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.inbox_recyclerview);
        layout = findViewById(R.id.inbox_email_thread_page);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setExpandablePage(layout);
        recyclerView.setTintPainter(TintPainter.uncoveredArea(Color.WHITE,0.65f));

        //recyclerView.setAdapter();

    }

    @Override
    protected void onDestroy() {
        onDestroy.accept(new Object());
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if(layout.isExpandingOrCollapsing()) {
            recyclerView.collapse();
        } else{
            super.onBackPressed();
        }
    }
}