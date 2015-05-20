package bohac.matej.pocasieproject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import bohac.matej.pocasieproject.model.Conditions;
import bohac.matej.pocasieproject.model.Coordinates;
import bohac.matej.pocasieproject.model.Country;
import bohac.matej.pocasieproject.model.Location;
import bohac.matej.pocasieproject.model.Temperature;
import bohac.matej.pocasieproject.model.Weather;
import bohac.matej.pocasieproject.model.Wind;

/**
 * Created by Matej Bohac 5ZSA21 on 5.5.2015.
 */

/**
 * Trieda umoznuje parsovanie ziskanych dat vo formate JSON
 */
public class Parser implements CommonConstants {

    /**
     * Funkcia zparsuje ziskane data z openweather api, na ine data to nebude fungovat
     * a vyhodi vynimku ak doslo k chybe pri parsovaniu.
     * @param data String
     * @return Weather
     * @throws JSONException
     */
    public static Weather parseDataToWeather(String data) throws JSONException{
        Weather weather = new Weather();

        //objekt dat v tvare jsonu, musime rozparsovat
        JSONObject json = new JSONObject(data);

        //zacneme parsovanim postupne suradnice, lokacia, temperature atd...
        Coordinates coordinates = new Coordinates();

        JSONObject jsonCoordinates = getObject("coord", json);
        coordinates.set_latitude ( getString("lat", jsonCoordinates) );
        coordinates.set_longitude( getString("lon", jsonCoordinates) );

        //country
        Country country = new Country();
        JSONObject jsonCountry = getObject("sys", json);
        country.set_country( getString("country", jsonCountry) );
        country.set_sunrise( getInt("sunrise", jsonCountry) );
        country.set_sunset( getInt("sunset", jsonCountry) );

        //lokacia
        Location location = new Location();
        location.set_coordinates(coordinates);
        location.set_country(country);
        location.set_locationId( getString("id", json) );
        location.set_locationName( getString("name", json) );

        //temperature
        Temperature temperature = new Temperature();
        JSONObject jsonTemperature = getObject("main", json);
        temperature.set_avgTemperature( getFloat("temp", jsonTemperature) );
        temperature.set_minTemperature( getFloat("temp_min", jsonTemperature) );
        temperature.set_maxTemperature( getFloat("temp_max", jsonTemperature) );

        //conditions
        Conditions conditions = new Conditions();
        JSONArray jsonArray = json.getJSONArray("weather");
        JSONObject jsonCondition = jsonArray.getJSONObject(0);
        conditions.set_condition( getString("main", jsonCondition) );
        conditions.set_description( getString("description", jsonCondition) );
        conditions.set_icon( getString("icon", jsonCondition));

        //wind
        Wind wind = new Wind();
        JSONObject jsonWind = getObject("wind", json);
        wind.set_speed( getFloat("speed", jsonWind) );
        wind.set_degree( getFloat("deg", jsonWind) );

        //nastavime nas objekt pocasia
        weather.set_wind(wind);
        weather.set_conditions(conditions);
        weather.set_location(location);
        weather.set_temperature(temperature);
        return weather;
    }

    /**
     * Funkcia parsuje data predpovede pocasia z openweather api
     * na ine data nebude fungovat, ak dojde k chybe pri parsovaniu vyhodi vynimku.
     * @param data String
     * @return ArrayList
     * @throws JSONException
     */
    public static ArrayList<Weather> parseDataToForecast(String data) throws JSONException {

        if(data !=null) {
            ArrayList<Weather> arrayListForecast = new ArrayList<>();

            //objekt dat v tvare jsonu, musime rozparsovat
            JSONObject json = new JSONObject(data);

            JSONArray listData = json.getJSONArray("list");

            for (int i = 0; i < listData.length(); i++) {
                JSONObject listItem = listData.getJSONObject(i);
                Weather forecastWeather = new Weather();

                //parsovanie teploty
                JSONObject itemTemperature = getObject("main", listItem);
                Temperature temperature = new Temperature();

                temperature.set_avgTemperature(getFloat("temp", itemTemperature));
                temperature.set_minTemperature(getFloat("temp_min", itemTemperature));
                temperature.set_maxTemperature(getFloat("temp_max", itemTemperature));

                //parsovanie conditions
                JSONArray conditionList = listItem.getJSONArray("weather");
                JSONObject itemConditions = conditionList.getJSONObject(0);
                Conditions conditions = new Conditions();

                conditions.set_condition(getString("main", itemConditions));
                conditions.set_description(getString("description", itemConditions));
                conditions.set_icon(getString("icon", itemConditions));

                //parsovanie wind
                JSONObject jsonWind = getObject("wind", listItem);
                Wind wind = new Wind();
                wind.set_speed( getFloat("speed", jsonWind) );
                wind.set_degree( getFloat("deg", jsonWind) );

                //parsovanie casoveho udaju
                String time = getString( "dt_txt", listItem);

                //nastavenie nasho objektu pocasia
                forecastWeather.set_temperature(temperature);
                forecastWeather.set_conditions(conditions);
                forecastWeather.set_time(time);
                forecastWeather.set_wind(wind);

                arrayListForecast.add(forecastWeather);
            }
            return arrayListForecast;
        }
        return null;
    }

    /**
     * Funkcia obaluje volanie jsonObject aby sa dalo lepsie pouzit pri parsovani
     * @param tagName String
     * @param json JSONObject
     * @return JSONObject
     * @throws JSONException
     */
    private static JSONObject getObject(String tagName, JSONObject json) throws JSONException{
        return json.getJSONObject(tagName);
    }

    /**
     * Funkcia obaluje volanie na rychlejsie parsovanie stringov
     * @param tagName String
     * @param json JSONObject
     * @return JSONObject
     * @throws JSONException
     */
    private static String getString(String tagName, JSONObject json) throws JSONException{
        return json.getString(tagName);
    }

    /**
     * Funkcia obaluje volanie na rychlejsie parsovanie floatov
     * @param tagName String
     * @param json JSONObject
     * @return JSONObject
     * @throws JSONException
     */
    private static float getFloat(String tagName, JSONObject json) throws JSONException{
        return (float) json.getDouble(tagName);
    }

    /**
     * Funkcia obaluje volanie na rychlejsie parsovanie integerov
     * @param tagName String
     * @param json JSONObject
     * @return JSONObject
     * @throws JSONException
     */
    private static int getInt(String tagName, JSONObject json) throws JSONException{
        return json.getInt(tagName);
    }

}
