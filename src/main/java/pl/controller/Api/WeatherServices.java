package pl.controller.Api;

import org.json.JSONObject;

public class WeatherServices {

    public WeatherPOJO getWeatherByCityName(String cityName) {
        String response;
        HttpService httpService = new HttpService();
        response = httpService.connect(Config.APP_MAIN_URL + cityName + Config.APP_Id_WITH_PARAMS);
        return parseJson(response);
    }

    public WeatherPOJO parseJson(String jsonResponse) {
        WeatherPOJO weatherPOJO = new WeatherPOJO();
        JSONObject rootObject = new JSONObject(jsonResponse);
        JSONObject mainArray = rootObject.getJSONObject("main");
        weatherPOJO.setTemp(mainArray.getDouble("temp"));
        weatherPOJO.setHumidity(mainArray.getDouble("humidity"));
        weatherPOJO.setPressure(mainArray.getDouble("pressure"));
        weatherPOJO.setClouds(rootObject.getJSONObject("clouds").getDouble("all"));
        weatherPOJO.setWind(rootObject.getJSONObject("wind").getDouble("speed"));
        weatherPOJO.setDescription(rootObject.getJSONArray("weather").
                getJSONObject(0).getString("description"));

        return weatherPOJO;
    }
}
