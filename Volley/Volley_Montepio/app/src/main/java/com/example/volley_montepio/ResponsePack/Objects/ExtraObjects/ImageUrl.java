package com.example.volley_montepio.ResponsePack.Objects.ExtraObjects;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ImageUrl implements Serializable {
    private static final long serialVersionUID = -2323601126640399456L;

    @SerializedName("Landscape")
    private String mLandscape;

    @SerializedName("Large")
    private String mLarge;

    @SerializedName("Portrait")
    private String mPortrait;

    public String getmLandscape() {
        return mLandscape;
    }

    public ImageUrl(String mLandscape, String mLarge, String mPortrait) {
        this.mLandscape = mLandscape;
        this.mLarge = mLarge;
        this.mPortrait = mPortrait;
    }

    public void setmLandscape(String mLandscape) {
        this.mLandscape = mLandscape;
    }

    public String getmLarge() {
        return mLarge;
    }

    public void setmLarge(String mLarge) {
        this.mLarge = mLarge;
    }

    public String getmPortrait() {
        return mPortrait;
    }

    public void setmPortrait(String mPortrait) {
        this.mPortrait = mPortrait;
    }
}
