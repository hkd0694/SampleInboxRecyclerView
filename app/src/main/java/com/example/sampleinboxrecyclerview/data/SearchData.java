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

    public SearchData(){

    }

    public SearchData(int id, String name, String distance, int profile) {
        this.id = id;
        this.name = name;
        this.distance = distance;
        this.profile = profile;
    }

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

}
