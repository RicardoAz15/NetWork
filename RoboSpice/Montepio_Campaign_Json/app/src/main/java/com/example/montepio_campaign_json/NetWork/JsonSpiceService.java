package com.example.montepio_campaign_json.NetWork;

import android.app.Application;

import com.octo.android.robospice.SpringAndroidSpiceService;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;
import com.octo.android.robospice.persistence.googlehttpclient.json.JacksonObjectPersisterFactory;

import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class JsonSpiceService extends SpringAndroidSpiceService {
    @Override
    public CacheManager createCacheManager(Application application) throws CacheCreationException {
        CacheManager cacheManager = new CacheManager();
        JacksonObjectPersisterFactory jacksonObjectPersisterFactory = new JacksonObjectPersisterFactory(application);
        cacheManager.addPersister( jacksonObjectPersisterFactory ) ;
        return cacheManager;
    }

    @Override
    public RestTemplate createRestTemplate(){

        RestTemplate restTemplate = new RestTemplate();

        FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();



        final List<HttpMessageConverter< ? >> listHttpMessageConverters = restTemplate.getMessageConverters();

        listHttpMessageConverters.add( formHttpMessageConverter );
        listHttpMessageConverters.add( stringHttpMessageConverter );
        listHttpMessageConverters.add( gsonHttpMessageConverter);
        restTemplate.setMessageConverters ( listHttpMessageConverters) ;

        return restTemplate;
    }
}
