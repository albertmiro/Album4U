package com.albertmiro.albums4u.viewmodel.data;

import com.albertmiro.albums4u.utils.AppUtils;

public class Artist extends CommonLookupData {

    public Artist(int artistId, String artistName, AppUtils.WRAPPER_TYPES wrapperType) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.wrapperType = wrapperType;
    }
}
