package bohac.matej.pocasieproject.fragments;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import bohac.matej.pocasieproject.CommonConstants;
import bohac.matej.pocasieproject.R;
import bohac.matej.pocasieproject.model.Weather;

/**
 * A simple {@link Fragment} subclass.
 * Fragment hlavnych dat pocasia
 */
public class WeatherDataFragment extends Fragment implements CommonConstants {

    private TextView _textViewLocation;
    private TextView _textViewCountry;
    private TextView _textViewTemperature;
    private TextView _textViewConditions;
    private TextView _textViewWind;
    private TextView _textViewCloud;
    private ImageView _imageViewIcon;

    /**
     * Nutny konstruktor
     */
    public WeatherDataFragment() {
        // Required empty public constructor
    }

    /**
     * Naplna sa fragment datami z pocasia
     * @param inflater LayoutInflater
     * @param container ViewGroup
     * @param savedInstanceState Bundle
     * @return View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_weather_data, container, false);

        _textViewLocation = (TextView) view.findViewById(R.id.textViewLocation);
        _textViewCountry = (TextView) view.findViewById(R.id.textViewCountry);
        _textViewTemperature = (TextView) view.findViewById(R.id.textViewTemperature);
        _textViewConditions = (TextView) view.findViewById(R.id.textViewCondition);
        _textViewWind = (TextView) view.findViewById(R.id.textViewWind);
        _textViewCloud = (TextView) view.findViewById(R.id.textViewClouds);
        _imageViewIcon = (ImageView) view.findViewById(R.id.imageViewIcon);

        return view;
    }

    /**
     * Ukladanie stavu pri otoceni zariadenia
     * @param outState Bundle
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString( TEMPERATURE_TAG, _textViewTemperature.getText().toString() );
        outState.putString( LOCATION_TAG, _textViewLocation.getText().toString() );
        outState.putString( COUNTRY_TAG, _textViewCountry.getText().toString() );
        outState.putString( CONDITION_TAG, _textViewConditions.getText().toString() );
        outState.putString( CLOUD_TAG, _textViewCloud.getText().toString() );
        outState.putString( WIND_TAG, _textViewWind.getText().toString() );
        if( _imageViewIcon.getDrawable() != null) {
            BitmapDrawable tmpDrawable = (BitmapDrawable) _imageViewIcon.getDrawable();
            Bitmap bitmap = tmpDrawable.getBitmap();
            outState.putParcelable(ICON_TAG, bitmap);
        }
    }

    /**
     * Obnovenie stavu ak nejaky existuje
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if( savedInstanceState != null ){
            _textViewTemperature.setText(savedInstanceState.getString(TEMPERATURE_TAG));
            _textViewLocation.setText(savedInstanceState.getString(LOCATION_TAG));
            _textViewCountry.setText(savedInstanceState.getString(COUNTRY_TAG));
            _textViewConditions.setText(savedInstanceState.getString(CONDITION_TAG));
            _textViewCloud.setText(savedInstanceState.getString(CLOUD_TAG));
            _textViewWind.setText(savedInstanceState.getString(WIND_TAG));
            _imageViewIcon.setImageBitmap((Bitmap)savedInstanceState.getParcelable(ICON_TAG));
        }
    }

    /**
     * Hlavny view nastavuje data vo fragmente cez tuto metodu
     * @param weather Weather
     */
    public void setWeatherData(Weather weather){
        _textViewLocation.setText( getString(R.string.location) + " " + weather.get_location().get_locationName() );

        _textViewCountry.setText(getString(R.string.country) + " " + weather.get_location().get_country().get_country()) ;

        _textViewTemperature.setText(String.format("%.2f", weather.get_temperature().get_avgTemperature()) + " °C" );

        _textViewConditions.setText( getString(R.string.condition) + " " + weather.get_conditions().get_condition() );

        _textViewCloud.setText( getString(R.string.clouds) + " " + weather.get_conditions().get_description() );

        _textViewWind.setText( getString(R.string.wind) + " " + weather.get_wind().get_speed() + " m/s");

        _imageViewIcon.setImageBitmap(weather.get_bitmap() );
    }
}
