package example.lgcode.ktpopularshows.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBindingsModule::class))
interface AppComponent: AndroidInjector<DaggerApplication> {

    override fun inject(instance: DaggerApplication?)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }

}