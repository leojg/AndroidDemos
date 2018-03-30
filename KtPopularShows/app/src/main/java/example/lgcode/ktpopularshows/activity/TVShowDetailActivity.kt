package example.lgcode.ktpopularshows.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import example.lgcode.ktpopularshows.R
import example.lgcode.ktpopularshows.domain.TVShow
import example.lgcode.ktpopularshows.mvp.model.TVShowDetailModel
import example.lgcode.ktpopularshows.mvp.presenter.TVShowDetailPresenter
import example.lgcode.ktpopularshows.mvp.view.TVShowDetailView
import example.lgcode.ktpopularshows.repository.TVShowsRepository

class TVShowDetailActivity: AppCompatActivity() {

    private lateinit var presenter: TVShowDetailPresenter

    companion object {

        val KEY_TV_SHOW = "tvShow"

        fun getIntent(activity: Activity, tvShow: TVShow): Intent {
            val intent = Intent(activity, TVShowDetailActivity::class.java)
            intent.putExtra(KEY_TV_SHOW, tvShow)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_view)

        val tvShow = intent.extras[KEY_TV_SHOW] as TVShow
        presenter = TVShowDetailPresenter(TVShowDetailModel(tvShow, TVShowsRepository()), TVShowDetailView(this))
        presenter.init(this)
    }

}