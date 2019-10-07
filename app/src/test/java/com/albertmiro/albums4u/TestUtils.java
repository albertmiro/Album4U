package com.albertmiro.albums4u;

import com.albertmiro.albums4u.api.model.GenericITunesResponse;
import com.albertmiro.albums4u.api.model.LookupResponse;
import com.albertmiro.albums4u.domain.Artist;
import com.albertmiro.albums4u.domain.ArtistAndAlbums;

import java.util.ArrayList;
import java.util.Arrays;

import io.reactivex.Single;

public class TestUtils {
    public static final String SONG_ENTITY = "song";
    public static final String ALBUM_ENTITY = "album";

    public static final String COLLECTION_WRAPPER_TYPE = "collection";
    public static final String TRACK_WRAPPER_TYPE = "track";

    public static final int JACK_JOHNSON_ID = 909253;
    public static final int ALBUM_ID_WITH_SONGS = 659234734;
    public static final int ALBUM_ID_NO_SONGS = 109181599;

    public static ArtistAndAlbums createArtistWithNoAlbums() {
        ArtistAndAlbums artistAndAlbums = new ArtistAndAlbums();
        artistAndAlbums.setArtist(new Artist());
        return artistAndAlbums;
    }

    public static Single<GenericITunesResponse> createTwoResults() {
        GenericITunesResponse genericITunesResponse = new GenericITunesResponse();
        genericITunesResponse.resultCount = 2;
        ArrayList<LookupResponse> results = new ArrayList<>();

        LookupResponse response1 = new LookupResponse();
        response1.artistId = 1;
        response1.wrapperType = "artist";

        LookupResponse response2 = new LookupResponse();
        response2.artistId = 1;
        response2.wrapperType = "collection";

        LookupResponse response3 = new LookupResponse();
        response3.artistId = 1;
        response3.wrapperType = "collection";

        results.addAll(Arrays.asList(response1,response2, response3));
        genericITunesResponse.results = results;
        return Single.just(genericITunesResponse);
    }
}
