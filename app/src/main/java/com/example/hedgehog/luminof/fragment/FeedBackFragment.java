package com.example.hedgehog.luminof.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.hedgehog.luminof.R;

public class FeedBackFragment extends Fragment {
    private final String address = "luminofo@gmail.com";
    private final String subject = "luminof";
    Button send;
    EditText emailtext;

    public FeedBackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_feed_back, container, false);
        send = (Button) rootView.findViewById(R.id.emailsendbutton);

        emailtext = (EditText) rootView.findViewById(R.id.emailtext);

        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final Intent emailIntent = new Intent(Intent.ACTION_SEND);

                emailIntent.setType("plain/text");

                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                        new String[] { address});

                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                        subject);

                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
                        emailtext.getText().toString());

                emailIntent.setType("message/rfc822");

                getActivity().startActivityFromFragment(FeedBackFragment.this, Intent.createChooser(emailIntent, getContext().getResources().getString(
                        R.string.send_email)), 5555);

            }
        });
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        emailtext.setText("");
    }
}
