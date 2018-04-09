package example.lgcode.ktpopularshows.di

import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import example.lgcode.ktpopularshows.activity.TVShowListActivity

@Module(includes = arrayOf(AndroidInjectionModule::class))
abstract class ActivityBindingsModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(TVShowListModule::class))
    abstract fun TVShowListActivityInjector(): TVShowListActivity

}