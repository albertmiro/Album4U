package com.albertmiro.albums4u.api.mapper;

import com.albertmiro.albums4u.api.model.GenericITunesResponse;
import com.albertmiro.albums4u.api.model.LookupResponse;
import com.albertmiro.albums4u.domain.Album;
import com.albertmiro.albums4u.domain.ArtistAndAlbums;
import com.albertmiro.albums4u.domain.WrapperTypes;

public class GenericITunesResponseMapper {

    public ArtistAndAlbums toAlbumsAndArtist(GenericITunesResponse response) {
        ArtistAndAlbums artistAndAlbums = new ArtistAndAlbums();
        for (LookupResponse item : response.results) {
            if (item.wrapperType.equals(WrapperTypes.WRAPPER_ARTIST)) {
                artistAndAlbums.setArtist(new LookupResponseMapper().toArtist(item));
            } else if (item.wrapperType.equals(WrapperTypes.WRAPPER_COLLECTION)) {
                artistAndAlbums.addAlbum(new LookupResponseMapper().toAlbum(item));
            } else if (item.wrapperType.equals(WrapperTypes.WRAPPER_TRACK)) {
                Album album = artistAndAlbums.getAlbum(item.collectionId);
                if (album != null) {
                    album.addTrack(new LookupResponseMapper().toTrack(item));
                }
            }
        }
        return artistAndAlbums;
    }

}
