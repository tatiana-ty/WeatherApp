package com.example.a1l1;

public class ForecastRequest {
    private ForecastItem[] item;

    public ForecastItem[] getItem() {
        return item;
    }

    public void setWeather(ForecastItem[] item) {
        this.item = item;
    }

}
