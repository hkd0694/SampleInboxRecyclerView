package com.example.sampleinboxrecyclerview.data;

import android.annotation.SuppressLint;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class SearchData {

    private int id;
    private String name;
    private String distance;
    @DrawableRes private int profile;
    //private String location;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    /*public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }*/

    static class ItemDiffer extends DiffUtil.ItemCallback<SearchData> {

        @Override
        public boolean areItemsTheSame(@NonNull SearchData oldItem, @NonNull SearchData newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        @SuppressLint("DiffUtilEquals")
        public boolean areContentsTheSame(SearchData oldItem, SearchData newItem) {
            return oldItem.equals(newItem);
        }
    }

}
