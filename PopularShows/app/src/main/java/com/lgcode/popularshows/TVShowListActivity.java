package com.lgcode.popularshows;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lgcode.popularshows.bus.BusProvider;
import com.lgcode.popularshows.models.TvShowListModel;
import com.lgcode.popularshows.presenters.TvShowListPresenter;
import com.lgcode.popularshows.views.TvShowListView;
import com.squareup.otto.Bus;

import glb.lg.popularshows.R;

public class TVShowListActivity extends AppCompatActivity {

    private Bus bus = BusProvider.getInstance();
    private TvShowListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_list);

        this.presenter = new TvShowListPresenter(
                new TvShowListModel(bus),
                new TvShowListView(this, bus));
    }

    @Override
    protected void onStart() {
        super.onStart();
        bus.register(presenter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        bus.unregister(presenter);
    }
}
