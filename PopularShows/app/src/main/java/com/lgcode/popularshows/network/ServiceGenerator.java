package com.lgcode.popularshows.network;

import java.io.IOException;

import glb.lg.popularshows.BuildConfig;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by le.garcia on 07/11/2016.
 */
public class ServiceGenerator {

    private static final String KEY_API_TOKEN = "api_key";

    private ServiceGenerator() {
    }
    public static <S> S createService(Class<S> serviceClass) {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        HttpUrl url = request
                                .url()
                                .newBuilder()
                                .addQueryParameter(KEY_API_TOKEN, BuildConfig.API_TOKEN)
                                .build();
                        request = request.newBuilder()
                                .addHeader("Accept", "application/json")
                                .url(url)
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();

        return new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVICES_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()
                .create(serviceClass);
    }

}
