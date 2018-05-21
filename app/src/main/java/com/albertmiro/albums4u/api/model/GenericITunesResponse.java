package com.albertmiro.albums4u.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GenericITunesResponse {
    @SerializedName("resultCount")
    int resultCount;

    @SerializedName("results")
    List<LookupResponse> results;
}
