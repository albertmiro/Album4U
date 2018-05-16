package com.albertmiro.albums4u.webservice;

import com.albertmiro.albums4u.BuildConfig;
import com.albertmiro.albums4u.repository.model.GenericITunesResponse;
import com.albertmiro.albums4u.utils.AppUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestAPI {

    private iTunesAPI api;

    public void init() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(10, TimeUnit.SECONDS);
        httpClient.readTimeout(30, TimeUnit.SECONDS);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(httpClient.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(iTunesAPI.class);
    }

    public Observable<GenericITunesResponse> getAlbumsByArtist(int artistId) {
        return api.getAlbumsByArtist(artistId, AppUtils.ALBUM_ENTITY);
    }

    public Observable<GenericITunesResponse> getAlbumSongs(int albumId) {
        return api.getAlbumSongs(albumId, AppUtils.SONG_ENTITY);
    }

}
