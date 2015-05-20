package bohac.matej.pocasieproject.model;

import android.graphics.Bitmap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Matej Bohac 5ZSA21 on 5.5.2015.
 */

/**
 * Trieda obaluje vsetky data o pocasi do jednej obsahuje vlastne typy
 */
public class Weather {
    private Conditions _conditions;
    private Coordinates _coordinates;
    private Location _location;
    private Temperature _temperature;
    private Wind _wind;
    private Bitmap _bitmap;
    private String _time;

    /**
     * Uzitocny konstruktor
     */
    public Weather() {
    }

    /**
     * Parametricky konstruktor
     * @param _conditions Conditions
     * @param _coordinates Coordinates
     * @param _location Location
     * @param _temperature Temperature
     * @param _wind Wind
     */
    public Weather(Conditions _conditions, Coordinates _coordinates, Location _location, Temperature _temperature, Wind _wind) {
        this._conditions = _conditions;
        this._coordinates = _coordinates;
        this._location = _location;
        this._temperature = _temperature;
        this._wind = _wind;
    }

    /**
     * Getter pre podmienky pocasia
     * @return Conditions
     */
    public Conditions get_conditions() {
        return _conditions;
    }

    /**
     * Setter pre podmienky pocasia
     * @param _conditions Conditions
     */
    public void set_conditions(Conditions _conditions) {
        this._conditions = _conditions;
    }

    /**
     * Getter pre suradnice lokacie
     * @return Coordinates
     */
    public Coordinates get_coordinates() {
        return _coordinates;
    }

    /**
     * Setter pre suradnice lokacie
     * @param _coordinates Coordinates
     */
    public void set_coordinates(Coordinates _coordinates) {
        this._coordinates = _coordinates;
    }

    /**
     * Getter pre lokaciu
     * @return Location
     */
    public Location get_location() {
        return _location;
    }

    /**
     * Setter pre lokaciu
     * @param _location Location
     */
    public void set_location(Location _location) {
        this._location = _location;
    }

    /**
     * Getter pre teplotu lokacie
     * @return Temperature
     */
    public Temperature get_temperature() {
        return _temperature;
    }

    /**
     * Setter pre teplotu lokacie
     * @param _temperature Temperature
     */
    public void set_temperature(Temperature _temperature) {
        this._temperature = _temperature;
    }

    /**
     * Getter pre vietor v lokacii
     * @return Wind
     */
    public Wind get_wind() {
        return _wind;
    }

    /**
     * Setter pre vietor v lokacii
     * @param _wind Wind
     */
    public void set_wind(Wind _wind) {
        this._wind = _wind;
    }

    /**
     * Getter pre obrazok pocasia
     * @return Bitmap
     */
    public Bitmap get_bitmap() {
        return _bitmap;
    }

    /**
     * Setter pre obrazok pocasia
     * @param _bitmap Bitmap
     */
    public void set_bitmap(Bitmap _bitmap) {
        this._bitmap = _bitmap;
    }

    /**
     * Getter pre casovy usek pocasia
     * @return String
     */
    public String get_time() {
        return _time;
    }

    /**
     * Setter pre casovuy usek pocasia, automaticke preformatovanie vstupu na moj format
     * @param _time String
     */
    public void set_time(String _time) {
        this._time = formatDateFromString(_time);
    }

    /**
     * Metoda ktora preformatuje vstup z api, ktory ma divny format na lepsie citatelny
     * @param inputDate String
     * @return String
     */
    private String formatDateFromString(String inputDate){
        Date date = new Date();
        String result = "";
        String inputFormat = "yyyy-MM-dd HH:mm:ss";
        String outputFormat = "HH:mm dd.MM.yyyy";

        SimpleDateFormat inputSimpleDateFormat = new SimpleDateFormat(inputFormat);
        SimpleDateFormat outputSimpleDateFormat = new SimpleDateFormat(outputFormat);

        try {
            date = inputSimpleDateFormat.parse(inputDate);
            result = outputSimpleDateFormat.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
