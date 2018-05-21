package com.albertmiro.albums4u.api.model;

import com.google.gson.annotations.SerializedName;

public class LookupResponse {

    //COMMON

    @SerializedName("wrapperType")
    String wrapperType;

    @SerializedName("artistName")
    String artistName;

    @SerializedName("artistId")
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
    int collectionId;

    @SerializedName("collectionName")
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
    String artworkUrl100;

    @SerializedName("collectionPrice")
    double collectionPrice;

    @SerializedName("collectionExplicitness")
    String collectionExplicitness;

    @SerializedName("trackCount")
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
    int trackId;

    @SerializedName("trackName")
    String trackName;

    @SerializedName("trackPrice")
    double trackPrice;

    @SerializedName("trackNumber")
    int trackNumber;

    @SerializedName("trackTimeMillis")
    long trackTimeMillis;

    @SerializedName("previewUrl")
    String previewUrl;

    @SerializedName("isStreamable")
    boolean isStreamable;
}
