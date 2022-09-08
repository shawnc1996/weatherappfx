package com.example.weatherappfx;

import com.example.weatherappfx.utils.Metadata;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class HelloController {
    Weather weather = new Weather();
    ArrayList<String> name = new ArrayList<String>();
    @FXML
    private Label welcomeText;
    @FXML
    private ComboBox locCombo;
    @FXML
    private Label forecastData;

    @FXML
    public void initialize() {
//        System.out.println("Hello");
        weather.getWeather();
        loadName();
        locCombo.getItems().addAll(name);
    }
    @FXML
    protected void onRefreshButtonClick() {
        welcomeText.setText("Refreshed!");
        Weather weather = new Weather();
        weather.getWeather();
        loadName();
    }

    private void loadName() {
        for (Metadata data : weather.weatherList) {
            name.add(data.name);
        }
    }


    public void onSelectLoc(ActionEvent actionEvent) {
        welcomeText.setText("");
//        System.out.println(locCombo.getValue());
        String loc = locCombo.getValue().toString();
        for (Metadata metadata : weather.weatherList) {
            if (metadata.name.equals(loc)) {
                forecastData.setText(metadata.forecast);
                break;
            }
        }
    }
}