package bohac.matej.pocasieproject.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import bohac.matej.pocasieproject.R;


/**
 * A simple {@link Fragment} subclass.
 * Fragment paticky vkladany do aktivit
 */
public class FooterFragment extends Fragment {

    private TextView _textViewFri;
    private TextView _textViewUniza;

    /**
     * Nutny konstruktor
     */
    public FooterFragment() {
        // Required empty public constructor
    }

    /**
     * Naplna sa fragment datami a nastavuje logika onclickov
     * @param inflater LayoutInflater
     * @param container ViewGroup
     * @param savedInstanceState Bundle
     * @return View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_footer, container, false);

        _textViewFri = (TextView) view.findViewById(R.id.textViewFRI);
        _textViewFri.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.fri.uniza.sk");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        _textViewUniza = (TextView) view.findViewById(R.id.textViewUniza);
        _textViewUniza.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.uniza.sk");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        return view;
    }
}
