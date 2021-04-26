package pl.controller.Api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpService {

    public String connect(String url){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();

            InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getResponseCode() == 200
                    ? httpURLConnection.getInputStream():httpURLConnection.getErrorStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = " ";
            while ( (line = bufferedReader.readLine()) != null ) {
                stringBuilder.append(line);
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
