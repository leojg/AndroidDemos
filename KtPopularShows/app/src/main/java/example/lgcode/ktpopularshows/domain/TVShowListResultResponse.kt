package example.lgcode.ktpopularshows.domain

import com.google.gson.annotations.SerializedName

data class TVShowListResultResponse(
        val page: Int,
        val results: List<TVShow>,
        @SerializedName("total_pages")
        val totalPages: Int
)