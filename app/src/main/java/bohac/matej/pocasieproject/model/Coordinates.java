package bohac.matej.pocasieproject.model;

/**
 * Created by Matej Bohac 5ZSA21 on 5.5.2015.
 */
/**
 * Trieda predstavuje udaje o lokacii
 */
public class Coordinates {
    private String _longitude;
    private String _latitude;

    /**
     * Uzitocny konstruktor
     */
    public Coordinates() {
    }

    /**
     * Parametricky konstruktor, vhodny na komplexnejsie vytvaranie
     * @param _longitude String
     * @param _latitude String
     */
    public Coordinates(String _longitude, String _latitude) {
        this._longitude = _longitude;
        this._latitude = _latitude;
    }

    /**
     * Getter zem. sirky
     * @return String
     */
    public String get_longitude() {
        return _longitude;
    }

    /**
     * Setter zem. sirky
     * @param _longitude String
     */
    public void set_longitude(String _longitude) {
        this._longitude = _longitude;
    }

    /**
     * Getter zem. dlzky
     * @return String
     */
    public String get_latitude() {
        return _latitude;
    }

    /**
     * Setter zem. dlzky
     * @param _latitude String
     */
    public void set_latitude(String _latitude) {
        this._latitude = _latitude;
    }

    /**
     * Vlastne zobrazenie vhodne pri log vypisoch
     * @return String
     */
    @Override
    public String toString() {
        return _latitude + "|" + _longitude;
    }
}
