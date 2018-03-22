package com.lgcode.popularshows.network.client;

import java.io.IOException;

import com.lgcode.popularshows.network.ServiceGenerator;
import com.lgcode.popularshows.network.response.TVShowListResultResponse;
import com.lgcode.popularshows.network.services.TVShowService;

/**
 * Created by le.garcia on 07/11/2016.
 */
public class TVShowClient {

    private TVShowService tvShowService;

    public TVShowClient() {
        this.tvShowService = ServiceGenerator.createService(TVShowService.class);
    }

    public TVShowListResultResponse fetchTVShows(int page) throws IOException {
        return tvShowService.getTVShows(page).execute().body();
    }

    public TVShowListResultResponse fetchSimilarTVShows(int showId, int page) throws IOException {
        return tvShowService.getSimilarTVShows(showId, page).execute().body();
    }

}
