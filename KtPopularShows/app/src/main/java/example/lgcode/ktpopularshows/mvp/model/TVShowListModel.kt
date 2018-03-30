package example.lgcode.ktpopularshows.mvp.model

import example.lgcode.ktpopularshows.domain.TVShow
import example.lgcode.ktpopularshows.repository.TVShowsRepository
import io.reactivex.observers.DisposableObserver

class TVShowListModel(
    private val tvShowRepository: TVShowsRepository
) {

    fun getTVShows(page: Int, observer: DisposableObserver<List<TVShow>>) {
        tvShowRepository.getTVShowsList(page, observer)
    }

}