package com.lgcode.popularshows.views;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.otto.Bus;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import glb.lg.popularshows.R;
import com.lgcode.popularshows.adapters.SimilarTVShowsAdapter;

/**
 * Created by leojg on 11/5/16.
 */
public class TvShowDetailView extends ActivityView {

    private Bus bus;

    @BindView(R.id.tv_show_detail_collapsing_toolbar) CollapsingToolbarLayout titleToolbar;
    @BindView(R.id.tv_show_overview) TextView tvShowOverview;
    @BindView(R.id.similar_tv_shows_list) RecyclerView similarTVShowsList;
    @BindView(R.id.tv_show_hero_image) ImageView tvShowHeroImage;

    private boolean isLoading;
    private boolean isLastPage;
    private int pageSize;


    public TvShowDetailView(AppCompatActivity activity, final Bus bus) {
        super(activity);
        this.bus = bus;

        setHomeAsUpEnabled(true);
        ButterKnife.bind(this, activity);
        similarTVShowsList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        similarTVShowsList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (!isLoading && !isLastPage) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= pageSize) {
                        bus.post(new SimilarTVShowsNewPageRequestEvent());
                        //TODO: Send event to request more items
                    }
                }
            }
        });


    }

    public void setAdapter(SimilarTVShowsAdapter adapter) {
        similarTVShowsList.setAdapter(adapter);
    }

    public void updateSimilarTVShowList(Integer currentPageSize) {
        similarTVShowsList.getAdapter().notifyDataSetChanged();
        pageSize = currentPageSize;
    }

    public void updateTVShow(String title, String overview, String heroImage) {
        Activity activity = this.getActivity();
        if (activity == null) {
            return;
        }
        titleToolbar.setTitle(title);
        tvShowOverview.setText(overview);
        Picasso.with(activity).load(heroImage).into(tvShowHeroImage);
    }

    //Events
    public class SimilarTVShowsNewPageRequestEvent {
    }
}
