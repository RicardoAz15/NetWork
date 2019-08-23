package com.example.volley_montepio.ResponsePack.Objects;

import com.example.volley_montepio.ResponsePack.Objects.ExtraObjects.ExtraInfo;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MetaInfoObj implements Serializable {

    private static final long serialVersionUID = 2172971544464051694L;

    /*
        The execution start.
     */
    @SerializedName("ExecutionStart")
    private String mExecutionStart;

    /*
        The execution time
     */
    @SerializedName("ExecutionTime")
    private int mExecutionTime;

    /*
        The extra info
     */
    @SerializedName("ExtraInfo")
    private List<ExtraInfo> mExtraInfo;

    /*
        Instantiates a new Meta info obj
     */

    public MetaInfoObj(String mExecutionStart, int mExecutionTime, List<ExtraInfo> mExtraInfo) {
        super();
        mExecutionStart = mExecutionStart;
        mExecutionTime = mExecutionTime;
        mExtraInfo = mExtraInfo;
    }

    public List<ExtraInfo> getExtraInfo() {
        return mExtraInfo;
    }

    public void setExtraInfo(List<ExtraInfo> ExtraInfo) {
        mExtraInfo = ExtraInfo;
    }

    public int getExecutionTime() {
        return mExecutionTime;
    }

    public void setExecutionTime(int ExecutionTime) {
        mExecutionTime = ExecutionTime;
    }

    public String getExecutionStart() {
        return mExecutionStart;
    }

    public void setExecutionStart(String ExecutionStart) {
        mExecutionStart = ExecutionStart;
    }
}
