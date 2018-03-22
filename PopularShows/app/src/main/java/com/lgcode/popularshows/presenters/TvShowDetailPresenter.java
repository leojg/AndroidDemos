package com.lgcode.popularshows.presenters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.squareup.otto.Subscribe;

import com.lgcode.popularshows.TvShow;
import com.lgcode.popularshows.TvShowDetailActivity;
import com.lgcode.popularshows.adapters.SimilarTVShowsAdapter;
import com.lgcode.popularshows.bus.BusProvider;
import com.lgcode.popularshows.models.TvShowDetailModel;
import com.lgcode.popularshows.views.TvShowDetailView;

/**
 * Created by leojg on 11/5/16.
 */
public class TvShowDetailPresenter {

    private TvShowDetailModel model;
    private TvShowDetailView view;

    public TvShowDetailPresenter(TvShowDetailModel model, TvShowDetailView view) {
        this.view = view;
        this.model = model;

        TvShow tvShow = model.getTvShow();
        view.updateTVShow(tvShow.getTitle(), tvShow.getOverview(), tvShow.getHeroImage());
        view.setAdapter(new SimilarTVShowsAdapter(model.getSimilarTvShowList(), view.getActivity(), BusProvider.getInstance()));
    }

    @Subscribe
    public void onSimilarTVShowsNewPageRequest(TvShowDetailView.SimilarTVShowsNewPageRequestEvent event) {
        model.fetchSimilarTVShows(model.getNextPage(), model.getTvShow().getId());
    }

    @Subscribe
    public void onTVShowsGetDetailView(SimilarTVShowsAdapter.TVShowsGetDetailViewEvent event) {
        AppCompatActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity, TvShowDetailActivity.class);
        intent.putExtra(TvShowDetailActivity.KEY_TV_SHOW, model.getSimilarTVShow(event.showId));
        activity.startActivity(intent);
    }

    @Subscribe
    public void onSimilarTVShowsUpdated(TvShowDetailModel.SimilarTVShowsUpdatedEvent event) {
        view.updateSimilarTVShowList(model.getCurrentPageSize());
    }
}
