package com.example.montepio_campaign_json.jsonClasses;

import com.example.montepio_campaign_json.NetWork.ResponseContent;
import com.example.montepio_campaign_json.NetWork.ResponseObject;
import com.example.montepio_campaign_json.Objects.ContentToSend;
import com.google.gson.GsonBuilder;
import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import com.google.gson.Gson;
import org.springframework.web.client.RestTemplate;

import com.example.montepio_campaign_json.NetWork.JsonSpiceService;

import java.util.Arrays;

public class CampaignRequest extends SpringAndroidSpiceRequest<ResponseContent> {

    private static final HttpHeaders headers = new HttpHeaders();

    public CampaignRequest(){
        super(ResponseContent.class);
        headers.add("ITSAPP-DEVICE", "ANDROIDPHONE");
        headers.add("ITSAPP-LANG","pt-PT");
        headers.add("ITSAPP-SO", "24");
        headers.add("ITSAPP-VER", "2.38");
        headers.add("MGAppId", "Android-Mobile");
        headers.add("MGIP", "192.168.102.23");
        headers.add("MGMdwVersion", "5");
        headers.add("MGScreen", "LoginFragment");

    }

    @Override
    public ResponseContent loadDataFromNetwork() {
        final String url = "http://mobile-montepio.itsector.local/public/contentByGroup";

        JsonSpiceService service = new JsonSpiceService();
        RestTemplate template = service.createRestTemplate();

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        ContentToSend bodyToSend = new ContentToSend("MARKETING");

        HttpEntity<ContentToSend> requestWithHeaders = new HttpEntity<>(bodyToSend,headers);

        ResponseEntity<ResponseContent> result = template.exchange(url, HttpMethod.POST,
                requestWithHeaders, ResponseContent.class);

        return result.getBody();
    }

    public String createCashKey() {
        return "" + headers.hashCode();
    }
}
