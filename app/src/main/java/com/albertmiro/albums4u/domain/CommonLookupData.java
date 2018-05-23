package com.albertmiro.albums4u.domain;

public class CommonLookupData {

    int artistId;
    String artistName;
    WrapperTypes.WRAPPER_TYPES wrapperType;

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public WrapperTypes.WRAPPER_TYPES getWrapperType() {
        return wrapperType;
    }

    public void setWrapperType(WrapperTypes.WRAPPER_TYPES wrapperType) {
        this.wrapperType = wrapperType;
    }
}
