package com.example.sampleinboxrecyclerview.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleinboxrecyclerview.R;
import com.example.sampleinboxrecyclerview.data.SearchData;
import com.example.sampleinboxrecyclerview.data.SearchDetailData;
import com.example.sampleinboxrecyclerview.data.SearchListData;
import com.jakewharton.rxrelay2.PublishRelay;

import java.util.ArrayList;
import java.util.List;

public class ThreadAdapter extends ListAdapter<SearchListData, ThreadAdapter.SearchViewHolder> {

    public static final String TAG = ThreadAdapter.class.getSimpleName();

    public static final int NORMAL = 1;
    public static final int WITH_IMAGE_ATTACHMENTS = 2;

    public List<SearchData> dataList ;

    public PublishRelay<SearchDetailData> itemClicks = PublishRelay.create();

    public ThreadAdapter(SearchListData.ItemDiffer itemDiffer) {
        super(itemDiffer);
        dataList = new ArrayList<>();
    }


    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        SearchViewHolder holder = new SearchViewHolder(view,itemClicks);
        /*SearchViewHolder holder = null;
        if(viewType == NORMAL) {
            holder = new SearchViewHolder(view);
        } else if(viewType == WITH_IMAGE_ATTACHMENTS) {
            holder = new SearchDetailViewHolder(view);
        }*/
        return holder;
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(String.valueOf(position));
    }

    @Override
    public int getItemViewType(int position) {
        //SearchData d = getItem(position).getListLiveData().get(0);

        return super.getItemViewType(position);
    }

    public void addItem(SearchData data1) {
        dataList.add(data1);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        SearchData data = dataList.get(position);
        holder.title.setText(data.getName());
        holder.subject.setText(data.getName() + " : " +  data.getDistance());
        holder.contents.setText(data.getDistance());
        holder.image.setImageResource(R.drawable.bucket_thumbnail_img01);
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView subject;
        private ImageView image;
        private TextView contents;

        private SearchData data = new SearchData();

        public SearchViewHolder(View view, final PublishRelay<SearchDetailData> dataPublishRelay) {
            super(view);
            title = view.findViewById(R.id.emailthread_item_title);
            subject = view.findViewById(R.id.emailthread_item_subject);
            image = view.findViewById(R.id.emailthread_item_avatar);
            contents = view.findViewById(R.id.emailthread_item_body);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dataPublishRelay.accept(new SearchDetailData(data,getItemId()));
                }
            });
        }

    }
}