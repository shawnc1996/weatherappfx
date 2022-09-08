package com.example.weatherappfx;

import com.example.weatherappfx.utils.Metadata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.json.*;
public class Weather {
    private static final String baseUrl = "https://api.data.gov.sg/v1/environment/2-hour-weather-forecast";
    public ArrayList<Metadata> weatherList;

    public Weather(){this.weatherList=new ArrayList<>();};
    public void getWeather() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
            TimeZone tz = TimeZone.getTimeZone("Asia/Singapore");
            sdf.setTimeZone(tz);

            Date date = new Date();
            String dateStr = sdf.format(date);
//            System.out.println(dateStr);

            String urlStr = baseUrl + "?date_time=" + dateStr;
//            System.out.println(urlStr);

            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int status = conn.getResponseCode();
            if (status == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String readIn;
                StringBuffer content = new StringBuffer();
                while ((readIn = in.readLine()) != null) {
                    content.append(readIn);
                }
                in.close();
                parseJSON(content);
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void parseJSON(StringBuffer content) {
        JSONObject json = new JSONObject(content.toString());
        JSONArray items = json.getJSONArray("items");
        JSONObject items1 = items.getJSONObject(0);
        JSONArray forecast = items1.getJSONArray("forecasts");
        for (int i = 0; i < forecast.length(); i++) {
            JSONObject pair = forecast.getJSONObject(i);
            String name = pair.getString("area");
            String weather = pair.getString("forecast");
            weatherList.add(new Metadata(name,weather));
        }
    }
}
