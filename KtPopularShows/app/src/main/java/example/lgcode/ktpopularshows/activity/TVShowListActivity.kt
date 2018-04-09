package example.lgcode.ktpopularshows.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import example.lgcode.ktpopularshows.R
import example.lgcode.ktpopularshows.mvp.presenter.TVShowListPresenter
import javax.inject.Inject

class TVShowListActivity: AppCompatActivity() {

    @Inject
    lateinit var presenter: TVShowListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_list)
        //presenter = TVShowListPresenter(TVShowListModel(TVShowsRepository()), TVShowListView(this))
        presenter.setUp(this)
    }
}