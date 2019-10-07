package com.albertmiro.albums4u.repository;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import com.albertmiro.albums4u.api.ITunesService;
import com.albertmiro.albums4u.api.mapper.GenericITunesResponseMapper;
import com.albertmiro.albums4u.api.mapper.LookupResponseMapper;
import com.albertmiro.albums4u.api.model.GenericITunesResponse;
import com.albertmiro.albums4u.domain.Album;
import com.albertmiro.albums4u.domain.Artist;
import com.albertmiro.albums4u.domain.ArtistAndAlbums;
import com.albertmiro.albums4u.domain.WrapperType;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import io.reactivex.Single;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class LookupRepositoryTest {

    @Rule // used to make all live data calls sync
    public InstantTaskExecutorRule instantExecutor = new InstantTaskExecutorRule();

    ITunesService iTunesService;
    LookupRepositoryImpl repository;
    LookupResponseMapper lookupResponseMapper = new LookupResponseMapper();
    GenericITunesResponseMapper genericITunesResponseMapper = new GenericITunesResponseMapper();

    @Before
    public void setup() {
        iTunesService = mock(ITunesService.class);
        repository = new LookupRepositoryImpl(iTunesService, lookupResponseMapper, genericITunesResponseMapper);
    }

    @Test
    public void noAlbumsResponse() {
        Mockito.when(iTunesService.getAlbumsByArtist(Mockito.anyInt(), Mockito.anyString()))
                .thenReturn(Single.just(new GenericITunesResponse()));
        ArtistAndAlbums response = repository.getJackJohnsonAlbums().blockingGet();
        assertThat(response.getAlbumList().size(), Matchers.is(0));
    }

    public ArtistAndAlbums buildArtistAndAlbums() {
        ArtistAndAlbums artistAndAlbums = new ArtistAndAlbums();
        artistAndAlbums.setArtist(new Artist(1, "name", WrapperType.ARTIST));
        artistAndAlbums.addAlbum(new Album(1, "album1", "url", 2, 1, "name", WrapperType.COLLECTION));
        artistAndAlbums.addAlbum(new Album(2, "album2", "url", 2, 1, "name", WrapperType.COLLECTION));
        return artistAndAlbums;
    }


}
