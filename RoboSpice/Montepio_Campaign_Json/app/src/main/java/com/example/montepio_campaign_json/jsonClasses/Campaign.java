package com.example.montepio_campaign_json.jsonClasses;

import android.media.Image;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Campaign implements Serializable {

    private String contentID;
    private Image contentImage;
    private String link;
    private String descrition;
    private String title;
    private float valueAsHTML;
    private float valueAsText;

    public Image getImage(){
        return contentImage;
    }

    public String getTitle(){
        return title;
    }

    public String getDescrition() {
        return descrition;
    }

    public String getLink(){
        return link;
    }
}
