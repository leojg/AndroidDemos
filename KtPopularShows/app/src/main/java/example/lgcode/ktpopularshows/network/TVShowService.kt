package example.lgcode.ktpopularshows.network

import example.lgcode.ktpopularshows.domain.TVShowListResultResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TVShowService {

    @GET("tv/top_rated")
    fun getTVShows(@Query("page") page: Int): Call<TVShowListResultResponse>

    @GET("tv/{id}/similar")
    fun getSimilarTVShows(@Path("id") id: Int, @Query("page") page: Int): Call<TVShowListResultResponse>

}