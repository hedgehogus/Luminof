package com.dls.luminof.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dls.luminof.R;
import com.dls.luminof.example.MailSenderClass;

public class FeedBackFragment extends Fragment implements View.OnFocusChangeListener {
    //private final String address = "luminofo@gmail.com";
    private final String address = "research162@gmail.com";
    private final String subject = "luminof feedback";

    private String textMessage;
    Button send;
    EditText etName, etNumber, etMail, etDetails;
    View rootView;

    public FeedBackFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        rootView = inflater.inflate(R.layout.fragment_feed_back, container, false);
        send = (Button) rootView.findViewById(R.id.emailsendbutton);

        etName = (EditText) rootView.findViewById(R.id.etName);
        etNumber = (EditText) rootView.findViewById(R.id.etNumber);
        etMail = (EditText) rootView.findViewById(R.id.etMail);
        etDetails = (EditText) rootView.findViewById(R.id.etDetails);

        etName.setOnFocusChangeListener(this);
        etNumber.setOnFocusChangeListener(this);
        etMail.setOnFocusChangeListener(this);
        etDetails.setOnFocusChangeListener(this);

        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!isNetworkExist(getContext())){
                    Toast.makeText(getContext(), R.string.no_connection, Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuilder sb = new StringBuilder();
                sb.append(getContext().getResources().getString(R.string.feedback_details));
                sb.append(":   ");
                sb.append(etDetails.getText().toString());
                sb.append("\n");
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
                textMessage = sb.toString();

                FeedBackFragment.SenderMailAsync async_sending = new FeedBackFragment.SenderMailAsync();
                async_sending.execute();

            }
        });

        return rootView;
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        fillDefaultValues(v, hasFocus);
    }

    private void fillDefaultValues(View v, boolean hasFocus) {
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
            case R.id.etDetails:
                defaultValue = getContext().getResources().getString(R.string.feedback_details);
        }
        if (hasFocus) {
            if (((EditText) v).getText().toString().equals(defaultValue)) {
                ((EditText) v).setText("");

            }
        } else {
            if (((EditText) v).getText().toString().equals("")) {
                ((EditText) v).setText(defaultValue);

            }
        }

    }

    private class SenderMailAsync extends AsyncTask<Object, String, Boolean> {
        ProgressDialog WaitingDialog;

        @Override
        protected void onPreExecute() {
            WaitingDialog = ProgressDialog.show(FeedBackFragment.this.getActivity(), getResources().getString(R.string.sending), getResources().getString(R.string.sending_message), true);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            WaitingDialog.dismiss();
            Toast.makeText(getContext(), R.string.feedback_sent, Toast.LENGTH_LONG).show();
            // (getActivity()).finish();
        }

        @Override
        protected Boolean doInBackground(Object... params) {

            try {

                String from = address;
                String where = address;

                MailSenderClass sender = new MailSenderClass(getResources().getString(R.string.str2), getResources().getString(R.string.str1));

                sender.sendMail(subject, textMessage, from, where, "");
            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }
    }
    public static boolean isNetworkExist (Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
               return  true;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                return true;
            }
        }
        return false;
    }

}
