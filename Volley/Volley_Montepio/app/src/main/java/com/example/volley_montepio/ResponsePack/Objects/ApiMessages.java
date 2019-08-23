package com.example.volley_montepio.ResponsePack.Objects;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ApiMessages implements Serializable {

    private static final long serialVersionUID = 167502853783286680L;

    /*
        The type
     */
    @SerializedName("Type")
    private String mType;

    /*
        The code
     */

    @SerializedName("Code")
    private String mCode;

    /*
        The message
     */
    @SerializedName("Message")
    private String mMessage;

    /*
        is visible to human
     */

    @SerializedName("VisibleToHuman")
    private Boolean mVisibleToHuman;

    /*
        Instantiates a new Api messages
     */

    public ApiMessages(String Type, String Code,
                       String Message, Boolean VisibleToHuman) {
        mType = Type;
        mCode = Code;
        mMessage = Message;
        mVisibleToHuman = VisibleToHuman;
    }

    public String getType() {
        return mType;
    }

    public void setType(String Type) {
        mType = Type;
    }

    public String getCode() {
        return mCode;
    }

    public void setCode(String Code) {
        mCode = Code;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String Message) {
        mMessage = Message;
    }

    public Boolean getVisibleToHuman() {
        return mVisibleToHuman;
    }

    public void setVisibleToHuman(Boolean VisibleToHuman) {
        mVisibleToHuman = VisibleToHuman;
    }
}
