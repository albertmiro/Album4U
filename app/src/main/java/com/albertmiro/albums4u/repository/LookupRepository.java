package com.albertmiro.albums4u.repository;

import com.albertmiro.albums4u.domain.ArtistAndAlbums;

import io.reactivex.Single;

interface LookupRepository {
    Single<ArtistAndAlbums> getAlbumSongs(final int albumId);
    Single<ArtistAndAlbums> getJackJohnsonAlbums();
}
