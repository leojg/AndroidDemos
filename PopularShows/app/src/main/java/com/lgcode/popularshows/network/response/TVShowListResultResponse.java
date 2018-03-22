package com.lgcode.popularshows.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import com.lgcode.popularshows.TvShow;

/**
 * Created by leojg on 11/5/16.
 */
public class TVShowListResultResponse {

    private int page;
    private List<TvShow> results;
    @SerializedName("total_pages") private int totalPages;

    public int getPage() {
        return page;
    }

    public List<TvShow> getResults() {
        return results;
    }

    public int getTotalPages() {
        return totalPages;
    }

}
