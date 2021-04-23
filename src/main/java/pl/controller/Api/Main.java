package pl.controller.Api;

public class Main {
    public static void main(String[] args) {

        WeatherServices weatherServices= new WeatherServices();
        System.out.println( weatherServices.getWeatherByCityName("Kraków"));



    }
}