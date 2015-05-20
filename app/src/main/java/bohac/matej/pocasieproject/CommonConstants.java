package bohac.matej.pocasieproject;

/**
 * Created by Matej Bohac 5ZSA21 on 16.5.2015.
 */

/**
 * Interface konstant a pouzivanych tagov pri posielani intentov, logov, Bundlov atd.
 * Trochu cistejsie riesenie ako pouzivat staticke premenne a efektivnejsie ako vytvorit enum stringov
 */
public interface CommonConstants {
    String WEATHER_TAG = "weather";
    String ERROR_TAG = "error";

    String INTERNET_ERROR_TITLE = "No internet connection!";
    String INTERNET_ERROR_MESSAGE = "Please turn on your internet connection otherwise application can not load any data.";

    String LOCATION_ERROR_TITLE = "No location data!";
    String LOCATION_ERROR_MESSAGE = "Please check your location, it does not exists.";

    String GENERAL_ERROR_TITLE = "There is a problem with openweatherapi!";
    String GENERAL_ERROR_MESSAGE = "Please wait for openweatherapi server to catch on.";

    String TEMPERATURE_TAG = "temperatureTag";
    String TIME_TAG = "timeTag";
    String LOCATION_TAG = "locationTag";
    String COUNTRY_TAG = "countryTag";
    String CONDITION_TAG = "conditionTag";
    String CLOUD_TAG = "cloudTag";
    String WIND_TAG = "windTag";
    String ICON_TAG = "iconTag";
    String FORECAST_TAG = "forecastTag";

}
