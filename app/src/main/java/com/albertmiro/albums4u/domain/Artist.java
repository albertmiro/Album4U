package com.albertmiro.albums4u.domain;

public class Artist extends CommonLookupData {

    public Artist(int artistId, String artistName, WrapperTypes.WRAPPER_TYPES wrapperType) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.wrapperType = wrapperType;
    }
}
