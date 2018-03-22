package com.lgcode.popularshows.models;

import android.os.AsyncTask;

import com.squareup.otto.Bus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import glb.lg.popularshows.BuildConfig;
import com.lgcode.popularshows.TvShow;
import com.lgcode.popularshows.network.client.TVShowClient;
import com.lgcode.popularshows.network.response.TVShowListResultResponse;

/**
 * Created by leojg on 11/13/16.
 */
public class TvShowDetailModel {

    private TVShowClient tvShowClient;
    private Bus bus;

    private Integer currentPage;
    private Integer totalPages;
    private Integer currentPageSize;
    private List<TvShow> similarTvShowList;

    private TvShow tvShow;

    public TvShowDetailModel(Bus bus, TvShow tvShow) {
        this.bus = bus;
        this.tvShow = tvShow;
        this.similarTvShowList = new ArrayList<>();
        this.tvShowClient = new TVShowClient();
        fetchSimilarTVShows(1, tvShow.getId());
    }

    public List<TvShow> getSimilarTvShowList() {
        return similarTvShowList;
    }

    public Integer getCurrentPageSize() {
        return currentPageSize;
    }

    public TvShow getTvShow() {
        return tvShow;
    }

    public TvShow getSimilarTVShow(int showId) {
        for (TvShow tvShow: similarTvShowList) {
            if (tvShow.getId() == showId) {
                return tvShow;
            }
        }
        return null;
    }

    public void fetchSimilarTVShows(int page, int showId) {
        new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... integers) {
                try {
                    int page = integers[0];
                    int showId = integers[1];
                    TVShowListResultResponse response = tvShowClient.fetchSimilarTVShows(showId, page);

                    currentPage = response.getPage();
                    totalPages = response.getTotalPages();

                    for (TvShow tvShow: response.getResults()) {
                        tvShow.setImage(BuildConfig.SMALL_IMAGE_BASE_URL + tvShow.getImage());
                        tvShow.setHeroImage(BuildConfig.HERO_IMAGE_BASE_URL + tvShow.getHeroImage());
                        similarTvShowList.add(tvShow);
                    }
                    currentPageSize = response.getResults().size();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                bus.post(new SimilarTVShowsUpdatedEvent());
            }
        }.execute(page, showId);
    }

    public Integer getNextPage() {
        Integer nextPage = 1;
        if (currentPage != null && currentPage < totalPages) {
            nextPage = currentPage += 1;
        }
        return nextPage;
    }

    public class SimilarTVShowsUpdatedEvent {
    }

}
