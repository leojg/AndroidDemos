package example.lgcode.ktpopularshows.mvp.presenter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import example.lgcode.ktpopularshows.activity.TVShowDetailActivity
import example.lgcode.ktpopularshows.adapter.TVShowsAdapter
import example.lgcode.ktpopularshows.domain.TVShow
import example.lgcode.ktpopularshows.domain.TVShowDetailEvent
import example.lgcode.ktpopularshows.mvp.model.TVShowListModel
import example.lgcode.ktpopularshows.mvp.view.TVShowListView
import io.reactivex.observers.DisposableObserver
import kotlinx.android.synthetic.main.activity_show_list.*

class TVShowListPresenter(
        private val model: TVShowListModel,
        private val view: TVShowListView
) {

    private var isLoading = false
    private var isLastPage = false
    private var pageSize = 0
    private var page = 1

    fun init(context: Context) {

        view.showLoader()
        fetchTVShows()

        view.setAdapter(TVShowsAdapter(context, object : DisposableObserver<TVShowDetailEvent>() {

            override fun onNext(tvShowDetailEvent: TVShowDetailEvent) {
                view.activity.startActivity(TVShowDetailActivity.getIntent(view.activity, tvShowDetailEvent.tvShow))
            }

            override fun onError(e: Throwable) {
                //todo show a snack
            }

            override fun onComplete() {
            }
        }))

        view.setShowListScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = view.activity.shows_list.layoutManager as LinearLayoutManager

                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading && !isLastPage) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0 && totalItemCount >= pageSize) {
                        view.activity.loading_panel.visibility = View.VISIBLE
                        isLoading = true
                        fetchTVShows()
                    }
                }
            }
        })
    }

    private fun fetchTVShows() {
        view.showLoader()
        model.getTVShows(page, object: DisposableObserver<List<TVShow>>() {
            override fun onNext(tvShows: List<TVShow>) {
                view.updateList(tvShows)
                page += 1
                isLoading = false
                pageSize = tvShows.size
                view.hideLoader()
            }

            override fun onError(exception: Throwable) {
                //todo show a snack
            }

            override fun onComplete() {
            }
        })
    }

}