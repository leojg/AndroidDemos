package example.lgcode.ktpopularshows.mvp.view

import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.squareup.picasso.Picasso
import example.lgcode.ktpopularshows.BuildConfig
import example.lgcode.ktpopularshows.adapter.TVShowsAdapter
import example.lgcode.ktpopularshows.domain.TVShow
import kotlinx.android.synthetic.main.activity_detail_view.*
import java.lang.ref.WeakReference

class TVShowDetailView(val activity: AppCompatActivity): ActivityView(WeakReference(activity)) {

    init {
        if (activity?.tvshow_detail_toolbar != null) {
            activity.setSupportActionBar(activity.tvshow_detail_toolbar)
        }
        setHomeAsUpEnabled(true)
        activity.similar_tv_shows_list.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

    }

    fun getListLinearLayoutManager(): LinearLayoutManager {
        return activity.similar_tv_shows_list.layoutManager as LinearLayoutManager
    }

    fun setShowListScrollListener(scrollListener: RecyclerView.OnScrollListener) {
        activity.similar_tv_shows_list.addOnScrollListener(scrollListener)
    }

    fun setAdapter(adapter: TVShowsAdapter) {
        activity.similar_tv_shows_list.adapter = adapter
    }

    fun updateTVShow(title: String, overview: String, heroImage: String) {
        if (activity == null) {
            return
        }

        activity.tv_show_detail_collapsing_toolbar.title = title
        activity.tv_show_overview.text = overview
        Picasso.with(activity)
                .load(StringBuilder(BuildConfig.HERO_IMAGE_BASE_URL).append(heroImage).toString())
                .into(activity.tv_show_hero_image)

    }

    fun updateList(tvShows: List<TVShow>) {
        (activity.similar_tv_shows_list.adapter as TVShowsAdapter).addAll(tvShows)
        activity.similar_tv_shows_list.adapter.notifyDataSetChanged()
    }

    private fun setHomeAsUpEnabled(showHomeAsUp: Boolean) {
        val actionBar = getActionBar()

        if (activity.tvshow_detail_toolbar == null || actionBar == null) {
            return
        }

        activity.tvshow_detail_toolbar.setNavigationOnClickListener({
            activity.onBackPressed()
        })

        actionBar.setDisplayHomeAsUpEnabled(showHomeAsUp)

    }

    private fun getActionBar(): ActionBar? {
        return activity.supportActionBar
    }

}