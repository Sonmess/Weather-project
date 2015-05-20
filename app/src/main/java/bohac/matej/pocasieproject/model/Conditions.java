package bohac.matej.pocasieproject.model;

/**
 * Created by Matej Bohac 5ZSA21 on 5.5.2015.
 */

/**
 * Trieda predstavujuca podmienky v pocasi
 */
public class Conditions {
    private String _condition;
    private String _description;
    private String _icon;

    /**
     * Uzitocny konstruktor, vacinou pouzivam tento
     */
    public Conditions() {
    }

    /**
     * Parametricky konstruktor, vhodny na komplexne vytvaranie
     * @param _condition String
     * @param _description String
     * @param _icon String
     */
    public Conditions(String _condition, String _description, String _icon) {
        this._condition = _condition;
        this._description = _description;
        this._icon = _icon;
    }

    /**
     * Getter stavu pocasia
     * @return String
     */
    public String get_condition() {
        return _condition;
    }

    /**
     * Setter stavu pocasia
     * @param _condition String
     */
    public void set_condition(String _condition) {
        this._condition = _condition;
    }

    /**
     * Getter popisu pocasia
     * @return String
     */
    public String get_description() {
        return _description;
    }

    /**
     * Setter popisu pocasia
     * @param _description String
     */
    public void set_description(String _description) {
        this._description = _description;
    }

    /**
     * Getter oznacenia ikony
     * @return String
     */
    public String get_icon() {
        return _icon;
    }

    /**
     * Setter oznacenia ikony
     * @param _icon String
     */
    public void set_icon(String _icon) {
        this._icon = _icon;
    }

    /**
     * Vlastne zobrazenie pouzite pri log vypisoch
     * @return String
     */
    @Override
    public String toString() {
        return _condition + "|" + _description + "|" + _icon;
    }
}
