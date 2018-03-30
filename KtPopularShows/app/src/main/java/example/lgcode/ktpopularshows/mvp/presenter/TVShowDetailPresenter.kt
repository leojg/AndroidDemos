package example.lgcode.ktpopularshows.mvp.presenter

import android.content.Context
import android.support.v7.widget.RecyclerView
import example.lgcode.ktpopularshows.activity.TVShowDetailActivity
import example.lgcode.ktpopularshows.adapter.TVShowsAdapter
import example.lgcode.ktpopularshows.domain.TVShow
import example.lgcode.ktpopularshows.domain.TVShowDetailEvent
import example.lgcode.ktpopularshows.mvp.model.TVShowDetailModel
import example.lgcode.ktpopularshows.mvp.view.TVShowDetailView
import io.reactivex.observers.DisposableObserver

class TVShowDetailPresenter(
        private val model: TVShowDetailModel,
        private val view: TVShowDetailView
) {
    private var isLoading = false
    private var isLastPage = false
    private var pageSize = 0
    private var page = 1

    fun init(context: Context) {

        view.showLoader()

        view.updateTVShow(
                model.tvShow.title,
                model.tvShow.overview,
                model.tvShow.heroImage)

        view.setAdapter(TVShowsAdapter(context, object : DisposableObserver<TVShowDetailEvent>() {
            override fun onError(e: Throwable) {
            }

            override fun onComplete() {
            }

            override fun onNext(tvShowDetailEvent: TVShowDetailEvent) {
                view.activity.startActivity(TVShowDetailActivity.getIntent(view.activity, tvShowDetailEvent.tvShow))
            }
        }))

        fetchTVShows()

        view.setShowListScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = view.getListLinearLayoutManager()

                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading && !isLastPage) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0 && totalItemCount >= pageSize) {
                        isLoading = true
                        fetchTVShows()
                    }
                }
            }
        })
    }

    private fun fetchTVShows() {
        model.getSimilarTVShows(page, object : DisposableObserver<List<TVShow>>() {
            override fun onNext(tvShows: List<TVShow>) {
                view.updateList(tvShows)
                page += 1
                isLoading = false
                pageSize = tvShows.size
            }

            override fun onError(exception: Throwable) {
            }

            override fun onComplete() {
            }
        })
    }

}