package bohac.matej.pocasieproject;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Matej Bohac 5ZSA21 on 18.5.2015.
 */

/**
 * Trieda sluzi ako riadok pre recyclerView, obsahuje elementy ktore vykresluje
 */
public class ForecastListRowHolder extends RecyclerView.ViewHolder {
    protected ImageView _imageViewIcon;
    protected TextView _textViewTemperature;
    protected TextView _textViewTime;
    protected TextView _textViewConditions;
    protected TextView _textViewClouds;
    protected TextView _textViewWind;

    /**
     * Parametricky konstruktor
     * @param itemView View
     */
    public ForecastListRowHolder(View itemView) {
        super(itemView);

        this._imageViewIcon = (ImageView) itemView.findViewById(R.id.imageViewIcon);
        this._textViewTemperature = (TextView) itemView.findViewById(R.id.textViewTemperature);
        this._textViewTime = (TextView) itemView.findViewById(R.id.textViewTime);
        this._textViewConditions = (TextView) itemView.findViewById(R.id.textViewCondition);
        this._textViewClouds = (TextView) itemView.findViewById(R.id.textViewClouds);
        this._textViewWind = (TextView) itemView.findViewById(R.id.textViewWind);
    }
}
