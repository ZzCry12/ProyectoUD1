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


            JSONObject jsonResult = new JSONObject(result);
            JSONArray results = jsonResult.getJSONArray("results");

            ArrayList<Planetas> listaPlanetas = new ArrayList<>();

            for (int i = 0; i < result.length(); i++) {
                try {

                    JSONObject planetasJson = results.getJSONObject(i);

                    Planetas planeta = new Planetas();
                    planeta.setTitle(planetasJson.getString("title"));

                    JSONObject sprites = jsonResult.getJSONObject("sprites");
                    String spriteDefault = sprites.getString("front_default");

                    //planeta.setDate(jsonResult.getDouble("height"));
                    planeta.setHdurl(spriteDefault);


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
