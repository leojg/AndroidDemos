package example.lgcode.ktpopularshows.repository

import example.lgcode.ktpopularshows.domain.TVShow
import example.lgcode.ktpopularshows.network.ServiceUtil
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class TVShowsRepository {

    private val tvShowService = ServiceUtil.tvShowsService

    fun getTVShowsList(page: Int, observer: DisposableObserver<List<TVShow>>) {
        Observable.create(ObservableOnSubscribe<List<TVShow>> { emitter ->
            val response = tvShowService.getTVShows(page).execute()

            //TODO: Handle failure
            if (response.isSuccessful) {
                val tvShowsListResultResponse = response!!.body()
                emitter.onNext(tvShowsListResultResponse!!.results)
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }

    fun getSimilarTVShowsList(tvShowId: Int, page: Int, observer: DisposableObserver<List<TVShow>>) {
        Observable.create(ObservableOnSubscribe<List<TVShow>> { emitter ->
            val response = tvShowService.getSimilarTVShows(tvShowId, page).execute()

            //TODO: Handle failure
            if (response.isSuccessful) {
                val tvShowsListResultResponse = response!!.body()
                emitter.onNext(tvShowsListResultResponse!!.results)
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }

}