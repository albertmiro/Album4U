package com.albertmiro.albums4u.api;


import com.albertmiro.albums4u.api.model.GenericITunesResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ITunesService {

    @GET("lookup")
    Single<GenericITunesResponse> getAlbumsByArtist(@Query("id") int artistId,
                                                    @Query("entity") String entityType);

    @GET("lookup")
    Single<GenericITunesResponse> getAlbumSongs(@Query("id") int albumId,
                                                    @Query("entity") String entityType);

}
