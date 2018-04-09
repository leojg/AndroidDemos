package example.lgcode.ktpopularshows.mvp.model

import example.lgcode.ktpopularshows.domain.TVShow
import example.lgcode.ktpopularshows.repository.TVShowsRepository
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class TVShowDetailModel(
        val tvShow: TVShow
) {

    @Inject lateinit var tvShowRepository: TVShowsRepository

    fun getSimilarTVShows(page: Int, observer: DisposableObserver<List<TVShow>>) {
        tvShowRepository.getSimilarTVShowsList(tvShow.id, page, observer)
    }



}