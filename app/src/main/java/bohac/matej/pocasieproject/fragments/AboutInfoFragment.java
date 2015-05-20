package bohac.matej.pocasieproject.fragments;


import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import bohac.matej.pocasieproject.R;


/**
 * A simple {@link Fragment} subclass.
 * Fragment info o mne aj s obrazkom
 */
public class AboutInfoFragment extends Fragment {

    private TextView _textViewEmail;


    /**
     * Nutny konstruktor
     */
    public AboutInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Naplna sa fragment datami a vyraba logika
     * @param inflater LayoutInflater
     * @param container ViewGroup
     * @param savedInstanceState Bundle
     * @return View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_info, container, false);
        _textViewEmail = (TextView) view.findViewById(R.id.textViewEmail);
        _textViewEmail.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(getString(R.string.mailto));
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.emailSubject));
                startActivity(intent);
            }
        });
        return view;
    }
}
