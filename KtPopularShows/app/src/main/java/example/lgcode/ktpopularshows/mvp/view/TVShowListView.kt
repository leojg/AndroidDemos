package example.lgcode.ktpopularshows.mvp.view

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import example.lgcode.ktpopularshows.adapter.TVShowsAdapter
import example.lgcode.ktpopularshows.domain.TVShow
import kotlinx.android.synthetic.main.activity_show_list.*
import java.lang.ref.WeakReference

class TVShowListView(
        val activity: AppCompatActivity
): ActivityView(WeakReference(activity)) {

    fun setUp() {
        activity.shows_list.layoutManager = LinearLayoutManager(activity)
    }

    fun getListLinearLayoutManager(): LinearLayoutManager {
        return activity.shows_list.layoutManager as LinearLayoutManager
    }

    fun setShowListScrollListener(scrollListener: RecyclerView.OnScrollListener) {
        activity.shows_list.addOnScrollListener(scrollListener)
    }

    fun setAdapter(adapter: TVShowsAdapter) {
        activity.shows_list.adapter = adapter
    }

    fun updateList(tvShows: List<TVShow>) {
        (activity.shows_list.adapter as TVShowsAdapter).addAll(tvShows)
        activity.shows_list.adapter.notifyDataSetChanged()
    }

    fun showLoader() {
        activity.loading_panel.visibility = View.VISIBLE
    }

    fun hideLoader() {
        activity.loading_panel.visibility = View.GONE
    }

}