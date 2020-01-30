package com.example.sampleinboxrecyclerview.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleinboxrecyclerview.R;

public class ThreadAdapter extends RecyclerView.Adapter<ThreadAdapter.EmailViewHolder> {


    @NonNull
    @Override
    public EmailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull EmailViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class EmailViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView subject;
        private ImageView image;
        private TextView contents;

        public EmailViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.emailthread_item_title);
            subject = view.findViewById(R.id.emailthread_item_subject);
            image = view.findViewById(R.id.emailthread_item_avatar);
            contents = view.findViewById(R.id.emailthread_item_body);

        }

    }
}