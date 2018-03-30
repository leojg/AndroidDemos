package example.lgcode.ktpopularshows.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import example.lgcode.ktpopularshows.R
import example.lgcode.ktpopularshows.mvp.model.TVShowListModel
import example.lgcode.ktpopularshows.mvp.presenter.TVShowListPresenter
import example.lgcode.ktpopularshows.mvp.view.TVShowListView
import example.lgcode.ktpopularshows.repository.TVShowsRepository

class TVShowListActivity: AppCompatActivity() {

    lateinit var presenter: TVShowListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_list)

        presenter = TVShowListPresenter(TVShowListModel(TVShowsRepository()), TVShowListView(this))
        presenter.init(this)
    }
}