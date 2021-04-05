package com.example.a1l1;

import android.os.Build;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

public class Request {
    private static final String TAG = "WEATHER";
    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/forecast?q=Moscow,RU&units=metric&appid=";
    private static final String WEATHER_API_KEY = "c37a35dea8008f97604e99eed21435e1";

    static String city;
    static String[] temperatures;
    static String temperature;
    static String feelsLike;
    static String pressure;
    static String humidity;
    static String windSpeed;
    static  String weatherType;

    @RequiresApi(api = Build.VERSION_CODES.N)
    static void request() {
        try {
            final URL uri = new URL(WEATHER_URL + WEATHER_API_KEY);
            final Handler handler = new Handler();
            new Thread(() -> {
                HttpsURLConnection urlConnection = null;
                try {
                    urlConnection = (HttpsURLConnection) uri.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setReadTimeout(10000);
                    BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    String result = getLines(in);
                    Gson gson = new Gson();
                    final ForecastRequest forecast = gson.fromJson(result, ForecastRequest.class);
                    handler.post(() -> {
                        displayWeather(forecast);
                    });
                } catch (Exception e) {
                    Log.e(TAG, "Fail connection", e);
                    e.printStackTrace();
                } finally {
                    if (null != urlConnection) {
                        urlConnection.disconnect();
                    }
                }
            }).start();
        } catch (MalformedURLException e) {
            Log.e(TAG, "Fail URI", e);
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private static String getLines(BufferedReader in) {
        return in.lines().collect(Collectors.joining("\n"));
    }

    private static void displayWeather(ForecastRequest forecast){
        temperature = String.valueOf(forecast.getItem()[0].getMain().getTemp());
        feelsLike = String.valueOf(forecast.getItem()[0].getMain().getFeels_like());
        pressure = String.valueOf(forecast.getItem()[0].getMain().getPressure());
        humidity = String.valueOf(forecast.getItem()[0].getMain().getHumidity());
        weatherType = forecast.getItem()[0].getWeather()[0].getMain();
        windSpeed = String.valueOf(forecast.getItem()[0].getWind().getSpeed());
    }

}
