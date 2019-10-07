package com.albertmiro.albums4u.api;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.albertmiro.albums4u.TestUtils.*;

public class ITunesServiceTest {

    private ITunesService iTunesService;
    private MockWebServer mockWebServer;

    @Before
    public void createService() {
        mockWebServer = new MockWebServer();

        iTunesService = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ITunesService.class);
    }

    @After
    public void stopService() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void shouldGetAlbumsList() throws IOException {
        enqueueResponse("albums_jackjohnson.json");
        iTunesService.getAlbumsByArtist(JACK_JOHNSON_ID, ALBUM_ENTITY)
                .test()
                .assertValue(genericITunesResponse -> genericITunesResponse.resultCount == 25)
                .assertValue(genericITunesResponse -> genericITunesResponse.results.size() == 25);
    }

    @Test
    public void shouldGetJackJohnsonAlbumsList() throws IOException {
        enqueueResponse("albums_jackjohnson.json");
        iTunesService.getAlbumsByArtist(JACK_JOHNSON_ID, ALBUM_ENTITY)
                .test()
                .assertValue(genericITunesResponse -> genericITunesResponse.results.size() > 0)
                .assertValue(genericITunesResponse -> genericITunesResponse.results.get(0)
                        .artistId == JACK_JOHNSON_ID);

    }

    @Test
    public void shouldGetAlbumWithNoSongsAndJustTheArtist() throws IOException {
        enqueueResponse("album_no_songs.json");
        iTunesService.getAlbumSongs(ALBUM_ID_NO_SONGS, SONG_ENTITY)
                .test()
                .assertValue(genericITunesResponse -> genericITunesResponse.resultCount == 1)
                .assertValue(genericITunesResponse -> genericITunesResponse.results.size() == 1)
                .assertValue(genericITunesResponse -> genericITunesResponse.results.get(0)
                        .wrapperType.equals(COLLECTION_WRAPPER_TYPE));
    }

    @Test
    public void shouldGetAlbumWithSongs() throws IOException {
        enqueueResponse("album_with_songs.json");
        iTunesService.getAlbumSongs(ALBUM_ID_WITH_SONGS, SONG_ENTITY)
                .test()
                .assertValue(genericITunesResponse -> genericITunesResponse.resultCount == 13)
                .assertValue(genericITunesResponse -> genericITunesResponse.results.size() == 13)
                .assertValue(genericITunesResponse -> genericITunesResponse.results.get(0)
                        .wrapperType.equals(COLLECTION_WRAPPER_TYPE))
                .assertValue(genericITunesResponse -> genericITunesResponse.results.get(1)
                        .wrapperType.equals(TRACK_WRAPPER_TYPE));
    }

    @Test
    public void error400() {
        mockWebServer.enqueue(new MockResponse().setBody("{error:\"bad request\"").setResponseCode(400));
        iTunesService.getAlbumsByArtist(JACK_JOHNSON_ID, ALBUM_ENTITY)
                .test()
                .assertError(HttpException.class);
    }

    private void enqueueResponse(String fileName) throws IOException {
        InputStream inputStream =
                getClass().getClassLoader().getResourceAsStream("api-response/" + fileName);
        BufferedSource source = Okio.buffer(Okio.source(inputStream));
        MockResponse mockResponse = new MockResponse();
        mockWebServer.enqueue(mockResponse.setBody(source.readString(StandardCharsets.UTF_8)));
    }

}