package com.albertmiro.albums4u.repository;

import com.albertmiro.albums4u.api.model.GenericITunesResponseMapper;
import com.albertmiro.albums4u.utils.AppUtils;
import com.albertmiro.albums4u.viewmodel.data.ArtistAndAlbums;
import com.albertmiro.albums4u.api.ITunesService;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class LookupRepository {

    private ITunesService iTunesService;

    @Inject
    LookupRepository(ITunesService iTunesService)
    {
        this.iTunesService = iTunesService;
    }

    private LookupCache artistAndAlbumsCache = new LookupCache();

    public Observable<ArtistAndAlbums> getJackJohnsonAlbums() {
        if (artistAndAlbumsCache != null &&
                !artistAndAlbumsCache.isEmpty()) {
            return Observable.just(artistAndAlbumsCache.getArtistAndAlbums());
        }

        return iTunesService.getAlbumsByArtist(AppUtils.JACK_JOHNSON_ID, AppUtils.ALBUM_ENTITY)
                .map(genericAlbumAndArtistResponse -> {
                    ArtistAndAlbums artistAndAlbums = new GenericITunesResponseMapper().toAlbumsAndArtist(genericAlbumAndArtistResponse);
                    artistAndAlbumsCache.setArtistAndAlbums(artistAndAlbums);
                    return artistAndAlbums;
                });
    }

    public Observable<ArtistAndAlbums> getAlbumSongs(final int albumId) {
        if (artistAndAlbumsCache != null &&
                !artistAndAlbumsCache.isEmpty() &&
                artistAndAlbumsCache.hasSongsAlbum(albumId)) {
            return Observable.just(artistAndAlbumsCache.getArtistAndAlbums());
        }

        return iTunesService.getAlbumSongs(albumId, AppUtils.SONG_ENTITY)
                .map(genericAlbumAndArtistResponse -> {
                    ArtistAndAlbums artistAndAlbums = new GenericITunesResponseMapper().toAlbumsAndArtist(genericAlbumAndArtistResponse);
                    artistAndAlbumsCache.updateAlbumWithTracks(albumId,artistAndAlbums.getAlbum(albumId).getTracks());
                    return artistAndAlbums;
                });
    }

}
