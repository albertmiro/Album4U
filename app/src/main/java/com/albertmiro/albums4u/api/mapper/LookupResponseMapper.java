package com.albertmiro.albums4u.api.mapper;

import com.albertmiro.albums4u.api.model.LookupResponse;
import com.albertmiro.albums4u.domain.Album;
import com.albertmiro.albums4u.domain.Artist;
import com.albertmiro.albums4u.domain.Track;
import com.albertmiro.albums4u.domain.WrapperTypes;

import javax.inject.Inject;

public class LookupResponseMapper {

    @Inject
    public LookupResponseMapper() {

    }

    public Album toAlbum(LookupResponse item) {
        WrapperTypes.WRAPPER_TYPES wrapperType = getWrapperType(item.wrapperType);
        return new Album(
                item.collectionId,
                item.collectionName,
                item.artworkUrl100,
                item.trackCount,
                item.artistId,
                item.artistName,
                wrapperType);
    }

    public Artist toArtist(LookupResponse item) {
        WrapperTypes.WRAPPER_TYPES wrapperType = getWrapperType(item.wrapperType);
        return new Artist(
                item.artistId,
                item.artistName,
                wrapperType);
    }

    public Track toTrack(LookupResponse item) {
        WrapperTypes.WRAPPER_TYPES wrapperType = getWrapperType(item.wrapperType);
        return new Track(item.trackId,
                item.trackNumber,
                item.trackName,
                item.trackTimeMillis,
                item.previewUrl,
                item.collectionId,
                wrapperType);
    }

    private WrapperTypes.WRAPPER_TYPES getWrapperType(String wrapperType) {
        if (wrapperType.equals(WrapperTypes.WRAPPER_ARTIST)) {
            return WrapperTypes.WRAPPER_TYPES.ARTIST;
        } else if (wrapperType.equals(WrapperTypes.WRAPPER_COLLECTION)) {
            return WrapperTypes.WRAPPER_TYPES.COLLECTION;
        } else if (wrapperType.equals(WrapperTypes.WRAPPER_TRACK)) {
            return WrapperTypes.WRAPPER_TYPES.TRACK;
        } else {
            return WrapperTypes.WRAPPER_TYPES.NO_DEFINED;
        }
    }
}
