package com.sydney.controller;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class ExternalAPIController {
    private static final String openWeatherMapKey = "b40fcac67f4795e36979264e1d4bb3f4";

    @RequestMapping("getCityWeather")
    @ResponseBody
    public String callWeatherAPI(String city) throws IOException {

        System.out.println("Call city weather: " + city);

        String callUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" +openWeatherMapKey;
        System.out.println(callUrl);
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(callUrl)
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();

        return response.body().string();

    }

}
