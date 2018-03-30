package example.lgcode.ktpopularshows.network

import example.lgcode.ktpopularshows.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceUtil {

    private val KEY_API_TOKEN = "api_key"

    val tvShowsService = createTVShowsService()

    private fun createTVShowsService(): TVShowService {
        val httpClient = OkHttpClient.Builder().addInterceptor({ chain ->
            var request = chain.request()
            val url = request
                    .url()
                    .newBuilder()
                    .addQueryParameter(KEY_API_TOKEN, BuildConfig.API_TOKEN)
                    .build()
            request = request
                    .newBuilder()
                    .addHeader("Accept", "application/json")
                    .url(url)
                    .build()
            chain.proceed(request)
        }).build()

        return Retrofit.Builder()
                .baseUrl(BuildConfig.SERVICES_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()
                .create(TVShowService::class.java)
    }

}