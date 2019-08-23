package com.example.retrofit_montepio.ResponsePack.Objects.ExtraObjects;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by E493 on 17/01/2017.
 */
public class ExtraInfo implements Serializable {

    private static final long serialVersionUID = 6662811571360304491L;

    /**
     * the key.
     *
     */
    @SerializedName("Key")
    private String mKey;

    /**
     * the value.
     *
     */
    @SerializedName("Value")
    private String mValue;

    /**
     * Instantiates a new Extra info.
     *
     * @param value the value
     * @param key the key
     */
    public ExtraInfo(String value, String key) {
        super();
        this.mValue = value;
        this.mKey = key;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getValue() {
        return mValue;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(String value) {
        this.mValue = value;
    }

    /**
     * Gets key.
     *
     * @return the key
     */
    public String getKey() {
        return mKey;
    }

    /**
     * Sets key.
     *
     * @param key the key
     */
    public void setKey(String key) {
        this.mKey = key;
    }

}
