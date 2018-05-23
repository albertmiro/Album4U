package com.albertmiro.albums4u.api.mapper;

import com.albertmiro.albums4u.api.model.LookupResponse;
import com.albertmiro.albums4u.domain.Album;
import com.albertmiro.albums4u.domain.Artist;
import com.albertmiro.albums4u.domain.Track;
import com.albertmiro.albums4u.domain.WrapperType;

import javax.inject.Inject;

public class LookupResponseMapper {

    @Inject
    public LookupResponseMapper() {

    }

    public Album toAlbum(LookupResponse item) {
        WrapperType wrapperType = getWrapperType(item.wrapperType);
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
        WrapperType wrapperType = getWrapperType(item.wrapperType);
        return new Artist(
                item.artistId,
                item.artistName,
                wrapperType);
    }

    public Track toTrack(LookupResponse item) {
        WrapperType wrapperType = getWrapperType(item.wrapperType);
        return new Track(item.trackId,
                item.trackNumber,
                item.trackName,
                item.trackTimeMillis,
                item.previewUrl,
                item.collectionId,
                wrapperType);
    }

    private WrapperType getWrapperType(String wrapperType) {
        if (wrapperType.equals(Constants.WRAPPER_ARTIST)) {
            return WrapperType.ARTIST;
        } else if (wrapperType.equals(Constants.WRAPPER_COLLECTION)) {
            return WrapperType.COLLECTION;
        } else if (wrapperType.equals(Constants.WRAPPER_TRACK)) {
            return WrapperType.TRACK;
        } else {
            return WrapperType.NO_DEFINED;
        }
    }
}
