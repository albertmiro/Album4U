package com.albertmiro.albums4u.viewmodel.data;

import com.albertmiro.albums4u.utils.AppUtils;

public class CommonLookupData {

    int artistId;
    String artistName;
    AppUtils.WRAPPER_TYPES wrapperType;

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

    public AppUtils.WRAPPER_TYPES getWrapperType() {
        return wrapperType;
    }

    public void setWrapperType(AppUtils.WRAPPER_TYPES wrapperType) {
        this.wrapperType = wrapperType;
    }
}
