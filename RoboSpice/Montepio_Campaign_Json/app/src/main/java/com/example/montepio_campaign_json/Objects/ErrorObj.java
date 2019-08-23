package com.example.montepio_campaign_json.Objects;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ErrorObj implements Serializable {

    private static final long serialVersionUID = 8962353409145033564L;

    /*
        The code
     */
    @SerializedName("Code")
    private String mCode;

    /*
        The inner error;
     */
    @SerializedName("Inner error")
    private ErrorObj mInnerError;

    /*
        The message
     */
    @SerializedName("Message")
    private String mMessage;

    /*
        The type
     */
    @SerializedName("Type")
    private String mType;

    /*
        is visible to human
     */
    @SerializedName("VisibleToHuman")
    private boolean mVisibleToHuman;

    /*
        Instantiates a new Error obj.
     */

    public ErrorObj(String Code, ErrorObj InnerError, String Message,
                    String Type, boolean VisibleToHuman) {
        super();
        mCode = Code;
        mInnerError = InnerError;
        mMessage = Message;
        mType = Type;
        mVisibleToHuman = VisibleToHuman;
    }

    public String getCode() {
        return mCode;
    }

    public void setCode(String Code) {
        mCode = Code;
    }

    public ErrorObj getInnerError() {
        return mInnerError;
    }

    public void setInnerError(ErrorObj InnerError) {
        mInnerError = InnerError;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String Message) {
        mMessage = Message;
    }

    public String getType() {
        return mType;
    }

    public void setType(String Type) {
        mType = Type;
    }

    public boolean isVisibleToHuman() {
        return mVisibleToHuman;
    }

    public void setVisibleToHuman(boolean VisibleToHuman) {
        mVisibleToHuman = VisibleToHuman;
    }
}
