package example.lgcode.ktpopularshows.mvp.view

import android.support.v7.app.AppCompatActivity
import android.view.View
import java.lang.ref.WeakReference

abstract class ActivityView(
        private val activityRef: WeakReference<AppCompatActivity>
) {

}