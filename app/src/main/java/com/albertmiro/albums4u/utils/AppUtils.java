package com.albertmiro.albums4u.utils;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class AppUtils {

    public static final String SONG_ENTITY = "song";
    public static final String ALBUM_ENTITY = "album";

    public static int JACK_JOHNSON_ID = 909253;

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
