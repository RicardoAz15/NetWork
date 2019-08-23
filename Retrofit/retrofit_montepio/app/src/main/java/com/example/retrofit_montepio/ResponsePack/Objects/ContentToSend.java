package com.example.retrofit_montepio.ResponsePack.Objects;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ContentToSend implements Serializable {
    private static final long serialVersionUID = 5711957825542859993L;


    public ContentToSend(String ContentModule) {
        mContentModule = ContentModule;
    }

    @SerializedName("ContentModule")
    private String mContentModule;

}
