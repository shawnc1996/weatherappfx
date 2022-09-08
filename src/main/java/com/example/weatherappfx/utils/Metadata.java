package com.example.weatherappfx.utils;

import javafx.util.Pair;

public class Metadata {
    public  String name;
    public  Pair<Double, Double> coords;
    public  String forecast;

    public Metadata (String name, String forecast) {
        this.name = name;
        this.forecast = forecast;
    }
}
