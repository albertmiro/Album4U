package com.albertmiro.albums4u.api.model;

import com.albertmiro.albums4u.utils.AppUtils;
import com.albertmiro.albums4u.viewmodel.data.Album;
import com.albertmiro.albums4u.viewmodel.data.Artist;
import com.albertmiro.albums4u.viewmodel.data.Track;

public class LookupResponseMapper {

    public Album toAlbum(LookupResponse item) {
        AppUtils.WRAPPER_TYPES wrapperType = AppUtils.getWrapperType(item.wrapperType);
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
        AppUtils.WRAPPER_TYPES wrapperType = AppUtils.getWrapperType(item.wrapperType);
        return new Artist(
                item.artistId,
                item.artistName,
                wrapperType);
    }

    public Track toTrack(LookupResponse item) {
        AppUtils.WRAPPER_TYPES wrapperType = AppUtils.getWrapperType(item.wrapperType);
        return new Track(item.trackId,
                item.trackNumber,
                item.trackName,
                item.trackTimeMillis,
                item.previewUrl,
                item.collectionId,
                wrapperType);
    }
}
