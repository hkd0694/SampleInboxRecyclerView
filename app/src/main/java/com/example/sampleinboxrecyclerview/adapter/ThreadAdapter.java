package com.example.sampleinboxrecyclerview.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleinboxrecyclerview.R;

public class ThreadAdapter  {



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