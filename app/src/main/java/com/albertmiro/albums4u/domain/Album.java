package com.albertmiro.albums4u.domain;

import java.util.ArrayList;

public class Album extends CommonLookupData {

    private int id;
    private String name;
    private String thumbnailUrl;
    private int trackCount;
    private ArrayList<Track> tracks = new ArrayList<>();

    public Album(int id, String name, String thumbnailUrl, int trackCount,
                 int artistId, String artistName, WrapperTypes.WRAPPER_TYPES wrapperType) {
        this.id = id;
        this.name = name;
        this.thumbnailUrl = thumbnailUrl;
        this.trackCount = trackCount;
        this.artistId = artistId;
        this.artistName = artistName;
        this.wrapperType = wrapperType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl.replace("100x100","250x250");
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(int trackCount) {
        this.trackCount = trackCount;
    }

    public void setTracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public void addTrack(Track track) {
        tracks.add(track);
    }
}
