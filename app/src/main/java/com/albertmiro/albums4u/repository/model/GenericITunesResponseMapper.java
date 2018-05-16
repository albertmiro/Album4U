package com.albertmiro.albums4u.repository.model;

import com.albertmiro.albums4u.utils.AppUtils;
import com.albertmiro.albums4u.viewmodel.data.Album;
import com.albertmiro.albums4u.viewmodel.data.ArtistAndAlbums;

public class GenericITunesResponseMapper {

    public ArtistAndAlbums toAlbumsAndArtist(GenericITunesResponse response) {
        ArtistAndAlbums artistAndAlbums = new ArtistAndAlbums();
        for (LookupResponse item : response.results) {
            if (item.wrapperType.equals(AppUtils.WRAPPER_ARTIST)) {
                artistAndAlbums.setArtist(new LookupResponseMapper().toArtist(item));
            } else if (item.wrapperType.equals(AppUtils.WRAPPER_COLLECTION)) {
                artistAndAlbums.addAlbum(new LookupResponseMapper().toAlbum(item));
            } else if (item.wrapperType.equals(AppUtils.WRAPPER_TRACK)) {
                Album album = artistAndAlbums.getAlbum(item.collectionId);
                if (album != null) {
                    album.addTrack(new LookupResponseMapper().toTrack(item));
                }
            }
        }
        return artistAndAlbums;
    }
}
