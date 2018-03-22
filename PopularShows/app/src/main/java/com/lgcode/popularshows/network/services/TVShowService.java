package com.lgcode.popularshows.network.services;

import com.lgcode.popularshows.network.response.TVShowListResultResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by leojg on 11/6/16.
 */
public interface TVShowService {

    @GET("tv/top_rated")
    Call<TVShowListResultResponse> getTVShows(@Query("page") int page);

    @GET("tv/{id}/similar")
    Call<TVShowListResultResponse> getSimilarTVShows(@Path("id") int id, @Query("page") int page);

}
