package com.example.hedgehog.luminof.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hedgehog.luminof.R;


public class CatalogFragment extends Fragment {


    public CatalogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_catalog, container, false);

       /* TextView tvLuminofor = (TextView) rootView.findViewById(R.id.tvLuminofor);
        Spannable spans = new SpannableString(getContext().getResources().getString(R.string.luminofor));
        spans.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.colorProductTitle)), 0, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvLuminofor.setText(spans);*/

       /* TextView tvFluorescent = (TextView) rootView.findViewById(R.id.tvFluorescent);
        spans = new SpannableString(getContext().getResources().getString(R.string.fluorescent));
        spans.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.colorProductTitle)), 0, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvFluorescent.setText(spans);*/
        return rootView;
    }

}
