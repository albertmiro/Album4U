package com.albertmiro.albums4u.repository;

import com.albertmiro.albums4u.viewmodel.data.ArtistAndAlbums;
import com.albertmiro.albums4u.viewmodel.data.Track;

import java.util.ArrayList;

class LookupCache {

    private ArtistAndAlbums artistAndAlbums;

    public ArtistAndAlbums getArtistAndAlbums() {
        return artistAndAlbums;
    }

    public boolean isEmpty() {
        return artistAndAlbums == null ||
                artistAndAlbums.getArtist() == null ||
                artistAndAlbums.isAlbumListEmpty();
    }

    public void setArtistAndAlbums(ArtistAndAlbums artistAndAlbums) {
        this.artistAndAlbums = artistAndAlbums;
    }

    public boolean hasSongsAlbum(int albumId) {
        return (this.artistAndAlbums.getAlbum(albumId) != null &&
                !this.artistAndAlbums.getAlbum(albumId).getTracks().isEmpty());
    }

    public void updateAlbumWithTracks(int albumId, ArrayList<Track> tracks) {
        this.artistAndAlbums.getAlbum(albumId).setTracks(tracks);
    }
}
