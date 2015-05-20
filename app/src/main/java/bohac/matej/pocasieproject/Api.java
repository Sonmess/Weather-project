package bohac.matej.pocasieproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Matej Bohac 5ZSA21 on 5.5.2015.
 */
/**
 * Trieda Api sa pripaja na openweather api a poskytuje metody na ziskanie a nacitanie dat
 */
public class Api implements CommonConstants {

    public static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    public static final String FORECAST_URL = "http://api.openweathermap.org/data/2.5/forecast?q=";
    public static final String IMG_URL = "http://openweathermap.org/img/w/";
    public static final String API_KEY = "dd83f24d57b1e26c184cca2806952335";

    /**
     * Metoda poskytuje moznost pripojenia na api zadanej ako url parameter s konkretnym
     * dotazom na lokaciu, location parameter. Posuva dalej vynimku.
     * @param url String
     * @param location String
     * @return String
     * @throws IOException
     */
    private static String getDataFromUrl(String url, String location) throws IOException {
        HttpURLConnection connection;
        InputStream inputStream;

        //pripojime sa na stranku, ak nevyslo vyhodime vynimku
        try{
            connection = (HttpURLConnection) (new URL(url + location)).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();
            int statusCode = connection.getResponseCode();

            /*stavalo sa mi ze na stranku sa pripojil ale nedokazal nacitat data, asi pretazenie
            serverov alebo docasnne obmedzenie, status code 200 je HTTP odpoved OK
            */
            if( statusCode == 200) {
                StringBuilder buffer = new StringBuilder();
                inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    buffer.append(line).append("\r\n");
                }
                inputStream.close();
                connection.disconnect();
                return buffer.toString();
            }
            else{
                return null;
            }
        }
        catch (IOException ioe){
            return null;
        }
    }

    /**
     * Metoda obaluje volanie getDataFromUrl
     * @param location String
     * @return String
     * @throws IOException
     */
    public static String getWeatherData(String location) throws IOException {
       return getDataFromUrl(WEATHER_URL, location);
    }

    /**
     * Metoda obaluje volanie getDataFromUrl
     * @param location String
     * @return String
     * @throws IOException
     */
    public static String getForecastData(String location) throws IOException {
        return getDataFromUrl(FORECAST_URL, location);
    }

    /**
     * Metoda nacita obrazok z url openweather api
     * @param src String
     * @return Bitmap
     */
    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(IMG_URL + src + ".png");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            connection.disconnect();
            input.close();
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
