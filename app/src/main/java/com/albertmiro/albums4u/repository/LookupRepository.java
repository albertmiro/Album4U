package com.albertmiro.albums4u.repository;

import com.albertmiro.albums4u.repository.model.GenericITunesResponse;
import com.albertmiro.albums4u.repository.model.GenericITunesResponseMapper;
import com.albertmiro.albums4u.utils.AppUtils;
import com.albertmiro.albums4u.viewmodel.data.ArtistAndAlbums;
import com.albertmiro.albums4u.webservice.RestAPI;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class LookupRepository {

    private final RestAPI api;

    public LookupRepository(RestAPI api) {
        this.api = api;
    }

    private LookupCache artistAndAlbumsCache = new LookupCache();

    public Observable<ArtistAndAlbums> getJackJohnsonAlbums() {
        if (artistAndAlbumsCache != null &&
                !artistAndAlbumsCache.isEmpty()) {
            return Observable.just(artistAndAlbumsCache.getArtistAndAlbums());
        }

        return api.getAlbumsByArtist(AppUtils.JACK_JOHNSON_ID)
                .map(new Function<GenericITunesResponse, ArtistAndAlbums>() {
                    @Override
                    public ArtistAndAlbums apply(GenericITunesResponse genericAlbumAndArtistResponse) {
                        ArtistAndAlbums artistAndAlbums = new GenericITunesResponseMapper().toAlbumsAndArtist(genericAlbumAndArtistResponse);
                        artistAndAlbumsCache.setArtistAndAlbums(artistAndAlbums);
                        return artistAndAlbums;
                    }
                });
    }

    public Observable<ArtistAndAlbums> getAlbumSongs(final int albumId) {
        if (artistAndAlbumsCache != null &&
                !artistAndAlbumsCache.isEmpty() &&
                artistAndAlbumsCache.hasSongsAlbum(albumId)) {
            return Observable.just(artistAndAlbumsCache.getArtistAndAlbums());
        }

        return api.getAlbumSongs(albumId)
                .map(new Function<GenericITunesResponse, ArtistAndAlbums>() {
                    @Override
                    public ArtistAndAlbums apply(GenericITunesResponse genericAlbumAndArtistResponse) {
                        ArtistAndAlbums artistAndAlbums = new GenericITunesResponseMapper().toAlbumsAndArtist(genericAlbumAndArtistResponse);
                        artistAndAlbumsCache.updateAlbumWithTracks(albumId,artistAndAlbums.getAlbum(albumId).getTracks());
                        return artistAndAlbums;
                    }
                });
    }

}
