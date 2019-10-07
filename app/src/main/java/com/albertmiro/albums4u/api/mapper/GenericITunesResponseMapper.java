package com.albertmiro.albums4u.api.mapper;

import com.albertmiro.albums4u.api.model.GenericITunesResponse;
import com.albertmiro.albums4u.api.model.LookupResponse;
import com.albertmiro.albums4u.domain.Album;
import com.albertmiro.albums4u.domain.ArtistAndAlbums;

import javax.inject.Inject;

public class GenericITunesResponseMapper {

    @Inject
    public GenericITunesResponseMapper() {

    }

    public ArtistAndAlbums toAlbumsAndArtist(LookupResponseMapper lookupResponseMapper, GenericITunesResponse response) {
        ArtistAndAlbums artistAndAlbums = new ArtistAndAlbums();
        if (response != null && response.results != null) {
            for (LookupResponse item : response.results) {
                if (item.wrapperType != null) {
                    if (item.wrapperType.equals(Constants.WRAPPER_ARTIST)) {
                        artistAndAlbums.setArtist(lookupResponseMapper.toArtist(item));
                    } else if (item.wrapperType.equals(Constants.WRAPPER_COLLECTION)) {
                        artistAndAlbums.addAlbum(lookupResponseMapper.toAlbum(item));
                    } else if (item.wrapperType.equals(Constants.WRAPPER_TRACK)) {
                        Album album = artistAndAlbums.getAlbum(item.collectionId);
                        if (album != null) {
                            album.addTrack(lookupResponseMapper.toTrack(item));
                        }
                    }
                }
            }
        }
        return artistAndAlbums;
    }

}
