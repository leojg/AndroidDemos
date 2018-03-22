package com.lgcode.popularshows;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.squareup.otto.Bus;

import glb.lg.popularshows.R;

import com.lgcode.popularshows.bus.BusProvider;
import com.lgcode.popularshows.models.TvShowDetailModel;
import com.lgcode.popularshows.presenters.TvShowDetailPresenter;
import com.lgcode.popularshows.views.TvShowDetailView;

/**
 * Created by le.garcia on 08/11/2016.
 */
public class TvShowDetailActivity extends AppCompatActivity {

    public static final String KEY_TV_SHOW = "tvShow";

    private Bus bus = BusProvider.getInstance();
    private TvShowDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_view);

        Intent intent = getIntent();

        TvShow tvShow = (TvShow) intent.getSerializableExtra(KEY_TV_SHOW);

        this.presenter = new TvShowDetailPresenter(
                new TvShowDetailModel(bus, tvShow),
                new TvShowDetailView(this, bus));
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
