package com.example.hedgehog.luminof;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hedgehog on 26.12.2016.
 */

public class MenuAdapter extends BaseAdapter {

    private ArrayList<MainActivity.Item> arrayList;
    private LayoutInflater inflater;
    private Context context;

    public MenuAdapter(Context context, ArrayList<MainActivity.Item> arrayList){
        this.arrayList = arrayList;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public MainActivity.Item getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = convertView;
        ViewHolder vh;
        if (convertView == null) {
            rootView = inflater.inflate(R.layout.menu_item_layout, parent, false);
            vh = new ViewHolder(rootView);
            rootView.setTag(vh);
        } else {
            vh = (ViewHolder) rootView.getTag();
        }

        vh.tvName.setText(arrayList.get(position).getResource());
        return rootView;
    }

    private class ViewHolder{
        View rootView;
        TextView tvName;

        ViewHolder(View itemView) {
            rootView = itemView;
            tvName = (TextView) rootView.findViewById(R.id.tvName);
        }
    }
}
