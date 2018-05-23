package com.albertmiro.albums4u.api.model;

import com.google.gson.annotations.SerializedName;

public class LookupResponse {

    //COMMON

    @SerializedName("wrapperType")
    public String wrapperType;

    @SerializedName("artistName")
    public String artistName;

    @SerializedName("artistId")
    public int artistId;

    @SerializedName("amgArtistId")
    public int amgArtistId;

    @SerializedName("primaryGenreName")
    public String primaryGenreName;

    //ARTIST

    @SerializedName("primaryGenreId")
    public int primaryGenreId;

    @SerializedName("artistLinkUrl")
    public String artistLinkUrl;

    @SerializedName("artistType")
    public String artistType;

    //COLLECTION

    @SerializedName("collectionType")
    public String collectionType;

    @SerializedName("collectionId")
    public int collectionId;

    @SerializedName("collectionName")
    public String collectionName;

    @SerializedName("collectionCensoredName")
    public String collectionCensoredName;

    @SerializedName("artistViewUrl")
    public String artistViewUrl;

    @SerializedName("collectionViewUrl")
    public String collectionViewUrl;

    @SerializedName("artworkUrl60")
    public String artworkUrl60;

    @SerializedName("artworkUrl100")
    public String artworkUrl100;

    @SerializedName("collectionPrice")
    public double collectionPrice;

    @SerializedName("collectionExplicitness")
    public String collectionExplicitness;

    @SerializedName("trackCount")
    public int trackCount;

    @SerializedName("copyright")
    public String copyright;

    @SerializedName("country")
    public String country;

    @SerializedName("currency")
    public String currency;

    @SerializedName("releaseDate")
    public String releaseDate;

    //TRACK

    @SerializedName("kind")
    public String kind;

    @SerializedName("trackId")
    public int trackId;

    @SerializedName("trackName")
    public String trackName;

    @SerializedName("trackPrice")
    public double trackPrice;

    @SerializedName("trackNumber")
    public int trackNumber;

    @SerializedName("trackTimeMillis")
    public long trackTimeMillis;

    @SerializedName("previewUrl")
    public String previewUrl;

    @SerializedName("isStreamable")
    public boolean isStreamable;
}
