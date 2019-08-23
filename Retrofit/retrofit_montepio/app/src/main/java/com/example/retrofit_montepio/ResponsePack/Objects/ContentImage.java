package com.example.retrofit_montepio.ResponsePack.Objects;

import com.example.retrofit_montepio.ResponsePack.Objects.ExtraObjects.ImageUrl;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class ContentImage implements Serializable {

       private static final long serialVersionUID = 572240186134105706L;

       @SerializedName("imageBase64")
       private String mImageBase64;

       @SerializedName("Link")
       private String mLink;

       @SerializedName("Url")
       private ImageUrl mUrl;

       public String getmImageBase64() {
              return mImageBase64;
       }

       public void setmImageBase64(String mImageBase64) {
              this.mImageBase64 = mImageBase64;
       }

       public String getmLink() {
              return mLink;
       }

       public void setmLink(String mLink) {
              this.mLink = mLink;
       }

       public ImageUrl getmUrl() {
              return mUrl;
       }

       public void setmUrl(ImageUrl mUrl) {
              this.mUrl = mUrl;
       }

       public ContentImage(String mImageBase64, String mLink, ImageUrl mUrl) {
              this.mImageBase64 = mImageBase64;
              this.mLink = mLink;
              this.mUrl = mUrl;
       }
}
