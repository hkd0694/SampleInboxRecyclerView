package com.example.sampleinboxrecyclerview.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.sampleinboxrecyclerview.MainActivity;
import com.example.sampleinboxrecyclerview.R;
import com.example.sampleinboxrecyclerview.data.SearchData;
import com.jakewharton.rxrelay2.BehaviorRelay;
import com.jakewharton.rxrelay2.PublishRelay;

import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import kotlin.jvm.functions.Function3;
import me.saket.inboxrecyclerview.page.ExpandablePageLayout;
import me.saket.inboxrecyclerview.page.InterceptResult;
import me.saket.inboxrecyclerview.page.SimplePageStateChangeCallbacks;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment implements View.OnClickListener{

    public static final String TAG = DetailFragment.class.getSimpleName();

    private MainActivity activity;

    private BehaviorRelay<Long> behaviorRelay = BehaviorRelay.create();
    private PublishRelay<Object> onDestroy =PublishRelay.create();

    private ImageButton collapseButton;
    private ExpandablePageLayout layout;
    private ScrollView scrollView;

    private TextView title;

    List<SearchData> dataList;

    public DetailFragment(List<SearchData> dataList) {
        this.dataList = dataList;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_detail, container, false);
        collapseButton = view.findViewById(R.id.emailthread_collapse);
        scrollView = view.findViewById(R.id.emailthread_scrollable_container);
        title = view.findViewById(R.id.emailthread_subject);
        activity = (MainActivity)getActivity();
        layout = activity.findViewById(R.id.inbox_email_thread_page);
        collapseButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if(behaviorRelay.hasValue()) {
            outState.putLong("thread_id", behaviorRelay.getValue());
        }
        super.onSaveInstanceState(outState);
    }

    private void onRestoreInstanceState(Bundle bundle) {
        Long id = bundle.getLong("thread_id");
        if(id != null) {
            behaviorRelay.accept(id);
        }
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        }

        behaviorRelay.map(new Function<Long, Object>() {
            @Override
            public Object apply(Long aLong) throws Exception {
                return aLong;
            }
        }).takeUntil(onDestroy).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Long k = (Long)o;
                render(k);
            }
        });

        /*layout.setPullToCollapseInterceptor(new Function3<Float, Float, Boolean, InterceptResult>() {
            @Override
            public InterceptResult invoke(Float aFloat, Float aFloat2, Boolean aBoolean) {
                return InterceptResult.IGNORED;
                //scrollView.canScrollVertically()
            }
        });

        layout.addStateChangeCallbacks(new SimplePageStateChangeCallbacks() {
            @Override
            public void onPageAboutToCollapse(long collapseAnimDuration) {
                scrollView.scrollTo(0,0);
            }
        });*/

    }

    public void render(Long data) {
        SearchData k = dataList.get(Integer.parseInt(String.valueOf(data)));
        Log.e(TAG,k.getName() + " : " + k.getDistance());
        title.setText(k.getName());

    }

    @Override
    public void onDestroyView() {
        onDestroy.accept(new Object());
        super.onDestroyView();
    }

    public void popluate(Long id) {
        behaviorRelay.accept(id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.emailthread_collapse:
                requireActivity().onBackPressed();
                break;
        }
    }
}
