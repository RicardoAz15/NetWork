package com.example.montepio_campaign_json.NetWork;
import com.example.montepio_campaign_json.Objects.ApiMessages;
import com.example.montepio_campaign_json.Objects.ErrorObj;
import com.example.montepio_campaign_json.Objects.MetaInfoObj;
import com.example.montepio_campaign_json.Objects.ProgressObj;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by E493 on 17/01/2017.
 */
public class ResponseObject implements Serializable {

    private static final long serialVersionUID = 7697330253727352850L;


    public ResponseObject(){}

    /**
     * the error.
     *
     */
    @SerializedName("Error")
    private ErrorObj mError;

    /**
     * the messages list.
     *
     */
    @SerializedName("Messages")
    private List<ApiMessages> mMessages;

    /**
     * the meta info.
     *
     */
    @SerializedName("MetaInfo")
    private MetaInfoObj mMetaInfo;

    /**
     * the operation id.
     *
     */
    @SerializedName("OperationId")
    private String mOperationId;

    /**
     * the next security token.
     *
     */
    @SerializedName("NextSecurityToken")
    private String mNextSecurityToken;

    /**
     * the progress.
     *
     */
    @SerializedName("Progress")
    private ProgressObj mProgress;

    /**
     * the refresh accounts.
     *
     */
    @SerializedName("RefreshAccounts")
    private Boolean mRefreshAccounts;

    /**
     * the status.
     *
     */
    @SerializedName("Status")
    private String mStatus;

    /**
     * Instantiates a new Response object.
     *
     * @param error the error
     * @param messages the messages list
     * @param metaInfo the meta info
     * @param operationId the operation id
     * @param nextSecurityToken the next security token
     * @param progress the progress
     * @param refreshAccounts the refresh accounts
     * @param status the status
     */
    public ResponseObject(ErrorObj error, List<ApiMessages> messages, MetaInfoObj metaInfo,
                          String operationId, String nextSecurityToken, ProgressObj progress,
                          Boolean refreshAccounts, String status) {
        mError = error;
        mMessages = messages;
        mMetaInfo = metaInfo;
        mOperationId = operationId;
        mNextSecurityToken = nextSecurityToken;
        mProgress = progress;
        mRefreshAccounts = refreshAccounts;
        mStatus = status;
    }

    /**
     * Instantiates a new Response object.
     *
     * @param error the error
     * @param messages the messages list
     * @param refreshAccounts the refresh accounts
     * @param metaInfo the meta info
     * @param status the status
     */
    public ResponseObject(ErrorObj error, List<ApiMessages> messages,
                          Boolean refreshAccounts, MetaInfoObj metaInfo, String status) {
        mError = error;
        mMessages = messages;
        mRefreshAccounts = refreshAccounts;
        mMetaInfo = metaInfo;
        mStatus = status;
    }

    /**
     * Instantiates a new Response object.
     *
     * @param error the error
     * @param messages the messages list
     * @param refreshAccounts the refresh accounts
     * @param metaInfo the meta info
     * @param status the status
     * @param progress the progress
     */
    public ResponseObject(ErrorObj error, List<ApiMessages> messages,
                          Boolean refreshAccounts, MetaInfoObj metaInfo, String status,
                          ProgressObj progress) {
        mError = error;
        mMessages = messages;
        mRefreshAccounts = refreshAccounts;
        mMetaInfo = metaInfo;
        mStatus = status;
        mProgress = progress;
    }

    /**
     * Gets error.
     *
     * @return the error
     */
    public ErrorObj getError() {
        return mError;
    }

    /**
     * Sets error.
     *
     * @param error the error
     */
    public void setError(ErrorObj error) {
        mError = error;
    }

    /**
     * Gets messages list.
     *
     * @return the messages list
     */
    public List<ApiMessages> getMessages() {
        return mMessages;
    }

    /**
     * Sets messages list.
     *
     * @param messages the messages list
     */
    public void setMessages(List<ApiMessages> messages) {
        mMessages = messages;
    }

    /**
     * Gets meta info.
     *
     * @return the meta info
     */
    public MetaInfoObj getMetaInfo() {
        return mMetaInfo;
    }

    /**
     * Sets meta info.
     *
     * @param metaInfo the meta info
     */
    public void setMetaInfo(MetaInfoObj metaInfo) {
        mMetaInfo = metaInfo;
    }

    /**
     * Gets operation id.
     *
     * @return the operation id
     */
    public String getOperationId() {
        return mOperationId;
    }

    /**
     * Sets operation id.
     *
     * @param operationId the operation id
     */
    public void setOperationId(String operationId) {
        mOperationId = operationId;
    }

    /**
     * Gets next security token.
     *
     * @return the next security token
     */
    public String getNextSecurityToken() {
        return mNextSecurityToken;
    }

    /**
     * Sets next security token.
     *
     * @param nextSecurityToken the next security token
     */
    public void setNextSecurityToken(String nextSecurityToken) {
        mNextSecurityToken = nextSecurityToken;
    }

    /**
     * Gets progress.
     *
     * @return the progress
     */
    public ProgressObj getProgress() {
        return mProgress;
    }

    /**
     * Sets progress.
     *
     * @param progress the progress
     */
    public void setProgress(ProgressObj progress) {
        mProgress = progress;
    }

    /**
     * Gets refresh accounts.
     *
     * @return the refresh accounts
     */
    public Boolean getRefreshAccounts() {
        return mRefreshAccounts;
    }

    /**
     * Sets refresh accounts.
     *
     * @param refreshAccounts the refresh accounts
     */
    public void setRefreshAccounts(Boolean refreshAccounts) {
        mRefreshAccounts = refreshAccounts;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
        return mStatus;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(String status) {
        mStatus = status;
    }
}
