package bohac.matej.pocasieproject.model;

/**
 * Created by Matej Bohac 5ZSA21 on 5.5.2015.
 */

/**
 * Trieda obaluje data o teplote
 * obsahuje aj nejaku matematicku logiku
 */
public class Temperature {
    private float _avgTemperature;
    private float _minTemperature;
    private float _maxTemperature;

    /**
     * Konstanta potrebna na prevod kelvinov na ine jednotky
     */
    private final float KELVIN = (float) 273.15;

    /**
     * Uzitocny konstruktor
     */
    public Temperature() {
    }

    /**
     * Parametricky konstruktor
     * @param _avgTemperature float
     * @param _minTemperature float
     * @param _maxTemperature float
     */
    public Temperature(float _avgTemperature, float _minTemperature, float _maxTemperature) {
        this._avgTemperature = _avgTemperature;
        this._minTemperature = _minTemperature;
        this._maxTemperature = _maxTemperature;
    }

    /**
     * Getter priemernej teploty
     * @return float
     */
    public float get_avgTemperature() {
        return _avgTemperature;
    }

    /**
     * Setter priemernej teploty
     * @param _avgTemperature float
     */
    public void set_avgTemperature(float _avgTemperature) {
        this._avgTemperature =  convertKelvinToCelsius(_avgTemperature);
    }

    /**
     * Getter minimalnej teploty
     * @return float
     */
    public float get_minTemperature() {
        return _minTemperature;
    }

    /**
     * Setter minimalnej teploty automaticky premiena kelviny na celsia
     * @param _minTemperature float
     */
    public void set_minTemperature(float _minTemperature) {
        this._minTemperature = convertKelvinToCelsius(_minTemperature);
    }

    /**
     * Getter maximalnej teploty
     * @return float
     */
    public float get_maxTemperature() {
        return _maxTemperature;
    }

    /**
     * Setter maximalnej teploty, automaticky premierna kelviny na celsia
     * @param _maxTemperature float
     */
    public void set_maxTemperature(float _maxTemperature) {
        this._maxTemperature = convertKelvinToCelsius(_maxTemperature);
    }

    /**
     * Metoda premiena fahrenheity na celsia
     * @param fahrenheit float
     * @return float
     */
    public float convertFahrenheitToCelsius(float fahrenheit){
        return ( (fahrenheit - 32) * 5 / 9);
    }

    /**
     * Metoda premiena celsia na fahrenheity
     * @param celsius float
     * @return float
     */
    public float convertCelsiusToFahrenheit(float celsius){
        return (( celsius * 9) / 5 ) + 32;
    }

    /**
     * Metoda premiena kelviny na celsia
     * @param kelvin float
     * @return float
     */
    public float convertKelvinToCelsius(float kelvin){
        return kelvin - KELVIN;
    }

    /**
     * Metoda premiena celsia na kelviny
     * @param celsius float
     * @return float
     */
    public float convertCelsiusToKelvin(float celsius){
        return celsius + KELVIN;
    }

    /**
     * Vlastne zobrazenie pre vypisy
     * @return String
     */
    @Override
    public String toString() {
        return _avgTemperature + "|" + _minTemperature + "|" + _maxTemperature;
    }
}
