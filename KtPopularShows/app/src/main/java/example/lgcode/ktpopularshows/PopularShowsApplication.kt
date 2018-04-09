package example.lgcode.ktpopularshows

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import example.lgcode.ktpopularshows.di.DaggerAppComponent

class PopularShowsApplication: DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

}