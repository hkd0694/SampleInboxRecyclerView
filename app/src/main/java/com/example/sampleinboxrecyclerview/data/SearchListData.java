package com.example.sampleinboxrecyclerview.data;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DiffUtil;

import java.util.ArrayList;
import java.util.List;

public class SearchListData {

    private int dataId;
    public List<SearchData> listLiveData = new ArrayList<>();

    public SearchListData(){

    }

    public SearchListData(int dataId, List<SearchData> listLiveData) {
        this.dataId = dataId;
        this.listLiveData = listLiveData;
    }

    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    public List<SearchData> getListLiveData() {
        return listLiveData;
    }

    public void setListLiveData(List<SearchData> listLiveData) {
        this.listLiveData = listLiveData;
    }

    //RecyclerView 성능 향상하기
    //두 목록간의 차이점을 찾고 업데이트 되어야 할 목록을 반환해준다. RecyclerView 어댑터에 대한 업데이트를 알리는데 사용한다.
    public static class ItemDiffer extends DiffUtil.ItemCallback<SearchListData> {

        @Override
        public boolean areItemsTheSame(@NonNull SearchListData oldItem, @NonNull SearchListData newItem) {
            return oldItem.getDataId() == newItem.getDataId();
        }

        @Override
        @SuppressLint("DiffUtilEquals")
        public boolean areContentsTheSame(SearchListData oldItem, SearchListData newItem) {
            return oldItem.equals(newItem);
        }
    }

}
