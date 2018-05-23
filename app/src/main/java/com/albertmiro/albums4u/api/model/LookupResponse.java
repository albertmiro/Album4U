package com.albertmiro.albums4u.api.model;

import com.google.gson.annotations.SerializedName;

public class LookupResponse {

    //COMMON

    @SerializedName("wrapperType")
    public
    String wrapperType;

    @SerializedName("artistName")
    public
    String artistName;

    @SerializedName("artistId")
    public
    int artistId;

    @SerializedName("amgArtistId")
    int amgArtistId;

    @SerializedName("primaryGenreName")
    String primaryGenreName;

    //ARTIST

    @SerializedName("primaryGenreId")
    int primaryGenreId;

    @SerializedName("artistLinkUrl")
    String artistLinkUrl;

    @SerializedName("artistType")
    String artistType;

    //COLLECTION

    @SerializedName("collectionType")
    String collectionType;

    @SerializedName("collectionId")
    public
    int collectionId;

    @SerializedName("collectionName")
    public
    String collectionName;

    @SerializedName("collectionCensoredName")
    String collectionCensoredName;

    @SerializedName("artistViewUrl")
    String artistViewUrl;

    @SerializedName("collectionViewUrl")
    String collectionViewUrl;

    @SerializedName("artworkUrl60")
    String artworkUrl60;

    @SerializedName("artworkUrl100")
    public
    String artworkUrl100;

    @SerializedName("collectionPrice")
    double collectionPrice;

    @SerializedName("collectionExplicitness")
    String collectionExplicitness;

    @SerializedName("trackCount")
    public
    int trackCount;

    @SerializedName("copyright")
    String copyright;

    @SerializedName("country")
    String country;

    @SerializedName("currency")
    String currency;

    @SerializedName("releaseDate")
    String releaseDate;

    //TRACK

    @SerializedName("kind")
    String kind;

    @SerializedName("trackId")
    public
    int trackId;

    @SerializedName("trackName")
    public
    String trackName;

    @SerializedName("trackPrice")
    double trackPrice;

    @SerializedName("trackNumber")
    public
    int trackNumber;

    @SerializedName("trackTimeMillis")
    public
    long trackTimeMillis;

    @SerializedName("previewUrl")
    public
    String previewUrl;

    @SerializedName("isStreamable")
    boolean isStreamable;
}
