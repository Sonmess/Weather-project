package bohac.matej.pocasieproject;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import bohac.matej.pocasieproject.fragments.WeatherDataFragment;
import bohac.matej.pocasieproject.fragments.WeatherInfoFragment;
import bohac.matej.pocasieproject.model.Weather;

/**
 * Hlavna aktivita, ktora sa spusti po zapnuti aplikacie
 * hlavne GUI vlakno.
 */
public class MainActivity extends AppCompatActivity implements CommonConstants {

    private EditText _editTextCity;
    private String _location_city;
    private ArrayList<Weather> _forecastWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _editTextCity = (EditText) findViewById(R.id.editTextCity);

        _forecastWeather = new ArrayList<>();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String location = _editTextCity.getText().toString();
        outState.putString("editTextCity", location);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        _editTextCity.setText(savedInstanceState.getString("editTextCity"));
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == R.id.action_about){
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * OnClick metoda ktora sa vykona po stlaceni tlacidla Weather
     * skontroluje ci je zadana nejaka lokacia a ci existuje
     * pripojenie na internet inac nema zmysel nic nacitavat.
     * @param v View
     */
    public void onClickWeather(View v){
        if(TextUtils.isEmpty( _editTextCity.getText()) ){
            _editTextCity.setError(getString(R.string.cityError) );
        }
        else{
            _location_city = _editTextCity.getText().toString();

            if(isNetworkAvailable()){
                WeatherTask weatherTask = new WeatherTask();
                weatherTask.execute(_location_city);
            }
            else{
                //ak nastal problem so sietov vyhodi oznam pouzivatelovi
                createAlertDialog(INTERNET_ERROR_TITLE, INTERNET_ERROR_MESSAGE);
            }
        }
    }

    /**
     * Vnorena rozsirena trieda AsyncTask ktora umoznuje asynchrone teda mimo hlavne vlakno
     * nacitat data z internetu a zobrazit ich v hlavnom vlakne
     * preposiela si objekt weather ktori obsahuje vsetky potrebne data na zobrazenie
     * obsahuje niekolko metod ktore trebalo implementovat.
     */
    private class WeatherTask extends AsyncTask<String, Void, Weather> {
        /**
         * Metoda vykona samotne pripojenie na api a zparsovanie dat, ak nastal
         * nejaky problem vyhodi vynimku ktoru potom hlavne vlakno spracuje, povinna metoda.
         * @param strings String
         * @return Weather
         */
        @Override
        protected Weather doInBackground(String... strings) {
            Weather weather = new Weather();
            //najnachylnejsie miesto na padanie, osetril som ako sa dalo
            //api moze vratit ziadne data, alebo vrati data s oznamenim ze sa lokacia nenasla
            //v oboch pripadoch treba vyhodit pouzivatelovi oznam ze nacitavanie bolo neuspesne
            try{
                String weatherData = Api.getWeatherData(strings[0]);
                String forecastData = Api.getForecastData(strings[0]);

                try{
                    weather = Parser.parseDataToWeather(weatherData);
                    setBitmapImage(weather);

                    _forecastWeather = Parser.parseDataToForecast(forecastData);
                    for(int i = 0; i < _forecastWeather.size(); i++){
                        setBitmapImage(_forecastWeather.get(i));
                    }
                    return weather;
                }
                catch (JSONException jsonE){
                    jsonE.printStackTrace();
                    cancel(true);
                }
            }
            catch (IOException ioe){
                ioe.printStackTrace();
                cancel(true);
            }
            return null;
        }

        /**
         * Ak musime prerusit asynchronny chod zavola sa tato metoda, vypise oznam pouzivatelovi
         * ze doslo k preruseniu plus patricna sprava, nepovinna metoda.
         * @param weather Weather
         */
        @Override
        protected void onCancelled(Weather weather) {
            super.onCancelled(weather);
            if (weather == null){
                createAlertDialog(LOCATION_ERROR_TITLE, LOCATION_ERROR_MESSAGE);
            }
            else{
                createAlertDialog(GENERAL_ERROR_TITLE, GENERAL_ERROR_MESSAGE);
            }
        }

        /**
         * Ked skonci doInBackground metoda, zacne sa vykonavat tato, specialne ma pristup
         * k prvkom v UI vlakne cize ich moze editovat. Needituje priamo prvky, ale fragmenty
         * aby to bolo flexibilnejsie, povinna metoda.
         * @param weather Weather
         */
        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

            WeatherDataFragment fragmentData = (WeatherDataFragment) getFragmentManager()
                    .findFragmentById(R.id.fragmentWeatherData);
            if (fragmentData != null && fragmentData.isInLayout()) {
                fragmentData.setWeatherData(weather);
            }

            WeatherInfoFragment fragmentInfo = (WeatherInfoFragment) getFragmentManager()
                    .findFragmentById(R.id.fragmentWeatherInfo);
            if(fragmentInfo != null && fragmentInfo.isInLayout()) {
                fragmentInfo.setForecastData(_forecastWeather);
            }

            //Feature ktora vykresli data este aj ako notifikacia, ak by doslo k nejakej poruche
            //data neboli nacitavane, alebo prerusene, tak sa notifikacia nezobrazi.
            createNotification(weather);
        }
    }

    /**
     * Metoda ktora vytvori aj zobrazi notifikaciu v paneli androidu. Neviem preco ale nefungovalo to
     * vo verzii nizsej ako JELLY BEAN
     *
     * Notifikacia umoznuje kliknut na nu a spustit aplikaciu cez cakaci intent, zobrazuje zakladne
     * data o pocasi a umoznuje v pridavnych akciach spustit aj druhu aktivitu About
     * @param weather Weather
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void createNotification(Weather weather) {
        if(weather == null) {
            return;
        }
        Intent intentWeather = new Intent(this, MainActivity.class);
        PendingIntent pIntentWeather = PendingIntent.getActivity(this, 0, intentWeather, 0);

        Intent intentAbout = new Intent(this, AboutActivity.class);
        PendingIntent pIntentAbout = PendingIntent.getActivity(this, 0, intentAbout, 0);

        String contentText = String.format("%s : %.2f °C %s", weather.get_location().get_locationName(), weather.get_temperature().get_avgTemperature(), weather.get_conditions().get_condition());

        Notification notification = new Notification.Builder(this)
                .setContentTitle("Weather application notification")
                .setContentText(contentText)
                .setLargeIcon(weather.get_bitmap())
                .setSmallIcon(android.R.drawable.ic_menu_compass)
                .setContentIntent(pIntentWeather)
                .setWhen(0)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setPriority(Integer.MAX_VALUE)
                .addAction(android.R.drawable.ic_menu_view, "Weather", pIntentWeather)
                .addAction(android.R.drawable.ic_input_add, "About", pIntentAbout).build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }

    /**
     * Metoda obaluje volanie api a nacitanie obrazku, ziadna logika len jednoduchsi zapis v tele kodu
     * @param weather Weather
     */
    private void setBitmapImage(Weather weather){
        weather.set_bitmap(Api.getBitmapFromURL(weather.get_conditions().get_icon()));
    }

    /**
     * Metoda kontroluje internetove pripojenie zariadenia
     * Kod prevzaty zo stackOverflow
     * http://stackoverflow.com/questions/1560788/how-to-check-internet-access-on-android-inetaddress-never-timeouts
     * @return boolean
     */
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * Metoda na zobrazenie alert dialogu, vyuzitie default android spravania
     * @param title String
     * @param message String
     */
    private void createAlertDialog(String title, String message){
        new AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            })
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show();
    }
}
