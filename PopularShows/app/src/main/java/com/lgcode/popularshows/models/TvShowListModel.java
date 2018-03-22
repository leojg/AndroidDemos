package com.lgcode.popularshows.models;

import android.os.AsyncTask;

import com.squareup.otto.Bus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import glb.lg.popularshows.BuildConfig;
import com.lgcode.popularshows.network.client.TVShowClient;
import com.lgcode.popularshows.TvShow;
import com.lgcode.popularshows.network.response.TVShowListResultResponse;

/**
 * Created by le.garcia on 07/11/2016.
 */
public class TvShowListModel {

    private TVShowClient tvShowClient;
    private Bus bus;

    private Integer currentPage;
    private Integer totalPages;
    private Integer currentPageSize;
    private List<TvShow> tvShowList;

    public TvShowListModel(Bus bus) {
        this.bus = bus;
        this.tvShowList = new ArrayList<>();
        this.tvShowClient = new TVShowClient();
        fetchTVShows(1);
    }

    public List<TvShow> getTvShowList() {
        return tvShowList;
    }

    public Integer getCurrentPageSize() {
        return currentPageSize;
    }

    public TvShow getTVShow(int showId) {
        for (TvShow tvShow: this.tvShowList) {
            if (tvShow.getId() == showId) {
                return tvShow;
            }
        }
        return null;
    }

    public void fetchTVShows(int page) {
        new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... integers) {
                try {
                    int page = integers[0];
                    TVShowListResultResponse response = tvShowClient.fetchTVShows(page);

                    currentPage = response.getPage();
                    totalPages = response.getTotalPages();

                    for (TvShow tvShow: response.getResults()) {
                        tvShow.setImage(BuildConfig.SMALL_IMAGE_BASE_URL + tvShow.getImage());
                        tvShow.setHeroImage(BuildConfig.HERO_IMAGE_BASE_URL + tvShow.getHeroImage());
                        tvShowList.add(tvShow);
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
                bus.post(new TVShowsUpdatedEvent());
            }
        }.execute(page);
    }

    public Integer getNextPage() {
        Integer nextPage = 1;
        if (currentPage != null && currentPage < totalPages) {
            nextPage = currentPage += 1;
        }
        return nextPage;
    }

    public class TVShowsUpdatedEvent {
    }

}
