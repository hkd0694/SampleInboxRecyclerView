package com.example.sampleinboxrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.util.Log;

import com.example.sampleinboxrecyclerview.adapter.ThreadAdapter;
import com.example.sampleinboxrecyclerview.data.SearchData;
import com.example.sampleinboxrecyclerview.data.SearchDetailData;
import com.example.sampleinboxrecyclerview.data.SearchListData;
import com.example.sampleinboxrecyclerview.fragment.DetailFragment;
import com.jakewharton.rxrelay2.PublishRelay;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import me.saket.inboxrecyclerview.InboxRecyclerView;
import me.saket.inboxrecyclerview.dimming.TintPainter;
import me.saket.inboxrecyclerview.page.ExpandablePageLayout;
import me.saket.inboxrecyclerview.page.SimplePageStateChangeCallbacks;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private InboxRecyclerView recyclerView;

    private ExpandablePageLayout layout;
    private PublishRelay<Object> onDestroy =PublishRelay.create();

    List<SearchData> dataList = new ArrayList<>();
    List<SearchListData> searchListData = new ArrayList<>();

    private ThreadAdapter adapter;

    private DetailFragment detailFragment;


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
        adapter = new ThreadAdapter(new SearchListData.ItemDiffer());
        for(int  i=0;i<5;i++) {
            SearchData data = new SearchData(i+1,"한경동" + (i+1),"24km 뒤에",R.drawable.bucket_thumbnail_img01);
            dataList.add(data);
            adapter.addItem(data);
        }
        for(int i=0;i<5;i++) {
            SearchListData searchListData1 = new SearchListData(i+1, dataList);
            searchListData.add(searchListData1);
        }
        adapter.submitList(searchListData);
        adapter.setHasStableIds(true);
        recyclerView.setAdapter(adapter);


        adapter.itemClicks.takeUntil(onDestroy).subscribe(new Consumer<SearchDetailData>() {
            @Override
            public void accept(SearchDetailData searchDetailData) throws Exception {
                recyclerView.expandItem(searchDetailData.getId());
            }
        });

        adapter.itemClicks.takeUntil(onDestroy);

        setupPage();

        layout.addStateChangeCallbacks(new SimplePageStateChangeCallbacks() {
            @Override
            public void onPageAboutToCollapse(long collapseAnimDuration) {
                //애니 매이션 적용
                Log.e(TAG," Collapse");
            }

            @Override
            public void onPageAboutToExpand(long expandAnimDuration) {
                //애니 매이션 적용
                Log.e(TAG, " Expand");
            }
        });

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

    @SuppressLint("CheckResult")
    public void setupPage(){
        detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(layout.getId());

        if(detailFragment == null) {
            detailFragment = new DetailFragment(dataList);
        }

        getSupportFragmentManager().beginTransaction().replace(layout.getId(),detailFragment).commitNowAllowingStateLoss();


        adapter.itemClicks.map(new Function<SearchDetailData, Object>() {
            @Override
            public Object apply(SearchDetailData searchDetailData) throws Exception {
                return searchDetailData.getId();
            }
        }).takeUntil(onDestroy).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Log.e(TAG,o.toString() + " : " + o.getClass());
                Long k = (Long)o;
                detailFragment.popluate(k);
            }
        });

    }
}