package bohac.matej.pocasieproject.model;

/**
 * Created by Matej Bohac 5ZSA21 on 5.5.2015.
 */

/**
 * Trieda predstavuje udaje o krajine (slnecne data su viazane na krajinu)
 */
public class Country {
    private String _country;
    private long _sunrise;
    private long _sunset;

    /**
     * Uzitocny konstruktor
     */
    public Country() {
    }

    /**
     * Parametricky konstruktor, vhodny na komplexnejsie nacitanie
     * @param _country String
     * @param _sunrise long
     * @param _sunset long
     */
    public Country(String _country, long _sunrise, long _sunset) {
        this._country = _country;
        this._sunrise = _sunrise;
        this._sunset = _sunset;
    }

    /**
     * Getter nazvu krajiny
     * @return String
     */
    public String get_country() {
        return _country;
    }

    /**
     * Setter nazvu krajiny
     * @param _country String
     */
    public void set_country(String _country) {
        this._country = _country;
    }

    /**
     * Getter casu vychodu slnka
     * @return long
     */
    public long get_sunrise() {
        return _sunrise;
    }

    /**
     * Setter casu vychodu slnka
     * @param _sunrise long
     */
    public void set_sunrise(long _sunrise) {
        this._sunrise = _sunrise;
    }

    /**
     * Getter casu zapadu slnka
     * @return long
     */
    public long get_sunset() {
        return _sunset;
    }

    /**
     * Setter casu zapadu slnka
     * @param _sunset long
     */
    public void set_sunset(long _sunset) {
        this._sunset = _sunset;
    }

    /**
     * Vlastny vypis vhodny na log vypisy
     * @return String
     */
    @Override
    public String toString() {
        return _country + "|" + _sunrise + "|" + _sunset;
    }
}
