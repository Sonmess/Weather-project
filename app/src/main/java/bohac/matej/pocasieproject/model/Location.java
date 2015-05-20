package bohac.matej.pocasieproject.model;

/**
 * Created by Matej Bohac 5ZSA21 on 5.5.2015.
 */
/**
 * Trieda predstavuje data o meste obsahuje aj vlastne typy
 */
public class Location {

    private String _locationName;
    private String _locationId;
    private Coordinates _coordinates;
    private Country _country;

    /**
     * Uzitocny konstruktor
     */
    public Location() {
    }

    /**
     * Parametricky konstruktor
     * @param _locationName String
     * @param _locationId String
     * @param _coordinates Coordinates
     * @param _country Country
     */
    public Location(String _locationName, String _locationId, Coordinates _coordinates, Country _country) {
        this._locationName = _locationName;
        this._locationId = _locationId;
        this._coordinates = _coordinates;
        this._country = _country;
    }

    /**
     * Getter nazvu mesta
     * @return String
     */
    public String get_locationName() {
        return _locationName;
    }

    /**
     * Setter nazvu mesta
     * @param _locationName String
     */
    public void set_locationName(String _locationName) {
        this._locationName = _locationName;
    }

    /**
     * Getter idcko mesta
     * @return String
     */
    public String get_locationId() {
        return _locationId;
    }

    /**
     * Setter idcko mesta
     * @param _locationId String
     */
    public void set_locationId(String _locationId) {
        this._locationId = _locationId;
    }

    /**
     * Getter suradnic mesta
     * @return Coordinates
     */
    public Coordinates get_coordinates() {
        return _coordinates;
    }

    /**
     * Setter suradnic mesta
     * @param _coordinates Coordinates
     */
    public void set_coordinates(Coordinates _coordinates) {
        this._coordinates = _coordinates;
    }

    /**
     * Getter krajiny mesta
     * @return Country
     */
    public Country get_country() {
        return _country;
    }

    /**
     * Setter krajiny mesta
     * @param _country Country
     */
    public void set_country(Country _country) {
        this._country = _country;
    }

    /**
     * Vlastny vypis vhodny pre log vypisy
     * @return String
     */
    @Override
    public String toString() {
        return _country + "|" + _locationName;
    }
}
