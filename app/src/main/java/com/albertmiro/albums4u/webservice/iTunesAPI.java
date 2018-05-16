package com.albertmiro.albums4u.webservice;

import com.albertmiro.albums4u.repository.model.GenericITunesResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface iTunesAPI {

    @GET("lookup")
    Observable<GenericITunesResponse> getAlbumsByArtist(@Query("id") int artistId,
                                                        @Query("entity") String entityType);

    @GET("lookup")
    Observable<GenericITunesResponse> getAlbumSongs(@Query("id") int albumId,
                                                    @Query("entity") String entityType);

}
