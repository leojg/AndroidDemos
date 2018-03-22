package com.lgcode.popularshows.presenters;

import android.app.Activity;
import android.content.Intent;

import com.squareup.otto.Subscribe;

import com.lgcode.popularshows.TvShowDetailActivity;
import com.lgcode.popularshows.adapters.TVShowsAdapter;
import com.lgcode.popularshows.bus.BusProvider;
import com.lgcode.popularshows.models.TvShowListModel;
import com.lgcode.popularshows.views.TvShowListView;

/**
 * Created by leojg on 11/5/16.
 */
public class TvShowListPresenter {

    private TvShowListModel model;
    private TvShowListView view;

    public TvShowListPresenter(TvShowListModel model, TvShowListView view) {
        this.view = view;
        this.model = model;
        this.view.setAdapter(new TVShowsAdapter(model.getTvShowList(), view.getActivity(), BusProvider.getInstance()));
    }

    @Subscribe
    public void onTVShowsNewPageRequest(TvShowListView.TVShowsNewPageRequestEvent event) {
        model.fetchTVShows(model.getNextPage());
    }

    @Subscribe
    public void onTVShowsGetDetailView(TVShowsAdapter.TVShowsGetDetailViewEvent event) {
        Activity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity, TvShowDetailActivity.class);
        intent.putExtra(TvShowDetailActivity.KEY_TV_SHOW, model.getTVShow(event.showId));
        activity.startActivity(intent);
    }

    @Subscribe
    public void onTVShowsUpdated(TvShowListModel.TVShowsUpdatedEvent event) {
        view.updateList(model.getCurrentPageSize());
    }
}
