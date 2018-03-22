package com.lgcode.popularshows.views;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.squareup.otto.Bus;

import butterknife.BindView;
import butterknife.ButterKnife;
import glb.lg.popularshows.R;

import com.lgcode.popularshows.adapters.TVShowsAdapter;

/**
 * Created by leojg on 11/5/16.
 */
public class TvShowListView extends ActivityView {

    @BindView(R.id.loading_panel) RelativeLayout loadingPanel;
    @BindView(R.id.shows_list) RecyclerView recyclerView;

    private Bus bus;
    private boolean isLoading;
    private boolean isLastPage;
    private int pageSize;

    public TvShowListView(AppCompatActivity activity, final Bus bus) {
        super(activity);

        this.bus = bus;

        ButterKnife.bind(this, activity);

        //recyclerView = (RecyclerView) activity.findViewById(R.id.shows_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

                        loadingPanel.setVisibility(View.VISIBLE);
                        isLoading = true;
                        bus.post(new TVShowsNewPageRequestEvent());
                    }
                }
            }
        });

    }

    public void setAdapter(TVShowsAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    public void updateList(Integer currentPageSize) {
        recyclerView.getAdapter().notifyDataSetChanged();

        loadingPanel.setVisibility(View.GONE);
        isLoading = false;
        pageSize = currentPageSize;
    }

    //Events
    public class TVShowsNewPageRequestEvent {
    }

}
