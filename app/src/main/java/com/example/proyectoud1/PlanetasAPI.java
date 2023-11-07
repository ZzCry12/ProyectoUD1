package com.example.proyectoud1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class PlanetasAPI {
    ArrayList<Planetas> getPlanetas() {
        String url = "https://api.nasa.gov/planetary/apod?api_key=mBZ5Hr3Glv3ZkdE3xApJVbUoRKJF8MaOkG7UAdPt&count=20";

        try {
            String result = HttpUtils.get(url);

            JSONArray results = new JSONArray(result);
            ArrayList<Planetas> listaPlanetas = new ArrayList<>();

            for (int i = 0; i < results.length(); i++) {
                try {
                    JSONObject planetasJson = results.getJSONObject(i);
                    Planetas planeta = new Planetas();
                    planeta.setTitle(planetasJson.getString("title"));
                    planeta.setHdurl(planetasJson.getString("url"));
                    planeta.setExplanation(planetasJson.getString("explanation"));
                    planeta.setDate(planetasJson.getString("date"));
                    planeta.setCopyright(planetasJson.getString("copyright"));
                    listaPlanetas.add(planeta);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            System.out.println(listaPlanetas);
            return listaPlanetas;

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
