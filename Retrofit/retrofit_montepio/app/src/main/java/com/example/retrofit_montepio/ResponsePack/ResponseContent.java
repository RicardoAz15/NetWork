package com.example.retrofit_montepio.ResponsePack;


import com.example.retrofit_montepio.ResponsePack.Objects.ApiMessages;
import com.example.retrofit_montepio.ResponsePack.Objects.ContentImage;
import com.example.retrofit_montepio.ResponsePack.Objects.ErrorObj;
import com.example.retrofit_montepio.ResponsePack.Objects.MetaInfoObj;
import com.example.retrofit_montepio.ResponsePack.Objects.ProgressObj;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by E493 on 23/01/2017.
 */


public class ResponseContent extends ResponseObject implements Serializable {

    private static final long serialVersionUID = 3921726086156412474L;

    /**
     * The result.
     */
    @SerializedName("Result")
    private ResponseResult Result;

    public ResponseContent(){
        super();
        Result = new ResponseResult();
    };


    /**
     * Instantiates a new Response content.
     *
     * @param error the error
     * @param messages the messages
     * @param refreshAccounts the refresh accounts
     * @param metaInfo the meta info
     * @param status the status
     * @param result the result
     */
    public ResponseContent(ErrorObj error, List<ApiMessages> messages, MetaInfoObj metaInfo,
                           String nextSecurityToken, String operationId, ProgressObj progress,
                           Boolean refreshAccounts, ResponseResult result, String status) {
        super(error, messages, metaInfo, operationId,nextSecurityToken, progress, refreshAccounts,status);
        Result = result;
    }

    /**
     * Gets result.
     *
     * @return the result
     */
    public ResponseResult getResult() {
        return Result;
    }

    /**
     * Sets result.
     *
     * @param result the result
     */
    public void setResult(ResponseResult result) {
        Result = result;
    }

    public static class ResponseResult implements Serializable {

        public ResponseResult(){}

        private static final long serialVersionUID = 9111087738246784878L;

        @SerializedName("Content")
        private List<ResponseContentResult> ContentResult;

        @SerializedName("TotalResults")
        private int TotalResults;

        public ResponseResult(List<ResponseContentResult> contentResult, int totalResults) {
            ContentResult = contentResult;
            TotalResults = totalResults;
        }

        public List<ResponseContentResult> getContentResult() {
            return ContentResult;
        }

        public void setContentResult(List<ResponseContentResult> contentResult) {
            ContentResult = contentResult;
        }

        public int getTotalResults() {
            return TotalResults;
        }

        public void setTotalResults(int totalResults) {
            TotalResults = totalResults;
        }
    }

    /**
     * The type Response content result.
     */
    public static class ResponseContentResult implements Serializable {

        private static final long serialVersionUID = 3179762327569886291L;


        /**
         * The content id.
         */
        @SerializedName("ContentId")
        private String mContentId;

        /**
         * The link.
         */
        @SerializedName("Link")
        private String mLink;

        /**
         * The value as html.
         */
        @SerializedName("ValueAsHtml")
        private String mValueAsHtml;

        /**
         * The value as text.
         */
        @SerializedName("ValueAsText")
        private String mValueAsText;

        /**
         * The title.
         */
        @SerializedName("Title")
        private String mTitle;

        /**
         * The content image.
         */
        @SerializedName("ContentImage")
        private ContentImage mContentImage;

        /**
         * The description.
         */
        @SerializedName("Description")
        private String mDescription;

        /**
         * Instantiates a new Response content result.
         *
         * @param contentId the content id
         * @param link the link
         * @param valueAsHtml the value as html
         * @param valueAsText the value as text
         * @param title the title
         * @param contentImage the content image
         * @param description the description
         */


        public ResponseContentResult(String contentId, String link, String valueAsHtml, String valueAsText,
                                     String title, ContentImage contentImage, String description) {
            mContentId = contentId;
            mLink = link;
            mValueAsHtml = valueAsHtml;
            mValueAsText = valueAsText;
            mTitle = title;
            mContentImage = contentImage;
            mDescription = description;
        }


        /**
         * Gets content id.
         *
         * @return the content id
         */
        public String getContentId() {
            return mContentId;
        }

        /**
         * Sets content id.
         *
         * @param contentId the content id
         */
        public void setContentId(String contentId) {
            mContentId = contentId;
        }

        /**
         * Gets link.
         *
         * @return the link
         */
        public String getLink() {
            return mLink;
        }

        /**
         * Sets link.
         *
         * @param link the link
         */
        public void setLink(String link) {
            mLink = link;
        }

        /**
         * Gets value as html.
         *
         * @return the value as html
         */
        public String getValueAsHtml() {
            return mValueAsHtml;
        }

        /**
         * Sets value as html.
         *
         * @param valueAsHtml the value as html
         */
        public void setValueAsHtml(String valueAsHtml) {
            mValueAsHtml = valueAsHtml;
        }

        /**
         * Gets value as text.
         *
         * @return the value as text
         */
        public String getValueAsText() {
            return mValueAsText;
        }

        /**
         * Sets value as text.
         *
         * @param valueAsText the value as text
         */
        public void setValueAsText(String valueAsText) {
            mValueAsText = valueAsText;
        }

        /**
         * Gets title.
         *
         * @return the title
         */
        public String getTitle() {
            return mTitle;
        }

        /**
         * Sets title.
         *
         * @param title the title
         */
        public void setTitle(String title) {
            mTitle = title;
        }

        /**
         * Gets content image.
         *
         * @return the content image
         */
        public ContentImage getContentImage() {
            return mContentImage;
        }

        /**
         * Sets content image.
         *
         * @param contentImage the content image
         */
        public void setContentImage(ContentImage contentImage) {
            mContentImage = contentImage;
        }

        /**
         * Gets description.
         *
         * @return the description
         */
        public String getDescription() {
            return mDescription;
        }

        /**
         * Sets description.
         *
         * @param description the description
         */
        public void setDescription(String description) {
            mDescription = description;
        }
    }

}