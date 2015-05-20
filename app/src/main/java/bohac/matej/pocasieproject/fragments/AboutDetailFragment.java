package bohac.matej.pocasieproject.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import bohac.matej.pocasieproject.R;


/**
 * A simple {@link Fragment} subclass.
 * Fragment detailu o mne.
 */
public class AboutDetailFragment extends Fragment {

    private Spinner _spinnerHobby;
    protected ListView _listViewSocials;

    /**
     * Nutny konstruktor
     */
    public AboutDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Naplna sa fragment datami, generuju sa prvky a zabezpecuje logika
     * @param inflater LayoutInflater
     * @param container ViewGroup
     * @param savedInstanceState Bundle
     * @return View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_detail, container, false);
        initializeSpinner(view);
        initializeSocials(view);
        return view;
    }

    /**
     * Vytvori spinner co je proste combobox
     * Data nacita z resource
     * @param view View
     */
    private void initializeSpinner(View view){
        _spinnerHobby = (Spinner) view.findViewById(R.id.spinnerHobby);
        String[] _arrayHobbies = getResources().getStringArray(R.array.hobbies_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>( getActivity(),android.R.layout.simple_spinner_item, _arrayHobbies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _spinnerHobby.setAdapter(adapter);
    }

    /**
     * Vytvori listView odkazov, zoznam scrolujuci
     * Data nacita z resource
     * @param view View
     */
    private void initializeSocials(View view){
        _listViewSocials = (ListView) view.findViewById(R.id.listViewSocials);
        String[] _arraySocials = getResources().getStringArray(R.array.socials_list);
        final String[] _arrayLinks = getResources().getStringArray(R.array.socials_links);
        ArrayAdapter<String> adapter = new ArrayAdapter<>( getActivity(), android.R.layout.simple_list_item_1, _arraySocials);
        _listViewSocials.setAdapter(adapter);

        _listViewSocials.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Uri uri = Uri.parse(_arrayLinks[i]);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}
