package bohac.matej.pocasieproject.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import bohac.matej.pocasieproject.CommonConstants;
import bohac.matej.pocasieproject.MyRecycleAdapter;
import bohac.matej.pocasieproject.R;
import bohac.matej.pocasieproject.model.Weather;

/**
 * A simple {@link Fragment} subclass.
 * Fragment predpovede pocasia
 */
public class WeatherInfoFragment extends Fragment implements CommonConstants {

    private MyRecycleAdapter _myRecycleAdapter;
    private RecyclerView _recyclerView;
    private static ArrayList<Weather> _forecastData;

    /**
     * Nutny konstruktor
     */
    public WeatherInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Ulozenie stavu pri rotacii displeja, nic extra sa nerobi ale pre istotu
     * @param outState Bundle
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    /**
     * Obnovenie stavu pri rotacii displeja
     * nacita data pre naplnenie recyclerViewu
     * @param savedInstanceState Bundle
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null){
            setForecastData(_forecastData);
        }
    }

    /**
     * Naplnenie fragmentu datmi, fragment obsahuje len recyclerView cize vlastne jeho naplnenie
     * @param inflater LayoutInflater
     * @param container ViewGroup
     * @param savedInstanceState Bundle
     * @return View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_info, container, false);
        _recyclerView = (RecyclerView) view.findViewById(R.id.forecastList);
        _recyclerView.setLayoutManager(new LinearLayoutManager( getActivity() ) );
        return view;
    }

    /**
     * Hlavny view nastavuje data cez tuto metodu
     * @param forecastData ArrayList
     */
    public void setForecastData(ArrayList<Weather> forecastData){
        _forecastData = forecastData;
        _myRecycleAdapter = new MyRecycleAdapter( forecastData, getActivity() );
        _recyclerView.setAdapter(_myRecycleAdapter);
    }
}
