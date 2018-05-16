package com.albertmiro.albums4u.utils;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class AppUtils {

    public static final String SONG_ENTITY = "song";
    public static final String ALBUM_ENTITY = "album";

    public static String WRAPPER_TRACK = "track";
    public static String WRAPPER_COLLECTION = "collection";
    public static String WRAPPER_ARTIST = "artist";

    public static int JACK_JOHNSON_ID = 909253;

    public enum WRAPPER_TYPES {
        TRACK, COLLECTION, ARTIST, NO_DEFINED
    }

    public static WRAPPER_TYPES getWrapperType(String wrapperType) {
        if (wrapperType.equals(AppUtils.WRAPPER_ARTIST)) {
            return WRAPPER_TYPES.ARTIST;
        } else if (wrapperType.equals(AppUtils.WRAPPER_COLLECTION)) {
            return WRAPPER_TYPES.COLLECTION;
        } else if (wrapperType.equals(AppUtils.WRAPPER_TRACK)) {
            return WRAPPER_TYPES.TRACK;
        } else {
            return WRAPPER_TYPES.NO_DEFINED;
        }
    }

    public static <C> ArrayList<C> toList(SparseArray<C> sparseArray) {
        if (sparseArray == null) return null;
        ArrayList<C> arrayList = new ArrayList<>(sparseArray.size());
        for (int i = 0; i < sparseArray.size(); i++)
            arrayList.add(sparseArray.valueAt(i));
        return arrayList;
    }

    public static String convertMillisToString(long millis) {
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millis),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
        );
    }


}
