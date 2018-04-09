package example.lgcode.ktpopularshows.di

import dagger.Module
import dagger.Provides
import example.lgcode.ktpopularshows.activity.TVShowListActivity
import example.lgcode.ktpopularshows.mvp.model.TVShowListModel
import example.lgcode.ktpopularshows.mvp.presenter.TVShowListPresenter
import example.lgcode.ktpopularshows.mvp.view.TVShowListView
import example.lgcode.ktpopularshows.network.ServiceUtil
import example.lgcode.ktpopularshows.network.TVShowService
import example.lgcode.ktpopularshows.repository.TVShowsRepository

@Module
class TVShowListModule {

    @ActivityScope
    @Provides
    fun provideTVShowListPresenter(model: TVShowListModel, view: TVShowListView): TVShowListPresenter {
        return TVShowListPresenter(model, view)
    }

    @ActivityScope
    @Provides
    fun provideTVShowListModel(repository: TVShowsRepository): TVShowListModel {
        return TVShowListModel(repository)
    }

    @ActivityScope
    @Provides
    fun provideTVShowListView(activity: TVShowListActivity): TVShowListView {
        return TVShowListView(activity)
    }

    @ActivityScope
    @Provides
    fun provideTVShowService(): TVShowService {
        return ServiceUtil.tvShowsService
    }
}