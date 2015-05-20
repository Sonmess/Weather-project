package bohac.matej.pocasieproject;

/**
 * Created by Matej Bohac 5ZSA21 on 18.5.2015.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import bohac.matej.pocasieproject.model.Weather;

/**
 * Trieda rozsiruje bezny recyclerView na moj vlastny, naplneny vlastnym viewHolderom
 * Umoznuje mi zobrazit listView vlastnych prvkov a vykonovo a pamatovo efektivne
 * riadit ich zobrazenie na obrazovke.
 */
public class MyRecycleAdapter extends RecyclerView.Adapter<ForecastListRowHolder> {

    private ArrayList<Weather> _forecastDataList;
    private Context _context;

    /**
     * Parametricky konstruktor na vytvorenie vlastnej instancie
     * @param _forecastDataList ArrayList
     * @param _context Context
     */
    public MyRecycleAdapter(ArrayList<Weather> _forecastDataList, Context _context) {
        this._forecastDataList = _forecastDataList;
        this._context = _context;
    }

    /**
     * Vytvorenie vlastneho viewHoldera, povinna metoda
     * @param viewGroup ViewGroup
     * @param i int
     * @return ForecastListRowHolder
     */
    @Override
    public ForecastListRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from( viewGroup.getContext()).inflate(R.layout.forecast_row, viewGroup, false);
        return new ForecastListRowHolder(view);
    }

    /**
     * Naplnenie viewHoldera datami, povinna metoda
     * @param forecastListRowHolder ForecastListRowHolder
     * @param i int
     */
    @Override
    public void onBindViewHolder(ForecastListRowHolder forecastListRowHolder, int i) {
        Weather forecastWeather = _forecastDataList.get(i);

        forecastListRowHolder._textViewTime.setText( forecastWeather.get_time() );
        forecastListRowHolder._textViewTemperature.setText( String.format( "%.2f °C", forecastWeather.get_temperature().get_avgTemperature() ) );
        forecastListRowHolder._textViewConditions.setText( _context.getString( R.string.condition ) + " " + forecastWeather.get_conditions().get_condition() );
        forecastListRowHolder._textViewClouds.setText( _context.getString( R.string.clouds ) + " " + forecastWeather.get_conditions().get_description() );
        forecastListRowHolder._textViewWind.setText( _context.getString( R.string.wind ) + " " + forecastWeather.get_wind().get_speed() + " m/s");
        forecastListRowHolder._imageViewIcon.setImageBitmap( forecastWeather.get_bitmap() );
    }

    /**
     * Vratenie velkosti mojho recyclerViewu, povinna metoda
     * @return int
     */
    @Override
    public int getItemCount() {
        return _forecastDataList.size() ;
    }
}
