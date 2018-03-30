package example.lgcode.ktpopularshows.adapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import example.lgcode.ktpopularshows.BuildConfig
import example.lgcode.ktpopularshows.R
import example.lgcode.ktpopularshows.domain.TVShow
import example.lgcode.ktpopularshows.domain.TVShowDetailEvent
import io.reactivex.observers.DisposableObserver
import kotlinx.android.synthetic.main.tv_show_item.view.*

class TVShowsAdapter(
        private val context: Context,
        private val observer: DisposableObserver<TVShowDetailEvent>
): RecyclerView.Adapter<TVShowsAdapter.TVShowsViewHolder>() {

    private val tvShowItems: MutableList<TVShow> = mutableListOf()
    private var lastPosition = -1
    private lateinit var recyclerView: RecyclerView

    fun addAll(tvShows: List<TVShow>) {
        tvShowItems.addAll(tvShows)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TVShowsViewHolder {
        if (isVertical()) {
            return TVShowsViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.tv_show_item, parent, false))
        } else {
            return TVShowsViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.similar_tv_show_item, parent, false))

        }
    }

    override fun onBindViewHolder(holder: TVShowsViewHolder?, position: Int) {
        val tvShow = tvShowItems.get(position)

        holder!!.view.tv_show_title.text = tvShow.title
        holder.view.tv_show_votes.text = tvShow.voteAverage.toString()
        Picasso.with(context)
                .load(StringBuilder(BuildConfig.SMALL_IMAGE_BASE_URL).append(tvShow.image).toString())
                .into(holder.view.tv_show_thumbnail)
        holder.view.setOnClickListener {
            observer.onNext(TVShowDetailEvent(tvShow))
        }

        setAnimation(holder.view, position)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView!!
    }

    private fun setAnimation(view: View, position: Int) {
        if (position > lastPosition) {
            view.animate().cancel()
            view.translationY = 100f
            view.alpha = 0f
            if (isVertical()) {
                view.animate().alpha(1.0f).translationY(0f).setDuration(200).startDelay = 100
            } else {
                view.animate().alpha(1.0f).translationX(0f).setDuration(200).startDelay = 100
            }
            lastPosition = position
        }
    }

    private fun isVertical(): Boolean {
        return (recyclerView.layoutManager as LinearLayoutManager).orientation == LinearLayoutManager.VERTICAL
    }

    override fun getItemCount(): Int {
        return tvShowItems.size
    }

    class TVShowsViewHolder(val view: View): RecyclerView.ViewHolder(view)

}