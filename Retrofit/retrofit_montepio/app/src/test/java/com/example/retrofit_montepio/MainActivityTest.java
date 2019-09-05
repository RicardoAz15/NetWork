package com.example.retrofit_montepio;

import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;


import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class MainActivityTest {

    private Map<String, String> actual;
    private Map<String, String> expected;

    private MainActivity activity_to_test = mock(MainActivity.class);

    @Before
    public void setupTest(){
        expected = new HashMap<>();
    }

    @Test
    public void verifyHeaders(){
        expected.put("ITSAPP-DEVICE", "ANDROIDPHONE");
        expected.put("ITSAPP-LANG", "pt-PT");
        expected.put("ITSAPP-SO", "24");
        expected.put("ITSAPP-VER", "2.38");
        expected.put("MGAppId", "Android-Mobile");
        expected.put("MGIP", "192.168.102.23");
        expected.put("MGMdwVersion", "5");
        expected.put("MGScreen", "LoginFragment");

        when(activity_to_test.initHeaders(actual)).thenReturn(expected);
    }

    @Test
    public void verifyPerformRequest(){
        doThrow(NullPointerException.class).when(activity_to_test).performRequest(actual);
    }

}