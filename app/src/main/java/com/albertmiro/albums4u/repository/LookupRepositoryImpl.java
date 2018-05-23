package com.albertmiro.albums4u.repository;

import com.albertmiro.albums4u.api.ITunesService;
import com.albertmiro.albums4u.api.mapper.GenericITunesResponseMapper;
import com.albertmiro.albums4u.api.mapper.LookupResponseMapper;
import com.albertmiro.albums4u.domain.ArtistAndAlbums;
import com.albertmiro.albums4u.utils.AppUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class LookupRepositoryImpl implements LookupRepository {

    private final ITunesService iTunesService;
    private final LookupResponseMapper lookupResponseMapper;

    @Inject
    LookupRepositoryImpl(ITunesService iTunesService, LookupResponseMapper lookupResponseMapper)
    {
        this.iTunesService = iTunesService;
        this.lookupResponseMapper = lookupResponseMapper;
    }

    private LookupCache artistAndAlbumsCache = new LookupCache();

    public Single<ArtistAndAlbums> getJackJohnsonAlbums() {
        if (artistAndAlbumsCache != null &&
                !artistAndAlbumsCache.isEmpty()) {
            return Single.just(artistAndAlbumsCache.getArtistAndAlbums());
        }

        return iTunesService.getAlbumsByArtist(AppUtils.JACK_JOHNSON_ID, AppUtils.ALBUM_ENTITY)
                .map(genericAlbumAndArtistResponse -> {
                    ArtistAndAlbums artistAndAlbums = new GenericITunesResponseMapper().toAlbumsAndArtist(genericAlbumAndArtistResponse);
                    artistAndAlbumsCache.setArtistAndAlbums(artistAndAlbums);
                    return artistAndAlbums;
                });
    }

    public Single<ArtistAndAlbums> getAlbumSongs(final int albumId) {
        if (artistAndAlbumsCache != null &&
                !artistAndAlbumsCache.isEmpty() &&
                artistAndAlbumsCache.hasSongsAlbum(albumId)) {
            return Single.just(artistAndAlbumsCache.getArtistAndAlbums());
        }

        return iTunesService.getAlbumSongs(albumId, AppUtils.SONG_ENTITY)
                .map(genericAlbumAndArtistResponse -> {
                    ArtistAndAlbums artistAndAlbums = new GenericITunesResponseMapper().toAlbumsAndArtist(genericAlbumAndArtistResponse);
                    artistAndAlbumsCache.updateAlbumWithTracks(albumId,artistAndAlbums.getAlbum(albumId).getTracks());
                    return artistAndAlbums;
                });
    }

}
