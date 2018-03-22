package com.lgcode.popularshows.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.otto.Bus;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import glb.lg.popularshows.R;
import com.lgcode.popularshows.TvShow;

/**
 * Created by leojg on 11/9/16.
 */
public class TVShowsAdapter extends RecyclerView.Adapter<TVShowsAdapter.TVShowsViewHolder> {

    private List<TvShow> tvShowItems;
    private Bus bus;
    private Context context;
    private int lastPosition = -1;

    public TVShowsAdapter(List<TvShow> tvShowItems, Context context, Bus bus) {
        this.tvShowItems = tvShowItems;
        this.bus = bus;
        this.context = context;
    }

    @Override
    public TVShowsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tv_show_item, parent, false);
        return new TVShowsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TVShowsViewHolder viewHolder, int position) {

        final TvShow tvShow = tvShowItems.get(position);
        viewHolder.tvShowTitle.setText(tvShow.getTitle());
        viewHolder.tvShowVotes.setText(String.valueOf(tvShow.getVoteAverage()));
        Picasso.with(context).load(tvShow.getImage()).into(viewHolder.tvShowThumbnail);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bus.post(new TVShowsGetDetailViewEvent(tvShow.getId()));
            }
        });
        setAnimation(viewHolder.itemView, position);
    }

    private void setAnimation(View view, int position) {
        if (position > lastPosition) {
            view.animate().cancel();
            view.setTranslationY(100);
            view.setAlpha(0);
            view.animate().alpha(1.0f).translationY(0).setDuration(200).setStartDelay(100);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return (null != tvShowItems ? tvShowItems.size() : 0);
    }

    class TVShowsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_show_thumbnail) ImageView tvShowThumbnail;
        @BindView(R.id.tv_show_title) TextView tvShowTitle;
        @BindView(R.id.tv_show_votes) TextView tvShowVotes;

        TVShowsViewHolder(View view) {
            super(view);
            ButterKnife.setDebug(true);
            ButterKnife.bind(this, view);
        }
    }

    public class TVShowsGetDetailViewEvent {
        public final Integer showId;

        TVShowsGetDetailViewEvent(Integer showId) {
            this.showId = showId;
        }
    }
}
