package com.example.hedgehog.luminof.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hedgehog.luminof.R;

public class FeedBackFragment extends Fragment implements View.OnFocusChangeListener {
    // private final String address = "luminofo@gmail.com";
    private final String address = "research162@gmail.com";
    private final String subject = "luminof";
    Button send;
    EditText etName, etNumber, etMail;

    public FeedBackFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_feed_back, container, false);
        send = (Button) rootView.findViewById(R.id.emailsendbutton);

        etName = (EditText) rootView.findViewById(R.id.etName);
        etNumber = (EditText) rootView.findViewById(R.id.etNumber);
        etMail = (EditText) rootView.findViewById(R.id.etMail);

        etName.setOnFocusChangeListener(this);
        etNumber.setOnFocusChangeListener(this);
        etMail.setOnFocusChangeListener(this);

        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                StringBuilder sb = new StringBuilder();
                sb.append(getContext().getResources().getString(R.string.feedback_name));
                sb.append(":   ");
                sb.append(etName.getText().toString());
                sb.append("\n");
                sb.append(getContext().getResources().getString(R.string.feedback_number));
                sb.append(":   ");
                sb.append(etNumber.getText().toString());
                sb.append("\n");
                sb.append(getContext().getResources().getString(R.string.feedback_email));
                sb.append(":   ");
                sb.append(etMail.getText().toString());

                final Intent emailIntent = new Intent(Intent.ACTION_SEND);

                emailIntent.setType("plain/text");

                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                        new String[]{address});

                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                        subject);

                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
                        sb.toString());

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
        Toast.makeText(getContext(), R.string.feedback_sent, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        String defaultValue = "";
        switch (v.getId()) {
            case R.id.etName:
                defaultValue = getContext().getResources().getString(R.string.feedback_name);
                break;
            case R.id.etNumber:
                defaultValue = getContext().getResources().getString(R.string.feedback_number);
                break;
            case R.id.etMail:
                defaultValue = getContext().getResources().getString(R.string.feedback_email);
                break;
        }
        if (hasFocus) {
            if (((EditText)v).getText().toString().equals(defaultValue)) {
                ((EditText) v).setText("");
            }
        } else {
            if (((EditText)v).getText().toString().equals("")) {
                ((EditText) v).setText(defaultValue);
            }
        }


    }
}
