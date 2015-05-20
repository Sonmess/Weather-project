package bohac.matej.pocasieproject.model;

/**
 * Created by Matej Bohac 5ZSA21 on 5.5.2015.
 */

/**
 * Trieda obaluje data pre vietor
 */
public class Wind {
    private float _speed;
    private float _degree;

    /**
     * Uzitocny konstruktor
     */
    public Wind() {
    }

    /**
     * Parametricky konstruktor
     * @param _speed float
     * @param _degree float
     */
    public Wind(float _speed, float _degree) {
        this._speed = _speed;
        this._degree = _degree;
    }

    /**
     * Getter pre rychlost vetra
     * @return float
     */
    public float get_speed() {
        return _speed;
    }

    /**
     * Setter pre rychlost vetra
     * @param _speed float
     */
    public void set_speed(float _speed) {
        this._speed = _speed;
    }

    /**
     * Getter pre uhol dopadu vetra
     * @return float
     */
    public float get_degree() {
        return _degree;
    }

    /**
     * Setter pre uhol dopadu vetra
     * @param _degree float
     */
    public void set_degree(float _degree) {
        this._degree = _degree;
    }

    /**
     * Vlastne zobrazenie pre vypisy
     * @return String
     */
    @Override
    public String toString() {
        return _speed + "|" + _degree;
    }
}
