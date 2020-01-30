package com.example.sampleinboxrecyclerview.data;

public class SearchDetailData {
    private SearchData data;
    private Long id;

    public SearchDetailData(SearchData data, Long id) {
        this.data = data;
        this.id = id;
    }

    public SearchData getData() {
        return data;
    }

    public void setData(SearchData data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
