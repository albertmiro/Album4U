package com.albertmiro.albums4u.viewmodel.data;

import com.albertmiro.albums4u.utils.AppUtils;

public class Track extends CommonLookupData {

    private final int trackNumber;
    private final int trackId;
    private final int albumId;
    private final long trackTimeMillis;
    private final String previewUrl;
    private final String trackName;

    public Track(int trackId, int trackNumber, String trackName, long trackTimeMillis, String previewUrl,
                 int albumId, AppUtils.WRAPPER_TYPES wrapperType) {
        this.trackId = trackId;
        this.trackNumber = trackNumber;
        this.trackName = trackName;
        this.trackTimeMillis = trackTimeMillis;
        this.previewUrl = previewUrl;
        this.albumId = albumId;
        this.wrapperType = wrapperType;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public int getTrackId() {
        return trackId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public long getTrackTimeMillis() {
        return trackTimeMillis;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }
}
