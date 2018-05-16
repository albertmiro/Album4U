package com.albertmiro.albums4u.viewmodel.data;

import android.util.SparseArray;

import com.albertmiro.albums4u.utils.AppUtils;

import java.util.ArrayList;

public class ArtistAndAlbums {

    private Artist artist;
    private SparseArray<Album> albumSparseArray = new SparseArray<>();

    public ArtistAndAlbums() {

    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public ArrayList<Album> getAlbumList() {
        return AppUtils.toList(albumSparseArray);
    }

    public boolean isAlbumListEmpty() {
        return albumSparseArray.size() == 0;
    }

    public void addAlbum(Album album) {
        albumSparseArray.put(album.getId(), album);
    }

    public Album getAlbum(int collectionId) {
        return albumSparseArray.get(collectionId);
    }
}
